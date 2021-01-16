package cs2030.simulator;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The ArriveEvent class inherits the properties of its parent Event class.
 * It is used to decide what is next for when a Customer arrives.
 *
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Project 2
 */
public class ArriveEvent extends Event {
    /**
     * Initialises a new ArriveEvent.
     * @param customer  Customer with his/her details
     */
    public ArriveEvent(Customer customer) {
        super(shop -> {
            Optional<Server> availableServer = shop.find(server -> server.getAvailability());
            Optional<Server> queueServer = shop.find(server ->
                        server.customerQueueSize() < server.getCustomerLimit());
            Optional<Server> haxServer = shop.find(s ->
                    !s.getHasWaitingCustomer() && s.getCustomerLimit() == 1);

            if (availableServer.isPresent()) {
                return new Pair<Shop, Event>(shop, new ServeEvent(customer, availableServer.get(),
                        customer.getArrivalTime()));
            } else if (haxServer.isPresent()) {
                return new Pair<Shop, Event>(shop.replace(haxServer.get()),
                        new WaitEvent(customer, haxServer.get(), customer.getArrivalTime()));
            } else if (queueServer.isPresent()) {
                if (!customer.isGreedy()) {
                    Server serverCustQ = queueServer.get().queueCustomer(customer);
                    Optional<Server> counterServer = shop.getServers()
                            .stream()
                            .filter(s -> s.isSelfCheckout())
                            .min(Comparator.comparing(Server::getAvailableTime));
    
                    if (!serverCustQ.isSelfCheckout() && !customer.isGreedy()) {
                        return new Pair<Shop, Event>(shop.replace(serverCustQ),
                                new WaitEvent(customer, serverCustQ, customer.getArrivalTime()));
                    }
    
                    if (counterServer.isPresent() &&
                            shop.getSelfCheckQ().size() <
                                    counterServer.get().getCustomerLimit() &&
                            !customer.isGreedy()) {
                        return new Pair<Shop, Event>(
                            shop.replace(serverCustQ).queueCustomer(customer),
                            new SCWaitEvent(customer, serverCustQ,
                                    customer.getArrivalTime(),
                                    counterServer.get().getAvailableTime()));
                    }
                } else {
                    Optional<Server> humanServer = shop.getServers().stream()
                            .filter(s -> !s.isSelfCheckout() &&
                                    s.customerQueueSize() < s.getCustomerLimit())
                            .min(Comparator.comparing(Server::customerQueueSize));
                    Optional<Server> selfCOServer = shop.getServers().stream()
                            .filter(s -> s.isSelfCheckout() &&
                                    shop.getSelfCheckQ().size() < s.getCustomerLimit())
                            .min(Comparator.comparing(Server::customerQueueSize));
                    Optional<Server> tieServer = Optional.empty();

                    if (humanServer.isPresent() && selfCOServer.isPresent()) {
                        Server human = humanServer.get();
                        Server selfCO = selfCOServer.get();

                        if (human.customerQueueSize() <= shop.getSelfCheckQ().size()) {
                            tieServer = Optional.of(human);
                        }
                    }

                    if (humanServer.isPresent() || selfCOServer.isPresent()) {
                        if (selfCOServer.isPresent() && tieServer.isEmpty()) {
                            List<Server> tempList = shop
                                    .getServers()
                                    .stream()
                                    .filter(s -> !s.isSelfCheckout())
                                    .collect(Collectors.toList());
                            final int id = tempList.size() + 1;
                            Server returnServer = shop.find(s -> s.getIdentifier() == id)
                                    .get().queueCustomer(customer);
                            Optional<Server> counterServer = shop.getServers()
                                    .stream()
                                    .filter(s -> s.isSelfCheckout())
                                    .min(Comparator.comparing(Server::getAvailableTime));

                            return new Pair<Shop, Event>(
                                    shop.replace(returnServer).queueCustomer(customer),
                                    new SCWaitEvent(customer, returnServer,
                                            customer.getArrivalTime(),
                                            counterServer.get().getAvailableTime()));
                        } else if (humanServer.isPresent()) {
                            Server returnServer = humanServer.get().queueCustomer(customer);

                            return new Pair<Shop, Event>(shop.replace(returnServer),
                                    new WaitEvent(customer, returnServer,
                                            customer.getArrivalTime()));
                        }
                    }
                }
            }
        
            return new Pair<Shop, Event>(shop, new LeaveEvent(customer));    
        }, customer, customer.getArrivalTime(), 'A');
    }

    @Override
    public String toString() {
        return String.format("%.3f %s arrives",
                super.getCustomer().getArrivalTime(),
                super.getCustomer().printCustomer());
    }
}
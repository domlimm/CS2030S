package cs2030.simulator;

import java.util.Comparator;
import java.util.Optional;
import java.util.Queue;

/**
 * This ContWaitEvent class inherits the properties of its parent Event class.
 * It is used when a server is unable to serve a customer in its queue.
 *
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Project 2
 */
public class ContWaitEvent extends Event {
    private final double startTime;
    private final Server server;

    /**
     * Initialises a new ContWaitEvent.
     * @param customer  Customer with his/her details
     * @param server    Server with his/her details
     * @param startTime Start time of when a customer continues waiting for a server
     */
    public ContWaitEvent(Customer customer, Server server, double startTime) {
        super(shop -> {
            Server latestServer =
                    shop.find(s -> s.getIdentifier() == server.getIdentifier()).get();

            if (!latestServer.isSelfCheckout()) {
                Queue<Customer> latestQ = latestServer.getCustomerQueue();
                Server servingServer = latestServer.serverWait(startTime);

                boolean serveNonSelfCO =
                        latestServer.getAvailableTime() <= startTime &&
                        customer.equals(latestQ.peek());

                if (serveNonSelfCO) {
                    return new Pair<Shop, Event>(
                            shop.replace(servingServer),
                            new ServeEvent(customer, servingServer, startTime));
                } else {
                    return new Pair<Shop, Event>(shop,
                            new ContWaitEvent(customer, server, latestServer.getAvailableTime()));
                }
            }

            Server oldServer = shop.find(s -> s.getIdentifier() == server.getIdentifier()).get();
            Optional<Server> earlierServer = shop
                    .getServers()
                    .stream()
                    .filter(s -> s.isSelfCheckout())
                    .min(Comparator.comparing(Server::getAvailableTime));
            boolean serveSelfCO = false;

            if (oldServer.getIdentifier() != earlierServer.get().getIdentifier()) {
                oldServer = oldServer.deQueueCustomer();
                earlierServer = Optional.of(earlierServer.get().queueCustomer(customer));
            }

            serveSelfCO = earlierServer.get().getAvailableTime() <= startTime &&
                    customer.equals(shop.getSelfCheckQ().peek());

            if (serveSelfCO) {
                Server servingServer = earlierServer.get().serverWait(startTime);

                return new Pair<Shop, Event>(shop.replace(oldServer).replace(servingServer),
                        new ServeEvent(customer, servingServer, servingServer.getAvailableTime()));
            }

            return new Pair<Shop, Event>(shop.replace(oldServer).replace(earlierServer.get()),
                    new ContWaitEvent(customer, earlierServer.get(),
                            earlierServer.get().getAvailableTime()));
        }, customer, startTime, 'C');

        this.startTime = startTime;
        this.server = server;
    }

    @Override
    public String toString() {
        return String.format("%.3f %s continues to wait for %s",
                this.startTime,
                super.getCustomer().printCustomer(),
                this.server.printServer());
    }
}

package cs2030.simulator;

import java.util.Comparator;

/**
 * This SCWaitEvent class inherits the properties of its parent Event class. It is
 * used when a customer arrives and there are no servers available to serve
 * him/her, but the customer is welcome to wait for an available server.
 */
public class SCWaitEvent extends Event {
    private final double startTime;
    private final Server server;
    private final Customer customer;

    /**
     * Initialises a new SCWaitEvent.
     * @param customer  A customer and his/her details
     * @param server    A server and its details
     * @param startTime Start time of this event
     * @param serveTime Serve time of customer
     */
    public SCWaitEvent(Customer customer, Server server, double startTime, double serveTime) {
        super(shop -> {
            Server earlierServer = shop
                    .getServers()
                    .stream()
                    .filter(s -> s.isSelfCheckout())
                    .min(Comparator.comparing(Server::getAvailableTime))
                    .get();
            Server selfCOReplace =
                    shop.find(s -> s.getIdentifier() == earlierServer.getIdentifier()).get();
            Server oldServer = server;

            if (oldServer.getIdentifier() != selfCOReplace.getIdentifier()) {
                oldServer = oldServer.deQueueCustomer();
                selfCOReplace = selfCOReplace.queueCustomer(customer);
            }

            if (selfCOReplace.getAvailableTime() >= serveTime &&
                    customer.getCustomerID() != shop.getSelfCheckQ().peek().getCustomerID()) {
                return new Pair<Shop, Event>(shop.replace(oldServer).replace(selfCOReplace),
                        new ContWaitEvent(customer, selfCOReplace,
                                selfCOReplace.getAvailableTime()));
            }

            selfCOReplace = selfCOReplace.serverWait(selfCOReplace.getAvailableTime());
            shop = shop.replace(selfCOReplace);
            
            return new Pair<Shop, Event>(
                    shop.replace(oldServer),
                    new ServeEvent(customer, selfCOReplace, serveTime));
        }, customer, startTime, 'Z');

        this.startTime = startTime;
        this.server = server;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return String.format("%.3f %s waits to be served by %s",
                this.startTime,
                this.customer.printCustomer(),
                this.server.printServer());
    }
}

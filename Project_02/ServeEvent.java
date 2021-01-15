package cs2030.simulator;

import java.util.Queue;

/**
 * The ServeEvent class inherits the properties of its parent Event class.
 * It is used when a Customer is being served by a server.
 *
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Project 1
 */
public class ServeEvent extends Event {
    private final Customer customer;
    private final Server server;
    private final double startTime;

    /**
     * Initialises a new ServeEvent.
     * @param customer  A customer and his/her details
     * @param server    A server and its details
     * @param startTime Start time of this event
     */
    public ServeEvent(Customer customer, Server server, double startTime) {
        super(shop -> {
            Server latestServer = shop.find(s -> s.getIdentifier() == server.getIdentifier()).get();
            double serviceTime = customer.getServiceTimeS().get();
            Server servingServer =
                    latestServer.serverServe(startTime + serviceTime).deQueueCustomer();

            if (!servingServer.isSelfCheckout()) {
                return new Pair<Shop, Event>(shop.replace(servingServer)
                        .incrementWaitTime(startTime - customer.getArrivalTime()),
                        new DoneEvent(customer, servingServer, startTime + serviceTime));
            }
            
            return new Pair<Shop, Event>(shop.replace(servingServer)
                    .incrementWaitTime(startTime - customer.getArrivalTime())
                    .deQueueCustomer(),
                    new DoneEvent(customer, servingServer, startTime + serviceTime));            
        }, customer, startTime, 'S');

        this.customer = customer;
        this.server = server;
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return String.format("%.3f %s served by %s",
                this.startTime,
                this.customer.printCustomer(),
                this.server.printServer());
    }
}

package cs2030.simulator;

import java.util.Queue;

/**
 * This WaitEvent class inherits the properties of its parent Event class. It is
 * used when a customer arrives and there are no servers available to serve
 * him/her, but the customer is welcome to wait for an available server.
 *
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Project 2
 */
public class WaitEvent extends Event {
    private final Customer customer;
    private final Server server;
    private final double startTime;

    /**
     * Initialises a new WaitEvent.
     * @param customer  Customer with his/her details
     * @param server    Server with its details
     * @param startTime Start time of Wait Event
     */
    public WaitEvent(Customer customer, Server server, double startTime) {
        super(shop -> {
            Server latestServer = shop.find(s -> s.getIdentifier() == server.getIdentifier()).get();
            Queue<Customer> latestQ = latestServer.getCustomerQueue();
            Server servingServer = latestServer.serverWait(latestServer.getAvailableTime());

            if (latestServer.getAvailableTime() <= startTime && customer.equals(latestQ.peek())) {
                return new Pair<Shop, Event>(
                    shop.replace(servingServer),
                    new ServeEvent(customer, servingServer, latestServer.getAvailableTime()));
            }

            return new Pair<Shop, Event>(shop,
                    new ContWaitEvent(customer,
                            servingServer, latestServer.getAvailableTime()));
        }, customer, startTime, 'W');

        this.customer = customer;
        this.server = server;
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return String.format("%.3f %s waits to be served by %s",
                this.startTime,
                this.customer.printCustomer(),
                this.server.printServer());
    }
}

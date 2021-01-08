package cs2030.simulator;

import java.util.List;
import java.util.ListIterator;

/**
 * This WaitEvent class inherits the properties of its parent Event class.
 * It is used when a customer arrives and there are no servers available to 
 * serve him/her, but the customer is welcome to wait for an available
 * server.
 *
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Project 1
 */
public class WaitEvent extends Event {
    private final int serverId;
    private final double startTime;

    /**
     * Initialises a new WaitEvent.
     * @param customer  Customer with his/her details
     * @param servers   A list of servers and their details
     * @param serverId  ID of Server
     * @param startTime Time of the event
     */
    public WaitEvent(Customer customer, List<Server> servers, int serverId, double startTime) {
        super(customer, servers, customer.getArrivalTime(), 'W');
        this.serverId = serverId;
        this.startTime = startTime;
    }

    /**
     * This method updates the server that will serve a waiting customer.
     * @return Event Returns a ServeEvent when a server is finally available to serve.
     */
    public Event execute() {
        Customer c = super.getCustomer();
        List<Server> s = super.getServers();
        ListIterator<Server> li = s.listIterator();

        while (li.hasNext()) {
            Server server = li.next();

            if (server.getIdentifier() == this.serverId) {
                Server newServer = server.serverWait(this.startTime);
                li.set(newServer);
                break;
            }
        }

        return new ServeEvent(c, s, this.serverId, this.startTime);
    }

    /**
     * This method updates the statistics of the simulation.
     * @param s Takes in a Statistics object
     * @return Statistics Returns a statistics object with its attributes.
     */
    public Statistics updateStatistics(Statistics s) {
        return new Statistics(s.getNumCustomersServed(),
                s.getNumCustomersLeft(), s.getTotalWaitTime());
    }

    @Override
    public String toString() {
        return String.format("%.3f %d waits to be served by %d",
                super.getCustomer().getArrivalTime(),
                super.getCustomer().getCustomerID(),
                this.serverId);
    }
}

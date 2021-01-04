package cs2030.simulator;

import java.util.List;
import java.util.ListIterator;

/**
 * The DoneEvent class inherits the properties of its parent Event class.
 * It is used for when a server is done serving a customer.
 */
public class DoneEvent extends Event {
    private final int serverId;
    private final double endTime;

    /**
     * Initialises a new DoneEvent.
     * @param customer  ID of Customer
     * @param servers   A list of servers and their details
     * @param serverId  ID of Server
     * @param endTime   Time of completion of service for a customer
     */
    public DoneEvent(Customer customer, List<Server> servers, int serverId, double endTime) {
        super(customer, servers, endTime, 'D');
        this.serverId = serverId;
        this.endTime = endTime;
    }

    /**
     * Initialises a new DoneEvent.
     * @param customer  ID of Customer
     * @param servers   A list of servers and their details
     */
    public DoneEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
        this.serverId = 0;
        this.endTime = 0.0;
    }

    /**
     * This method updates the statuses (availability, customer
     * waiting for server, available time) of the server that has
     * completed serving the respective customer.
     * @return Event    Returns an EndEvent to port the
     *                  updated servers list to other customers.
     */
    public Event execute() {
        Customer c = super.getCustomer();
        List<Server> s = super.getServers();
        ListIterator<Server> li = s.listIterator();

        while (li.hasNext()) {
            Server server = li.next();

            if (server.getIdentifier() == this.serverId) {
                Server newServer = server.serverAvailable();
                li.set(newServer);
                break;
            }
        }

        return new DoneEvent(c, s);
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
        return String.format("%.3f %d done serving by %d",
                this.endTime,
                super.getCustomer().getCustomerID(),
                this.serverId);
    }
}

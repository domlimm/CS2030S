package cs2030.simulator;

import java.util.List;
import java.util.ListIterator;

/**
 * The ServeEvent class inherits the properties of its parent Event class.
 * It is used when a Customer is being served by a server.
 *
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Project 1
 */
public class ServeEvent extends Event {
    private static final double SERVICE_TIME = 1.0;
    private final int serverId;
    private final double startTime;

    /**
     * Initialises a new ServeEvent.
     * @param customer  A customer and his/her details
     * @param servers   A list of servers and their details
     * @param serverId  ID of the server in ServeEvent
     * @param startTime Time of when the Serve Event starts
     */
    public ServeEvent(Customer customer, List<Server> servers, int serverId, double startTime) {
        super(customer, servers, startTime, 'S');
        this.serverId = serverId;
        this.startTime = startTime;
    }

    /**
     * This method is called to have the sever finish serving a customer.
     * @return  Event Returns a DoneEvent with the relevant fields
     *          its constructor requires.
     */
    public Event execute() {
        Customer c = super.getCustomer();
        List<Server> s = super.getServers();
        ListIterator<Server> li = s.listIterator();

        while (li.hasNext()) {
            Server server = li.next();

            if (server.getIdentifier() == this.serverId) {
                Server newServer = server.serverServe(this.startTime + SERVICE_TIME);
                li.set(newServer);
                break;
            }
        }

        return new DoneEvent(c, s, this.serverId, this.startTime + SERVICE_TIME);
    }

    /**
     * This method updates the statistics of the simulation.
     * @param s Takes in a Statistics object
     * @return Statistics Returns a statistics object with its attributes.
     */
    public Statistics updateStatistics(Statistics s) {
        return new Statistics(s.getNumCustomersServed() + 1,
                s.getNumCustomersLeft(),
                s.getTotalWaitTime() + (this.startTime - super.getCustomer().getArrivalTime()));
    }

    @Override
    public String toString() {
        return String.format("%.3f %d served by %d",
                this.startTime,
                super.getCustomer().getCustomerID(),
                this.serverId);
    }
}

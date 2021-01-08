package cs2030.simulator;

import java.util.ListIterator;
import java.util.List;

/**
 * The ArriveEvent class inherits the properties of its parent Event class.
 * It is used to decide what is next for when a Customer arrives.
 *
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Project 1
 */
public class ArriveEvent extends Event {
    /**
     * Initialises a new ArriveEvent.
     * @param customer  A customer and his/her details
     * @param servers   A list of servers and their details
     */
    public ArriveEvent(Customer customer, List<Server> servers) {
        super(customer, servers, customer.getArrivalTime(), 'A');
    }

    /**
     * This method determines whether a Serve, Wait or Leave Event should be returned
     * based on the availability of all servers.
     * @return Event    Returns an Event with the relevant fields
     *                  of the Event class constructor.
     * */
    public Event execute() {
        List<Server> servers = super.getServers();
        ListIterator<Server> li = servers.listIterator();
        Customer c = super.getCustomer();

        while (li.hasNext()) {
            Server s = li.next();
            boolean available = s.getAvailability();

            if (available) {
                return new ServeEvent(c, servers,
                        s.getIdentifier(), c.getArrivalTime());
            }
        }

        li = servers.listIterator();

        while (li.hasNext()) {
            Server s = li.next();
            boolean waitingCustomer = s.getHasWaitingCustomer();

            if (!waitingCustomer) {
                return new WaitEvent(c, servers,
                        s.getIdentifier(), s.getAvailableTime());
            }
        }

        return new LeaveEvent(super.getCustomer(), servers);
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
        return String.format("%.3f %d arrives",
                super.getCustomer().getArrivalTime(),
                super.getCustomer().getCustomerID());
    }
}

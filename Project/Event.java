package cs2030.simulator;

import java.util.List;

/**
 * The Event class holds information of an Event.
 * 
 *
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Project 1
 */
public abstract class Event {
    private final Customer customer;
    private final List<Server> servers;
    private final double eventTime;
    private final char eventType;

    /**
     * Initialises a new Event.
     * @param customer  Customer details
     * @param servers   List of servers with their details
     */
    public Event(Customer customer, List<Server> servers) {
        this.customer = customer;
        this.servers = servers;
        this.eventTime = 0.0;
        this.eventType = '\0';
    }

    /**
     * Initialises a new Event.
     * @param customer  Customer details
     * @param servers   List of servers with their details
     * @param eventTime Time of when the Event starts
     * @param eventType Type of Event
     */
    public Event(Customer customer, List<Server> servers, double eventTime, char eventType) {
        this.customer = customer;
        this.servers = servers;
        this.eventTime = eventTime;
        this.eventType = eventType;
    }

    public double getEventTime() {
        return this.eventTime;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public List<Server> getServers() {
        return this.servers;
    }

    public char getEventType() {
        return eventType;
    }

    /**
     * All sub-classes of this Event has to implement an
     * execute method as it is abstract.
     * @return Event Returns an Event with relevant information from its constructor
     */
    public abstract Event execute();

    /**
     * All sub-classes of this Event has to implement this method to update
     * the statistics of the simulation.
     * @param s Statistics object
     * @return Statistics Returns a new Statistics object with its relevant data
     */
    public abstract Statistics updateStatistics(Statistics s);
}

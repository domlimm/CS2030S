package cs2030.simulator;

import java.util.function.Function;
import java.util.ArrayList;
import java.util.List;

/**
 * The Event class holds information of an Event.
 *
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Project 2
 */
public abstract class Event {
    private final Customer customer;
    private final double eventTime;
    private final char eventType;
    private final Function<Shop, Pair<Shop, Event>> func;

    /**
     * Initialise a new Event.
     * @param func    Executable function for simulate operation
     */
    public Event(Function<Shop, Pair<Shop, Event>> func) {
        this.func = func;
        this.customer = null;
        this.eventTime = 0.0;
        this.eventType = '\0';
    }

    /**
     * Initialise a new Event.
     * @param func      Executable function for simulate operation
     * @param c         Customer object with its details
     * @param eventTime Start of this Event
     * @param eventType Type of this Event
     */
    public Event(Function<Shop, Pair<Shop, Event>> func,
            Customer c, double eventTime, char eventType) {
        this.func = func;
        this.customer = c;
        this.eventTime = eventTime;
        this.eventType = eventType;
    }

    public final Pair<Shop, Event> execute(Shop shop) {
        return this.func.apply(shop);
    }

    public Function<Shop, Pair<Shop, Event>> getFunc() {
        return this.func;
    }

    public double getEventTime() {
        return this.eventTime;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public char getEventType() {
        return eventType;
    }
}

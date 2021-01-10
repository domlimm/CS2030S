package cs2030.simulator;

import java.util.Comparator;

/**
 * This EventCompare class compares two events for sorting purposes
 * in the queue.
 */
public class EventCompare implements Comparator<Event> {
    @Override
    public int compare(Event event1, Event event2) {
        double e1Time = event1.getEventTime();
        double e2Time = event2.getEventTime();
        int custId1 = event1.getCustomer().getCustomerID();
        int custId2 = event2.getCustomer().getCustomerID();

        if (e1Time > e2Time) {
            return 1;
        } else if (e1Time < e2Time) {
            return -1;
        } else {
            if (custId1 > custId2) {
                return 1;
            } else if (custId1 < custId2) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}

package cs2030.simulator;

/**
 * This LeaveEvent class inherits the properties of its parent Event class.
 * It is used when the queue is full when the customer arrives.
 */
public class LeaveEvent extends Event {
    /**
     * Initialises a new LeaveEvent.
     * @param customer  Customer with his/her details
     */
    public LeaveEvent(Customer customer) {
        super(shop -> new Pair<Shop, Event>(shop.incrementLeave(), null),
                customer, customer.getArrivalTime(), 'L');
    }
    
    @Override
    public String toString() {
        return String.format("%.3f %s leaves",
                super.getCustomer().getArrivalTime(),
                super.getCustomer().printCustomer());
    }
}

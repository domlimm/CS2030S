package cs2030.simulator;

/**
 * The BackEvent class inherits the properties of its parent Event class.
 * It is used for when a server goes on break.
 * */
public class BackEvent extends Event {
    private final double startTime;
    private final double endTime;
    private final Server server;

    /**
     * Initialises a new BackEvent.
     * @param customer  Customer with his/her details
     * @param server    Server with its details
     * @param startTime Start time of server rest
     * @param endTime   The time server returns
     */
    public BackEvent(Customer customer, Server server, double startTime, double endTime) {
        super(shop -> new Pair<Shop, Event>(
                shop.replace(shop.find(s ->
                        server.getIdentifier() == s.getIdentifier()).get().serverAvailable()),
                        null),
                customer, endTime, 'B');

        this.startTime = startTime;
        this.endTime = endTime;
        this.server = server;
    }
    
    @Override
    public String toString() {
        return String.format("%.3f server %d rest, returns @ %.3f",
                this.startTime,
                this.server.getIdentifier(),
                this.endTime);
    }
}

package cs2030.simulator;

/**
 * The DoneEvent class inherits the properties of its parent Event class.
 * It is used for when a server is done serving a customer.
 *
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Project 2
 */
public class DoneEvent extends Event {
    private final Server server;
    private final Customer customer;
    private final double endTime;

    /**
     * Initialises a new DoneEvent.
     * @param customer  ID of Customer
     * @param server    A server and its details
     * @param endTime   Time when server is done serving customer 
     */
    public DoneEvent(Customer customer, Server server, double endTime) {
        super(shop -> {
            Server latestServer = shop.find(s -> s.getIdentifier() == server.getIdentifier()).get()
                    .serverAvailable();

            boolean resting = !latestServer.isSelfCheckout() ?
                    shop.getRng().genRandomRest() < shop.getRestProbability() : false;
            Shop latestShop = shop.incrementServe();

            if (resting) {
                Server backServer = latestServer.serveRest(endTime +
                        shop.getRng().genRestPeriod());
                latestShop = latestShop.replace(backServer);

                return new Pair<Shop, Event>(latestShop,
                        new BackEvent(customer, backServer, endTime,
                                backServer.getAvailableTime()));
            }
            
            return new Pair<Shop, Event>(latestShop.replace(latestServer), null);
        }, customer, endTime, 'D');
        
        this.server = server;
        this.customer = customer;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("%.3f %s done serving by %s",
                this.endTime,
                this.customer.printCustomer(),
                this.server.printServer());
    }
}

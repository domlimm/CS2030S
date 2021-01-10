package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This Simulator class is the handler of the discrete event simulator with
 * the process of assigning/removing servers to customers waiting
 * in a priority queue.
 */
public class Simulator {
    private final Optional<Shop> shop;
    private final List<Customer> customers;
    private final PriorityQueue<Event> pq;
    private final Statistics stats;
    private final int customerLimit;

    /**
     * Initialises a new Simulator. Level 1-2.
     * @param numOfServers  Number of servers on start up
     * @param customers     List of customers and their details
     * @param stats         To keep track of statistics
     */
    public Simulator(int numOfServers, List<Customer> customers, Statistics stats) {
        this.shop = Optional.of(new Shop(numOfServers));
        this.customers = customers;
        this.pq = new PriorityQueue<Event>(new EventCompare());
        this.stats = stats;
        this.customerLimit = 1;
    }

    /**
     * Initialises a new Simulator. Level 3-4.
     * @param numOfServers  Number of servers on start up
     * @param customerLimit Limit the number of customers in each server's queue
     * @param customers     List of customers and their details
     * @param stats         To keep track of statistics
     */
    public Simulator(int numOfServers, int customerLimit, List<Customer> customers,
            Statistics stats) {
        this.shop = Optional.of(new Shop(numOfServers, customerLimit));
        this.customers = customers;
        this.pq = new PriorityQueue<Event>(new EventCompare());
        this.stats = stats;
        this.customerLimit = customerLimit;
    }

    /**
     * Initialises a new Simulator. Level 5.
     * @param numOfServers  Number of servers on start up
     * @param customerLimit Limit the number of customers in each server's queue
     * @param customers     List of customers and their details
     * @param stats         To keep track of statistics
     * @param rng           RandomGenerator object    
     * @param restP         Probability of server rest
     */
    public Simulator(int numOfServers, int customerLimit, List<Customer> customers,
            Statistics stats, RandomGenerator rng, double restP) {
        this.shop = Optional.of(new Shop(numOfServers, customerLimit, rng, restP));
        this.customers = customers;
        this.pq = new PriorityQueue<Event>(new EventCompare());
        this.stats = stats;
        this.customerLimit = customerLimit;
    }

    /**
     * Initialises a new Simulator. Level 6.
     * @param numOfServers  Number of servers on start up
     * @param customerLimit Limit the number of customers in each server's queue
     * @param customers     List of customers and their details
     * @param stats         To keep track of statistics
     * @param rng           RandomGenerator object    
     * @param restP         Probability of server rest
     * @param selfCO        Number of self-checkout server/counter
     */
    public Simulator(int numOfServers, int customerLimit, List<Customer> customers,
            Statistics stats, RandomGenerator rng, double restP, int selfCO) {
        this.shop = Optional.of(new Shop(numOfServers, customerLimit, rng, restP, selfCO));
        this.customers = customers;
        this.pq = new PriorityQueue<Event>(new EventCompare());
        this.stats = stats;
        this.customerLimit = customerLimit;
    }

    /**
     * Initialises a new Simulator. Level 7.
     * @param numOfServers  Number of servers on start up
     * @param customerLimit Limit the number of customers in each server's queue
     * @param customers     List of customers and their details
     * @param stats         To keep track of statistics
     * @param rng           RandomGenerator object    
     * @param restP         Probability of server rest
     * @param selfCO        Number of self-checkout server/counter
     * @param greedyP       Probability of greedy customer
     */
    public Simulator(int numOfServers, int customerLimit, List<Customer> customers,
            Statistics stats, RandomGenerator rng, double restP, int selfCO, double greedyP) {
        this.shop = Optional.of(new Shop(numOfServers, customerLimit, rng,
                restP, selfCO, greedyP));
        this.customers = customers;
        this.pq = new PriorityQueue<Event>(new EventCompare());
        this.stats = stats;
        this.customerLimit = customerLimit;
    }

    /**
     * Initialises a new Simulator.
     * @param servers       List of servers and their details
     * @param customers     List of customers and their details
     * @param pq            Latest priority queue
     * @param stats         To keep track of statistics
     * @param cL            Customer Queue limit
     */
    public Simulator(List<Server> servers, List<Customer> customers,
            PriorityQueue<Event> pq, Statistics stats, int cL,
            RandomGenerator rng, double restP, Queue<Customer> selfCO,
            double greedyP) {
        this.shop = Optional.of(new Shop(servers, stats, rng, restP, selfCO, greedyP));
        this.customers = customers;
        this.pq = new PriorityQueue<Event>(pq);
        this.stats = stats;
        this.customerLimit = cL;
    }

    public Statistics getStats() {
        return this.stats;
    }

    public Shop getShop() {
        return this.shop.get();
    }

    /**
     * Boots up program with necessary variables for simulation.
     * @param args Takes in an array of arguments
     * @return Simulator Returns a new Simulator with its filled details.
     */
    public static Simulator init(String[] args) {
        int seed = 0;
        int numOfServers = 0;
        int queueLength = 0;
        int numOfCustomers = 0;
        double arrivalRate = 0.0;
        double serviceRate = 0.0;
        int countCustomers = 0;
        double time = 0.0;
        double restRate = 0.0;
        double restProbability = 0.0;
        int numOfSelfCheckout = 0;
        double greedyProbability = 0.0;

        if (args.length == 5) {
            seed = Integer.parseInt(args[0]);
            numOfServers = Integer.parseInt(args[1]);
            numOfCustomers = Integer.parseInt(args[2]);
            arrivalRate = Double.parseDouble(args[3]);
            serviceRate = Double.parseDouble(args[4]);
        } else if (args.length == 6) {
            seed = Integer.parseInt(args[0]);
            numOfServers = Integer.parseInt(args[1]);
            queueLength = Integer.parseInt(args[2]);
            numOfCustomers = Integer.parseInt(args[3]);
            arrivalRate = Double.parseDouble(args[4]);
            serviceRate = Double.parseDouble(args[5]);
        } else if (args.length == 8) {
            seed = Integer.parseInt(args[0]);
            numOfServers = Integer.parseInt(args[1]);
            queueLength = Integer.parseInt(args[2]);
            numOfCustomers = Integer.parseInt(args[3]);
            arrivalRate = Double.parseDouble(args[4]);
            serviceRate = Double.parseDouble(args[5]);
            restRate = Double.parseDouble(args[6]);
            restProbability = Double.parseDouble(args[7]);
        } else if (args.length == 9) {
            seed = Integer.parseInt(args[0]);
            numOfServers = Integer.parseInt(args[1]);
            numOfSelfCheckout = Integer.parseInt(args[2]);
            queueLength = Integer.parseInt(args[3]);
            numOfCustomers = Integer.parseInt(args[4]);
            arrivalRate = Double.parseDouble(args[5]);
            serviceRate = Double.parseDouble(args[6]);
            restRate = Double.parseDouble(args[7]);
            restProbability = Double.parseDouble(args[8]);
        } else if (args.length == 10) {
            seed = Integer.parseInt(args[0]);
            numOfServers = Integer.parseInt(args[1]);
            numOfSelfCheckout = Integer.parseInt(args[2]);
            queueLength = Integer.parseInt(args[3]);
            numOfCustomers = Integer.parseInt(args[4]);
            arrivalRate = Double.parseDouble(args[5]);
            serviceRate = Double.parseDouble(args[6]);
            restRate = Double.parseDouble(args[7]);
            restProbability = Double.parseDouble(args[8]);
            greedyProbability = Double.parseDouble(args[9]);
        }

        RandomGenerator rng = new RandomGenerator(seed, arrivalRate, serviceRate, restRate);
        List<Customer> customers = new ArrayList<Customer>();
        Supplier<Double> serviceTime = () -> rng.genServiceTime();

        while (countCustomers != numOfCustomers) {
            ++countCustomers;
            customers.add(new Customer(countCustomers, time, serviceTime));
            time += rng.genInterArrivalTime();
        }

        return args.length == 5 ?
                new Simulator(numOfServers, customers, new Statistics()) :
                        args.length == 6 ?
                                new Simulator(numOfServers, queueLength,
                                        customers, new Statistics()) :
                        args.length == 8 ?
                                new Simulator(numOfServers, queueLength,
                                        customers, new Statistics(),
                                        rng, restProbability) :
                        args.length == 9 ?
                                new Simulator(numOfServers, queueLength,
                                        customers, new Statistics(),
                                        rng, restProbability, numOfSelfCheckout) :
                                new Simulator(numOfServers, queueLength,
                                        customers, new Statistics(),
                                        rng, restProbability, numOfSelfCheckout,
                                        greedyProbability);
    }

    /**
     * This method automates the simulation.
     */
    public Pair<Optional<Event>, Simulator> simulateNextEvent(Simulator previous) {
        Event e = this.pq.poll();
        addNewCustomer(e.getEventTime(), e.getCustomer());
        Pair<Shop, Event> tempPair = e.execute(previous.getShop());

        if (tempPair.second() != null) {
            this.pq.add(tempPair.second());
        }

        return new Pair<Optional<Event>, Simulator>(abortPrint(Optional.of(e)) ?
                Optional.empty() : Optional.of(e),
                new Simulator(
                        tempPair.first().getServers(),
                        this.customers,
                        this.pq,
                        tempPair.first().getStats(),
                        this.customerLimit,
                        tempPair.first().getRng(),
                        tempPair.first().getRestProbability(),
                        tempPair.first().getSelfCheckQ(),
                        tempPair.first().getGreedyProbability()));
    }

    /**
     * This method initialises the queue with the first customer.
     */
    public void startQueue() {
        this.pq.add(new ArriveEvent(this.customers
                .get(0)
                .toGreedy(this.getShop().getRng().genCustomerType() <
                        this.getShop().getGreedyProbability())));
    }

    private boolean abortPrint(Optional<Event> e) {
        if (!e.isPresent()) {
            return true;
        }

        char[] abortPrint = {'B', 'C'};
        char eventType = e.get().getEventType();

        for (char event: abortPrint) {
            if (event == eventType) {
                return true;
            }
        }

        return false;
    }

    private void addNewCustomer(double eventTime, Customer customer) {
        for (Customer c: this.customers) {
            boolean isInQueue = isCustomerInQueue(c);
            boolean isNextCustomer = customer.getCustomerID() + 1 == c.getCustomerID();
            boolean isArrivalLater = c.getArrivalTime() >= eventTime;

            if (!isInQueue && isNextCustomer && isArrivalLater) {
                this.pq.add(new ArriveEvent(c.toGreedy(this.getShop().getRng().genCustomerType() <
                        this.getShop().getGreedyProbability())));
                return;
            }
        }
    }

    private boolean isCustomerInQueue(Customer customer) {
        PriorityQueue<Event> tempPQ = new PriorityQueue<Event>(this.pq);
        List<Customer> customersInQ = new ArrayList<Customer>();

        while (tempPQ.peek() != null) {
            Event e = tempPQ.poll();
            Customer c = e.getCustomer();
            
            if (c.getCustomerID() == customer.getCustomerID()) {
                return true;
            }
        }

        return false;
    }

    /**
     * This methods checks if the queue is empty.
     * @return boolean Returns if the queue is empty
     */
    public boolean hasNextEvent() {
        if (this.pq.size() > 0) {
            return true;
        }

        return false;
    }
}

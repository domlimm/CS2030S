package cs2030.simulator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The Shop class holds all servers, human and self checkout.
 * It aids with the communication between servers and their customers.
 *
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Project 2
 */
public class Shop {
    private final List<Server> servers;
    private final Statistics stats;
    private final RandomGenerator rng;
    private final double restProbability;
    private final Queue<Customer> selfCheckQ;
    private final double greedyProbability;

    /**
     * Creates a new Shop. Level 1-2.
     * @param servers List of Servers
     */
    public Shop(List<Server> servers) {
        this.servers = new ArrayList<Server>(servers);
        this.stats = new Statistics();
        this.rng = new RandomGenerator(0, 0, 0, 0);
        this.restProbability = -99.99;
        this.selfCheckQ = new LinkedList<Customer>();
        this.greedyProbability = -99.99;
    }

    /**
     * Creates a new Shop. Level 1-2.
     * @param numOfServers Number of Servers
     */
    public Shop(int numOfServers) {
        this.servers = new ArrayList<Server>(Stream
                .iterate(1, x -> x + 1)
                .map(i -> new Server(i))
                .limit(numOfServers)
                .collect(Collectors.toList()));
        this.stats = new Statistics();
        this.rng = new RandomGenerator(0, 0, 0, 0);
        this.restProbability = -99.99;
        this.selfCheckQ = new LinkedList<Customer>();
        this.greedyProbability = -99.99;
    }

    /**
     * Creates a new Shop. Level 3-4.
     * @param numOfServers  Number of Servers
     * @param customerLimit Limit per customer queue
     */
    public Shop(int numOfServers, int customerLimit) {
        this.servers = new ArrayList<Server>(Stream
                .iterate(1, x -> x + 1)
                .map(i -> new Server(i, customerLimit))
                .limit(numOfServers)
                .collect(Collectors.toList()));
        this.stats = new Statistics();
        this.rng = new RandomGenerator(0, 0, 0, 0);
        this.restProbability = -99.99;
        this.selfCheckQ = new LinkedList<Customer>();
        this.greedyProbability = -99.99;
    }

    /**
     * Creates a new Shop. Level 5.
     * @param numOfServers  Number of Servers
     * @param customerLimit Limit per customer queue
     * @param rng           RandomGenerator object
     * @param restP         Probability of server rest
     */
    public Shop(int numOfServers, int customerLimit, RandomGenerator rng, double restP) {
        this.servers = new ArrayList<Server>(Stream
                .iterate(1, x -> x + 1)
                .map(i -> new Server(i, customerLimit))
                .limit(numOfServers)
                .collect(Collectors.toList()));
        this.stats = new Statistics();
        this.rng = rng;
        this.restProbability = restP;
        this.selfCheckQ = new LinkedList<Customer>();
        this.greedyProbability = -99.99;
    }

    /**
     * Creates a new Shop. Level 6.
     * @param numOfServers  Number of Servers
     * @param customerLimit Limit per customer queue
     * @param rng           RandomGenerator object
     * @param restP         Probability of server rest
     * @param selfCO        Number of selfcheckout counters
     */
    public Shop(int numOfServers, int customerLimit, RandomGenerator rng,
            double restP, int selfCO) {
        List<Server> humanServers = new ArrayList<Server>(Stream
                .iterate(1, x -> x + 1)
                .map(i -> new Server(i, customerLimit, false))
                .limit(numOfServers)
                .collect(Collectors.toList()));
        List<Server> selfCheckouts = new ArrayList<Server>(Stream
                .iterate(humanServers.size() + 1, x -> x + 1)
                .map(i -> new Server(i, customerLimit, true))
                .limit(selfCO)
                .collect(Collectors.toList()));

        this.servers = new ArrayList<Server>(Stream
                .of(humanServers, selfCheckouts)
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));
        this.stats = new Statistics();
        this.rng = rng;
        this.restProbability = restP;
        this.selfCheckQ = new LinkedList<Customer>();
        this.greedyProbability = -99.99;
    }

    /**
     * Creates a new Shop. Level 7.
     * @param numOfServers  Number of Servers
     * @param customerLimit Limit per customer queue
     * @param rng           RandomGenerator object
     * @param restP         Probability of server rest
     * @param selfCO        Number of selfcheckout counters
     * @param greedyP       Probability of greedy customer
     */
    public Shop(int numOfServers, int customerLimit, RandomGenerator rng,
            double restP, int selfCO, double greedyP) {
        List<Server> humanServers = new ArrayList<Server>(Stream
                .iterate(1, x -> x + 1)
                .map(i -> new Server(i, customerLimit, false))
                .limit(numOfServers)
                .collect(Collectors.toList()));
        List<Server> selfCheckouts = new ArrayList<Server>(Stream
                .iterate(humanServers.size() + 1, x -> x + 1)
                .map(i -> new Server(i, customerLimit, true))
                .limit(selfCO)
                .collect(Collectors.toList()));

        this.servers = new ArrayList<Server>(Stream
                .of(humanServers, selfCheckouts)
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));
        this.stats = new Statistics();
        this.rng = rng;
        this.restProbability = restP;
        this.selfCheckQ = new LinkedList<Customer>();
        this.greedyProbability = greedyP;
    }

    /**
     * Creates a new Shop.
     * @param servers   List of Servers
     * @param stats     Statistics
     * @param rng       RandomGenerator object
     * @param restP     Probability of server rest
     * @param selfCO    Number of selfcheckout counters
     * @param greedyP   Probability of greedy customer
     */
    public Shop(List<Server> servers, Statistics stats, RandomGenerator rng, double restP,
            Queue<Customer> selfCO, double greedyP) {
        this.servers = new ArrayList<Server>(servers);
        this.stats = stats;
        this.rng = rng;
        this.restProbability = restP;
        this.selfCheckQ = new LinkedList<Customer>(selfCO);
        this.greedyProbability = greedyP;
    }

    protected Shop queueCustomer(Customer customer) {
        Queue<Customer> tempPQ =
                new LinkedList<Customer>(this.selfCheckQ);
        tempPQ.add(customer);

        return new Shop(
                this.servers,
                this.stats,
                this.rng,
                this.restProbability,
                tempPQ,
                this.greedyProbability);
    }

    protected Shop deQueueCustomer() {
        Queue<Customer> tempPQ =
                new LinkedList<Customer>(this.selfCheckQ);
        tempPQ.poll();

        return new Shop(
                this.servers,
                this.stats,
                this.rng,
                this.restProbability,
                tempPQ,
                this.greedyProbability);
    }

    /**
     * Increments when a customer is successfully served.
     * @return Shop Returns the existing shop with update statistics.
     */
    public Shop incrementServe() {
        return new Shop(
            this.servers,
            new Statistics(this.stats.getNumCustomersServed() + 1,
                    this.stats.getNumCustomersLeft(),
                    this.stats.getTotalWaitTime()),
            this.rng,
            this.restProbability,
            this.selfCheckQ,
            this.greedyProbability);
    }

    /**
     * Increments the customer's waiting time for a server.
     * @param time  How long did the customer wait before being serve
     * @return Shop Returns the existing shop with update statistics.
     */
    public Shop incrementWaitTime(double time) {
        return new Shop(
            this.servers,
            new Statistics(this.stats.getNumCustomersServed(),
                    this.stats.getNumCustomersLeft(),
                    this.stats.getTotalWaitTime() + time),
            this.rng,
            this.restProbability,
            this.selfCheckQ,
            this.greedyProbability);
    }

    /**
     * Increments the number of customers that have left.
     * @return Shop Returns the existing shop with update statistics.
     */
    public Shop incrementLeave() {
        return new Shop(
            this.servers,
            new Statistics(this.stats.getNumCustomersServed(),
                    this.stats.getNumCustomersLeft() + 1,
                    this.stats.getTotalWaitTime()),
            this.rng,
            this.restProbability,
            this.selfCheckQ,
            this.greedyProbability);
    }

    public double getGreedyProbability() {
        return this.greedyProbability;
    }

    public Queue<Customer> getSelfCheckQ() {
        return this.selfCheckQ;
    }

    public RandomGenerator getRng() {
        return this.rng;
    }

    public double getRestProbability() {
        return this.restProbability;
    }

    public List<Server> getServers() {
        return this.servers;
    }

    public Statistics getStats() {
        return this.stats;
    }

    /**
     * Search for Server that satisfies the specified condition.
     * @param p         Specified condition
     * @return Server   Returns the Server that satisfied the specified condition..
     */
    public Optional<Server> find(Predicate<Server> p) {
        return this.servers
                .stream()
                .filter(p)
                .findFirst();
    }

    /**
     * Search for Server that satisfies the specified condition.
     * @param newServer Server to update
     * @return Shop     Returns the existing shop with updated servers.
     */
    public Shop replace(Server newServer) {
        return new Shop(this.servers
                .stream()
                .map(s -> s.getIdentifier() == newServer.getIdentifier() ? newServer : s)
                .collect(Collectors.toList()),
                this.stats,
                this.rng,
                this.restProbability,
                this.selfCheckQ,
                this.greedyProbability);
    }

    @Override
    public String toString() {
        return this.servers.toString();
    }
}

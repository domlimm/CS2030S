package cs2030.simulator;

/**
 * The Statistics class produces analysis on the simulation.
 * I.e. Number of customers served, number of customers left, waiting time for a customer.
 *
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Project 1
 */
public class Statistics {
    private final int numCustomersServed;
    private final int numCustomersLeft;
    private final double totalWaitTime;

    /**
     * Initialises a new Statistics object.
     */
    public Statistics() {
        this.numCustomersServed = 0;
        this.numCustomersLeft = 0;
        this.totalWaitTime = 0.0;
    }

    /**
     * Initialises a new Statistics object when updating internal attributes.
     * @param numCustomersServed    Number of customer/s served
     * @param numCustomersLeft      Number of customer/s left
     * @param totalWaitTime         Total waiting time
     */
    public Statistics(int numCustomersServed, int numCustomersLeft, double totalWaitTime) {
        this.numCustomersServed = numCustomersServed;
        this.numCustomersLeft = numCustomersLeft;
        this.totalWaitTime = totalWaitTime;
    }
   
    public int getNumCustomersServed() {
        return numCustomersServed;
    }

    public int getNumCustomersLeft() {
        return numCustomersLeft;
    }

    public double getTotalWaitTime() {
        return totalWaitTime;
    }

    /**
     * This method computes the average waiting time for each customer before
     * he/she is served.
     * @return double Returns the average waiting time for each customer.
     */
    public double computeAverageWaitTime() {
        return this.totalWaitTime / (double) numCustomersServed; 
    }

    @Override
    public String toString() {
        double averageWaitTime = this.computeAverageWaitTime();

        return String.format("[%.3f %d %d]",
                averageWaitTime <= 0.0 || Double.isNaN(averageWaitTime)
                        ? 0.0 : averageWaitTime,
                this.numCustomersServed,
                this.numCustomersLeft);
    }
}

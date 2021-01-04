import cs2030.simulator.Simulator;
import cs2030.simulator.Statistics;
import cs2030.simulator.Customer;
import cs2030.simulator.Server;
import cs2030.simulator.Event;
import cs2030.simulator.EventCompare;
import cs2030.simulator.ArriveEvent;
import cs2030.simulator.ServeEvent;
import cs2030.simulator.WaitEvent;
import cs2030.simulator.DoneEvent;
import cs2030.simulator.LeaveEvent;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * The Main class of the program.
 */
public class Main {
    /**
     * Main program method.
     * @param args A list of arguments with type of String
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfServers = sc.nextInt();
        int numOfCustomers = 0;

        List<Server> servers = createServers(numOfServers);
        List<Customer> customers = new ArrayList<>();
        Statistics stats = new Statistics();

        while (sc.hasNextDouble()) {
            ++numOfCustomers;
            double arrivalTime = sc.nextDouble();
            customers.add(new Customer(numOfCustomers, arrivalTime));
        }

        sc.close();

        if (!customers.isEmpty()) {
            Simulator s = new Simulator(servers, customers);
            s.startQueue();

            while (s.hasNextEvent()) {
                Event e = s.simulateNextEvent();
                stats = e.updateStatistics(stats);
                System.out.println(e);
            }
        }

        System.out.println(stats);
    }
    
    /**
     * Initialises a list of Servers with using Server constructor.
     * @param numOfServers Number of servers 
     * @return List Returns a list of servers.
     */
    public static List<Server> createServers(int numOfServers) {
        List<Server> servers = new ArrayList<>();

        for (int i = 0; i < numOfServers; ++i) {
            servers.add(new Server(i + 1, true, false, 0.0));
        }

        return servers;
    }
}

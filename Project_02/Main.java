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
import cs2030.simulator.ContWaitEvent;
import cs2030.simulator.BackEvent;
import cs2030.simulator.Pair;
import cs2030.simulator.Shop;
import cs2030.simulator.RandomGenerator;

import java.util.Optional;

/**
 * The Main class of the program.
 *
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Project 2
 */
public class Main {
    /**
     * Main program method.
     * @param args A list of arguments with type of String
     */
    public static void main(String[] args) {
        Simulator s = Simulator.init(args);
        s.startQueue();

        Pair<Optional<Event>, Simulator> pair = s.simulateNextEvent(s);
        
        while (s.hasNextEvent()) {
            s = pair.second();

            if (pair.first().isPresent()) {
                System.out.println(pair.first().get());
            }
            
            pair = s.simulateNextEvent(s);
        }
        
        if (pair.first().isPresent()) {
            System.out.println(pair.first().get());
        }
        System.out.println(pair.second().getStats());
    }
}

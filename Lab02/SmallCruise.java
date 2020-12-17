/**
 * The SmallCruise class extends parent Cruise and holds its specific properties.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Lab 2
 */
class SmallCruise extends Cruise {
    private final static int NUM_OF_LOADERS = 1;
    private final static int SERVICE_TIME = 30;

    public SmallCruise(String identifier, int arrivalTime) {
        super(identifier, arrivalTime, NUM_OF_LOADERS, SERVICE_TIME);
    }
}

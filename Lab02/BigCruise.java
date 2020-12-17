/**
 * The BigCruise class extends parent Cruise and holds its specific properties.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Lab 1
 */
class BigCruise extends Cruise {
    public BigCruise(String identifier, int arrivalTime, int cruiseLength, int noOfPassengers) {
        super(
                identifier,
                arrivalTime,
                (int) Math.ceil((double) cruiseLength / 40),
                (int) Math.ceil(noOfPassengers / 50.0 * 1.0)
        );
    }
}

/**
 * The TakeACab class is a standalone service.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Lab 3
 */
public class TakeACab extends Services {
    private final int bookingFee = 200;
    private final int fareRate = 33;

    public int computeFare(Request request) {
        int baseFare = request.getDistance() * fareRate;

        return baseFare + bookingFee;
    }

    @Override
    public String toString() {
        return "TakeACab";
    }
}

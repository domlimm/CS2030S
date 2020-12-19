/**
 * The NormalCab class is a type of Driver.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Lab 3
 */
public class NormalCab extends Driver {
    public NormalCab(String licensePlate, int waitingTime) {
        super(licensePlate, waitingTime);
    }

    public double getLowerFare(Request r) {
      return finalFare(selectService(r), r);
    }

    public String selectService(Request r) {
        double justRide = new JustRide().computeFare(r);
        double takeACab = new TakeACab().computeFare(r);

        return justRide >= takeACab ? "TakeACab" : "JustRide";
    }

    public double finalFare(String serviceSelected, Request r) {
        double fare = 0.0;

        if (serviceSelected.equalsIgnoreCase("JustRide")) {
            JustRide jr = new JustRide();
            fare = (double) jr.computeFare(r) / 100.0;
        }
        else if (serviceSelected.equalsIgnoreCase("TakeACab")) {
            TakeACab tac = new TakeACab();
            fare = (double) tac.computeFare(r) / 100.0;
        }

        return fare;
    }

    @Override
    public String toString() {
        return String.format("%s (%d mins away) NormalCab"
                , super.getLicensePlate(), super.getWaitingTime());
    }
}

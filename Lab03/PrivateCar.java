/**
 * The PrivateCar class is a type of Driver.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Lab 3
 */
public class PrivateCar extends Driver {
    public PrivateCar(String licensePlate, int waitingTime) {
        super(licensePlate, waitingTime);
    }

    public double getLowerFare(Request r) {
      return finalFare(selectService(r), r);
    }

    public String selectService(Request r) {
        double justRide = new JustRide().computeFare(r);
        double shareARide = new ShareARide().computeFare(r);

        return justRide >= shareARide ? "ShareARide" : "JustRide";
    }

    public double finalFare(String serviceSelected, Request r) {
        double fare = 0.0;

        if (serviceSelected.equalsIgnoreCase("JustRide")) {
            JustRide jr = new JustRide();
            fare = (double) jr.computeFare(r) / 100.0;
        }
        else if (serviceSelected.equalsIgnoreCase("ShareARide")) {
            ShareARide sar = new ShareARide();
            fare = (double) sar.computeFare(r) / 100.0;
        }

        return fare;
    }

    @Override
    public String toString() {
        return String.format("%s (%d mins away) PrivateCar"
                , super.getLicensePlate(), super.getWaitingTime());
    }
}

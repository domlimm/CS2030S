/**
 * The RecycledLoader class extends parent Loader.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Lab 2
 */
class RecycledLoader extends Loader {
    private final int MAINTENANCE_TIME = 60;

    RecycledLoader(int identifier, Cruise cruise) {
        super(identifier, cruise);
    }

    @Override
    boolean canServe(Cruise cruise) {
        int recycledLoaderEnd = super.getNextAvailableTime() + MAINTENANCE_TIME;
        int nextCruiseStart = cruise.getArrivalTime();

        return nextCruiseStart >= recycledLoaderEnd;
    }

    @Override
    public String toString() {
        return String.format("%s", "Recycled " + super.toString());
    }
}

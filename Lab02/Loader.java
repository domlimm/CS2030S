/**
 * The Loader class holds properties of a loader.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Lab 2
 */
class Loader {
    private final int identifier;
    private final Cruise cruise;

    Loader(int identifier, Cruise cruise) {
        this.identifier = identifier;
        this.cruise = cruise;
    }

    boolean canServe(Cruise cruise) {
        int currentCruiseEnd = this.cruise.getServiceCompletionTime();
        int nextCruiseStart = cruise.getArrivalTime();

        return nextCruiseStart >= currentCruiseEnd;
    }

    Loader serve(Cruise cruise) {
        return canServe(cruise) ? new Loader(this.identifier, cruise) : this;
    }

    int getNextAvailableTime() {
        return this.cruise.getServiceCompletionTime();
    }

    int getIdentifier() {
        return this.identifier;
    }

    @Override
    public String toString() {
        return String.format("Loader %d serving " + this.cruise, this.identifier);
    }
}

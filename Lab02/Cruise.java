class Cruise {
    private final String identifier;
    private final int arrivalTime;
    private final int numOfLoader;
    private final int serviceTime;

    public Cruise(String identifier, int arrivalTime, int numOfLoader, int serviceTime) {
        this.identifier = identifier;
        this.arrivalTime = arrivalTime;
        this.numOfLoader = numOfLoader;
        this.serviceTime = serviceTime;
    }

    int getArrivalTime() {
        int minutesFromArrivalTime = getMinutes(this.arrivalTime);
        int hoursFromArrivalTime = getHours(this.arrivalTime);

        return minutesFromArrivalTime + hoursFromArrivalTime;
    }

    int getServiceCompletionTime() {
        return getArrivalTime() + this.serviceTime;
    }

    int getNumOfLoadersRequired() {
        return this.numOfLoader;
    }

    // Helper Methods
    private int getMinutes(int time) {
        return time % 100;
    }

    private int getHours(int time) {
        return (time - getMinutes(time)) / 100 * 60;
    }

    @Override
    public String toString() {
        return String.format("%s@%04d", this.identifier, this.arrivalTime);
    }
}

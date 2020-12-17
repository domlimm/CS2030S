class SmallCruise extends Cruise {
    private final static int NUM_OF_LOADERS = 1;
    private final static int SERVICE_TIME = 30;

    public SmallCruise(String identifier, int arrivalTime) {
        super(identifier, arrivalTime, NUM_OF_LOADERS, SERVICE_TIME);
    }
}

/**
 * The Driver class is an abstract class that 
 * - contains its specific properties
 * - contains abstract methods selectService, finalFare & getLowerFare
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Lab 3
 */
public abstract class Driver {
  private final String licensePlate;
  private final int waitingTime;

  public Driver(String licensePlate, int waitingTime){
    this.licensePlate = licensePlate;
    this.waitingTime = waitingTime;
  }

  public int getWaitingTime() {
      return this.waitingTime;
  }

  public String getLicensePlate() {
      return this.licensePlate;
  }

  public abstract String selectService(Request r);
  public abstract double finalFare(String serviceSelected, Request r);
  public abstract double getLowerFare(Request r);
}

/**
 * The Services class is abstract that contains static methods available to
 * be directly accessed by other classes.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Lab 3
 */
public abstract class Services {
    public static boolean isPeakHour(int time, int startPeak, int endPeak) {
        int timeInMinutes = getMinutes(time) + getHours(time);

        return timeInMinutes >= startPeak && timeInMinutes <= endPeak;
    }

    // Helper
    private static int getMinutes(int time) {
        return time % 100;
    }

    private static int getHours(int time) {
        return (time - getMinutes(time)) / 100 * 60;
    }

    public abstract int computeFare(Request request);
}

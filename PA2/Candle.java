import java.util.List;
import java.util.ArrayList;

/**
 * The Candle class extends parent Thing class. It holds the respective states
 * of a Candle.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Practical Assessment 2
 */
public class Candle extends Tg {
    private final List<String> states;
    private final String currentState;

    public Candle() {
        super("Candle flickers.");
        this.states = new ArrayList<String>();
        this.states.add("Candle flickers.");
        this.states.add("Candle is getting shorter.");
        this.states.add("Candle is about to burn out.");
        this.states.add("Candle has burned out.");
        this.currentState = this.states.get(0);
    }

    public Candle(int index) {
        super("");
        this.states = new ArrayList<String>();
        this.states.add("Candle flickers.");
        this.states.add("Candle is getting shorter.");
        this.states.add("Candle is about to burn out.");
        this.states.add("Candle has burned out.");
        this.currentState = this.states.get(index >= states.size() ? states.size() - 1 : index);
    }

    public Candle changeState(int index) {
        return new Candle(index);
    }
    
    @Override
    public String toString() {
        return this.currentState;
    }
}

import java.util.List;
import java.util.ArrayList;

/**
 * The Sword class extends parent Thing class. It holds the respective states
 * of a Sword.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Practical Assessment 2
 */
public class Sword extends Tg {
    private final List<String> states;
    private final String currentState;

    public Sword() {
        super("Sword is shimmering.");
        this.states = new ArrayList<String>();
        this.states.add("Sword is shimmering.");
        this.currentState = this.states.get(0);
    }
  
    public Sword(int index) {
        super("");
        this.states = new ArrayList<String>();
        this.states.add("Sword is shimmering.");
        this.currentState = this.states.get(index >= states.size() ? states.size() - 1 : index);
    }

    public Sword changeState(int index) {
        return new Sword(index);
    }

    @Override
    public String toString() {
        return this.currentState;
    }
}

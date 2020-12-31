import java.util.List;
import java.util.ArrayList;

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

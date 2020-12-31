import java.util.List;
import java.util.ArrayList;

public class Troll extends Tg {
    private final List<String> states;
    private final String currentState;

    public Troll() {
        super("Troll lurks in the shadows.");
        this.states = new ArrayList<String>();
        this.states.add("Troll lurks in the shadows.");
        this.states.add("Troll is getting hungry.");
        this.states.add("Troll is VERY hungry.");
        this.states.add("Troll is SUPER HUNGRY and is about to ATTACK!");
        this.states.add("Troll attacks!");
        this.currentState = this.states.get(0);
    }

    public Troll(int index) {
        super("");
        this.states = new ArrayList<String>();
        this.states.add("Troll lurks in the shadows.");
        this.states.add("Troll is getting hungry.");
        this.states.add("Troll is VERY hungry.");
        this.states.add("Troll is SUPER HUNGRY and is about to ATTACK!");
        this.states.add("Troll attacks!");
        this.currentState = this.states.get(index >= states.size() ? states.size() - 1 : index);
    }

    public Troll changeState(int index) {
        return new Troll(index);
    }
    
    @Override
    public String toString() {
        return this.currentState;
    }
}

import java.util.ArrayList;
import java.util.List;

/**
 * The Tg class is a parent class of Thing (Tg) where it holds a list of
 * things (Candle, Sword, Troll).
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Practical Assessment 2
 */
abstract class Tg {
    private final List<String> s;

    Tg(String a) {
        this.s = new ArrayList<>();
        s.add(a);
    }

    abstract Tg changeState(int i);
}

import java.util.ArrayList;
import java.util.List;

abstract class Tg {
    private final List<String> s;

    Tg(String a) {
        this.s = new ArrayList<>();
        s.add(a);
    }

    abstract Tg changeState(int i);
}

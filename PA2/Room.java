import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

public class Room {
    private final List<Tg> things;
    private final String location;
    private final int index;
    private final Room r;
    private final int s;

    public Room(String location) {
        this.things = new ArrayList<>();
        this.location = location;
        this.index = 0;
        this.s = 0;
        this.r = null;
    }

    public Room(String location, List<Tg> things, int index, int s, Room r) {
        this.things = new ArrayList<Tg>(things);
        this.location = location;
        this.index = index;
        this.s = s;
        this.r = r;
    }

    public Room(String location, Room p) {
        this.location = location;
        this.things = new ArrayList<>(p.t());
        this.index = p.i();
        this.s = p.s;
        this.r = p;
    }

    List<Tg> t() {
        return things;
    }

    String l() {
        return location;
    }

    int i() {
        return index;
    }

    int s() {
        return s;
    }

    Room r() {
        return r;
    }

    public Room add(Tg o) {
        List<Tg> newThings = new ArrayList<Tg>(this.things);
        newThings.add(o);

        return new Room(this.location, newThings, 0, s, r);
    }

    public Tg changeState(Tg thing, int index) {
        return thing.changeState(index);
    }

    public Room tick() {
        List<Tg> newThings = new ArrayList<Tg>();
        int index = this.index;
        ++index;

        for (Tg t: this.things) {
            newThings.add(this.changeState(t, index));
        }
     
        return new Room(this.location, newThings, index, s, r);
    }

    public Room tick(Function<Room, Room> f) {
        List<Tg> newThings = new ArrayList<>();
        int index = this.index;
        ++index;

        for (Tg t: this.things) {
            newThings.add(t);
        }
       
        return f.apply(new Room(this.location, newThings, --index, s, r)).tick(); 
    }

    public Room go(Function<Room, Room> f) {
        Room n = f.apply(this);
        List<Tg> l = new ArrayList<>(n.t());

        if (s() > 0) {
            l.add(0, new Sword());

            return new Room(n.l(), l, n.i(), 1, this);
        }

        return new Room(n.l(), l, n.i(), n.s, this);
    }

    Room back() {
        if (r != null) {
            r.t().removeIf(x -> x instanceof Sword);

            Room a = new Room(r.l(), r.t(), r.i(), s, r.r());

            return s > 0 ? a.tick().add(new Sword()) : a.tick();
        }
        
        return this;
    }

    @Override
    public String toString() {
        String s = "";
        int index = this.things.size();

        for (Tg t: this.things) {
            if (index - 1 < 0) {
                s += t.toString();
            } else {
                s += t.toString() + "\n";
            }
        }

        return String.format("@%s\n%s", this.location, s);
    }
}

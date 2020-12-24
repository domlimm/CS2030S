/**
 * The Student class is a child of parent KeyableMap.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Lab 5
 */
public class Student extends KeyableMap<Module> {
    private final String name;

    public Student(String name) {
        super(name);
        this.name = name;
    }

    public String getKey() {
        return this.name;
    }

    @Override
    public Student put(Module module) {
        super.put(module);
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.name, super.toString());
    }
}

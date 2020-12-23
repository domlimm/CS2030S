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

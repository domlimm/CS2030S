/**
 * The Instructor class holds a variable name of type String and an overriden
 * equals method to compare Instructor objects.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Practical Assessment 1
 */
public class Instructor {
    private final String name;

    public Instructor(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Instructor) {
            Instructor i = (Instructor) obj;
            
            return this.name.equals(i.getName());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}

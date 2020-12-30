/**
 * The Lecture class extends parent LTInfo. It contains methods
 * that make comparisons between other LTInfo objects.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Practical Assessment 1
 */
public class Lecture extends LTInfo {
    private final char type;

    public Lecture(String code, int classId, String venueId, Instructor i, int start) {
        super(code, classId, venueId, i, start);
        this.type = 'L';
    }

    public char getType() {
        return this.type;
    }

    public boolean hasSameModule(LTInfo info) {
        return this.getCode().equals(info.getCode());
    }

    public boolean hasSameInstructor(LTInfo info) {
        return this.getInstructor().equals(info.getInstructor());
    }

    public boolean hasSameVenue(LTInfo info) {
        return this.getVenue().equals(info.getVenue());
    }

    public boolean clashWith(LTInfo info) {
        if (this.hasSameModule(info)) {
            char firstType = this.getType();
            char secondType = info.getType();
            int firstDuration = firstType == 'L' ? 2 : 1;
            int secondDuration = secondType == 'L' ? 2 : 1;

            if (firstType == secondType) {
                if (this.hasSameVenue(info) && this.getStart() == info.getStart()) {
                    return true;
                } else {
                    if (this.getStart() + 2 > info.getStart()) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                if (this.getStart() + firstDuration > info.getStart() ^
                        info.getStart() + secondDuration <= this.getStart()) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            if (this.getType() == info.getType()) {
                if (this.hasSameVenue(info) && this.getStart() == info.getStart()) {
                    return true;
                } else {
                    if (this.getStart() % 2 > info.getStart()) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }

        return false;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s @ %s [%s] %d--%d",
                this.getCode(),
                "" + this.getType() + this.getId(),
                this.getVenue(),
                this.getInstructor(),
                this.getStart(),
                this.getStart() + 2);
    }
}

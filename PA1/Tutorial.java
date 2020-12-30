public class Tutorial extends LTInfo {
    private final char type;
    
    public Tutorial(String code, int classId, String venueId, Instructor i, int start) {
        super(code, classId, venueId, i, start);
        this.type = 'T';
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
        if (this.getCode() == info.getCode()) {
            if (this.getType() != info.getType()) {
                if ((this.getStart() + 2 > info.getStart()) ||
                        info.getStart() % 2 > this.getStart()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                boolean instructor = this.hasSameInstructor(info);
                boolean venue = this.hasSameVenue(info);
                boolean time = this.getStart() == info.getStart();

                if (instructor && venue ^ !time) {
                    return true;
                } else if (venue && time) {
                    return true;
                } else if (instructor && time) {
                    return true;
                }
            }
        } else {
            if (this.getType() == info.getType()) {
                if (this.hasSameVenue(info) && this.hasSameInstructor(info)) {
                    return true;
                } else if (this.getStart() == info.getStart() &&
                        this.hasSameInstructor(info)) {
                    return true;
                }
                else {
                    return false;
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
                this.getStart() + 1);
    }
}

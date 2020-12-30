public abstract class LTInfo {
    private final String code;
    private final int classId;
    private final String venueId;
    private final int start;
    private final Instructor i;

    public LTInfo(String code, int classId, String venueId, Instructor i, int start) {
        this.code = code;
        this.classId = classId;
        this.venueId = venueId;
        this.i = i;
        this.start = start;
    }

    public String getCode() {
        return this.code;
    }

    public Instructor getInstructor() {
        return this.i;
    }

    public String getVenue() {
        return this.venueId;
    }

    public int getStart() {
        return this.start;
    }
       
    public int getId() {
        return this.classId;
    }

    public abstract boolean hasSameModule(LTInfo info);
    public abstract boolean hasSameInstructor(LTInfo info);
    public abstract boolean hasSameVenue(LTInfo info);
    public abstract boolean clashWith(LTInfo info);
    public abstract char getType();
}

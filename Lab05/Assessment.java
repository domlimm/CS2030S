public class Assessment implements Keyable {
    private final String test;
    private final String grade;

    public Assessment(String test, String grade) {
        this.test = test;
        this.grade = grade;
    }

    public String getKey() {
        return this.test;
    }

    public String getGrade() {
        return this.grade;
    }

    @Override
    public String toString() {
        return String.format("{%s: %s}",
                this.getKey(), this.getGrade());
    }
}

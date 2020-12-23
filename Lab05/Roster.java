public class Roster extends KeyableMap<Student> {
    private final String period;

    public Roster(String period) {
        super(period);
        this.period = period;
    } 

    @Override
    public Roster put(Student student) {
        super.put(student);
        return this;
    }

    public String getGrade(String id, String code, String assessment) {
        return this.get(id)
                .flatMap(s -> s.get(code))
                .flatMap(m -> m.get(assessment))
                .map(Assessment::getGrade)
                .orElse(String.format("%s: %s %s %s", "No such record", id, code, assessment));
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.period, super.toString());
    }
}

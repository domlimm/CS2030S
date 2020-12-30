import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Schedule {
    private final List<LTInfo> classes;

    public Schedule() {
        this.classes = new ArrayList<LTInfo>();
    }

    public Schedule(List<LTInfo> c) {
        this.classes = c;
    }

    public Schedule add(LTInfo newClass) {
        List<LTInfo> newClasses = new ArrayList<LTInfo>();

        for (LTInfo lti: this.classes) {
            if (lti.clashWith(newClass))
                return this;

            newClasses.add(lti);
        }

        newClasses.add(newClass);
        Collections.sort(newClasses, Comparator.comparing(LTInfo::getStart)
                .thenComparing(LTInfo::getType, Comparator.reverseOrder())
                .thenComparing(LTInfo::getCode)
                .thenComparing(LTInfo::getId));

        return new Schedule(newClasses);
    }

    @Override
    public String toString() {
        String s = "";

        for (LTInfo i: this.classes) {
            s += i.toString() + "\n";
        }

        return s;
    }
}

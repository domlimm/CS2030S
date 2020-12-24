import java.util.Scanner;

/**
 * The Main class receives the modules that students took in a period with their
 * respective grades.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Lab 5
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int records = sc.nextInt();
        sc.nextLine();
        Roster roster = new Roster("AY2021");

        while (records > 0) {
            String request = sc.nextLine();
            String[] data = request.split("\\s+");
            String id = data[0];
            String code = data[1];
            String test = data[2];
            String grade = data[3];

            roster.get(id).ifPresentOrElse(
                    s -> s.get(code).ifPresentOrElse(m -> m.put(new Assessment(test, grade)),
                            () -> s.put(new Module(code).put(new Assessment(test, grade)))),
                    () -> roster.put(new Student(id).put(new Module(code).put(new Assessment(test, grade)))));

            --records;
        }

        while (sc.hasNextLine()) {
            String query = sc.nextLine();
            String[] queryData = query.split("\\s+");
            System.out.println(roster.getGrade(queryData[0], queryData[1], queryData[2]));
        }

        sc.close();
    }
}

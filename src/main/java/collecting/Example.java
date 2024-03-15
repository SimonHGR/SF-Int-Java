package collecting;

import superiterable.Student;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Example {
  public static String getLetterGrade(double gpa) {
    if (gpa > 3.5) return "A";
    if (gpa > 3) return "B";
    if (gpa > 2) return "C";
    return "D";
  }

  public static void main(String[] args) {
    List<Student> roster = List.of(
        superiterable.Student.of("Fred", 3.2, "Math", "Physics"),
        superiterable.Student.of("Jim", 2.2, "Journalism"),
        superiterable.Student.of("Bill", 0),
        superiterable.Student.of("Sheila", 3.8, "Math", "Physics", "AstroPhysics", "Quantum Mechanics"),
        Student.of("Bob", 2.8, "Math", "Statistics", "AstroPhysics", "Quantum Mechanics")
    );

    Map<String, List<Student>> res = roster.stream()
        .collect(Collectors.groupingBy(s -> getLetterGrade(s.getGpa())));
//    System.out.println(res);
    res.entrySet().stream()
        .forEach(e -> System.out.println("students with grade "
            + e.getKey() + " are " + e.getValue()));

    System.out.println("--------------------------------");

    Map<String, String> res2 = roster.stream()
        .collect(Collectors.groupingBy(s -> getLetterGrade(s.getGpa()),
            Collectors.mapping(s -> s.getName() + " with grade " + s.getGpa(),
                Collectors.joining(", "))));
    res2.entrySet().stream()
        .forEach(e -> System.out.println("students with grade "
            + e.getKey() + " are " + e.getValue()));

  }
}

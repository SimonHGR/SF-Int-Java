package schoolv3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class School {

  // E is a "generic type parameter" -- it exists ONLY (mostly) at compilation time
  // accept the most general arguments that do what you need
  // specific as possible in your return types
  public static <E> List<E> filter(Iterable<E> ls, Predicate<E> crit) {
    List<E> res = new ArrayList<>();
    for (E s : ls) {
      if (crit.test(s)) {
        res.add(s);
      }
    }
    return res;
  }

  public static <E> void showAll(List<E> ls) {
    for (E s : ls) {
      System.out.println("> " + s);
    }
  }

  public static void main(String[] args) {
    List<Student> roster = List.of(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.2, "Journalism"),
        Student.of("Sheila", 3.8, "Math", "Physics", "AstroPhysics", "Quantum Mechanics"),
        Student.of("Bob", 2.8, "Math", "Physics", "AstroPhysics", "Quantum Mechanics")
    );

    showAll(filter(roster, s -> s.getCourses().size() < 3 ));
    // the following is inconsistent with the <E> stuff in the filter method declaration,
    // so it fails
//    showAll(filter(roster, (String s) -> s.length() < 3 ));

    Predicate<Student> smart = s -> s.getGpa() > 3;
    Predicate<Student> notEnthusiastic = s -> s.getCourses().size() < 3;
    System.out.println("smart and not enthusiastic --------------");
    showAll(filter(roster, smart.and(notEnthusiastic)));

    System.out.println("not smart and is enthusiastic --------------");
    showAll(filter(roster, smart.negate().and(notEnthusiastic.negate())));

    List<String> names = List.of("Alice", "Bob", "Charlie", "Denise", "Elana");
    showAll(filter(names, s -> s.length() > 3));
  }
}

package superiterable;

import java.util.List;
import java.util.function.UnaryOperator;

public class LabExercise {
  public static void main(String[] args) {
    SuperIterable<Student> sis = new SuperIterable<>(List.of(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.2, "Journalism"),
        Student.of("Bill", 0),
        Student.of("Sheila", 3.8, "Math", "Physics", "AstroPhysics", "Quantum Mechanics"),
        Student.of("Bob", 2.8, "Math", "Statistics", "AstroPhysics", "Quantum Mechanics")
    ));

    /*
    Using code that starts with:
    sis

    and ends with:
    .forEach(s -> System.out.println(s));
     */

    System.out.println("print all the students");
    sis
      .forEach(s -> System.out.println(s));


    System.out.println("print all the smart students");
    sis
        .filter(s -> s.getGpa() > 3)
        .forEach(s -> System.out.println(s));

    System.out.println("print the names of all the smart student");
    sis
        .filter(s -> s.getGpa() > 3)
        .map(s -> s.getName())
        .forEach(s -> System.out.println(s));

    // for all the non-smart students, print e.g. "Fred is not smart, has grade 3.2"
    sis
        .filter(s -> s.getGpa() < 3.5)
        .map(s -> s.getName() + " is not smart, has grade " + s.getGpa())
        .forEach(s -> System.out.println(s));


    // print all students in the form "final grade of Fred who takes 2 courses is 3.7"
    // after giving students a "bonus" of 0.5 gpa points, IF, but only if, they
    // take more than one course
    UnaryOperator<Student> gradeBump = s -> {
      if (s.getCourses().size() > 2) {
        return s.withGpa(s.getGpa() + 0.5);
      } else {
        return s;
      }
    };

    sis
        .map(gradeBump)
        .map(s -> String.format("final grade of %s who takes %d courses is %4.1f",
            s.getName(), s.getCourses().size(), s.getGpa()))
        .forEach(s -> System.out.println(s));

    System.out.println("all courses of all students");
    sis
//        .map(s -> s.getCourses())
        .flatMap(s -> new SuperIterable<>(s.getCourses()))
        .forEach(s -> System.out.println(s));
  }
}

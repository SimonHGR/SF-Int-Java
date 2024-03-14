package superiterable;

import java.util.List;

public class LabExercise {
  public static void main(String[] args) {
    SuperIterable<Student> sis = new SuperIterable<>(List.of(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.2, "Journalism"),
        Student.of("Sheila", 3.8, "Math", "Physics", "AstroPhysics", "Quantum Mechanics"),
        Student.of("Bob", 2.8, "Math", "Physics", "AstroPhysics", "Quantum Mechanics")
    ));

    /*
    Using code that starts with:
    sis

    and ends with:
    .forEach(s -> System.out.prinln(s));
     */

    // print all the students

    // print all the smart students

    // print the names of all the smart student

    // for all the non-smart students, print e.g. "Fred is not smart, has grade 3.2"

    // print all students in the form "final grade of Fred who takes 2 courses is 3.7"
    // after giving students a "bonus" of 0.5 gpa points, IF, but only if, they
    // take more than one course

  }
}

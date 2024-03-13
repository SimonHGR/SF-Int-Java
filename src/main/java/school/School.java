package school;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class School {

  public static void showAll(List<Student> ls) {
    for (Student s : ls) {
      System.out.println("> " + s);
    }
  }

  // this approach let's the caller "just ask" and unless the caller has access to the
  // set method, then caller is denied privilege of changing this
  // this is also unlikely to be threadsafe...
//  private static double threshold = 3.0;
//  public static void setThreshold(int t) {
//    // validate?
//    // validate authority?
//    School.threshold = threshold;
//  }
  // passing threshold as an argument imposes responsibility, and grants authority, to the *caller*
  // THIS FAILS SINGLE RESPONSIBILITY PRINCIPLE
  // don't selct and print in the same place
//  public static void showAllSmart(List<Student> ls, double threshold) {
//    for (Student s : ls) {
//      if (s.getGpa() > threshold) { // threshold is a double
//        System.out.println("> " + s);
//      }
//    }
//  }

  public static List<Student> getSmart(List<Student> ls, double threshold) {
    List<Student> res = new ArrayList<>();
    for (Student s : ls) {
      if (s.getGpa() > threshold) {
        res.add(s);
      }
    }
    return res;
  }

  public static void main(String[] args) {
//    List<Student> roster = new ArrayList(); // now add one at a time
    // Arrays.asList is Java 8, and should be replaced in newer code
//    List<Student> roster = Arrays.asList(
    // Java 9+ ... List.of is "unmodifiable"
    // cannot add, remove, set, or ...
    // but, if the List contains mutable objects (e.g. StringBuilder)
    // of course, the individual elements can be mutated
    List<Student> roster = List.of(
//        Student.of("Fred", 3.2, Arrays.asList("Math", "Physics")),
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.2, "Journalism"),
        Student.of("Sheila", 3.8, "Math", "Math", "Physics", "AstroPhysics", "Quantum Mechanics")
    );

    showAll(roster);
    System.out.println("-------------------------");
//    showAllSmart(roster, 3.0);
    showAll(getSmart(roster, 3.0));
    System.out.println("Very smart-------------------------");
    showAll(getSmart(roster, 3.5));
  }
}

package students2;

import students.Student;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@FunctionalInterface
interface Criterion<E> {
  boolean test(E s);
//  void doStuff();
}
//
//interface Something<E> {
//  void doSomething(E e);
//}

//class PrintSomething implements Something<Student> {
class PrintSomething implements Consumer<Student> {
  @Override
//  public void doSomething(Student student) {
  public void accept(Student student) {
    System.out.println("> " + student);
  }
}

public class School {
//  public static <E> void showAll(Iterable<E> ls) {
//    for (E s : ls) {
//      System.out.println("> " + s);
//    }
//    System.out.println("---------------------------");
//  }
//  public static <E> void withEvery(Iterable<E> ls, Something<E> someObject) {
  public static <E> void withEvery(Iterable<E> ls, Consumer<E> someObject) {
    for (E s : ls) {
//      someObject.doSomething(s);
      someObject.accept(s);
    }
  }

  public static <E> List<E> getByCriterion(Iterable<E> ls, Criterion<E> crit) {
    List<E> res = new ArrayList<>();
    for (E s : ls) {
      if (crit.test(s)) {
        res.add(s);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    List<Student> roster = List.of(
        Student.of("Fred", 3.4, "Math", "Physics"),
        Student.of("Jim", 2.4, "Journalism"),
        Student.of("Sheila", 3.9, "Math", "Physics", "Quantum Mechanics", "Astrophysics")
    );

    withEvery(roster, x -> System.out.println("> " + x));
//    withEvery(roster, (Student student) -> {
//      System.out.println("> " + student);
//    });

    // show smart students
    withEvery(getByCriterion(roster, s -> s.getGpa() > 3.0), s -> System.out.println(s));

    // show unenthusiastic students
//    showAll(getByCriterion(roster, ???));

//    List<String> names = List.of("Alex", "James", "Fred", "Susan", "Bill");
    List<String> names = Arrays.asList("Alex", "James", "Fred", "Susan", "Bill");

    // show long names
//    showAll(getByCriterion(names, ???));

    // show names in second half of alphabet
//    showAll(getByCriterion(names, ???));
  }
}

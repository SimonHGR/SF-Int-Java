package schoolv2;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface CriterionOfStudent {
  boolean test(Student s);
//  void doStuff();
}

class SmartCriterion implements CriterionOfStudent {
  private double threshold;

  public SmartCriterion(double threshold) {
    this.threshold = threshold;
  }

  @Override
  public boolean test(Student s) {
    return s.getGpa() > threshold;
  }
}

class EnthusiasticCriterion implements CriterionOfStudent {
  @Override
  public boolean test(Student s) {
    return s.getCourses().size() > 3;
  }
}

public class School {

  public static CriterionOfStudent and(CriterionOfStudent first, CriterionOfStudent second) {
    return s -> first.test(s) && second.test(s);
  }

  public static CriterionOfStudent and(CriterionOfStudent ... tests) {
    return s -> {
      for (CriterionOfStudent crit : tests) {
        if (!crit.test(s)) return false;
      }
      return true;
    };
  }

  // passing an object primarily for the behavior it contains is an GoF design pattern:
  // Command
  public static List<Student> getByCriterion(List<Student> ls, CriterionOfStudent crit) {
    List<Student> res = new ArrayList<>();
    for (Student s : ls) {
      if (crit.test(s)) {
        res.add(s);
      }
    }
    return res;
  }
  // these are the bits that change
//  s.getGpa() > 3.0
//  s.getCourses().size() > threshold

  public static void showAll(List<Student> ls) {
    for (Student s : ls) {
      System.out.println("> " + s);
    }
  }

  public static void main(String[] args) {
    List<Student> roster = List.of(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.2, "Journalism"),
        Student.of("Sheila", 3.8, "Math", "Math", "Physics", "AstroPhysics", "Quantum Mechanics")
    );

    showAll(roster);
    System.out.println("-------------------------");
    showAll(getByCriterion(roster, new SmartCriterion(3.0)));
    System.out.println("Very smart-------------------------");
    showAll(getByCriterion(roster, new SmartCriterion(3.6)));
    System.out.println("Enthusiastic ------------------------");
    showAll(getByCriterion(roster, new EnthusiasticCriterion()));
    System.out.println("Un-enthusiastic ------------------------");
    // Since Java 1.1 "Anonymous inner class"
    showAll(getByCriterion(roster, new CriterionOfStudent() {
      @Override
      public boolean test(Student s) {
        return s.getCourses().size() < 3;
      }
    }));
    System.out.println("Un-enthusiastic ------------------------");
    // second argument MUST BE "CriterionOfStudent"
    // also CriterionOfStudent is an interface -- THIS IS A REQUIREMENT
    // and there is EXACLY ONE abstract method in that interface
    // and we ONLY WANT TO IMPLEMENT THAT ONE method
    // (this syntax ONLY PERMITS one method)
    // So, we (and the compiler) KNOW THAT THE TARGET METHOD must be "test"
//    showAll(getByCriterion(roster, /*new CriterionOfStudent()*/ /*{*/
//      /*@Override
//      public boolean test*/(Student s) -> {
//        return s.getCourses().size() < 3;
//      }
//    /*}*/));
//    showAll(getByCriterion(roster, (Student s) -> {
//        return s.getCourses().size() < 3;
//      } ));

    // either specify type name for ALL formal params, or for none...
    showAll(getByCriterion(roster, (s) -> {
        return s.getCourses().size() < 3;
      } ));

    // or use var for ALL of them -- allows annotation without having to specify full typename
    showAll(getByCriterion(roster, (@Deprecated var s) -> {
        return s.getCourses().size() < 3;
      } ));

    // if, but only if, there is a single formal param without any "type" element, then
    // can leave out the parens
    showAll(getByCriterion(roster, s -> {
        return s.getCourses().size() < 3;
      } ));

    // INDEPENDENT of the formal param list stuff above, if you have a method body
    // that contains ONLY return expression ;
    // then you can leave out { } and return and ;
    showAll(getByCriterion(roster, s -> s.getCourses().size() < 3 ));

    CriterionOfStudent unenthusiastic = s -> s.getCourses().size() < 3;
    showAll(getByCriterion(roster, unenthusiastic));

    System.out.println("Testing by Student.getSmartnessCriterion(3.0)---------------");
    showAll(getByCriterion(roster, Student.getSmartnessCriterion(3.0)));

    System.out.println("Testing by Student.getSmartnessCriterion(3.5)---------------");
    showAll(getByCriterion(roster, Student.getSmartnessCriterion(3.5)));







    CriterionOfStudent funct;
    funct = (Student s) -> {
      return s.getCourses().size() < 3;
    };
    Class<?> cl = funct.getClass();
    System.out.println("class of funct is " + cl.getName());
    Method[] methods = cl.getMethods();
    for (Method m : methods) {
      System.out.println("> " + m);
    }

    CriterionOfStudent smart = s -> s.getGpa() > 3;
    CriterionOfStudent notEnthusiastic = s -> s.getCourses().size() < 3;
    System.out.println("smart and not enthusiastic --------------");
    showAll(getByCriterion(roster, s -> {
      return smart.test(s) && notEnthusiastic.test(s);
    }));
    System.out.println("smart and not enthusiastic --------------");
    showAll(getByCriterion(roster, and(smart, notEnthusiastic)));

    // Using only smart and notEnthusiastic as criteria...
    // write the "not" function that allows this code to work
    System.out.println("not smart and is enthusiastic --------------");
    showAll(getByCriterion(roster, and(not(smart), not(notEnthusiastic))));


  }
}

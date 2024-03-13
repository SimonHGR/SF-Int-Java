package enumstuff;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Benefits of enum:
 - typesafe
 - object identity
Disadvantage, must recompile to add new elements
 */
enum DayOfWeek {
  // enum values are just public static final fields in the enum class
  SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
  // private constructors, fields, static members, methods...
}

//enum Course {
//  // Changing the set of courses offered, requires RECOMPILATION (and therefore server restart)
//  MATH, PHYSICS, CHEMISTRY;
//}

// not serializable, must work harder if that's needed
// this is a kind of "dynamically extensible enum"
// get identity, type-safety (like enum) but can add more instances at runtime
// sometimes called "Flyweight" pattern
// should ONLY be used where the representation is "immutable" (i.e. it can be shared safely)
class Course {
  private String name;

  private Course(String name) {
    this.name = name;
  }

  private static Map<String, Course> items = new HashMap<>();

  public static Course of(String name) {
    name = name.toUpperCase();
    Course c = items.get(name);
    if (c == null) {
      c = new Course(name);
      items.put(name, c);
    }
    return c;
  }
}

public class Example {
  public static boolean isWeekend(int dayOfWeek) {
    // saturday is 7, Sunday is 1
    return dayOfWeek == 7 || dayOfWeek == 1;
  }

  public static boolean isWeekend(DayOfWeek dayOfWeek) {
    // saturday is 7, Sunday is 1
    return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
  }

  public static void main(String[] args) {
    System.out.println("days of week are " + Arrays.toString(DayOfWeek.values()));
    System.out.println("is this a weekday? " + (! isWeekend(99)));
    // always valid, no matter how you get the object reference
    System.out.println("is WEDNESDAY WEDNESDAY " + (DayOfWeek.WEDNESDAY == DayOfWeek.WEDNESDAY));

    Course math1 = Course.of("MATH");
    Course math2 = Course.of("Math");
    Course ubw = Course.of("Underwater Basket Weaving");
    System.out.println(math1 == math2);
    System.out.println(math1 == ubw);

    Map<String, Integer> msi = new HashMap<>();
    System.out.println(msi);
    msi.put("One", 1);
    msi.put("Un", 1);
    msi.put("Ten", 10);
    System.out.println(msi);

    String text = "One";
    Integer valueOfOne = msi.get(text);
    System.out.println("matching " + text + " I found " + valueOfOne);

    String s1 = "Hello";
    String s2 = "Hello";
    String s3 = "He";
    String s4 = s3 + "llo";

    System.out.println(s1 == s2);
    System.out.println(s1 == s4);
    System.out.println("------------");
    String s5 = s4.intern();
    System.out.println(s1 == s4);
    System.out.println(s1 == s5);
    System.out.println("------------");
    s4 = s4.intern();
    System.out.println(s1 == s4);
    System.out.println(s1 == s5);

    System.out.println("------------");
    s4.toUpperCase();
    System.out.println(s4);
    System.out.println(s4.toUpperCase());

    System.out.println("------------");
    s4 = s4.toUpperCase();
    System.out.println(s4);

    System.out.println("------------");
    StringBuilder sb1 = new StringBuilder("H1");
    StringBuilder sb2 = new StringBuilder("H1");
    System.out.println(sb2);
    sb2.append(" is now different");
    System.out.println(sb2);

    // reading an XML document? <Customer> is likely to be repeated MANY times
  }
}

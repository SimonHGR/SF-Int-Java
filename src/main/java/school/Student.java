package school;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

// all classes should start out final, until / unless
// you have really decided that they need to have subtypes
// keep in mind final -> not-final is a consequence-free change
// opposite is not so!
public final class Student {
   // making these fields final is not critical, nor sufficient, to immutability, but
   // a) makes it a little clearer that we're intending to be immutable
   // b) might allow the compiler/runtime to perform some optimizations
   // remember it's easy to remove final, but hard to add it after the field is already being changed!
   private final String name;
//   private Float gpa;
//   private float gpa; // int long double -- unless large arrays
   private final double gpa;
//   String[] courses; // arrays are clumsy (but perhaps more efficient)
   private final List<String> courses;
   // HashSet -- requires elements to implement equals and hashCode correctly
   // TreeSet -- requires element to be ordered, or requires a Comparator to provide orderin
   // and order must be "consistent with equality"
   // ALL primitive wrappers (e.g. Integer) and String work in either type
//   private Set<String> courses; // how do we determine uniqueness??

   // this (also default constructor) would leave the student uninitialized
   // (and therefore likely "invalid")
//   public Student() {
//      super();
//   }

   // private makes subtypes have to be nested, and prevents "new Student" from "outside"
   private Student(String name, double gpa, List<String> courses) {
//      super(); // implicit if omitted
      if(!isValid(name, gpa, courses)) {
         throw new IllegalArgumentException("Bad student values");
      }
      this.name = name;
      this.gpa = gpa;
      this.courses = courses;
   }
   // cannot do these two:
//   Student(String name, String parentName) {}
//   Student(String name, String bankName) {}
   // CAN do this (these can have different names, instead of being overloads):
//   public static Student ofNameParentName(String n1, String n2) { return null; }
//   public static Student ofNameBankName(String n1, String n2) { return null; }

   // provide factory (usually static) to instantiate
//   public static Student of(String name, double gpa, List<String> courses) {
   // String ... is an *array*
   public static Student of(String name, double gpa, String... courses) {
      return Student.of(name, gpa, List.of(courses));
   }

   public static Student of(String name, double gpa, List<String> courses) {
//      if (daddy is rich) {
//         return new VIPStudent(...)
//      } else
//      return new Student(name, gpa, Arrays.asList(courses));
      return new Student(name, gpa, List.copyOf(courses));
   }

   public static boolean isValid(String name, double gpa, List<String> courses) {
      return name != null && name.length() > 0 && gpa >= 0 && gpa <= 5.0
          && courses != null;
   }

   public String getName() {
      return name;
   }

   public double getGpa() {
      return gpa;
   }

   public List<String> getCourses() {
      return courses;
   }

   // Convention for "set" type methods in immutable data (for Java) is "with-ers"
   public Student withName(String name) {
      // Validation is in the constructor...
      if (!isValid(name, this.gpa, this.courses)) {
         throw new IllegalArgumentException("Bad name");
      }
      // reusing this.courses is safe because it's an IMMUTABLE list!!!
      // also, consider if we should use our own factory?
      return new Student(name, this.gpa, this.courses);
   }

   public Student withGpa(double gpa) {
      // Validation is in the constructor...
      return new Student(this.name, gpa, this.courses);
   }

   public Student withCourse(String course) {
      // Validation is in the constructor...
      List<String> moreCourses = new ArrayList<>(this.courses);
      moreCourses.add(course);
      // List.copyOf -- Java 10??
      return new Student(this.name, this.gpa, List.copyOf(moreCourses));
   }

   @Override
   public String toString() {
      return "Student{" +
          "name='" + name + '\'' +
          ", gpa=" + gpa +
          ", courses=" + courses +
          '}';
   }
}

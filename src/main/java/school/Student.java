package school;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

// all classes should start out final, until / unless
// you have really decided that they need to have subtypes
// keep in mind final -> not-final is a consequence-free change
// opposite is not so!
public final class Student {
   private String name;
//   private Float gpa;
//   private float gpa; // int long double -- unless large arrays
   private double gpa;
//   String[] courses; // arrays are clumsy (but perhaps more efficient)
   private List<String> courses;
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
//      if (daddy is rich) {
//         return new VIPStudent(...)
//      } else
//      return new Student(name, gpa, Arrays.asList(courses));
      return new Student(name, gpa, List.of(courses));
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

   @Override
   public String toString() {
      return "Student{" +
          "name='" + name + '\'' +
          ", gpa=" + gpa +
          ", courses=" + courses +
          '}';
   }
}

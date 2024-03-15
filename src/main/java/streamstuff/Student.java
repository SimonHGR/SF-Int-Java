package streamstuff;

import java.util.ArrayList;
import java.util.List;

public final class Student {
   private final String name;
   private final double gpa;
   private final List<String> courses;

   private Student(String name, double gpa, List<String> courses) {
      if(!isValid(name, gpa, courses)) {
         throw new IllegalArgumentException("Bad student values");
      }
      this.name = name;
      this.gpa = gpa;
      this.courses = courses;
   }

   public static Student of(String name, double gpa, String... courses) {
      return Student.of(name, gpa, List.of(courses));
   }

   public static Student of(String name, double gpa, List<String> courses) {
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

   public Student withName(String name) {
      if (!isValid(name, this.gpa, this.courses)) {
         throw new IllegalArgumentException("Bad name");
      }
      return new Student(name, this.gpa, this.courses);
   }

   public Student withGpa(double gpa) {
      return new Student(this.name, gpa, this.courses);
   }

   public Student withCourse(String course) {
      List<String> moreCourses = new ArrayList<>(this.courses);
      moreCourses.add(course);
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

import models.Student;
import models.Teacher;
import models.School;
import models.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) throws FileNotFoundException {

      School school = new School(new ArrayList<>());


      File studentFile = new File("src/student.txt");
      Scanner studentScanner = new Scanner(studentFile);

      while (studentScanner.hasNextLine()) {
         String line = studentScanner.nextLine();
         String[] parts = line.split(",");


         String name = parts[0].trim();
         String surname = parts[1].trim();
         int age = Integer.parseInt(parts[2].trim());
         boolean gender = parts[3].trim().equalsIgnoreCase("male");

         Student student = new Student();
         student.setName(name);
         student.setSurname(surname);
         student.setAge(age);
         student.setGender(gender);


         if (parts.length > 4) {
            String[] grades = parts[4].trim().split(" ");
            for (String grade : grades) {
               student.addGrade(Integer.parseInt(grade));
            }
         }


         school.addMember(student);
      }
      studentScanner.close();


      File teacherFile = new File("src/teacher.txt");
      Scanner teacherScanner = new Scanner(teacherFile);

      while (teacherScanner.hasNextLine()) {
         String line = teacherScanner.nextLine();
         String[] parts = line.split(",");


         String name = parts[0].trim();
         String surname = parts[1].trim();
         int age = Integer.parseInt(parts[2].trim());
         boolean gender = parts[3].trim().equalsIgnoreCase("male");
         String subject = parts[4].trim();
         int yearsOfExperience = Integer.parseInt(parts[5].trim());
         int salary = Integer.parseInt(parts[6].trim());

         Teacher teacher = new Teacher();
         teacher.setName(name);
         teacher.setSurname(surname);
         teacher.setAge(age);
         teacher.setGender(gender);
         teacher.setSubject(subject);
         teacher.setYearsOfExperience(yearsOfExperience);
         teacher.setSalary(salary);


         if (yearsOfExperience > 10) {
            teacher.giveRaise(10);
         }


         school.addMember(teacher);
      }
      teacherScanner.close();


      System.out.println("School Members:");
      System.out.println(school);


      System.out.println("******** GPA Calculation ********");
      for (Person member : school.getMembers()) {
         if (member instanceof Student) {
            Student student = (Student) member;
            System.out.println(student.getName() + "'s GPA: " + student.calculateGPA());
         }
      }
   }
}

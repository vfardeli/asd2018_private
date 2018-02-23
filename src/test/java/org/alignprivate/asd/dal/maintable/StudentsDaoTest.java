package org.alignprivate.asd.dal.maintable;

import java.util.List;

import org.alignprivate.asd.enumeration.Campus;
import org.alignprivate.asd.enumeration.DegreeCandidacy;
import org.alignprivate.asd.enumeration.EnrollmentStatus;
import org.alignprivate.asd.enumeration.Gender;
import org.alignprivate.asd.model.Students;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentsDaoTest {
  public static final int COUNT_OF_MALE = 5;
  private static final int COUNT_OF_FEMALE = 5;

  private StudentsDao studentdao;

  @Before
  public void init() {
    studentdao = new StudentsDao();
  }

  @Test
  public void addStudentTest() {
    Students newStudent = new Students("0000000","tomcat@gmail.com", "Tom", "",
            "Cat", Gender.M, "F1", "1111111111",
            "401 Terry Ave", "WA", "Seattle","98109",
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null);

    Students student = studentdao.addStudent(newStudent);
    Assert.assertTrue(student.toString().equals(newStudent.toString()));
    studentdao.deleteStudent("0000000");
  }

  @Test
  public void deleteStudentRecord() {
    studentdao.deleteStudent("0000000");
  }

  @Test
  public void getAllStudents() {
    List<Students> students = studentdao.getAllStudents();

    Students newStudent = new Students("0000000","tomcat@gmail.com", "Tom", "",
            "Cat", Gender.M, "F1", "1111111111",
            "401 Terry Ave", "WA", "Seattle", "98109",
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null);
    studentdao.addStudent(newStudent);
    List<Students> newStudents = studentdao.getAllStudents();
    Assert.assertTrue(newStudents.size() == students.size() + 1);
    studentdao.deleteStudent("0000000");

  }

  @Test
  public void getStudentRecord() {
    Students newStudent = new Students("0000000","tomcat@gmail.com", "Tom", "",
            "Cat", Gender.M, "F1", "1111111111",
            "401 Terry Ave", "WA", "Seattle", "98109",
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null);

    studentdao.addStudent(newStudent);
    Students student = studentdao.getStudentRecord("0000000");
    Assert.assertTrue(student.toString().equals(newStudent.toString()));
    studentdao.deleteStudent("0000000");

  }

  @Test
  public void countMaleStudents() {
    int males = studentdao.countMaleStudents();
    int females = studentdao.countFemaleStudents();
    List<Students> students = studentdao.getAllStudents();
    Assert.assertTrue(students.size() == males + females);
  }

  @Test
  public void searchSimilarStudents() {
    Students student = new Students("0000000","tomcat@gmail.com", "Tom", "",
            "Cat", Gender.M, "F1", "1111111111",
            "401 Terry Ave", "WA", "Seattle", "98109",
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null);
    studentdao.addStudent(student);

    List<Students> students = studentdao.searchSimilarStudents(DegreeCandidacy.MASTERS);

    for (Students s : students) {
      Assert.assertTrue(s.getDegree().name().equals("MASTERS"));
    }
  }

  @Test
  public void updateStudentRecord() {
    Students student = new Students("0000000","tomcat@gmail.com", "Tom", "",
            "Cat", Gender.M, "F1", "1111111111",
            "401 Terry Ave", "WA", "Seattle", "98109",
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null);

    studentdao.addStudent(student);
    student = studentdao.getStudentRecord("0000000");
    Assert.assertTrue(student.getAddress().equals("401 Terry Ave"));

    Students newStudent = new Students("0000000","tomcat@gmail.com", "Tom", "",
            "Cat", Gender.M, "F1", "1111111111",
            "225 Terry Ave", "WA", "Seattle", "98109",
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null);

    student = studentdao.updateStudentRecord(newStudent);
    Assert.assertTrue(student.getAddress().equals("225 Terry Ave"));

    studentdao.deleteStudent("0000000");
  }
}
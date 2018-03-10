package org.alignprivate.asd.dal.maintable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.alignprivate.asd.enumeration.*;
import org.alignprivate.asd.model.Courses;
import org.alignprivate.asd.model.Electives;
import org.alignprivate.asd.model.Students;

import org.alignprivate.asd.model.WorkExperiences;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StudentsDaoTest {
  public static final int COUNT_OF_MALE = 5;
  private static final int COUNT_OF_FEMALE = 5;

  private static StudentsDao studentdao;
  private static WorkExperiencesDao workExperiencesDao;
  private static ElectivesDao electivesDao;
  private static CoursesDao coursesDao;

  @BeforeClass
  public static void init() {
    studentdao = new StudentsDao();
    workExperiencesDao = new WorkExperiencesDao();
    electivesDao = new ElectivesDao();
    coursesDao = new CoursesDao();
  }

  @Test
  public void getAdminFilteredStudentsTest() throws ParseException {
    Students newStudent = new Students("0000000", "tomcat@gmail.com", "Tom", "",
            "Cat", Gender.M, "F1", "1111111111",
            "401 Terry Ave", "WA", "Seattle", "98109", Term.FALL, 2015,
            Term.SPRING, 2017,
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS, null, true);
    Students newStudent2 = new Students("1111111", "jerrymouse@gmail.com", "Jerry", "",
            "Mouse", Gender.M, "F1", "1111111111",
            "225 Terry Ave", "WA", "Seattle", "98109", Term.FALL, 2014,
            Term.SPRING, 2016,
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS, null, true);
    Students newStudent3 = new Students("2222222", "tomcat3@gmail.com", "Tom", "",
            "Dog", Gender.M, "F1", "1111111111",
            "401 Terry Ave", "WA", "Seattle", "98109", Term.FALL, 2015,
            Term.SPRING, 2017,
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS, null, true);
    studentdao.addStudent(newStudent);
    studentdao.addStudent(newStudent2);
    studentdao.addStudent(newStudent3);

    WorkExperiences newWorkExperience = new WorkExperiences();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    newWorkExperience.setStartDate(dateFormat.parse("2017-06-01"));
    newWorkExperience.setEndDate(dateFormat.parse("2017-12-01"));
    newWorkExperience.setCurrentJob(false);
    newWorkExperience.setTitle("Title");
    newWorkExperience.setDescription("Description");
    newWorkExperience.setNeuId("1111111");
    newWorkExperience.setCompanyName("Amazon");
    workExperiencesDao.createWorkExperience(newWorkExperience);

    // no filter case
    Assert.assertTrue(studentdao.getAdminFilteredStudents(new HashMap<>()).size() == 3);

    // first name = Tom
    List<String> firstName = new ArrayList<>();
    firstName.add("Tom");
    Map<String, List<String>> filters = new HashMap<>();
    filters.put("firstName", firstName);
    List<Students> students = studentdao.getAdminFilteredStudents(filters);
    Assert.assertTrue(students.size() == 2);
    Assert.assertTrue(students.get(0).getNeuId().equals("2222222"));
    Assert.assertTrue(students.get(1).getNeuId().equals("0000000"));

    // first name = Tom or Jerry, and company = Amazon
    Map<String, List<String>> filters2 = new HashMap<>();
    firstName.add("Jerry");
    List<String> companyName = new ArrayList<>();
    companyName.add("Amazon");
    filters2.put("firstName", firstName);
    filters2.put("companyName", companyName);
    List<Students> students2 = studentdao.getAdminFilteredStudents(filters2);
    Assert.assertTrue(students2.size() == 1);
    Assert.assertTrue(students2.get(0).getNeuId().equals("1111111"));

    // company = Apple
    List<String> companyName2 = new ArrayList<>();
    companyName2.add("Apple");
    Map<String, List<String>> filters3 = new HashMap<>();
    filters3.put("companyName", companyName2);
    Assert.assertTrue(studentdao.getAdminFilteredStudents(filters3).isEmpty());

    workExperiencesDao.deleteWorkExperienceById(
            workExperiencesDao.getWorkExperiencesByNeuId("1111111").get(0).getWorkExperienceId());
    studentdao.deleteStudent("2222222");
    studentdao.deleteStudent("1111111");
    studentdao.deleteStudent("0000000");
  }

  @Test
  public void addStudentTest() {
    Students newStudent = new Students("0000000", "tomcat@gmail.com", "Tom", "",
            "Cat", Gender.M, "F1", "1111111111",
            "401 Terry Ave", "WA", "Seattle", "98109", Term.FALL, 2015,
            Term.SPRING, 2017,
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS, null, true);

    Students student = studentdao.addStudent(newStudent);
    Assert.assertTrue(student.toString().equals(newStudent.toString()));
    studentdao.deleteStudent("0000000");
  }

  @Test
  public void deleteStudentRecord() {
    Students newStudent = new Students("0000000", "tomcat@gmail.com", "Tom", "",
            "Cat", Gender.M, "F1", "1111111111",
            "401 Terry Ave", "WA", "Seattle", "98109", Term.FALL, 2015,
            Term.SPRING, 2017,
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS, null, true);

    Students student = studentdao.addStudent(newStudent);
    Assert.assertTrue(student.toString().equals(newStudent.toString()));
    studentdao.deleteStudent("0000000");
    Assert.assertTrue(!studentdao.ifNuidExists("0000000"));
  }

  @Test
  public void getAllStudents() {
    List<Students> students = studentdao.getAllStudents();

    Students newStudent = new Students("0000000", "tomcat@gmail.com", "Tom", "",
            "Cat", Gender.M, "F1", "1111111111",
            "401 Terry Ave", "WA", "Seattle", "98109", Term.FALL, 2015,
            Term.SPRING, 2017,
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS, null, true);
    studentdao.addStudent(newStudent);
    List<Students> newStudents = studentdao.getAllStudents();
    Assert.assertTrue(newStudents.size() == students.size() + 1);
    studentdao.deleteStudent("0000000");

  }

  @Test
  public void getStudentRecord() {
    Students newStudent = new Students("0000000", "tomcat@gmail.com", "Tom", "",
            "Cat", Gender.M, "F1", "1111111111",
            "401 Terry Ave", "WA", "Seattle", "98109", Term.FALL, 2015,
            Term.SPRING, 2017,
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS, null, true);

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
    Students student = new Students("0000000", "tomcat@gmail.com", "Tom", "",
            "Cat", Gender.M, "F1", "1111111111",
            "401 Terry Ave", "WA", "Seattle", "98109", Term.FALL, 2015,
            Term.SPRING, 2017,
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS, null, true);
    studentdao.addStudent(student);

    List<Students> students = studentdao.searchSimilarStudents(DegreeCandidacy.MASTERS);

    for (Students s : students) {
      Assert.assertTrue(s.getDegree().name().equals("MASTERS"));
    }
  }

  @Test
  public void updateStudentRecord() {
    Students student = new Students("0000000", "tomcat@gmail.com", "Tom", "",
            "Cat", Gender.M, "F1", "1111111111",
            "401 Terry Ave", "WA", "Seattle", "98109", Term.FALL, 2015,
            Term.SPRING, 2017,
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS, null, true);

    studentdao.addStudent(student);
    student = studentdao.getStudentRecord("0000000");
    Assert.assertTrue(student.getAddress().equals("401 Terry Ave"));

    Students newStudent = new Students("0000000", "tomcat@gmail.com", "Tom", "",
            "Cat", Gender.M, "F1", "1111111111",
            "225 Terry Ave", "WA", "Seattle", "98109", Term.FALL, 2015,
            Term.SPRING, 2017,
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS, null, true);

//        Students newStudent = new Students("0000000","tomcat@gmail.com", "Tom", "",
//                "Cat", Gender.M, "F1", "1111111111",
//                "225 Terry Ave", "WA", "Seattle", "98109",
//                EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null);

    student = studentdao.updateStudentRecord(newStudent);
    Assert.assertTrue(student.getAddress().equals("225 Terry Ave"));

    studentdao.deleteStudent("0000000");
  }

    @Test
    public void getStudentFilteredStudents() throws Exception {
      Students newStudent = new Students("0000000", "tomcat@gmail.com", "Tom", "",
              "Cat", Gender.M, "F1", "1111111111",
              "401 Terry Ave", "WA", "Seattle", "98109", Term.FALL, 2015,
              Term.SPRING, 2017,
              EnrollmentStatus.FULL_TIME, Campus.BOSTON, DegreeCandidacy.MASTERS, null, true);
      Students newStudent2 = new Students("1111111", "jerrymouse@gmail.com", "Jerry", "",
              "Mouse", Gender.M, "F1", "1111111111",
              "225 Terry Ave", "WA", "Seattle", "98109", Term.FALL, 2014,
              Term.SPRING, 2016,
              EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS, null, true);
      Students newStudent3 = new Students("2222222", "tomcat3@gmail.com", "Tom", "",
              "Dog", Gender.M, "F1", "1111111111",
              "401 Terry Ave", "WA", "Seattle", "98109", Term.FALL, 2015,
              Term.SPRING, 2017,
              EnrollmentStatus.FULL_TIME, Campus.SILICON_VALLEY, DegreeCandidacy.MASTERS, null, true);
      studentdao.addStudent(newStudent);
      studentdao.addStudent(newStudent2);
      studentdao.addStudent(newStudent3);

      // no filter case
      Assert.assertTrue(studentdao.getStudentFilteredStudents(new HashMap<>()).size() == 3);

      Map<String, List<String>> map = new HashMap<>();

      // filter by campus
      List<String> campuses = new ArrayList<>();
      campuses.addAll(Arrays.asList(new String[] {"SEATTLE", "BOSTON"}));
      map.put("campus", campuses);
      Assert.assertTrue(studentdao.getStudentFilteredStudents(map).size() == 2);
      map.remove("campus");

      // filter by work experience - company name
      WorkExperiences newWorkExperience = new WorkExperiences();
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      newWorkExperience.setStartDate(dateFormat.parse("2017-06-01"));
      newWorkExperience.setEndDate(dateFormat.parse("2017-12-01"));
      newWorkExperience.setCurrentJob(false);
      newWorkExperience.setTitle("Title");
      newWorkExperience.setDescription("Description");
      newWorkExperience.setNeuId("1111111");
      newWorkExperience.setCompanyName("Amazon");
      workExperiencesDao.createWorkExperience(newWorkExperience);

      newWorkExperience.setNeuId("2222222");
      newWorkExperience.setCompanyName("Facebook");
      workExperiencesDao.createWorkExperience(newWorkExperience);

      List<String> companies = new ArrayList<>();
      companies.addAll(Arrays.asList(new String[] {"Amazon", "Facebook", "Google"}));
      map.put("companyName", companies);
      Assert.assertTrue(studentdao.getStudentFilteredStudents(map).size() == 2);
      map.remove("companyName");

      // filter by course name
      Courses newCourse1 = new Courses("5800", "Algorithm", "Algorithm");
      Courses newCourse2 = new Courses("5100", "AI", "AI");
      coursesDao.createCourse(newCourse1);
      coursesDao.createCourse(newCourse2);

      Electives newElective1 = new Electives("1111111", "5800", Term.SPRING,
              2018, false, 4, false);
      Electives newElective2 = new Electives("2222222", "5100", Term.SPRING,
              2018, false, 4, false);
      electivesDao.addElective(newElective1);
      electivesDao.addElective(newElective2);

      List<String> courses = new ArrayList<>();
      courses.addAll(Arrays.asList(new String[] {"Algorithm", "AI", "Database"}));
      map.put("courseName", courses);
      Assert.assertTrue(studentdao.getStudentFilteredStudents(map).size() == 2);
      map.remove("courseName");

      // filter by company name, course name, campus
      map.put("campus", campuses);
      map.put("companyName", companies);
      map.put("courseName", courses);
      Assert.assertTrue(studentdao.getStudentFilteredStudents(map).size() == 1);

      workExperiencesDao.deleteWorkExperienceById(
              workExperiencesDao.getWorkExperiencesByNeuId("1111111").get(0).getWorkExperienceId());
      workExperiencesDao.deleteWorkExperienceById(
              workExperiencesDao.getWorkExperiencesByNeuId("2222222").get(0).getWorkExperienceId());
      coursesDao.deleteCourseById("5800");
      coursesDao.deleteCourseById("5100");
      electivesDao.deleteElectiveRecord(electivesDao.getElectivesByNeuId("1111111").get(0).getElectiveId());
      electivesDao.deleteElectiveRecord(electivesDao.getElectivesByNeuId("2222222").get(0).getElectiveId());
      studentdao.deleteStudent("2222222");
      studentdao.deleteStudent("1111111");
      studentdao.deleteStudent("0000000");
    }
}

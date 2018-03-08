//package org.alignprivate.asd.dal.script;
//
//import org.alignprivate.asd.dal.maintable.StudentsDao;
//import org.alignprivate.asd.enumeration.*;
//import org.alignprivate.asd.model.Students;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//public class TotalFemaleStudentsDaoTest {
//  public static final int TOTAL_FEMALE_STUDENTS_TEST = 5;
//
//  TotalFemaleStudentsDao totalFemaleStudentsDao;
//  StudentsDao studentsDao;
//
//  @Before
//  public void init() throws Exception {
//    totalFemaleStudentsDao = TotalFemaleStudentsDao.getInstance();
//    studentsDao = new StudentsDao();
//  }
//
//  @Test
//  public void getTotalFemaleStudentsFromPrivateDatabaseTest() throws Exception {
//    int totalFemaleStudents = totalFemaleStudentsDao.getTotalFemaleStudentsFromPrivateDatabase();
//    Assert.assertTrue(totalFemaleStudents == TOTAL_FEMALE_STUDENTS_TEST);
//    Students newStudent = new Students("0000000","tomcat@gmail.com", "Tom", "",
//            "Cat", Gender.F, "F1", "1111111111",
//            "401 Terry Ave", "WA", "Seattle", "98109",
//            Term.FALL, 2014, Term.SPRING, 2016,
//            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null, true);
//
//    studentsDao.addStudent(newStudent);
//    totalFemaleStudents = totalFemaleStudentsDao.getTotalFemaleStudentsFromPrivateDatabase();
//    Assert.assertTrue(totalFemaleStudents == TOTAL_FEMALE_STUDENTS_TEST + 1);
//    studentsDao.deleteStudent("0000000");
//  }
//
//  @Test
//  public void updateTotalFemaleStudentsInPublicDatabase() throws Exception {
//    int totalFemaleStudents = totalFemaleStudentsDao.getTotalFemaleStudentsFromPrivateDatabase();
//
//    totalFemaleStudentsDao.updateTotalFemaleStudentsInPublicDatabase(
//            TOTAL_FEMALE_STUDENTS_TEST + 1);
//    Assert.assertTrue(totalFemaleStudentsDao.getTotalFemaleStudentsFromPublicDatabase()
//            == TOTAL_FEMALE_STUDENTS_TEST + 1);
//
//    totalFemaleStudentsDao.updateTotalFemaleStudentsInPublicDatabase(
//            totalFemaleStudents);
//    Assert.assertTrue(totalFemaleStudentsDao.getTotalFemaleStudentsFromPublicDatabase()
//            == totalFemaleStudents);
//  }
//}
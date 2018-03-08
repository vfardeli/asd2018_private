//package org.alignprivate.asd.dal.script;
//
//import org.alignprivate.asd.dal.maintable.StudentsDao;
//import org.alignprivate.asd.enumeration.*;
//import org.alignprivate.asd.model.Students;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class TotalStudentsWithScholarshipDaoTest {
//
//
//  public static final int TOTAL_STUDENTS_WITH_SCHOLARSHIP_TEST = 36;
//
//  TotalStudentsWithScholarshipDao totalStudentsWithScholarishipDao;
//  StudentsDao studentsDao;
//
//  @Before
//  public void init() throws Exception {
//    totalStudentsWithScholarishipDao = TotalStudentsWithScholarshipDao.getInstance();
//    studentsDao = new StudentsDao();
//  }
//
//  @Test
//  public void getTotalStudentsWithScholarshipFromPrivateDatabase() throws Exception {
//    int totalStudentWithScholarship = totalStudentsWithScholarishipDao.getTotalStudentsWithScholarshipFromPrivateDatabase();
//    Students newStudent = new Students("0000000", "tomcat@gmail.com", "Tom", "",
//            "Cat", Gender.M, "F1", "1111111111",
//            "401 Terry Ave", "WA", "Seattle", "98109",
//            Term.FALL, 2014, Term.SPRING, 2016,
//            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS, null, true);
//
//    newStudent.setScholarship(true);
//    studentsDao.addStudent(newStudent);
//    int totalStudentWithScholarship_incre = totalStudentsWithScholarishipDao.getTotalStudentsWithScholarshipFromPrivateDatabase();
//    studentsDao.deleteStudent("0000000");
//
//    Assert.assertTrue(totalStudentWithScholarship == totalStudentWithScholarship_incre - 1);
//  }
//
//  @Test
//  public void updateTotalStudentsWithScholarshipInPublicDatabase() throws Exception {
//    int totalStudentWithScholarship = totalStudentsWithScholarishipDao.getTotalStudentsWithScholarshipFromPublicDatabase();
//
//    totalStudentsWithScholarishipDao.updateTotalStudentsWithScholarshipInPublicDatabase(
//            TOTAL_STUDENTS_WITH_SCHOLARSHIP_TEST);
//    Assert.assertTrue(totalStudentsWithScholarishipDao.getTotalStudentsWithScholarshipFromPublicDatabase()
//            == TOTAL_STUDENTS_WITH_SCHOLARSHIP_TEST);
//
//    totalStudentsWithScholarishipDao.updateTotalStudentsWithScholarshipInPublicDatabase(
//            totalStudentWithScholarship);
//  }
//
//  @Test
//  public void getTotalStudentsWithScholarshipFromPublicDatabase() throws Exception {
//    int totalStudentWithScholarship = totalStudentsWithScholarishipDao.getTotalStudentsWithScholarshipFromPublicDatabase();
//
//    totalStudentsWithScholarishipDao.updateTotalStudentsWithScholarshipInPublicDatabase(
//            TOTAL_STUDENTS_WITH_SCHOLARSHIP_TEST);
//    Assert.assertTrue(totalStudentsWithScholarishipDao.getTotalStudentsWithScholarshipFromPublicDatabase()
//            == TOTAL_STUDENTS_WITH_SCHOLARSHIP_TEST);
//
//    totalStudentsWithScholarishipDao.updateTotalStudentsWithScholarshipInPublicDatabase(
//            totalStudentWithScholarship);
//  }
//}
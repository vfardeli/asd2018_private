package org.alignprivate.asd.dal.maintable;

import java.util.List;

import org.alignprivate.asd.enumeration.Campus;
import org.alignprivate.asd.enumeration.DegreeCandidacy;
import org.alignprivate.asd.enumeration.EnrollmentStatus;
import org.alignprivate.asd.enumeration.Gender;
import org.alignprivate.asd.enumeration.Term;
import org.alignprivate.asd.model.Courses;
import org.alignprivate.asd.model.Electives;
import org.alignprivate.asd.model.Students;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ElectiveDaoTest {
  private static ElectivesDao electivesDao;
  private static StudentsDao studentsDao;
  private static CoursesDao coursesDao;

  @BeforeClass
  public static void init() {
    electivesDao = new ElectivesDao();
    studentsDao = new StudentsDao();
    coursesDao = new CoursesDao();
  }

  @Test
  public void addNullElectivesTest() {
    Electives Electives = electivesDao.addElective(null);
    Assert.assertNull(Electives);
  }

//  @Test
//  public void getAllElectives() {
//    List<Electives> electivesList = electivesDao.getElectivesByNeuId();
//
//    System.out.println(electivesList.size());
//  }

  @Test
  public void addElectivesTest() {
    String tempId = "1221";

    Students newStudent = new Students(tempId, "tomcat78@gmail.com", "Tom3", "",
            "Cat", Gender.M, "F1", "1111111111",
            "401 Terry Ave", "WA", "Seattle", "98109",
            Term.FALL, 2014, Term.SPRING, 2016,
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS, null, true);

    studentsDao.addStudent(newStudent);

    Courses newCourse = new Courses(tempId + "", "course2", "course description 2");
    Courses courses = coursesDao.createCourse(newCourse);

//    Terms newTerm = new Terms(Term.FALL, tempId, TermType.QUARTER);
//    Terms term = termsDao.addTerm(newTerm);

    Electives elective = new Electives();
    elective.setNeuId(newStudent.getNeuId());
    elective.setCourseId(newCourse.getCourseId());
//    elective.setTerms(newTerm);
    elective.setRetake(false);
    elective.setGpa((float) 3.2);
    elective.setPlagiarism(false);

    Electives electivesNew = electivesDao.addElective(elective);

    electivesDao.deleteElectiveRecord(electivesNew.getElectiveId());
    coursesDao.deleteCourseById(tempId + "");
//    termsDao.deleteTerm(term.getTermId());
    studentsDao.deleteStudent(tempId + "");
  }

  @Test
  public void deleteElectivesTest() {
    String tempId = "289";

    List<Electives> experiencesOld = electivesDao.getElectivesByNeuId(tempId);
    int oldSize = experiencesOld.size();

    Students newStudent = new Students(tempId, "tomcat2e1kk3@gmail.com", "Tom3", "",
            "Cat", Gender.M, "F1", "1111111111",
            "401 Terry Ave", "WA", "Seattle", "98109",
            Term.FALL, 2014, Term.SPRING, 2016,
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS, null, true);

    studentsDao.addStudent(newStudent);

    Courses newCourse = new Courses(tempId + "", "course2", "course description 2");
    Courses courses = coursesDao.createCourse(newCourse);

//    Terms newTerm = new Terms(Term.FALL, tempId, TermType.QUARTER);
//    Terms term = termsDao.addTerm(newTerm);

    Electives elective = new Electives();
    elective.setNeuId(newStudent.getNeuId());
    elective.setCourseId(newCourse.getCourseId());
//    elective.setTerms(newTerm);
    elective.setRetake(false);
    elective.setGpa((float) 3.2);
    elective.setPlagiarism(false);

    Electives electivesNew = electivesDao.addElective(elective);
    electivesDao.deleteElectiveRecord(electivesNew.getElectiveId());

    List<Electives> electivessNew = electivesDao.getElectivesByNeuId(tempId);
    int newSize = electivessNew.size();
    Assert.assertEquals(oldSize, newSize);

    coursesDao.deleteCourseById(tempId + "");
//    termsDao.deleteTerm(term.getTermId());
    studentsDao.deleteStudent(tempId + "");
  }

  //
  @Test
  public void updateElectivesTest() {
    String tempId = "9187";

    Students newStudent = new Students(tempId, "tommcautty@gmail.com", "Tom3", "",
            "Cat", Gender.M, "F1", "1111111111",
            "401 Terry Ave", "WA", "Seattle", "98109",
            Term.FALL, 2014, Term.SPRING, 2016,
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS, null, true);

    studentsDao.addStudent(newStudent);

    Courses newCourse = new Courses(tempId + "", "course2", "course description 2");
    Courses courses = coursesDao.createCourse(newCourse);

//    Terms newTerm = new Terms(Term.FALL, tempId, TermType.QUARTER);
//    Terms term = termsDao.addTerm(newTerm);

    Electives elective = new Electives();
    elective.setNeuId(newStudent.getNeuId());
    elective.setCourseId(newCourse.getCourseId());
//    elective.setTerms(newTerm);
    elective.setRetake(false);
    elective.setGpa((float) 3.2);
    elective.setPlagiarism(false);

    Electives electivesNew = electivesDao.addElective(elective);

    electivesNew.setGpa((float) 4.2332);
    electivesDao.updateElectives(electivesNew);
    Assert.assertEquals(electivesNew.getGpa(), ((float) 4.2332), 0.1);

    electivesDao.deleteElectiveRecord(electivesNew.getElectiveId());
    coursesDao.deleteCourseById(tempId + "");
//    termsDao.deleteTerm(term.getTermId());
    studentsDao.deleteStudent(tempId + "");
  }


}

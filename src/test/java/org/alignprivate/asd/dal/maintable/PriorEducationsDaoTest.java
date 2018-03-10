package org.alignprivate.asd.dal.maintable;

import org.alignprivate.asd.enumeration.*;
import org.alignprivate.asd.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.*;

public class PriorEducationsDaoTest {
  private static PriorEducationsDao priorEducationsDao;
  private static StudentsDao studentsDao;

  @BeforeClass
  public static void init() {
    priorEducationsDao = new PriorEducationsDao();
    studentsDao = new StudentsDao();
  }

  @Before
  public void addDatabasePlaceholder() throws ParseException {
    Students student = new Students("001234567","tomcat@gmail.com", "Tom", "",
            "Cat", Gender.M, "F1", "1111111111",
            "401 Terry Ave", "WA", "Seattle", "98109",
            Term.FALL, 2014, Term.SPRING, 2016,
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null, true);
    Students student2 = new Students("111234567","jerrymouse@gmail.com", "Jerry", "",
            "Mouse", Gender.M, "F1", "1111111111",
            "401 Terry Ave", "WA", "Seattle", "98109",
            Term.FALL, 2014, Term.SPRING, 2016,
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null, true);
    studentsDao.addStudent(student);
    studentsDao.addStudent(student2);

    PriorEducations newPriorEducation = new PriorEducations();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    newPriorEducation.setGraduationDate(dateFormat.parse("2015-01-01"));
    newPriorEducation.setGpa(3.50f);
    newPriorEducation.setDegreeCandidacy(DegreeCandidacy.BACHELORS);
    newPriorEducation.setNeuId(student.getNeuId());
    newPriorEducation.setMajorName("Computer Science");
    newPriorEducation.setInstitutionName("University of Washington");

    priorEducationsDao.createPriorEducation(newPriorEducation);
  }

  @After
  public void deleteDatabasePlaceholder() {
    priorEducationsDao.deletePriorEducationById(
            priorEducationsDao.getPriorEducationsByNeuId("001234567").get(0).getPriorEducationId());
    studentsDao.deleteStudent("001234567");
    studentsDao.deleteStudent("111234567");

  }

  @Test
  public void getPriorEducationByIdTest() {
    int tempId = priorEducationsDao.getPriorEducationsByNeuId("001234567").get(0).getPriorEducationId();
    PriorEducations tempPriorEducation = priorEducationsDao.getPriorEducationById(tempId);
    assertTrue(tempPriorEducation.getNeuId().equals("001234567"));
    assertTrue(tempPriorEducation.getInstitutionName().equals("University of Washington"));
    assertTrue(tempPriorEducation.getGpa() == 3.50f);
    assertTrue(priorEducationsDao.getPriorEducationById(-20) == null);
  }

  @Test
  public void getPriorEducationsByNeuIdTest() {
    List<PriorEducations> listOfPriorEducation = priorEducationsDao.getPriorEducationsByNeuId("001234567");
    assertTrue(listOfPriorEducation.get(0).getInstitutionName().equals("University of Washington"));
    assertTrue(listOfPriorEducation.get(0).getMajorName().equals("Computer Science"));
    assertTrue(priorEducationsDao.getPriorEducationsByNeuId("000000000") == null);
  }

  @Test
  public void createUpdateDeletePriorEducation() throws ParseException {
    PriorEducations newPriorEducation = new PriorEducations();

    Students student = studentsDao.getStudentRecord("111234567");

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    newPriorEducation.setGraduationDate(dateFormat.parse("2015-01-01"));
    newPriorEducation.setGpa(4.00f);
    newPriorEducation.setDegreeCandidacy(DegreeCandidacy.BACHELORS);
    newPriorEducation.setNeuId(student.getNeuId());
    newPriorEducation.setMajorName("Accounting");
    newPriorEducation.setInstitutionName("Stanford University");

    // create new work experience
    priorEducationsDao.createPriorEducation(newPriorEducation);
    PriorEducations foundPriorEducation = priorEducationsDao.getPriorEducationsByNeuId("111234567").get(0);
    System.out.println(foundPriorEducation.getGpa());
    assertTrue(foundPriorEducation.getGpa() == 4.00f);
    assertTrue(foundPriorEducation.getInstitutionName().equals("Stanford University"));

    // update found work experience
    foundPriorEducation.setGpa(3.99f);
    priorEducationsDao.updatePriorEducation(foundPriorEducation);
    assertTrue(priorEducationsDao.getPriorEducationsByNeuId("111234567").get(0).getGpa() == 3.99f);
    newPriorEducation.setPriorEducationId(-100);
    assertFalse(priorEducationsDao.updatePriorEducation(newPriorEducation));

    // delete the work experience
    priorEducationsDao.deletePriorEducationById(foundPriorEducation.getPriorEducationId());
    assertTrue(priorEducationsDao.getPriorEducationById(foundPriorEducation.getPriorEducationId()) == null);
    assertFalse(priorEducationsDao.deletePriorEducationById(-100));
  }
}
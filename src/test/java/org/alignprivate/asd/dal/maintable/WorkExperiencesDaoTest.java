package org.alignprivate.asd.dal.maintable;

import org.alignprivate.asd.enumeration.*;
import org.alignprivate.asd.model.Students;
import org.alignprivate.asd.model.WorkExperiences;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.*;

public class WorkExperiencesDaoTest {
  private static WorkExperiencesDao workExperiencesDao;
  private static StudentsDao studentsDao;
//  private static CompaniesDao companiesDao;

  @BeforeClass
  public static void init() {
    workExperiencesDao = new WorkExperiencesDao();
    studentsDao = new StudentsDao();
//    companiesDao = new CompaniesDao();
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

    WorkExperiences newWorkExperience = new WorkExperiences();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    newWorkExperience.setStartDate(dateFormat.parse("2017-06-01"));
    newWorkExperience.setEndDate(dateFormat.parse("2017-12-01"));
    newWorkExperience.setCurrentJob(false);
    newWorkExperience.setTitle("Title");
    newWorkExperience.setDescription("Description");
    newWorkExperience.setNeuId(student.getNeuId());
    newWorkExperience.setCompanyName("Amazon");
    workExperiencesDao.createWorkExperience(newWorkExperience);
  }

  @After
  public void deleteDatabasePlaceholder() {
    workExperiencesDao.deleteWorkExperienceById(
            workExperiencesDao.getWorkExperiencesByNeuId("001234567").get(0).getWorkExperienceId());
    studentsDao.deleteStudent("001234567");
    studentsDao.deleteStudent("111234567");
  }

  @Test
  public void getWorkExperienceIdTest() {
    int tempId = workExperiencesDao.getWorkExperiencesByNeuId("001234567").get(0).getWorkExperienceId();
    WorkExperiences workExperience1 = workExperiencesDao.getWorkExperienceById(tempId);
    assertTrue(workExperience1.getNeuId().equals("001234567"));
    assertTrue(workExperience1.getCompanyName().equals("Amazon"));
    WorkExperiences notFoundWorkExperience = workExperiencesDao.getWorkExperienceById(-10);
    assertTrue(notFoundWorkExperience == null);
  }

  @Test
  public void getWorkExperiencesByNeuIdTest() {
    List<WorkExperiences> listOfWorkExperiences = workExperiencesDao.getWorkExperiencesByNeuId("001234567");
    assertTrue(listOfWorkExperiences.get(0).getCompanyName().equals("Amazon"));

    assertTrue(workExperiencesDao.getWorkExperiencesByNeuId("00000000") == null);
  }

  @Test
  public void createUpdateDeleteWorkExperience() throws ParseException {
    WorkExperiences newWorkExperience = new WorkExperiences();

    Students student = studentsDao.getStudentRecord("111234567");

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    newWorkExperience.setStartDate(dateFormat.parse("2017-06-01"));
    newWorkExperience.setEndDate(dateFormat.parse("2017-12-01"));
    newWorkExperience.setCurrentJob(false);
    newWorkExperience.setTitle("Title");
    newWorkExperience.setDescription("Description");
    newWorkExperience.setNeuId(student.getNeuId());
    newWorkExperience.setCompanyName("Facebook");

    // create new work experience
    workExperiencesDao.createWorkExperience(newWorkExperience);
    WorkExperiences foundWorkExperience = workExperiencesDao.getWorkExperiencesByNeuId("111234567").get(0);
    assertTrue(foundWorkExperience.getCompanyName().equals("Facebook"));

    // update found work experience
    foundWorkExperience.setDescription("Description2");
    workExperiencesDao.updateWorkExperience(foundWorkExperience);
    assertTrue(workExperiencesDao.getWorkExperiencesByNeuId("111234567").get(0).getDescription().equals("Description2"));
    newWorkExperience.setWorkExperienceId(-100);
    assertFalse(workExperiencesDao.updateWorkExperience(newWorkExperience));

    // delete the work experience
    workExperiencesDao.deleteWorkExperienceById(foundWorkExperience.getWorkExperienceId());
    assertTrue(workExperiencesDao.getWorkExperienceById(foundWorkExperience.getWorkExperienceId()) == null);
    assertFalse(workExperiencesDao.deleteWorkExperienceById(-100));
  }
}
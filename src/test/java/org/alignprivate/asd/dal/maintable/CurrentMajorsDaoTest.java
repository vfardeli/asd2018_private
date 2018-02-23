package org.alignprivate.asd.dal.maintable;

import org.alignprivate.asd.enumeration.DegreeCandidacy;
import org.alignprivate.asd.model.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.*;

public class CurrentMajorsDaoTest {
  private static CurrentMajorsDao currentMajorsDao;

  @BeforeClass
  public static void init() {
    currentMajorsDao = new CurrentMajorsDao();
  }

  @Test
  public void getCurrentMajorByIdTest() {
    CurrentMajors currentMajors = currentMajorsDao.getCurrentMajorById(1);
    assertTrue(currentMajors.getMajor().getMajor().equals("Computer Science"));
    assertTrue(currentMajors.getStudent().getLastName().equals("Kwan"));

    // get non existent current major
    assertTrue(currentMajorsDao.getCurrentMajorById(-100) == null);
  }

  @Test
  public void getCurrentMajorsByNeuIdTest() {
    List<CurrentMajors> listOfCurrentMajor = currentMajorsDao.getCurrentMajorsByNeuId("001234543");
    assertTrue(listOfCurrentMajor.get(0).getMajor().getMajor().equals("Computer Science"));

    // get a current major for non existent neu id
    assertTrue(currentMajorsDao.getCurrentMajorsByNeuId("000000000") == null);
  }

  @Test
  public void createUpdateDeleteCurrentMajor() {
    CurrentMajors newCurrentMajor = new CurrentMajors();
    StudentsDao studentsDao = new StudentsDao();
    MajorsDao majorsDao = new MajorsDao();

    Students student = studentsDao.getStudentRecord("001234567");
    Majors major = majorsDao.getMajorByName("English");

    newCurrentMajor.setStudent(student);
    newCurrentMajor.setMajor(major);

    // create new current major
    currentMajorsDao.createCurrentMajor(newCurrentMajor);
    CurrentMajors foundCurrentMajor = currentMajorsDao.getCurrentMajorsByNeuId("001234567").get(1);
    assertTrue(foundCurrentMajor.getMajor().getMajor().equals("English"));
    assertTrue(foundCurrentMajor.getStudent().getLastName().equals("Kwan"));

    // update found current major
    foundCurrentMajor.setMajor(majorsDao.getMajorByName("Accounting"));
    currentMajorsDao.updateCurrentMajor(foundCurrentMajor);
    assertTrue(currentMajorsDao.getCurrentMajorsByNeuId("001234567").get(1).getMajor().getMajor().equals("Accounting"));
    newCurrentMajor.setCurrentMajorId(-100);
    assertFalse(currentMajorsDao.updateCurrentMajor(newCurrentMajor));

    // delete the work experience
    currentMajorsDao.deleteCurrentMajorById(foundCurrentMajor.getCurrentMajorId());
    assertTrue(currentMajorsDao.getCurrentMajorById(foundCurrentMajor.getCurrentMajorId()) == null);
    assertFalse(currentMajorsDao.deleteCurrentMajorById(-100));
  }
}
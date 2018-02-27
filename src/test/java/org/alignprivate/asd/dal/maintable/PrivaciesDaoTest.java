package org.alignprivate.asd.dal.maintable;

import org.alignprivate.asd.model.Privacies;
import org.alignprivate.asd.model.Students;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrivaciesDaoTest {
    private static PrivaciesDao privaciesDao;
    private static StudentsDao studentsDao;

    @BeforeClass
    public static void init() {
        privaciesDao = new PrivaciesDao();
        studentsDao = new StudentsDao();
    }

    @Test
    public void addPrivacyRecordTest() {
        Students student = studentsDao.getStudentRecord("001234567");
        Privacies privacy = new Privacies(student, true, true, true,
                true, true, true, true, true,
                true, true, true, true, true);

        privaciesDao.addPrivacyRecord(privacy);
        Assert.assertTrue(privaciesDao.ifNuidExists("001234567"));
        privaciesDao.deletePrivacyRecord(privacy);
    }

    @Test
    public void getPrivacyRecordByNeuIdTest() {
        Students student = studentsDao.getStudentRecord("001234567");
        Privacies privacy = new Privacies(student, true, true, true,
                true, true, true, true, true,
                true, true, true, true, true);

        privaciesDao.addPrivacyRecord(privacy);
        Assert.assertTrue(privaciesDao.getPrivacyRecordByNeuId("001234567").isZipShown());
        privaciesDao.deletePrivacyRecord(privacy);
    }

    @Test
    public void updatePrivacyRecordTest() {
        Students student = studentsDao.getStudentRecord("001234567");
        Privacies privacy = new Privacies(student, true, true, true,
                true, true, true, true, true,
                true, true, true, true, true);

        privaciesDao.addPrivacyRecord(privacy);

        privacy.setAddressShown(false);
        privaciesDao.updatePrivacyRecord(privacy);

        Assert.assertTrue(!privaciesDao.getPrivacyRecordByNeuId("001234567").isAddressShown());
        privaciesDao.deletePrivacyRecord(privacy);
    }

    @Test
    public void deletePrivacyRecord() {
        Students student = studentsDao.getStudentRecord("001234567");
        Privacies privacy = new Privacies(student, true, true, true,
                true, true, true, true, true,
                true, true, true, true, true);

        privaciesDao.addPrivacyRecord(privacy);
        Assert.assertTrue(privaciesDao.ifNuidExists("001234567"));
        privaciesDao.deletePrivacyRecord(privacy);
        Assert.assertTrue(!privaciesDao.ifNuidExists("001234567"));
    }

    @Test
    public void ifNuidExists() {
        Students student = studentsDao.getStudentRecord("001234567");
        Privacies privacy = new Privacies(student, true, true, true,
                true, true, true, true, true,
                true, true, true, true, true);

        privaciesDao.addPrivacyRecord(privacy);
        Assert.assertTrue(privaciesDao.ifNuidExists("001234567"));
        privaciesDao.deletePrivacyRecord(privacy);
        Assert.assertTrue(!privaciesDao.ifNuidExists("001234567"));
    }
}
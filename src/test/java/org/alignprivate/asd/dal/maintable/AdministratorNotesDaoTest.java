package org.alignprivate.asd.dal.maintable;

import org.alignprivate.asd.model.AdministratorNotes;
import org.alignprivate.asd.model.Administrators;
import org.alignprivate.asd.model.Students;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AdministratorNotesDaoTest {
    private static AdministratorNotesDao administratorNotesDao;
    private static StudentsDao studentsDao;
    private static AdministratorsDao adminDao;

    @BeforeClass
    public static void init() {
        studentsDao = new StudentsDao();
        adminDao = new AdministratorsDao();
        administratorNotesDao = new AdministratorNotesDao();
    }

    @Test
    public void getAdministratorNoteRecordtTest() {
        Students student = studentsDao.getStudentRecord("001234567");
        Administrators admin = adminDao.getAdministratorRecord("123456789");
        AdministratorNotes note = new AdministratorNotes(student, admin, "TEST", "TEST");
        administratorNotesDao.addAdministratorNoteRecord(note);

        List<AdministratorNotes> notes = administratorNotesDao.getAdministratorNoteRecordByNeuId("001234567");
        for (AdministratorNotes n : notes)
            Assert.assertTrue(n.getTitle().equals("TEST"));

        administratorNotesDao.deleteAdministratorNoteRecord(note);
    }

    @Test
    public void addAdministratorNoteRecordTest() {
        Students student = studentsDao.getStudentRecord("001234567");
        Administrators admin = adminDao.getAdministratorRecord("123456789");
        AdministratorNotes note = new AdministratorNotes(student, admin, "TEST", "TEST");
        administratorNotesDao.addAdministratorNoteRecord(note);

        Assert.assertTrue(administratorNotesDao.ifNuidExists("001234567"));
        administratorNotesDao.deleteAdministratorNoteRecord(note);
        Assert.assertTrue(!administratorNotesDao.ifNuidExists("001234567"));
    }

    @Test
    public void deleteAdministratorNoteRecordTest() {
        Students student = studentsDao.getStudentRecord("001234567");
        Administrators admin = adminDao.getAdministratorRecord("123456789");
        AdministratorNotes note = new AdministratorNotes(student, admin, "TEST", "TEST");
        administratorNotesDao.addAdministratorNoteRecord(note);
        administratorNotesDao.deleteAdministratorNoteRecord(note);
        Assert.assertTrue(!administratorNotesDao.ifNuidExists("001234567"));
    }

    @Test
    public void ifNuidExistsTest() {
        Students student = studentsDao.getStudentRecord("001234567");
        Administrators admin = adminDao.getAdministratorRecord("123456789");
        AdministratorNotes note = new AdministratorNotes(student, admin, "TEST", "TEST");
        administratorNotesDao.addAdministratorNoteRecord(note);
        Assert.assertTrue(administratorNotesDao.ifNuidExists("001234567"));
        administratorNotesDao.deleteAdministratorNoteRecord(note);
        Assert.assertTrue(!administratorNotesDao.ifNuidExists("001234567"));
    }
}
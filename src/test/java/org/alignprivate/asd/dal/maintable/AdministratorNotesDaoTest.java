package org.alignprivate.asd.dal.maintable;

import org.alignprivate.asd.model.AdministratorNotes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AdministratorNotesDaoTest {
    private static AdministratorNotesDao administratorNotesDao;

    @BeforeClass
    public static void init() {
        administratorNotesDao = new AdministratorNotesDao();
    }

    @Test
    public void getAdministratorNoteRecordtTest() {
        AdministratorNotes note = new AdministratorNotes("001234567", "123456789", "TEST", "TEST");
        administratorNotesDao.addAdministratorNoteRecord(note);

        List<AdministratorNotes> notes = administratorNotesDao.getAdministratorNoteRecordByNeuId("001234567");
        for (AdministratorNotes n : notes)
            Assert.assertTrue(n.getNeuId().equals("001234567"));

        administratorNotesDao.deleteAdministratorNoteRecord(note);
    }

    @Test
    public void addAdministratorNoteRecordTest() {
        AdministratorNotes note = new AdministratorNotes("001234567", "123456789", "TEST", "TEST");
        administratorNotesDao.addAdministratorNoteRecord(note);
        Assert.assertTrue(administratorNotesDao.ifNuidExists("001234567"));
        administratorNotesDao.deleteAdministratorNoteRecord(note);
        Assert.assertTrue(!administratorNotesDao.ifNuidExists("001234567"));
    }

    @Test
    public void deleteAdministratorNoteRecordTest() {
        AdministratorNotes note = new AdministratorNotes("001234567", "123456789", "TEST", "TEST");
        administratorNotesDao.deleteAdministratorNoteRecord(note);
        Assert.assertTrue(!administratorNotesDao.ifNuidExists("001234567"));
    }

    @Test
    public void ifNuidExistsTest() {
        AdministratorNotes note = new AdministratorNotes("001234567", "123456789", "TEST", "TEST");
        administratorNotesDao.addAdministratorNoteRecord(note);
        Assert.assertTrue(administratorNotesDao.ifNuidExists("001234567"));
        administratorNotesDao.deleteAdministratorNoteRecord(note);
        Assert.assertTrue(!administratorNotesDao.ifNuidExists("001234567"));
    }
}
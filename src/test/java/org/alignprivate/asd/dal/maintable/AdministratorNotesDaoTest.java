package org.alignprivate.asd.dal.maintable;

import org.alignprivate.asd.model.AdministratorNotes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdministratorNotesDaoTest {
    AdministratorNotesDao administratorNotesDao;

    @Before
    public void init() {
        administratorNotesDao = new AdministratorNotesDao();
    }

    @Test
    public void getAdministratorNoteRecordtTest() {
        AdministratorNotes note = new AdministratorNotes("001234567", "123456789", "TEST", "TEST");
        administratorNotesDao.addAdministratorNoteRecord(note);

        note = administratorNotesDao.getAdministratorNoteRecord("001234567");
        Assert.assertTrue(note.getTitle().equals("TEST"));
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
    public void updateAdministratorNoteRecordTest() {
        AdministratorNotes note = new AdministratorNotes("001234567", "123456789", "TEST", "TEST");
        administratorNotesDao.addAdministratorNoteRecord(note);

        note.setDesc("NEW TEST");

        administratorNotesDao.updateAdministratorNoteRecord(note);
        AdministratorNotes newNote = administratorNotesDao.getAdministratorNoteRecord("001234567");
        Assert.assertTrue(newNote.getDesc().equals("NEW TEST"));
        administratorNotesDao.deleteAdministratorNoteRecord(newNote);
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
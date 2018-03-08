package org.alignprivate.asd.dal.maintable;

import org.alignprivate.asd.enumeration.*;
import org.alignprivate.asd.model.AdministratorNotes;
import org.alignprivate.asd.model.Administrators;
import org.alignprivate.asd.model.Students;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class AdministratorNotesDaoTest {
    private static AdministratorNotesDao administratorNotesDao;
    private static AdministratorsDao administratorsDao;
    private static StudentsDao studentsDao;

    @BeforeClass
    public static void init() {
        administratorNotesDao = new AdministratorNotesDao();
        administratorsDao = new AdministratorsDao();
        studentsDao = new StudentsDao();
    }

    @Before
    public void addDatabasePlaceholder() {
        Students student = new Students("001234567","tomcat@gmail.com", "Tom", "",
                "Cat", Gender.M, "F1", "1111111111",
                "401 Terry Ave", "WA", "Seattle", "98109",
                Term.FALL, 2014, Term.SPRING, 2016,
                EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null, true);
        Administrators newAdministrator = new Administrators("123456789", "john.stewart@gmail.com",
                "John", "Main", "Stewart");
        administratorsDao.addAdministrator(newAdministrator);
        studentsDao.addStudent(student);
    }

    @After
    public void deleteDatabasePlaceholder() {
        administratorsDao.deleteAdministrator("123456789");
        studentsDao.deleteStudent("001234567");
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
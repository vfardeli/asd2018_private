package org.alignprivate.asd.dal.maintable;

import org.alignprivate.asd.enumeration.*;
import org.alignprivate.asd.model.AdministratorNotes;
import org.alignprivate.asd.model.Administrators;
import org.alignprivate.asd.model.Students;
import org.junit.*;

import java.util.List;

public class AdministratorNotesDaoTest {
    private static AdministratorNotesDao administratorNotesDao;
    private static StudentsDao studentsDao;
    private static AdministratorsDao adminDao;

    @BeforeClass
    public static void init() {
        studentsDao = new StudentsDao();
        adminDao = new AdministratorsDao();
        administratorNotesDao = new AdministratorNotesDao();
        adminDao = new AdministratorsDao();
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
        adminDao.addAdministrator(newAdministrator);
        studentsDao.addStudent(student);
    }

    @After
    public void deleteDatabasePlaceholder() {
        adminDao.deleteAdministrator("123456789");
        studentsDao.deleteStudent("001234567");
    }

    @Test
    public void getAdministratorNoteRecordtTest() {
        Students student = studentsDao.getStudentRecord("001234567");
        Administrators admin = adminDao.getAdministratorRecord("123456789");
        AdministratorNotes note = new AdministratorNotes(student.getNeuId(), admin.getAdministratorNeuId(), "TEST", "TEST");
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
        AdministratorNotes note = new AdministratorNotes(student.getNeuId(), admin.getAdministratorNeuId(), "TEST", "TEST");
        administratorNotesDao.addAdministratorNoteRecord(note);

        Assert.assertTrue(administratorNotesDao.ifNuidExists("001234567"));
        administratorNotesDao.deleteAdministratorNoteRecord(note);
        Assert.assertTrue(!administratorNotesDao.ifNuidExists("001234567"));
    }

    @Test
    public void deleteAdministratorNoteRecordTest() {
        Students student = studentsDao.getStudentRecord("001234567");
        Administrators admin = adminDao.getAdministratorRecord("123456789");
        AdministratorNotes note = new AdministratorNotes(student.getNeuId(), admin.getAdministratorNeuId(), "TEST", "TEST");
        administratorNotesDao.addAdministratorNoteRecord(note);
        administratorNotesDao.deleteAdministratorNoteRecord(note);
        Assert.assertTrue(!administratorNotesDao.ifNuidExists("001234567"));
    }

    @Test
    public void ifNuidExistsTest() {
        Students student = studentsDao.getStudentRecord("001234567");
        Administrators admin = adminDao.getAdministratorRecord("123456789");
        AdministratorNotes note = new AdministratorNotes(student.getNeuId(), admin.getAdministratorNeuId(), "TEST", "TEST");
        administratorNotesDao.addAdministratorNoteRecord(note);
        Assert.assertTrue(administratorNotesDao.ifNuidExists("001234567"));
        administratorNotesDao.deleteAdministratorNoteRecord(note);
        Assert.assertTrue(!administratorNotesDao.ifNuidExists("001234567"));
    }
}
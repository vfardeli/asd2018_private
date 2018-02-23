package org.alignprivate.asd.dal.script;

import org.alignprivate.asd.dal.maintable.StudentsDao;
import org.alignprivate.asd.enumeration.Campus;
import org.alignprivate.asd.enumeration.DegreeCandidacy;
import org.alignprivate.asd.enumeration.EnrollmentStatus;
import org.alignprivate.asd.enumeration.Gender;
import org.alignprivate.asd.model.Students;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TotalStudentsInSeattleDaoTest {
    public static final int TOTAL_STUDENTS_IN_SEATTLE_TEST = 36;

    TotalStudentsInSeattleDao totalStudentsInSeattleDao;
    StudentsDao studentsDao;

    @Before
    public void init() throws Exception {
        totalStudentsInSeattleDao = TotalStudentsInSeattleDao.getInstance();
        studentsDao = new StudentsDao();
    }

    @Test
    public void getTotalStudentsInSeattleFromPrivateDatabaseTest() throws Exception {
        int totalStudentInSeattle = totalStudentsInSeattleDao.getTotalStudentsInSeattleFromPrivateDatabase();
        Students newStudent = new Students("0000000","tomcat@gmail.com", "Tom", "",
                "Cat", Gender.M, "F1", "1111111111",
                "401 Terry Ave", "WA", "Seattle", "98109",
                EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null);

        studentsDao.addStudent(newStudent);
        int totalStudentInSeattle_incre = totalStudentsInSeattleDao.getTotalStudentsInSeattleFromPrivateDatabase();
        studentsDao.deleteStudent("0000000");

        Assert.assertTrue(totalStudentInSeattle == totalStudentInSeattle_incre - 1);
    }

    @Test
    public void updateTotalStudentsInSeattleInPublicDatabase() throws Exception {
        int totalStudentsInSeattle = totalStudentsInSeattleDao.getTotalStudentsInSeattleFromPublicDatabase();

        totalStudentsInSeattleDao.updateTotalStudentsInSeattleInPublicDatabase(
                TOTAL_STUDENTS_IN_SEATTLE_TEST);
        Assert.assertTrue(totalStudentsInSeattleDao.getTotalStudentsInSeattleFromPublicDatabase()
                == TOTAL_STUDENTS_IN_SEATTLE_TEST);

        totalStudentsInSeattleDao.updateTotalStudentsInSeattleInPublicDatabase(
                totalStudentsInSeattle);
    }
}
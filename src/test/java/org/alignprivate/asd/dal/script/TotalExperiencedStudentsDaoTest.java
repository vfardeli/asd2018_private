package org.alignprivate.asd.dal.script;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TotalExperiencedStudentsDaoTest {
    public static final int TOTAL_EXPERIENCED_STUDENTS_TEST = 10;

    TotalExperiencedStudentsDao totalExperiencedStudentsDao;

    @Before
    public void init() throws Exception {
        totalExperiencedStudentsDao = TotalExperiencedStudentsDao.getInstance();
    }

    @Test
    public void updateTotalExperiencedStudentsTest() throws Exception {
        int totalExperiencedStudents = totalExperiencedStudentsDao.getTotalExperiencedStudents();

        totalExperiencedStudentsDao.updateTotalExperiencedStudents(TOTAL_EXPERIENCED_STUDENTS_TEST);
        Assert.assertTrue(totalExperiencedStudentsDao.getTotalExperiencedStudents() ==
                TOTAL_EXPERIENCED_STUDENTS_TEST);

        totalExperiencedStudentsDao.updateTotalExperiencedStudents(totalExperiencedStudents);
        Assert.assertTrue(totalExperiencedStudentsDao.getTotalExperiencedStudents() ==
                totalExperiencedStudents);
    }
}
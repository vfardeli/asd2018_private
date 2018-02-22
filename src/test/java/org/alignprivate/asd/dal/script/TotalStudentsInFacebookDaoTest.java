package org.alignprivate.asd.dal.script;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TotalStudentsInFacebookDaoTest {
    public static final int TOTAL_FACEBOOK_STUDENTS_TEST = 10;

    TotalStudentsInFacebookDao totalFacebookStudentsDao;

    @Before
    public void init() throws Exception {
        totalFacebookStudentsDao = TotalStudentsInFacebookDao.getInstance();
    }

    @Test
    public void updateTotalExperiencedStudents() throws Exception {
        int totalFacebookStudents = totalFacebookStudentsDao.getTotalStudentsInFacebook();

        totalFacebookStudentsDao.updateTotalStudentsInFacebook(TOTAL_FACEBOOK_STUDENTS_TEST);
        Assert.assertTrue(totalFacebookStudentsDao.getTotalStudentsInFacebook() ==
                TOTAL_FACEBOOK_STUDENTS_TEST);

        totalFacebookStudentsDao.updateTotalStudentsInFacebook(totalFacebookStudents);
        Assert.assertTrue(totalFacebookStudentsDao.getTotalStudentsInFacebook() ==
                totalFacebookStudents);
    }
}
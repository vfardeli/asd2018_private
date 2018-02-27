package org.alignprivate.asd.dal.maintable;

import java.util.List;

import org.alignprivate.asd.enumeration.Campus;
import org.alignprivate.asd.enumeration.DegreeCandidacy;
import org.alignprivate.asd.enumeration.EnrollmentStatus;
import org.alignprivate.asd.enumeration.Gender;
import org.alignprivate.asd.enumeration.Term;
import org.alignprivate.asd.enumeration.TermType;
import org.alignprivate.asd.model.ExpectedLastTerms;
import org.alignprivate.asd.model.Students;
import org.alignprivate.asd.model.Terms;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExpectedLastTermsDaoTest {
	
	private static ExpectedLastTermsDao expectedLastTermsDao;
	private static StudentsDao studentsDao;

	@BeforeClass
	public static void init() {
		expectedLastTermsDao = new ExpectedLastTermsDao();
		studentsDao =  new StudentsDao();
	}

	@Test
	public void getAllExpectedLastTermTest() {
		List<ExpectedLastTerms> expectedLastTerms = expectedLastTermsDao.getAllExpectedLastTerms();
		System.out.println(expectedLastTerms.size());
	}
	
	@Test
	public void addNullExpectedLastTermsTest() {
		ExpectedLastTerms expectedLastTerm = expectedLastTermsDao.addExpectedLastTermRecord(null);
		Assert.assertNull(expectedLastTerm);
	}

	@Test
	public void addExpectedLastTermsTest() {
		int tempId = 1221;
		String email= "abc@def.com";

		Students newStudent = new Students(tempId + "",email, "Tom3", "",
				"Cat", Gender.M, "F1", "1111111111",
				"401 Terry Ave", "WA", "Seattle","98109",
				EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null);

		studentsDao.addStudent(newStudent);

		Terms newTerm = new Terms(Term.FALL, tempId, TermType.QUARTER);
		TermsDao termsDao = new TermsDao();
		Terms term = termsDao.addTerm(newTerm);

		ExpectedLastTerms expectedLastTerm = new ExpectedLastTerms();
		expectedLastTerm.setStudent(newStudent);
		expectedLastTerm.setTerm(newTerm);

		ExpectedLastTerms newExpectedLastTerm = expectedLastTermsDao.addExpectedLastTermRecord(expectedLastTerm);
		Assert.assertTrue(newExpectedLastTerm.getStudent().getEmail().equals(email));

		expectedLastTermsDao.deleteExpectedLastTermRecord(newExpectedLastTerm.getExpectedLastTermId());

		termsDao.deleteTerm(term.getTermId());
		studentsDao.deleteStudent(tempId + "");
	}
	
	@Test
	public void deleteExpectedLastTermsTes() {
		List<ExpectedLastTerms> list = expectedLastTermsDao.getAllExpectedLastTerms();
		
		int tempId = 1221;
		String email= "abc@def.com";

		Students newStudent = new Students(tempId + "",email, "Tom3", "",
				"Cat", Gender.M, "F1", "1111111111",
				"401 Terry Ave", "WA", "Seattle","98109",
				EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null);

		studentsDao.addStudent(newStudent);

		Terms newTerm = new Terms(Term.FALL, tempId, TermType.QUARTER);
		TermsDao termsDao = new TermsDao();
		Terms term = termsDao.addTerm(newTerm);

		ExpectedLastTerms expectedLastTerm = new ExpectedLastTerms();
		expectedLastTerm.setStudent(newStudent);
		expectedLastTerm.setTerm(newTerm);


		ExpectedLastTerms newExpectedLastTerm = expectedLastTermsDao.addExpectedLastTermRecord(expectedLastTerm);
		Assert.assertTrue(newExpectedLastTerm.getStudent().getEmail().equals(email));

		expectedLastTermsDao.deleteExpectedLastTermRecord(newExpectedLastTerm.getExpectedLastTermId());
		List<ExpectedLastTerms> listnew = expectedLastTermsDao.getAllExpectedLastTerms();
		Assert.assertEquals(list.size(), listnew.size()); 

		
		termsDao.deleteTerm(term.getTermId());
		studentsDao.deleteStudent(tempId + "");
	}
	
}

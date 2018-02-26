package org.alignprivate.asd.dal.maintable;

import java.util.List;

import org.alignprivate.asd.enumeration.Campus;
import org.alignprivate.asd.enumeration.DegreeCandidacy;
import org.alignprivate.asd.enumeration.EnrollmentStatus;
import org.alignprivate.asd.enumeration.Gender;
import org.alignprivate.asd.enumeration.Term;
import org.alignprivate.asd.enumeration.TermType;
import org.alignprivate.asd.model.EntryTerms;
import org.alignprivate.asd.model.Students;
import org.alignprivate.asd.model.Terms;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EntryTermsDaoTest {

	private EntryTermsDao entryTermsDao;

	@Before
	public void init() {
		entryTermsDao = new EntryTermsDao();
	}

	@Test
	public void getAllEntryTerms() {
		List<EntryTerms> entryTerms = entryTermsDao.getAllEntryTerms();

		System.out.println(entryTerms.size());
	}

	@Test
	public void addNullEntryTermsTest() {
		EntryTerms EntryTerms = entryTermsDao.addEntryTerm(null);
		Assert.assertNull(EntryTerms);
	}

	@Test
	public void addEntryTermsTest() {
		int tempId = 1221;
		String email= "abc@def.com";

		Students newStudent = new Students(tempId + "",email, "Tom3", "",
				"Cat", Gender.M, "F1", "1111111111",
				"401 Terry Ave", "WA", "Seattle","98109",
				EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null);

		StudentsDao studentsDao =  new StudentsDao();
		studentsDao.addStudent(newStudent);

		Terms newTerm = new Terms(Term.FALL, tempId, TermType.QUARTER);
		TermsDao termsDao = new TermsDao();
		Terms term = termsDao.addTerm(newTerm);

		EntryTerms entryTerms = new EntryTerms();
		entryTerms.setStudent(newStudent);
		entryTerms.setTerms(newTerm);

		EntryTerms newEntryTerms = entryTermsDao.addEntryTerm(entryTerms);
		Assert.assertTrue(newEntryTerms.getStudent().getEmail().equals(email));

		entryTermsDao.deleteEntryTerms(newEntryTerms.getEntryTermId());

		termsDao.deleteTerm(term.getTermId());
		studentsDao.deleteStudent(tempId + "");
	}
	//
	@Test
	public void deleteEntryTermsTest() {
		List<EntryTerms> list = entryTermsDao.getAllEntryTerms();
		
		int tempId = 1221;
		String email= "abc@def.com";

		Students newStudent = new Students(tempId + "",email, "Tom3", "",
				"Cat", Gender.M, "F1", "1111111111",
				"401 Terry Ave", "WA", "Seattle","98109",
				EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null);

		StudentsDao studentsDao =  new StudentsDao();
		studentsDao.addStudent(newStudent);

		Terms newTerm = new Terms(Term.FALL, tempId, TermType.QUARTER);
		TermsDao termsDao = new TermsDao();
		Terms term = termsDao.addTerm(newTerm);

		EntryTerms entryTerms = new EntryTerms();
		entryTerms.setStudent(newStudent);
		entryTerms.setTerms(newTerm);

		EntryTerms newEntryTerms = entryTermsDao.addEntryTerm(entryTerms);
		Assert.assertTrue(newEntryTerms.getStudent().getEmail().equals(email));

		entryTermsDao.deleteEntryTerms(newEntryTerms.getEntryTermId());
		List<EntryTerms> listnew = entryTermsDao.getAllEntryTerms();
		Assert.assertEquals(list.size(), listnew.size()); 

		
		termsDao.deleteTerm(term.getTermId());
		studentsDao.deleteStudent(tempId + "");
	}

}

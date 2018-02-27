package org.alignprivate.asd.dal.maintable;

import java.util.List;

import org.alignprivate.asd.enumeration.Campus;
import org.alignprivate.asd.enumeration.DegreeCandidacy;
import org.alignprivate.asd.enumeration.EnrollmentStatus;
import org.alignprivate.asd.enumeration.Gender;
import org.alignprivate.asd.enumeration.Term;
import org.alignprivate.asd.enumeration.TermType;
import org.alignprivate.asd.model.Students;
import org.alignprivate.asd.model.Terms;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TermsDaoTest {

	private TermsDao termsDao;

	@BeforeClass
	public void init() {
		termsDao = new TermsDao();
	}
	
	@Test
	public void addTermTest() {
		Terms newTerm = new Terms(Term.FALL, 2021, TermType.QUARTER);
		Terms term = termsDao.addTerm(newTerm);
        Assert.assertTrue(term.toString().equals(newTerm.toString()));
        termsDao.deleteTerm(term.getTermId()); 
	}
	
	@Test
	public void addNullTermTest() {
		Terms term = termsDao.addTerm(null);
		Assert.assertNull(term);
	}
	 
	@Test
	public void getAllTerms() {
		List<Terms> terms = termsDao.getAllTerms();
		Terms term = new Terms(Term.SUMMER, 2029, TermType.QUARTER);
		termsDao.addTerm(term);
		List<Terms> newTerms = termsDao.getAllTerms();
        Assert.assertTrue(newTerms.size() == terms.size() + 1);
        termsDao.deleteTerm(term.getTermId());
	}
	
	@Test
    public void deleteTermRecordTest() {
		List<Terms> terms = termsDao.getAllTerms();
		int oldTermSize = terms.size();		
		Terms newTerm = new Terms(Term.SUMMER, 2029, TermType.QUARTER);
		termsDao.addTerm(newTerm);
        termsDao.deleteTerm(newTerm.getTermId());
		int newTermSize = terms.size();	
		Assert.assertEquals(oldTermSize, newTermSize); 
    }
	
	@Test
	public void deleteNullTermTest() {
		boolean result = termsDao.deleteTerm(-1);
		Assert.assertFalse(result);
	}
}

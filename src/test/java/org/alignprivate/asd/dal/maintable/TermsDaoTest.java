package org.alignprivate.asd.dal.maintable;

import java.util.List;

import org.alignprivate.asd.enumeration.Term;
import org.alignprivate.asd.enumeration.TermType;
import org.alignprivate.asd.model.Students;
import org.alignprivate.asd.model.Terms;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TermsDaoTest {

	private TermsDao termsDao;

	@Before
	public void init() {
		termsDao = new TermsDao();
	}


	@Test
	public void getAllTerms() {
		List<Terms> terms = termsDao.getAllTerms();
		Terms term = new Terms(Term.SUMMER, 2020, TermType.QUARTER);
		termsDao.addTerm(term);
		List<Terms> newTerms = termsDao.getAllTerms();
        Assert.assertTrue(newTerms.size() == terms.size() + 1);
//        termsDao.deleteStudent("0000000");
	}
}

package org.alignprivate.asd.dal.maintable;

import java.util.List;

import org.alignprivate.asd.enumeration.Term;
import org.alignprivate.asd.enumeration.TermType;
import org.alignprivate.asd.model.Students;
import org.alignprivate.asd.model.Terms;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TermsDao {
	private static SessionFactory factory;
    private static Session session;
    
    /**
     * Default Constructor.
     */
    public TermsDao() {
        try {
            // it will check the hibernate.cfg.xml file and load it
            // next it goes to all table files in the hibernate file and loads them
            factory = new Configuration().configure().buildSessionFactory();
            session = factory.openSession();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);

        }
    }
    
    /**
     * This is the function to add a term into database.
     *
     * @param term
     * @return true if insert successfully. Otherwise throws exception.
     */
    public Terms addTerm(Terms term) {
    	if(term == null){
    		return null;
    	}
    	
        Transaction tx = null;

        if(ifTermExists(term)){
            System.out.println("term already exists!");
        }else{
            System.out.println("Adding a new term");
            try {
                tx = session.beginTransaction();
                session.save(term);
                tx.commit();
            } catch (HibernateException e) {
                System.out.println("HibernateException: " + e);
                if (tx!=null) tx.rollback();
            }
        }
        
        return term;
    }

    
    /**
     * Get all the Terms records from database.
     *
     * @return A list of Terms
     */
    public List<Terms> getAllTerms(){
        org.hibernate.query.Query query = session.createQuery("FROM Terms");
        List<Terms> list = query.list();
        return list;
    }
    
    

    /**
     * Delete a term record from database.
     *
     * @param termId
     * @return true if delete successfully. Otherwise, false.
     */
    public boolean deleteTerm(int termId){
    	if(termId <= 0){
    		return false;
    	}
    	
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("DELETE FROM Terms WHERE TermId = :termId");
            query.setParameter("termId", termId);
            System.out.println("Deleting term with termId " + termId);
            query.executeUpdate();
            tx.commit();
            if(ifTermIdExists(termId)){
                return false;
            }else{
                return true;
            }
        } catch (HibernateException e) {
            System.out.println("exceptionnnnnn");
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } 
        
        return true;
    }
    
    /**
     * Check if a specific term ID existed in the database 
     *
     * @param termId
     * @return true if existed, false if not.
     */
    private boolean ifTermIdExists(int termId) {
        org.hibernate.query.Query query = session.createQuery("FROM Terms where TermId = :termId");
        query.setParameter("termId", termId);
        List<Terms> list = query.list();
        if(list.size()==0){
        	return false;
        }
    	
		return true;
	}

	/**
     * Check if a specific term is existed in the database 
     *
     * @param term
     * @return true if existed, false if not.
     */
    public boolean ifTermExists(Terms term){
        try{
            System.out.println("Checking if an entered term exists or not.......");
            org.hibernate.query.Query query = session.createQuery("FROM Terms WHERE Term = :term and "
            		+ "TermYear = :termYear and TermType = :termType");
            query.setParameter("term", term.getTerm());
            query.setParameter("termYear", term.getTermYear());
            query.setParameter("termType", term.getTermType()); 
            List list = query.list();
            if(list.size() == 1){
                return true;
            }
        }catch (HibernateException e) {
            e.printStackTrace();
        }

        return false;
    }
    
}

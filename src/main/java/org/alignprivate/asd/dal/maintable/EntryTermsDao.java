package org.alignprivate.asd.dal.maintable;


import java.util.List;

import org.alignprivate.asd.model.EntryTerms;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EntryTermsDao {

	private static SessionFactory factory; 
	private static Session session;

	public EntryTermsDao(){
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

	public List<EntryTerms> getAllEntryTerms() {
		org.hibernate.query.Query query = session.createQuery("from EntryTerms");
		List<EntryTerms> list = query.list();  
		return list;
	}

	public EntryTerms getExperience(int id) {
		org.hibernate.query.Query query = session.createQuery("from EntryTerms where experienceId = :id");
		query.setParameter("id", id);
		List<EntryTerms> list = query.list();
		
		return list.get(0);
		
	}
	
	public EntryTerms updateExperience(EntryTerms experience) {
		if(experience == null) {
			return null;
		}
		
		Transaction tx = null;
		StudentsDao studentDaoHibernate = new StudentsDao();

		if(studentDaoHibernate.ifNuidExists(experience.getStudent().getNeuId())){
			try {
				tx = session.beginTransaction();
				session.saveOrUpdate(experience);
				tx.commit();
			} catch (HibernateException e) {
				if (tx!=null) tx.rollback();
				e.printStackTrace(); 
			} finally {
				//session.close(); 
			}
		}else{
			System.out.println("The student with a given nuid doesn't exists");
		}
		
		return experience;
	}

	public EntryTerms addEntryTerm(EntryTerms entryTerm) {
		if(entryTerm == null) {
			return null;
		}
		
		Transaction tx = null;
		StudentsDao studentDaoHibernate = new StudentsDao();

		if(studentDaoHibernate.ifNuidExists(entryTerm.getStudent().getNeuId())){
			try {
				tx = session.beginTransaction();
				session.save(entryTerm);
				tx.commit();
			} catch (HibernateException e) {
				if (tx!=null) tx.rollback();
				e.printStackTrace(); 
			} finally {
				//session.close(); 
			}
		}else{
			System.out.println("The student with a given nuid doesn't exists");
		}
		
		return entryTerm;
	}

	public boolean deleteEntryTerms(int id){		
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			EntryTerms experience = session.get(EntryTerms.class, id); 
			System.out.println("Deleting student for id = " + id);
			session.delete(experience); 
			tx.commit();
		} catch (HibernateException e) {
			System.out.println("exceptionnnnnn");
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			//session.close(); 
		}

		return true;
	}
}
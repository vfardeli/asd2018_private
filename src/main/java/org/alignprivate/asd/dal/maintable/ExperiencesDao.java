package org.alignprivate.asd.dal.maintable;


import java.util.List;

import org.alignprivate.asd.model.Experiences;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ExperiencesDao {

	private static SessionFactory factory; 
	private static Session session;

	public ExperiencesDao(){
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

	public List<Experiences> getAllExperiences() {
		org.hibernate.query.Query query = session.createQuery("from Experiences");
		List<Experiences> list = query.list();  
		return list;
	}

	public List<Experiences> getExperience(String nuid) {
		org.hibernate.query.Query query = session.createQuery("from Experience where nuid = :studentNuid");
		System.out.println("nuid here: " + nuid);
		query.setParameter("studentNuid", nuid);
		List<Experiences> list = query.list();  
		return list;
	}

//	public void addExperience(String nuid, Experiences experience) {
//		Transaction tx = null;
//		StudentDao studentDaoHibernate = new StudentDao();
//
//		if(studentDaoHibernate.ifNuidExists(nuid)){
//			try {
//				tx = session.beginTransaction();
//				session.save(experience);
//				tx.commit();
//			} catch (HibernateException e) {
//				if (tx!=null) tx.rollback();
//				e.printStackTrace(); 
//			} finally {
//				session.close(); 
//			}
//		}else{
//			System.out.println("The student with a given nuid doesn't exists");
//		}
//	}

//	public void updateStudentRecordDao(int id, Experiences experience) {
//		Transaction tx = null;
//
//		try {
//			tx = session.beginTransaction();
//			experience.setId(id);
//			session.update(experience);
//			tx.commit();
//		} catch (HibernateException e) {
//			if (tx!=null) tx.rollback();
//			e.printStackTrace(); 
//		} finally {
//			session.close(); 
//		}
//	}

	public boolean deleteExperienceRecord(int id){		
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Experiences experience = session.get(Experiences.class, id); 
			System.out.println("Deleting student for id = " + id);
			session.delete(experience); 
			tx.commit();
		} catch (HibernateException e) {
			System.out.println("exceptionnnnnn");
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}

		return true;
	}

}
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

	public Experiences getExperience(int id) {
		org.hibernate.query.Query query = session.createQuery("from Experiences where experienceId = :id");
		query.setParameter("id", id);
		List<Experiences> list = query.list();
		
		return list.get(0);
		
	}

	public Experiences addExperience(Experiences experience) {
		if(experience == null) {
			return null;
		}
		
		Transaction tx = null;
		StudentsDao studentDaoHibernate = new StudentsDao();

		if(studentDaoHibernate.ifNuidExists(experience.getStudent().getNeuId())){
			try {
				tx = session.beginTransaction();
				session.save(experience);
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
			//session.close(); 
		}

		return true;
	}

}
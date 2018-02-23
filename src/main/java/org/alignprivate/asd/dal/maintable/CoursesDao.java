package org.alignprivate.asd.dal.maintable;

import java.util.List;

import org.alignprivate.asd.model.Companies;
import org.alignprivate.asd.model.Courses;
import org.alignprivate.asd.model.Students;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CoursesDao {

	private static SessionFactory factory;
	private static Session session;

	/**
	 * Default constructor.
	 * it will check the Hibernate.cfg.xml file and load it
	 * next it goes to all table files in the hibernate file and loads them.
	 */
	public CoursesDao() {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Get list of all companies from the private database.
	 *
	 * @return list of All companies from private database.
	 */
	public List<Courses> getAllCourses() {
		session = factory.openSession();
		org.hibernate.query.Query query = session.createQuery("FROM Courses");
		List<Courses> listOfCoarses = query.list();

		session.close();
		return listOfCoarses;
	}

	/**
	 * Get a course from the private database from their
	 * course Id.
	 *
	 * @param course Id that wants to be found.
	 */
	public Courses getCourseById(String courseId) {
		session = factory.openSession();
		org.hibernate.query.Query query = session.createQuery("FROM Courses WHERE courseId = :courseId");
		query.setParameter("courseId", courseId);
		List listOfCourses = query.list();
		if (listOfCourses.isEmpty()) {
			return null;
		}
		session.close();
		return (Courses) listOfCourses.get(0);
	}

	/**
	 * Get a company from the private database from their name.
	 *
	 * @param companyName company Name that wants to be found.
	 * @return company if found, null if not found.
	 */
	public List<Courses> getCoursesByName(String courseName) {
		session = factory.openSession();
		org.hibernate.query.Query query = session.createQuery("FROM Courses WHERE courseName = :courseName");
		query.setParameter("courseName", courseName);
		List listOfCourses = query.list();
		session.close();

		return listOfCourses;
	}

	/**
	 * Create a new course in the private database.
	 */
	public Courses createCourse(Courses course) {
		if(course == null)  {
			return null;
		}
		session = factory.openSession();
		Transaction tx = null;
		System.out.println("saving " + course.getCourseName() + " in Courses table");
		try {
			tx = session.beginTransaction();
			session.save(course);
			tx.commit();
		} catch (HibernateException e) {
			System.out.println("HibernateException: " + e);
			if (tx != null) tx.rollback();
		} finally {
			session.close();
		}

		return course;
	}

	/**
	 * Delete a course from the private database based
	 * on the company Id.
	 */
	public boolean deleteCourseById(String courseId) {
		if(courseId == null || courseId.isEmpty()) {
			return false;
		}

		Courses course = getCourseById(courseId);
		if (course != null) {
			session = factory.openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				System.out.println("Deleting course with name = " + course.getCourseName());
				session.delete(course);
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null) tx.rollback();
				e.printStackTrace();
				return false;
			} finally {
				session.close();
			}
			return true;
		}
		return false;
	}

	/**
	 * Update a course based on the provided company model.
	 */
	public boolean updateCourse(Courses course) {
		if (getCourseById(course.getCourseId()) != null) {
			session = factory.openSession();
			Transaction tx = null;
			System.out.println("updating company in Companies table...");
			try {
				tx = session.beginTransaction();
				session.saveOrUpdate(course);
				tx.commit();
			} catch (HibernateException e) {
				System.out.println("HibernateException: " + e);
				if (tx != null) tx.rollback();
				return false;
			} finally {
				session.close();
			}
			return true;
		}
		return false;
	}

}

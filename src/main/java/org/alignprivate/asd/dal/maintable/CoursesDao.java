package org.alignprivate.asd.dal.maintable;

import java.util.List;

import org.alignprivate.asd.model.Students;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CoursesDao {
	private static SessionFactory factory;
    private static Session session;

    /**
     * Default Constructor.
     */
    public CoursesDao() {
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
    
//    /**
//     * Get all the students records from database.
//     *
//     * @return A list of students
//     */
//    public List<Students> getAllStudents(){
//        org.hibernate.query.Query query = session.createQuery("FROM Students");
//        List<Students> list = query.list();
//        return list;
//    }

}

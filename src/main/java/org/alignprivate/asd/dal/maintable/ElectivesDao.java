package org.alignprivate.asd.dal.maintable;

import java.util.List;

import org.alignprivate.asd.model.Electives;
import org.alignprivate.asd.model.Experiences;
import org.alignprivate.asd.model.Students;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ElectivesDao {
  private static SessionFactory factory;
  private static Session session;

//  private CoursesDao coursesDao;
  private StudentsDao studentDao;

  /**
   * Default Constructor.
   */
  public ElectivesDao() {
//    coursesDao = new CoursesDao();
    studentDao = new StudentsDao();
    try {
      // it will check the hibernate.cfg.xml file and load it
      // next it goes to all table files in the hibernate file and loads them
      factory = new Configuration().configure().buildSessionFactory();
    } catch (Throwable ex) {
      System.err.println("Failed to create sessionFactory object." + ex);
      throw new ExceptionInInitializerError(ex);

    }
  }

  public List<Electives> getElectivesByNeuId(String neuId) {
    session = factory.openSession();
    org.hibernate.query.Query query = session.createQuery("from Electives where neuId = :neuId");
    query.setParameter("neuId", neuId);
    List<Electives> list = query.list();
//    for (Electives elective : list) {
//      populateForeignKey(elective);
//    }
    session.close();
    return list;
  }

  public Electives getElectiveById(int electiveId) {
    session = factory.openSession();
    org.hibernate.query.Query query = session.createQuery("from Electives where electiveId = :electiveId");
    query.setParameter("electiveId", electiveId);
    List<Electives> list = query.list();
    session.close();
    if (list.isEmpty()) {
      return null;
    }
    Electives elective = list.get(0);
//    populateForeignKey(elective);
    return elective;
  }

  /**
   * This is the function to add an Elective for a given student into database.
   *
   * @param elective elective to be added; not null.
   * @return true if insert successfully. Otherwise throws exception.
   */
  public Electives addElective(Electives elective) {
    if (elective == null) {
      return null;
    }

    Transaction tx = null;
//    StudentsDao studentDao = new StudentsDao();
    session = factory.openSession();

    if (studentDao.ifNuidExists(elective.getNeuId())) {
      try {
        tx = session.beginTransaction();
        session.save(elective);
        tx.commit();
      } catch (HibernateException e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
        return null;
      } finally {
        session.close();
      }
    } else {
      System.out.println("The student with a given nuid doesn't exists");
      return null;
    }
//    populateForeignKey(elective);
    return elective;
  }

  public boolean updateElectives(Electives elective) {
    session = factory.openSession();
    Transaction tx = null;
    System.out.println("updating electives in electives table...");
    try {
      tx = session.beginTransaction();
      session.saveOrUpdate(elective);
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

  public boolean deleteElectiveRecord(int id) {
    Transaction tx = null;

    try {
      session = factory.openSession();
      tx = session.beginTransaction();
      Electives electives = session.get(Electives.class, id);
      System.out.println("Deleting elective for id = " + id);
      session.delete(electives);
      tx.commit();
    } catch (HibernateException e) {
      System.out.println("exceptionnnnnn");
      if (tx != null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }

    return true;
  }

//  private void populateForeignKey(Electives electives) {
//    electives.setStudent(studentsDao.getStudentRecord(electives.getStudent().getNeuId()));
//    electives.setCourse(coursesDao.getCourseById(electives.getCourse().getCourseId()));
//  }
}
package org.alignprivate.asd.dal.maintable;

import org.alignprivate.asd.model.AdministratorNotes;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AdministratorNotesDao {
    private static SessionFactory factory;
    private static Session session;

    /**
     * Default Constructor
     */
    public AdministratorNotesDao() {
        try {
            // it will check the hibernate.cfg.xml file and load it
            // next it goes to all table files in the hibernate file and loads them
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);

        }
    }

    /**
     * Search for an Administrator Note by neu Id.
     *
     * @param neuId
     * @return Administrator Note
     */
    public AdministratorNotes getAdministratorNoteRecord(String neuId) {
        try {
            session = factory.openSession();
            org.hibernate.query.Query query = session.createQuery("FROM AdministratorNotes WHERE NeuId = :neuId ");
            query.setParameter("neuId", neuId);
            List list = query.list();
            if(list.size()==1){
                return (AdministratorNotes) list.get(0);
            }else{
                System.out.println("The list should contain only one AdministratorNote with a given neu id");
                return null;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return null;
    }

    /**
     * Insert Administrator Note record into database.
     *
     * @param note
     * @return Newly inserted Administrator Note
     */
    public AdministratorNotes addAdministratorNoteRecord(AdministratorNotes note) {
        Transaction tx = null;

        if(ifNuidExists(note.getNeuId())){
            System.out.println("Administrator Note already exists!");
        }else{
            try {
                session = factory.openSession();
                tx = session.beginTransaction();
                session.save(note);
                tx.commit();
            } catch (HibernateException e) {
                e.printStackTrace();
                if (tx!=null) tx.rollback();
            } finally {
                session.close();
            }
        }
        return note;
    }

    /**
     * Update Administrator Note record.
     *
     * @param note
     * @return updated Administrator Note
     */
    public AdministratorNotes updateAdministratorNoteRecord(AdministratorNotes note) {
        Transaction tx = null;
        String neuId = note.getNeuId();
        if(ifNuidExists(neuId)){
            try{
                Session session = factory.openSession();
                tx = session.beginTransaction();
                String title = note.getTitle();
                String desc = note.getDesc();

                String hql = "UPDATE AdministratorNotes set Title = :title, "  +
                        "Description = :desc " +
                        "WHERE NeuId = :neuId";

                org.hibernate.query.Query query = session.createQuery(hql);
                query.setParameter("title", title);
                query.setParameter("desc", desc);
                query.setParameter("neuId", neuId);

                int result = query.executeUpdate();
                System.out.println("Rows affected: " + result);
                tx.commit();
                return note;
            }catch (HibernateException e) {
                if (tx!=null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }else{
            System.out.println("Administrator Note for this neu id doesn't exists..");
        }

        return null;
    }

    /**
     * Delete existing Administrator Record.
     *
     * @param note
     * @return true if delete successfully. Return false if failed.
     */
    public boolean deleteAdministratorNoteRecord(AdministratorNotes note) {
        Transaction tx = null;
        String neuId = note.getNeuId();
        try {
            session = factory.openSession();
            tx = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("DELETE FROM AdministratorNotes WHERE NeuId = :neuId ");
            query.setParameter("neuId", neuId);
            System.out.println("Deleting Administrator Note for nuid = " + neuId);
            query.executeUpdate();
            tx.commit();
            if(ifNuidExists(neuId)){
                return false;
            }else{
                return true;
            }
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return false;
    }

    /**
     * Check if an Administrator Note exists or not.
     *
     * @param neuId
     * @return true if exists, false if not.
     */
    public boolean ifNuidExists(String neuId) {
        try{
            System.out.println("Checking if an Administrator Note exists or not.......");
            session = factory.openSession();
            org.hibernate.query.Query query = session.createQuery("FROM AdministratorNotes WHERE NeuId = :neuId");
            query.setParameter("neuId", neuId);
            List list = query.list();
            if(list.size() == 1){
                return true;
            }
        }catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return false;
    }
}

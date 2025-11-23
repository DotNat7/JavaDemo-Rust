package com.example.demo.model;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProbandDAO {
    public void save(Proband proband) throws IllegalStateException {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(proband);
            tx.commit();
        } catch (IllegalStateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void update(Proband proband) throws IllegalStateException {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(proband);
            tx.commit();
        } catch (IllegalStateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Proband proband = session.get(Proband.class, id);
            if (proband != null) {
                session.remove(proband);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public Proband findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Proband.class, id);
        }
    }

    public List<Proband> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Proband", Proband.class).list();
        }
    }
}
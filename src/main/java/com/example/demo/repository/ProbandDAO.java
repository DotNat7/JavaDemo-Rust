package com.example.demo.repository;

import com.example.demo.model.Proband;
import com.example.demo.util.HibernateUtil;
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
            rollbackSafely(tx);
            throw e;
        } catch (Exception e) {
            rollbackSafely(tx);
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
            rollbackSafely(tx);
            throw e;
        } catch (Exception e) {
            rollbackSafely(tx);
            throw e; // Re-throw to let caller handle OptimisticLockExceptio
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
            rollbackSafely(tx);
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

    private void rollbackSafely(Transaction tx) {
        if (tx != null) {
            try {
                if (tx.isActive()) {
                    tx.rollback();
                }
            } catch (IllegalStateException e) {
                // Connection already closed, nothing to rollback
            } catch (Exception rollbackEx) {
                System.err.println("Rollback failed: " + rollbackEx.getMessage());
            }
        }
    }
}
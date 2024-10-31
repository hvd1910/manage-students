package com.example.quanlysinhvien.daos;

import com.example.quanlysinhvien.models.TinChi;
import com.example.quanlysinhvien.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TinChiDAO {

    public List<TinChi> findAll() {
        Transaction transaction = null;
        List<TinChi> tinchiList = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            tinchiList = session.createQuery("FROM TinChi", TinChi.class).getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return tinchiList;
    }

    public int add(TinChi objTC) {
        Transaction transaction = null;
        int result = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();

            // Save the TinChi object
            session.save(objTC);
            result = 1;

            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            result = 0;
        }
        return result;
    }

    public TinChi findById(String maTC) {
        TinChi tinchi = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Fetch the entity by its ID
            tinchi = session.get(TinChi.class, maTC);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tinchi;
    }
}

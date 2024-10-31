package com.example.quanlysinhvien.daos;

import com.example.quanlysinhvien.models.Khoa;
import com.example.quanlysinhvien.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class KhoaDAO {

    public List<Khoa> findAll() {
        Transaction transaction = null;
        List<Khoa> khoaList = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();


            khoaList = session.createQuery("FROM Khoa", Khoa.class).getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return khoaList;
    }

    public int add(Khoa objK) {
        Transaction transaction = null;
        int result = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();

            // Save the Khoa object
            session.save(objK);
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
}

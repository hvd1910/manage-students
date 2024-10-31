package com.example.quanlysinhvien.daos;

import com.example.quanlysinhvien.models.NienKhoa;
import com.example.quanlysinhvien.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class NienKhoaDAO {

    public List<NienKhoa> findAll() {
        Transaction transaction = null;
        List<NienKhoa> nienKhoaList = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            nienKhoaList = session.createQuery("FROM NienKhoa", NienKhoa.class).getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return nienKhoaList;
    }

    public int add(NienKhoa objNK) {
        Transaction transaction = null;
        int result = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();

            // Save the NienKhoa object
            session.save(objNK);
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
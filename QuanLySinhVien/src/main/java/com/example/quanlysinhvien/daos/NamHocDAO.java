package com.example.quanlysinhvien.daos;

import com.example.quanlysinhvien.models.NamHoc;
import com.example.quanlysinhvien.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class NamHocDAO {

    public List<NamHoc> findAll() {
        Transaction transaction = null;
        List<NamHoc> namhocList = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();

            namhocList = session.createQuery("FROM NamHoc", NamHoc.class).getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return namhocList;
    }

    public int add(NamHoc objNH) {
        Transaction transaction = null;
        int result = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();

            // Save the NamHoc object
            session.save(objNH);
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

    public NamHoc findById(String maNH) {
        NamHoc namHoc = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // Fetch the entity by its ID
            namHoc = session.get(NamHoc.class, maNH);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return namHoc;
    }
}

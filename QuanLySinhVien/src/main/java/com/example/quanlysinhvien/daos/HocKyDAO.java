package com.example.quanlysinhvien.daos;

import java.util.List;


import com.example.quanlysinhvien.models.HocKy;
import com.example.quanlysinhvien.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

public class HocKyDAO {

    public List<HocKy> findAll() {
        List<HocKy> hockyList = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            hockyList = session.createQuery("from HocKy", HocKy.class).getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException rbEx) {
                    System.err.println("Transaction rollback failed: " + rbEx);
                }
            }
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hockyList;
    }

    public int add(HocKy objHK) {
        Transaction transaction = null;
        int result = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(objHK);
            transaction.commit();
            result = 1;
        } catch (HibernateException e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException rbEx) {
                    System.err.println("Transaction rollback failed: " + rbEx);
                }
            }
            e.printStackTrace();
            result = 0;
        } catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }

        return result;
    }


    public HocKy findById(String maHK) {
        HocKy hocKy = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Fetch the entity by its ID
            hocKy = session.get(HocKy.class, maHK);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return hocKy;
    }
}

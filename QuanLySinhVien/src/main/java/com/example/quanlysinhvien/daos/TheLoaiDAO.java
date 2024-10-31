package com.example.quanlysinhvien.daos;

import com.example.quanlysinhvien.models.TheLoai;
import com.example.quanlysinhvien.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TheLoaiDAO {

    public List<TheLoai> findAll() {
        Transaction transaction = null;
        List<TheLoai> theloaiList = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            theloaiList = session.createQuery("FROM TheLoai", TheLoai.class).getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return theloaiList;
    }

    public int add(TheLoai objTL) {
        Transaction transaction = null;
        int result = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();

            // Save the TheLoai object
            session.save(objTL);
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

    public TheLoai findById(String maTL) {
        TheLoai theloai = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Fetch the entity by its ID
            theloai = session.get(TheLoai.class, maTL);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return theloai;
    }
}

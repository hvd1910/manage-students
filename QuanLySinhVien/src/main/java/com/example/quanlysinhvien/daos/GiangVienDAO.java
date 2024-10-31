package com.example.quanlysinhvien.daos;

import com.example.quanlysinhvien.models.GiangVien;
import com.example.quanlysinhvien.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GiangVienDAO {

    public List<GiangVien> findAll() {
        Transaction transaction = null;
        List<GiangVien> giangvienList = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            giangvienList = session.createQuery("FROM GiangVien", GiangVien.class).getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return giangvienList;
    }

    public int add(GiangVien objGV) {
        Transaction transaction = null;
        int result = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(objGV);
            transaction.commit();
            result = 1; // Assuming success if no exception is thrown
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            result = 0; // Failure case
        }

        return result;
    }

    public GiangVien findById(String maGV) {
        GiangVien giangVien = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // Fetch the entity by its ID
            giangVien = session.get(GiangVien.class, maGV);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return giangVien;
    }


    public int update(GiangVien giangVien) {
        Transaction transaction = null;
        int result = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Bắt đầu transaction
            transaction = session.beginTransaction();
            session.update(giangVien);
            // Commit transaction
            transaction.commit();
            result = 1; // Indicates success

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback nếu có lỗi xảy ra
            }
            e.printStackTrace();
        }
        return result;

    }

    public void delete(String maGV) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            GiangVien giangVien = session.get(GiangVien.class, maGV);
            if (giangVien != null) {
                session.delete(giangVien);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

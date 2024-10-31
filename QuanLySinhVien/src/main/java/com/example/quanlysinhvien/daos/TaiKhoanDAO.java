package com.example.quanlysinhvien.daos;

import com.example.quanlysinhvien.models.TaiKhoan;
import com.example.quanlysinhvien.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TaiKhoanDAO {

    public List<TaiKhoan> findAll() {
        List<TaiKhoan> taiKhoanList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            taiKhoanList = session.createQuery("FROM TaiKhoan ", TaiKhoan.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taiKhoanList;
    }

    public TaiKhoan findByUsernameAndPassword(String username, String password) {
        TaiKhoan objUser = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM TaiKhoan WHERE username = :username AND password = :password";
            objUser = session.createQuery(hql, TaiKhoan.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objUser;
    }

    public int add(TaiKhoan objTK) {
        Transaction transaction = null;
        int result = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(objTK);
            transaction.commit();
            result = 1;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            result = 0;
        }
        return result;
    }

    // Hàm tìm kiếm thông tin người dùng theo username
    public TaiKhoan viewAll(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        TaiKhoan taiKhoan = null;

        try {
            tx = session.beginTransaction();
            taiKhoan = session.createQuery("FROM TaiKhoan WHERE username = :username", TaiKhoan.class)
                    .setParameter("username", username)
                    .uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return taiKhoan;
    }


    public TaiKhoan findById(String id) {
        TaiKhoan taiKhoan = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // Fetch the entity by its ID
            taiKhoan = session.get(TaiKhoan.class, Integer.parseInt(id));

        } catch (Exception e) {

            e.printStackTrace();
        }

        return taiKhoan;
    }


    public int update(TaiKhoan taiKhoan) {
        Transaction transaction = null;
        int result = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Bắt đầu transaction
            transaction = session.beginTransaction();
            session.update(taiKhoan);
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

    public void delete(String id) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            TaiKhoan taiKhoan = session.get(TaiKhoan.class, Integer.parseInt(id));
            if (taiKhoan != null) {
                session.delete(taiKhoan);
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


package com.example.quanlysinhvien.daos;

import com.example.quanlysinhvien.models.MonHoc;
import com.example.quanlysinhvien.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MonHocDAO {

    public List<MonHoc> findAll() {
        List<MonHoc> monhocList = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "SELECT mh FROM MonHoc mh " +
                    "LEFT JOIN FETCH mh.tinchi tc " +
                    "LEFT JOIN FETCH mh.theloai tl " +
                    "LEFT JOIN FETCH mh.giangviens gv " +
                    "WHERE mh.maMH IN (SELECT DISTINCT mh2.maMH FROM MonHoc mh2)";
            monhocList = session.createQuery(hql, MonHoc.class).getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return monhocList;
    }

    public int add(MonHoc objMH) {
        Transaction transaction = null;
        int result = 0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Saving the MonHoc object
            session.save(objMH);
            result = 1;  // assuming save is successful

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

    public void update(MonHoc monHoc) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(monHoc);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return;
    }

    public void delete(String maMH) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            MonHoc monHoc = session.get(MonHoc.class, maMH);
            if (monHoc != null) {
                session.delete(monHoc);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public MonHoc findById(String maMH) {
        MonHoc monHoc = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // Fetch the entity by its ID
            monHoc = session.get(MonHoc.class, maMH);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return monHoc;
    }
}

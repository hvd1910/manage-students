package com.example.quanlysinhvien.daos;

import com.example.quanlysinhvien.models.Diem;
import com.example.quanlysinhvien.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DiemDAO {

    public int nhapdiem(Diem objDiem) {
        int result = 0;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(objDiem);
            transaction.commit();
            result = 1; // Indicates success
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback transaction in case of error
            }
            e.printStackTrace();
        }
        return result;
    }

    public List<Diem> findAll() {
        Transaction transaction = null;
        List<Diem> listOfDiem = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();

            // Get a list of Diem
            listOfDiem = session.createQuery("from Diem", Diem.class).list();

            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfDiem;
    }


    public int update(Diem diem) {
        Transaction transaction = null;
        int result = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Bắt đầu transaction
            transaction = session.beginTransaction();
            // Cập nhật đối tượng Diem
            session.update(diem);
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

    public Diem findById(String maBD) {
        Diem diem = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Fetch the entity by its ID
            int id = Integer.parseInt(maBD);
            diem = session.get(Diem.class, id);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return diem;
    }


    public void delete(String maBD) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            int id = Integer.parseInt(maBD);
            Diem diem = session.get(Diem.class, id);
            if (diem != null) {
                session.delete(diem);
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

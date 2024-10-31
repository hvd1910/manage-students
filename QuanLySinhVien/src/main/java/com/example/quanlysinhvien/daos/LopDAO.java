package com.example.quanlysinhvien.daos;

import com.example.quanlysinhvien.models.Lop;
import com.example.quanlysinhvien.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LopDAO {

    // Phương thức tìm tất cả Lop, bao gồm cả thông tin của Khoa
    public List<Lop> findAll() {
        List<Lop> lopList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Sử dụng HQL để truy vấn dữ liệu
            String hql = "SELECT l FROM Lop l JOIN FETCH l.khoa k";
            lopList = session.createQuery(hql, Lop.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lopList;
    }

    // Phương thức thêm mới một Lop vào cơ sở dữ liệu
    public int add(Lop objL) {
        Transaction transaction = null;
        int result = 0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(objL);
            transaction.commit();
            result = 1; // thành công
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }


    public Lop findLopBymaLop(int maLop) {
        Lop lop = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Truy vấn bằng HQL
            lop = session.get(Lop.class, maLop);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lop;
    }
}

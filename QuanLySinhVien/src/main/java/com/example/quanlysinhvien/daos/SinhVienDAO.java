package com.example.quanlysinhvien.daos;


import com.example.quanlysinhvien.models.Diem;
import com.example.quanlysinhvien.models.GiangVien;
import com.example.quanlysinhvien.models.SinhVien;
import com.example.quanlysinhvien.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SinhVienDAO {

    // Phương thức tìm tất cả SinhVien, bao gồm cả thông tin của Lop và Khoa
    public List<SinhVien> findAll() {
        List<SinhVien> sinhvienList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Sử dụng HQL để truy vấn dữ liệu
            String hql = "SELECT sv " +
                    "FROM SinhVien sv " +
                    "JOIN FETCH sv.lop l " +
                    "JOIN FETCH l.khoa k " +
                    "ORDER BY sv.maSV DESC";
            sinhvienList = session.createQuery(hql, SinhVien.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sinhvienList;
    }

    // Phương thức thêm mới một SinhVien vào cơ sở dữ liệu
    public int add(SinhVien objSV) {
        Transaction transaction = null;
        int result = 0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(objSV);
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

    public int update(SinhVien sinhVien) {
        Transaction transaction = null;
        int result = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Bắt đầu transaction
            transaction = session.beginTransaction();
            session.update(sinhVien);
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

    public void delete(String maSV) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            int id = Integer.parseInt(maSV);
            SinhVien sinhVien = session.get(SinhVien.class, id);
            if (sinhVien != null) {
                session.delete(sinhVien);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public SinhVien findById(String maSV) {
        SinhVien sinhVien = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            int id = Integer.parseInt(maSV);
            // Fetch the entity by its ID
            sinhVien = session.get(SinhVien.class, id);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return sinhVien;
    }
}

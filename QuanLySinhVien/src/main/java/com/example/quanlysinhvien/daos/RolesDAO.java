package com.example.quanlysinhvien.daos;

import com.example.quanlysinhvien.models.Roles;
import com.example.quanlysinhvien.models.SinhVien;
import com.example.quanlysinhvien.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RolesDAO {

    // Find all roles
    public List<Roles> findAll() {
        Transaction transaction = null;
        List<Roles> rolesList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            rolesList = session.createQuery("from Roles", Roles.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return rolesList;
    }

    // Add a new role
    public int add(Roles role) {
        Transaction transaction = null;
        int roleId = 0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            roleId = (Integer) session.save(role); // `save` method returns the generated ID
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return roleId;
    }


    public Roles findById(Integer id) {
        Roles role = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // Fetch the entity by its ID
            role = session.get(Roles.class, id);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return role;
    }
}

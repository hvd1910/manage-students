package com.example.quanlysinhvien.controllers;

import com.example.quanlysinhvien.daos.GiangVienDAO;
import com.example.quanlysinhvien.daos.SinhVienDAO;
import com.example.quanlysinhvien.models.GiangVien;
import com.example.quanlysinhvien.models.SinhVien;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class AdminGiangVienController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminGiangVienController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("userInfor") == null) {
            response.sendRedirect(request.getContextPath()+"/auth/login");
            return;
        }

        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            GiangVienDAO giangvienDAO = new GiangVienDAO();
            String maGVToDelete = request.getParameter("maGV");
            if (maGVToDelete != null && !maGVToDelete.isEmpty()) {
                try {
                    giangvienDAO.delete(maGVToDelete);
                    response.sendRedirect(request.getContextPath() + "/admin/giangvien?msg=OK");
                } catch (Exception e) {
                    e.printStackTrace(); // Log the exception
                    response.sendRedirect(request.getContextPath() + "/admin/giangvien?msg=FAIL");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/giangvien?msg=FAIL");
            }
        } else {
            GiangVienDAO giangvienDAO = new GiangVienDAO();
            List<GiangVien> giangvienList = giangvienDAO.findAll();
            request.setAttribute("giangvienList", giangvienList);

            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/giangvien.jsp");
            rd.forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");


        GiangVienDAO giangvienDAO = new GiangVienDAO();

        String magv = request.getParameter("maGV");
        String tengv = request.getParameter("tenGV");
        String diachi = request.getParameter("diaChi");
        int sdt = 0;
        try {
            sdt = Integer.parseInt(request.getParameter("sdt"));
        } catch (NumberFormatException e) {
            System.out.println("Lỗi..!");
        }
        String email = request.getParameter("email");


        String action = request.getParameter("action");
        String maGVupdate = request.getParameter("maGVupdate");

        if( maGVupdate != null && "update".equalsIgnoreCase(action)) {

            try {
                GiangVien giangVienUpdate = giangvienDAO.findById(maGVupdate);
                if (giangVienUpdate != null) {
                    giangVienUpdate.setTenGV(tengv);
                    giangVienUpdate.setEmail(email);
                    giangVienUpdate.setDiaChi(diachi);
                    giangVienUpdate.setSdt(sdt);

                    int update = giangvienDAO.update(giangVienUpdate);
                    if (update > 0) {
                        response.sendRedirect(request.getContextPath() + "/admin/giangvien?msg=OK");
                        return;
                    } else {
                        response.sendRedirect(request.getContextPath() + "/admin/giangvien?msg=ERROR");
                        return;
                    }
                }else {
                    response.sendRedirect(request.getContextPath() + "/admin/giangvien?msg=ERROR");
                    return;
                }
            }catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/admin/giangvien?msg=ERROR");
                return;
            }
        }



        GiangVien objGV = new GiangVien(magv, tengv, diachi, sdt, email);
        int add = giangvienDAO.add(objGV);
        if(add > 0) {
            response.sendRedirect(request.getContextPath()+"/admin/giangvien?msg=OK");
            return;
        }else {
            response.sendRedirect(request.getContextPath()+"/admin/giangvien?msg=ERROR");
            return;
        }

    }

}


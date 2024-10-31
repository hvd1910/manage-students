package com.example.quanlysinhvien.controllers;

import com.example.quanlysinhvien.daos.LopDAO;
import com.example.quanlysinhvien.daos.SinhVienDAO;
import com.example.quanlysinhvien.models.Lop;
import com.example.quanlysinhvien.models.SinhVien;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
public class AdminSinhVienController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminSinhVienController() {
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
            SinhVienDAO sinhVienDAO = new SinhVienDAO();
            String maSVToDelete = request.getParameter("maSV");
            if (maSVToDelete != null && !maSVToDelete.isEmpty()) {
                try {
                    sinhVienDAO.delete(maSVToDelete);
                    response.sendRedirect(request.getContextPath() + "/admin/sinhvien?msg=OK");
                } catch (Exception e) {
                    e.printStackTrace(); // Log the exception
                    response.sendRedirect(request.getContextPath() + "/admin/sinhvien?msg=FAIL");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/sinhvien?msg=FAIL");
            }
        } else {
            SinhVienDAO sinhvienDAO = new SinhVienDAO();
            List<SinhVien> sinhvienList = sinhvienDAO.findAll();
            request.setAttribute("sinhvienList", sinhvienList);

            LopDAO lopDAO = new LopDAO();
            List<Lop> lopList = lopDAO.findAll();
            request.setAttribute("lopList", lopList);

            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/sinhvien.jsp");
            rd.forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        SinhVienDAO sinhvienDAO = new SinhVienDAO();

        int masv = 0;
        try {
            masv = Integer.parseInt(request.getParameter("masv"));
        } catch (NumberFormatException e) {
            System.out.println("Lỗi..!");
        }
        String tensv = request.getParameter("tensv");
        String diachi = request.getParameter("diachi");
        int sdt = 0;
        try {
            sdt = Integer.parseInt(request.getParameter("sdt"));
        } catch (NumberFormatException e) {
            System.out.println("Lỗi..!");
        }
        String email = request.getParameter("email");
        int malop = 0;
        try {
            malop = Integer.parseInt(request.getParameter("malop"));

        } catch (NumberFormatException e) {
            System.out.println("Lỗi..!");
        }


        LopDAO lopDAO = new LopDAO();
        Lop lop = lopDAO.findLopBymaLop(malop);

        String action = request.getParameter("action");
        String maSvupdate = request.getParameter("maSVupdate");

        if( maSvupdate != null && "update".equalsIgnoreCase(action)) {

            try {
                SinhVien sinhVienUpdate = sinhvienDAO.findById(maSvupdate);
                if (sinhVienUpdate != null) {
                    sinhVienUpdate.setTenSV(tensv);
                    sinhVienUpdate.setSdt(sdt);
                    sinhVienUpdate.setEmail(email);
                    sinhVienUpdate.setDiaChi(diachi);
                    sinhVienUpdate.setLop(lop);

                    int update = sinhvienDAO.update(sinhVienUpdate);
                    if (update > 0) {
                        response.sendRedirect(request.getContextPath() + "/admin/sinhvien?msg=OK");
                        return;
                    } else {
                        response.sendRedirect(request.getContextPath() + "/admin/sinhvien?msg=ERROR");
                        return;
                    }
                }else {
                    response.sendRedirect(request.getContextPath() + "/admin/sinhvien?msg=ERROR");
                    return;
                }
            }catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/admin/sinhvien?msg=ERROR");
                return;
            }
        }


        SinhVien objSV = new SinhVien(masv, tensv, diachi, sdt, email,lop);


        int add = sinhvienDAO.add(objSV);
        if(add > 0) {
            response.sendRedirect(request.getContextPath()+"/admin/sinhvien?msg=OK");
            return;
        }else {
            response.sendRedirect(request.getContextPath()+"/admin/sinhvien?msg=ERROR");
            return;
        }

    }

}
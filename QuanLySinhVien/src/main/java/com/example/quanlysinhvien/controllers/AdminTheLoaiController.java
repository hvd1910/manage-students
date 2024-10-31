package com.example.quanlysinhvien.controllers;
import com.example.quanlysinhvien.daos.TheLoaiDAO;
import com.example.quanlysinhvien.models.TheLoai;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class AdminTheLoaiController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminTheLoaiController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("userInfor") == null) {
            response.sendRedirect(request.getContextPath()+"/auth/login");
            return;
        }

        TheLoaiDAO theLoaiDAO = new TheLoaiDAO();
        List<TheLoai> theLoaiList = theLoaiDAO.findAll();
        request.setAttribute("theLoaiList", theLoaiList);

        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/theloai.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        TheLoaiDAO theLoaiDAO = new TheLoaiDAO();

        String maTL = request.getParameter("maTL");

        String tenTL = request.getParameter("tenTL");

        TheLoai objTL = new TheLoai(maTL, tenTL);
        int add = theLoaiDAO.add(objTL);
        if(add > 0) {
            response.sendRedirect(request.getContextPath()+"/admin/theloai?msg=OK");
            return;
        }else {
            response.sendRedirect(request.getContextPath()+"/admin/theloai?msg=ERROR");
            return;
        }

    }

}

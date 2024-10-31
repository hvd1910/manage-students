package com.example.quanlysinhvien.controllers;

import com.example.quanlysinhvien.daos.KhoaDAO;
import com.example.quanlysinhvien.daos.LopDAO;
import com.example.quanlysinhvien.models.Khoa;
import com.example.quanlysinhvien.models.Lop;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
public class AdminLopController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminLopController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("userInfor") == null) {
            response.sendRedirect(request.getContextPath()+"/auth/login");
            return;
        }

        LopDAO lopDAO = new LopDAO();
        List<Lop> lopList = lopDAO.findAll();
        request.setAttribute("lopList", lopList);

        KhoaDAO khoaDAO = new KhoaDAO();
        List<Khoa> khoaList = khoaDAO.findAll();
        request.setAttribute("khoaList", khoaList);

        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/lop.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        LopDAO lopDAO = new LopDAO();

        String lop = request.getParameter("lop");

        String khoa = request.getParameter("khoa");

        Lop objL = new Lop(0, lop, new Khoa(khoa, null, null));
        int add = lopDAO.add(objL);
        if(add > 0) {
            response.sendRedirect(request.getContextPath()+"/admin/lop?msg=OK");
            return;
        }else {
            response.sendRedirect(request.getContextPath()+"/admin/lop?msg=ERROR");
            return;
        }

    }


}

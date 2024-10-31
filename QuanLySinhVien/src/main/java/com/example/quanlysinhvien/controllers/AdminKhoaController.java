package com.example.quanlysinhvien.controllers;

import com.example.quanlysinhvien.daos.KhoaDAO;
import com.example.quanlysinhvien.models.Khoa;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class AdminKhoaController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminKhoaController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("userInfor") == null) {
            response.sendRedirect(request.getContextPath()+"/auth/login");
            return;
        }

        KhoaDAO khoaDAO = new KhoaDAO();
        List<Khoa> khoaList = khoaDAO.findAll();
        request.setAttribute("khoaList", khoaList);

        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/khoa.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        KhoaDAO khoaDAO = new KhoaDAO();

        String maK = request.getParameter("maK");

        String tenK = request.getParameter("tenK");

        String lienhe = request.getParameter("lienhe");

        Khoa objK = new Khoa(maK, tenK, lienhe);
        int add = khoaDAO.add(objK);
        if(add > 0) {
            response.sendRedirect(request.getContextPath()+"/admin/khoa?msg=OK");
            return;
        }else {
            response.sendRedirect(request.getContextPath()+"/admin/khoa?msg=ERROR");
            return;
        }

    }


}


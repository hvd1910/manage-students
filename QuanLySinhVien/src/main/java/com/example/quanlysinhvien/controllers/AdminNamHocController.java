package com.example.quanlysinhvien.controllers;

import com.example.quanlysinhvien.daos.NamHocDAO;
import com.example.quanlysinhvien.models.NamHoc;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
public class AdminNamHocController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminNamHocController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("userInfor") == null) {
            response.sendRedirect(request.getContextPath()+"/auth/login");
            return;
        }
        NamHocDAO namHocDAO = new NamHocDAO();
        List<NamHoc> namHocList = namHocDAO.findAll();
        request.setAttribute("namHocList", namHocList);

        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/namhoc.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        NamHocDAO namHocDAO = new NamHocDAO();

        String maNH = request.getParameter("maNH");

        String tenNH = request.getParameter("tenNH");

        NamHoc objNH = new NamHoc(maNH, tenNH);
        int add = namHocDAO.add(objNH);
        if(add > 0) {
            response.sendRedirect(request.getContextPath()+"/admin/namhoc?msg=OK");
            return;
        }else {
            response.sendRedirect(request.getContextPath()+"/admin/namhoc?msg=ERROR");
            return;
        }

    }

}


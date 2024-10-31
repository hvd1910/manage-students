package com.example.quanlysinhvien.controllers;

import com.example.quanlysinhvien.daos.HocKyDAO;
import com.example.quanlysinhvien.models.HocKy;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;



public class AdminHocKyController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminHocKyController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("userInfor") == null) {
            response.sendRedirect(request.getContextPath()+"/auth/login");
            return;
        }

        HocKyDAO hoKyDAO = new HocKyDAO();
        List<HocKy> hockyList = hoKyDAO.findAll();
        request.setAttribute("hockyList", hockyList);


        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/hocky.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        HocKyDAO hocKyDAO = new HocKyDAO();

        String mahk = request.getParameter("mahk");

        String tenhk = request.getParameter("tenhk");

        HocKy objHK = new HocKy(mahk, tenhk);
        int add = hocKyDAO.add(objHK);
        if(add > 0) {
            response.sendRedirect(request.getContextPath()+"/admin/hocky?msg=OK");
            return;
        }else {
            response.sendRedirect(request.getContextPath()+"/admin/hocky?msg=ERROR");
            return;
        }

    }


}


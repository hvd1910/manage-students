package com.example.quanlysinhvien.controllers;

import com.example.quanlysinhvien.daos.TinChiDAO;
import com.example.quanlysinhvien.models.TinChi;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
public class AdminTinChiController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminTinChiController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("userInfor") == null) {
            response.sendRedirect(request.getContextPath()+"/auth/login");
            return;
        }

        TinChiDAO tinChiDAO = new TinChiDAO();
        List<TinChi> tinchiList = tinChiDAO.findAll();
        request.setAttribute("tinchiList", tinchiList);

        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/tinchi.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        TinChiDAO tinChiDAO = new TinChiDAO();

        String maTC = request.getParameter("maTC");

        int soTC = 0;
        try {
            soTC = Integer.parseInt(request.getParameter("soTC"));
        } catch (NumberFormatException e) {
            System.out.println("Lỗi ..!");
        }

        TinChi objTC = new TinChi(maTC, soTC);
        int add = tinChiDAO.add(objTC);
        if(add > 0) {
            response.sendRedirect(request.getContextPath()+"/admin/tinchi?msg=OK");
            return;
        }else {
            response.sendRedirect(request.getContextPath()+"/admin/tinchi?msg=ERROR");
            return;
        }

    }

}


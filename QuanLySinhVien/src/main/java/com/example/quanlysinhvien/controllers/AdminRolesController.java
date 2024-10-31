package com.example.quanlysinhvien.controllers;

import com.example.quanlysinhvien.daos.RolesDAO;
import com.example.quanlysinhvien.models.Roles;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class AdminRolesController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminRolesController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("userInfor") == null) {
            response.sendRedirect(request.getContextPath()+"/auth/login");
            return;
        }

        RolesDAO rolesDAO = new RolesDAO();
        List<Roles> rolesList = rolesDAO.findAll();
        request.setAttribute("rolesList", rolesList);

        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/roles.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        RolesDAO rolesDAO = new RolesDAO();

        String role = request.getParameter("role");

        Roles objR = new Roles(0, role);
        int add = rolesDAO.add(objR);
        if(add > 0) {
            response.sendRedirect(request.getContextPath()+"/admin/role?msg=OK");
            return;
        }else {
            response.sendRedirect(request.getContextPath()+"/admin/role?msg=ERROR");
            return;
        }

    }


}


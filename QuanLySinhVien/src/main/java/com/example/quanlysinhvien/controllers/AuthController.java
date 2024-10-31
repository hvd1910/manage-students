package com.example.quanlysinhvien.controllers;

import com.example.quanlysinhvien.daos.TaiKhoanDAO;
import com.example.quanlysinhvien.models.TaiKhoan;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


public class AuthController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AuthController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("/views/login/login.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

        String username = request.getParameter("username");
        String pass = request.getParameter("pass");

        //Kiểm tra thông tin đăng nhập
        TaiKhoan userInfor = taiKhoanDAO.findByUsernameAndPassword(username, pass);
        if(userInfor != null) {
            //đăng nhập đúng
            //Lưu thông tin đăng nhập(session)
            TaiKhoan taikhoan = taiKhoanDAO.viewAll(username);
            if(taikhoan != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userInfor",taikhoan);
                //Chuyển hướng đến trang index admin

                if ("Admin".equals(taikhoan.getRole().getRole())) {
                    response.sendRedirect(request.getContextPath() + "/admin");
                } else  {
                    response.sendRedirect(request.getContextPath() + "/giangvien/diem");
                }

            }
        }else {
            // đăng nhập sai
            response.sendRedirect(request.getContextPath()+"/auth/login?msg=ERROR");
            return;
        }

    }

}


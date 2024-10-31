package com.example.quanlysinhvien;

import java.io.*;

import com.example.quanlysinhvien.models.TaiKhoan;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "redirectServlet", value = "/")
public class RedirectServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get session and user information



        HttpSession session = request.getSession(false); // Use false to avoid creating a new session if it doesn't exist

        if (session != null && session.getAttribute("userInfor") != null) {
            TaiKhoan objTK = (TaiKhoan) session.getAttribute("userInfor");

            // Check user role and redirect accordingly
            if ("Admin".equals(objTK.getRole())) {
                response.sendRedirect(request.getContextPath() + "/admin");
            } else  {
                response.sendRedirect(request.getContextPath() + "/giangvien");
            }
        } else {
            // Redirect to login if no session or user is not logged in
            response.sendRedirect(request.getContextPath() + "/auth/login");
        }
    }
}


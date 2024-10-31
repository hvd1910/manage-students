package com.example.quanlysinhvien.controllers;

import com.example.quanlysinhvien.daos.GiangVienDAO;
import com.example.quanlysinhvien.daos.RolesDAO;
import com.example.quanlysinhvien.daos.TaiKhoanDAO;
import com.example.quanlysinhvien.models.Roles;
import com.example.quanlysinhvien.models.SinhVien;
import com.example.quanlysinhvien.models.TaiKhoan;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.management.relation.Role;
import java.io.IOException;
import java.util.List;
public class AdminTaiKhoanController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminTaiKhoanController() {
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
            TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
            String idToDelete = request.getParameter("id");
            if (idToDelete != null && !idToDelete.isEmpty()) {
                try {
                    taiKhoanDAO.delete(idToDelete);
                    response.sendRedirect(request.getContextPath() + "/admin/taikhoan?msg=OK");
                } catch (Exception e) {
                    e.printStackTrace(); // Log the exception
                    response.sendRedirect(request.getContextPath() + "/admin/taikhoan?msg=FAIL");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/taikhoan?msg=FAIL");
            }
        } else {
            TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
            List<TaiKhoan> taiKhoanList = taiKhoanDAO.findAll();
            request.setAttribute("taiKhoanList", taiKhoanList);

            RolesDAO rolesDAO = new RolesDAO();
            List<Roles> rolesList = rolesDAO.findAll();
            request.setAttribute("rolesList", rolesList);

            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/taikhoan.jsp");
            rd.forward(request, response);
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

        String username = request.getParameter("username");

        String matkhau = request.getParameter("password");

        String email = request.getParameter("email");
        RolesDAO rolesDAO = new RolesDAO();

        int id = Integer.parseInt(request.getParameter("role"));
        Roles role = rolesDAO.findById(id);





        String action = request.getParameter("action");
        String idTK = request.getParameter("id");

        if( idTK != null && "update".equalsIgnoreCase(action)) {

            try {
                TaiKhoan taiKhoanUpdate = taiKhoanDAO.findById(idTK);
                if (taiKhoanUpdate != null) {
                    taiKhoanUpdate.setEmail(email);
                    taiKhoanUpdate.setUsername(username);
                    if(matkhau != null) {
                        taiKhoanUpdate.setPassword(matkhau);
                    }
                    if(role != null) {
                        taiKhoanUpdate.setRole(role);
                    }

                    int update = taiKhoanDAO.update(taiKhoanUpdate);
                    if (update > 0) {
                        response.sendRedirect(request.getContextPath() + "/admin/taikhoan?msg=OK");
                        return;
                    } else {
                        response.sendRedirect(request.getContextPath() + "/admin/taikhoan?msg=ERROR");
                        return;
                    }
                }else {
                    response.sendRedirect(request.getContextPath() + "/admin/taikhoan?msg=ERROR");
                    return;
                }
            }catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/admin/taikhoan?msg=ERROR");
                return;
            }
        }



        TaiKhoan objTK = new TaiKhoan(0, username, matkhau, email,role);
        int add = taiKhoanDAO.add(objTK);
        if(add > 0) {
            response.sendRedirect(request.getContextPath()+"/admin/taikhoan?msg=OK");
            return;
        }else {
            response.sendRedirect(request.getContextPath()+"/admin/taikhoan?msg=ERROR");
            return;
        }

    }


}

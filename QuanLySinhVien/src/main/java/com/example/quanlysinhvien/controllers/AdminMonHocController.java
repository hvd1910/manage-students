package com.example.quanlysinhvien.controllers;

import com.example.quanlysinhvien.daos.MonHocDAO;
import com.example.quanlysinhvien.daos.TheLoaiDAO;
import com.example.quanlysinhvien.daos.TinChiDAO;
import com.example.quanlysinhvien.daos.GiangVienDAO;
import com.example.quanlysinhvien.models.MonHoc;
import com.example.quanlysinhvien.models.TheLoai;
import com.example.quanlysinhvien.models.TinChi;
import com.example.quanlysinhvien.models.GiangVien;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AdminMonHocController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminMonHocController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userInfor") == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }

        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            MonHocDAO monHocDAO = new MonHocDAO();
            String maMHToDelete = request.getParameter("maMH");
            if (maMHToDelete != null && !maMHToDelete.isEmpty()) {
                try {
                    monHocDAO.delete(maMHToDelete);
                    response.sendRedirect(request.getContextPath() + "/admin/monhoc?msg=OK");
                } catch (Exception e) {
                    e.printStackTrace(); // Log the exception
                    response.sendRedirect(request.getContextPath() + "/admin/monhoc?msg=FAIL");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/monhoc?msg=FAIL");
            }
        } else {

            MonHocDAO monHocDAO = new MonHocDAO();
            List<MonHoc> monHocList = monHocDAO.findAll();
            request.setAttribute("monHocList", monHocList);

            TinChiDAO tinchiDAO = new TinChiDAO();
            List<TinChi> tinchiList = tinchiDAO.findAll();
            request.setAttribute("tinchiList", tinchiList);

            TheLoaiDAO theloaiDAO = new TheLoaiDAO();
            List<TheLoai> theloaiList = theloaiDAO.findAll();
            request.setAttribute("theloaiList", theloaiList);

            GiangVienDAO giangVienDAO = new GiangVienDAO();
            List<GiangVien> giangVienList = giangVienDAO.findAll();
            request.setAttribute("giangvienList", giangVienList);

            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/monhoc.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        try  {
            MonHocDAO monHocDAO = new MonHocDAO();
            TinChiDAO tinchiDAO = new TinChiDAO();
            TheLoaiDAO theloaiDAO = new TheLoaiDAO();
            GiangVienDAO giangVienDAO = new GiangVienDAO();

            String maMH = request.getParameter("maMH");
            String tenMH = request.getParameter("tenMH");
            String maTC = request.getParameter("maTC");
            String maTL = request.getParameter("maTL");

            // Fetch associated entities
            TinChi tinchi = tinchiDAO.findById(maTC);
            TheLoai theloai = theloaiDAO.findById(maTL);

            // Fetch selected GiangVien IDs from the form and handle potential null values
            String[] giangVienIds = request.getParameterValues("giangVienIds");
            Set<GiangVien> giangviens = new HashSet<>();
            if (giangVienIds != null) {
                for (String id : giangVienIds) {
                    GiangVien gv = giangVienDAO.findById(id);
                    if (gv != null) {
                        giangviens.add(gv);
                    }
                }
            }

            // Create new MonHoc instance
            MonHoc objMH = new MonHoc(maMH, tenMH, tinchi, theloai, giangviens);

            // Decide on action
            String action = request.getParameter("action");
            switch (action) {
                case "add":
                    int addResult = monHocDAO.add(objMH);
                    if (addResult > 0) {
                        response.sendRedirect(request.getContextPath() + "/admin/monhoc?msg=OK");
                    } else {
                        response.sendRedirect(request.getContextPath() + "/admin/monhoc?msg=ERROR");
                    }
                    break;

                case "edit":
                    monHocDAO.update(objMH);
                    response.sendRedirect(request.getContextPath() + "/admin/monhoc?msg=OK");
                    break;

                case "delete":
                    String maMHToDelete = request.getParameter("maMH");
                    monHocDAO.delete(maMHToDelete);
                    response.sendRedirect(request.getContextPath() + "/admin/monhoc?msg=OK");
                    break;

                default:
                    response.sendRedirect(request.getContextPath() + "/admin/monhoc?msg=ERROR");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/monhoc?msg=ERROR");
        }
    }



}

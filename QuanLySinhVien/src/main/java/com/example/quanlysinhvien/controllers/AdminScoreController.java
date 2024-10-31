package com.example.quanlysinhvien.controllers;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;


import com.example.quanlysinhvien.daos.*;
import com.example.quanlysinhvien.dtos.DiemExcelDTO;
import com.example.quanlysinhvien.models.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@MultipartConfig
public class AdminScoreController extends HttpServlet {
    public static final String DIR_UPLOAD = "/uploads/score";

    private static final long serialVersionUID = 1L;

    public static final int COLUMN_INDEX_MASV = 0;
    public static final int COLUMN_INDEX_HOTEN = 1;
    public static final int COLUMN_INDEX_HS1 = 2;
    public static final int COLUMN_INDEX_HS3 = 3;
    public static final int COLUMN_INDEX_DIEMTHI = 4;
    public static final int COLUMN_INDEX_DIEMHP = 5;
    public static final int COLUMN_INDEX_LOP = 6;

    public AdminScoreController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session.getAttribute("userInfor") == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }


        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            DiemDAO diemDAO = new DiemDAO();
            String maBHToDelete = request.getParameter("maBH");
            if (maBHToDelete != null && !maBHToDelete.isEmpty()) {
                try {
                    diemDAO.delete(maBHToDelete);
                    response.sendRedirect(request.getContextPath() + "/admin/diem?msg=OK");
                } catch (Exception e) {
                    e.printStackTrace(); // Log the exception
                    response.sendRedirect(request.getContextPath() + "/admin/diem?msg=FAIL");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/diem?msg=FAIL");
            }
        } else {
            DiemDAO diemDAO = new DiemDAO();
            List<Diem> diems = diemDAO.findAll();
            request.setAttribute("diems", diems);


            // Data form add

            MonHocDAO monHocDAO = new MonHocDAO();
            List<MonHoc> monhocList = monHocDAO.findAll();
            request.setAttribute("monhocList", monhocList);

            HocKyDAO hockyDAO = new HocKyDAO();
            List<HocKy> hockyList = hockyDAO.findAll();
            request.setAttribute("hockyList", hockyList);

            NamHocDAO namhocDAO = new NamHocDAO();
            List<NamHoc> namhocList = namhocDAO.findAll();
            request.setAttribute("namhocList", namhocList);

            GiangVienDAO giangvienDAO = new GiangVienDAO();
            List<GiangVien> giangvienList = giangvienDAO.findAll();
            request.setAttribute("giangvienList", giangvienList);

            TinChiDAO tinchiDAO = new TinChiDAO();
            List<TinChi> tinchiList = tinchiDAO.findAll();
            request.setAttribute("tinchiList", tinchiList);

            TheLoaiDAO theloaiDAO = new TheLoaiDAO();
            List<TheLoai> theloaiList = theloaiDAO.findAll();
            request.setAttribute("theloaiList", theloaiList);

            if ("add".equalsIgnoreCase(action)) {
                // Forward to add form (diem.jsp)
                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/diem.jsp");
                rd.forward(request, response);
            } else {
                // Forward to list page (listdiem.jsp)
                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/listdiem.jsp");
                rd.forward(request, response);
            }
        }
        // Data list diem


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");


        DiemDAO diemDAO = new DiemDAO();
        MonHocDAO monHocDAO = new MonHocDAO();
        HocKyDAO hockyDAO = new HocKyDAO();
        NamHocDAO namhocDAO = new NamHocDAO();
        GiangVienDAO giangvienDAO = new GiangVienDAO();
        TinChiDAO tinchiDAO = new TinChiDAO();
        TheLoaiDAO theloaiDAO = new TheLoaiDAO();
        SinhVienDAO sinhVienDAO = new SinhVienDAO();
        LopDAO lopDAO = new LopDAO();

        String maMonHoc = request.getParameter("maMonHoc");
        String maHocKy = request.getParameter("maHocKy");
        String maNamhoc = request.getParameter("maNamhoc");
        String maGiangVien = request.getParameter("maGiangVien");
        String maTinChi = request.getParameter("maTinChi");
        String maTheLoai = request.getParameter("maTheLoai");


        String maBD = request.getParameter("maBD");
        String action = request.getParameter("action");
        Float heso1 = Float.valueOf(request.getParameter("heso1"));
        Float heso3 = Float.valueOf(request.getParameter("heso3"));
        Float heso6 = Float.valueOf(request.getParameter("heso6"));
        Float tongDiem = Float.valueOf(request.getParameter("tongDiem"));

        if( maBD!= null && "update".equalsIgnoreCase(action)) {
           try {
               Diem diemUpdate = diemDAO.findById(maBD);
               if (diemUpdate != null) {
                   diemUpdate.setHeso1(heso1);
                   diemUpdate.setHeso3(heso3);
                   diemUpdate.setHeso6(heso6);
                   diemUpdate.setTongDiem(tongDiem);

                   int update = diemDAO.update(diemUpdate);
                   if (update > 0) {
                       response.sendRedirect(request.getContextPath() + "/admin/diem?msg=OK");
                   } else {
                       response.sendRedirect(request.getContextPath() + "/admin/diem?msg=ERROR");
                   }
               }else {
                   response.sendRedirect(request.getContextPath() + "/admin/diem?msg=ERROR");
               }
           }catch (Exception e) {
               response.sendRedirect(request.getContextPath() + "/admin/diem?msg=ERROR");
           }
        }


        if("add".equalsIgnoreCase(action)) {
            List<SinhVien> sinhVienList = sinhVienDAO.findAll();
            List<Lop> lopList = lopDAO.findAll();

            // Create maps for quick lookup
            Map<Integer, SinhVien> sinhVienMap = new HashMap<>();
            Map<String, Lop> lopMap = new HashMap<>();

            for (SinhVien sinhVien : sinhVienList) {
                sinhVienMap.put(sinhVien.getMaSV(), sinhVien);
            }

            for (Lop lop : lopList) {
                lopMap.put(lop.getTenLop(), lop);
            }


            String filePath = null;
            Part part = request.getPart("fileExcel");
            String fileName = part.getSubmittedFileName();
            if (!"".equals(fileName)) {
                String dirPath = request.getServletContext().getRealPath("") + DIR_UPLOAD;
                File saveDir = new File(dirPath);
                if (!saveDir.exists()) {
                    saveDir.mkdirs();
                }
                filePath = dirPath + File.separator + fileName;
                part.write(filePath);
            }
            List<DiemExcelDTO> filexExx = readExcel(filePath);
            for (DiemExcelDTO diem : filexExx) {
                if (diem.getMaSV() <= 0) {
                    System.out.println("Lỗi null");
                    break;
                }

                // Find related objects
                GiangVien giangVien = giangvienDAO.findById(maGiangVien);
                MonHoc monHoc = monHocDAO.findById(maMonHoc);
                TinChi tinChi = tinchiDAO.findById(maTinChi);
                TheLoai theLoai = theloaiDAO.findById(maTheLoai);
                HocKy hocKy = hockyDAO.findById(maHocKy);
                NamHoc namHoc = namhocDAO.findById(maNamhoc);

                SinhVien sinhVien = sinhVienMap.get(diem.getMaSV());
                Lop lop = lopMap.get(diem.getTenLop());


                Diem objDiem = new Diem( 0, diem.getHeso1(), diem.getHeso3(), diem.getHeso6(), diem.getTongDiem(),
                        giangVien,
                        sinhVien,
                        monHoc,
                        tinChi,
                        theLoai,
                        hocKy,
                        namHoc,
                        lop);
                int add = diemDAO.nhapdiem(objDiem);
                if (add > 0) {
                    response.sendRedirect(request.getContextPath() + "/admin/diem?msg=OK");
                } else {
                    response.sendRedirect(request.getContextPath() + "/admin/diem?msg=ERROR");
                }
            }
        }




        if("delete".equalsIgnoreCase(action)) {
            String maBHToDelete = request.getParameter("maBH");
            diemDAO.delete(maBHToDelete);
            response.sendRedirect(request.getContextPath() + "/admin/diem?msg=OK");
        }


        }

    public static List<DiemExcelDTO> readExcel(String excelFilePath) throws IOException {
        List<DiemExcelDTO> diemList = new ArrayList<>();
        InputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = getWorkbook(inputStream, excelFilePath);

        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            int nextRow = row.getRowNum();
            if (nextRow == 0) {
                continue;
            }
            DiemExcelDTO diem = new DiemExcelDTO();
            for (Cell cell : row) {
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case COLUMN_INDEX_MASV:
                        int msv = new BigDecimal((double) cellValue).intValue();
                        diem.setMaSV(msv);
                        System.out.println("msv: " + msv);
                        break;
                    case COLUMN_INDEX_HOTEN:
                        String hoTen = getCellValue(cell).toString();
                        System.out.print("hoten: " + hoTen);
                        break;
                    case COLUMN_INDEX_HS1:
                        String hs1 = getCellValue(cell).toString();
                        diem.setHeso1(Float.parseFloat(hs1));
                        System.out.print(hs1);
                        break;
                    case COLUMN_INDEX_HS3:
                        String hs3 = getCellValue(cell).toString();
                        diem.setHeso3(Float.parseFloat(hs3));
                        System.out.print(hs3);
                        break;
                    case COLUMN_INDEX_DIEMTHI:
                        String diemThi = getCellValue(cell).toString();
                        diem.setHeso6(Float.parseFloat(diemThi));
                        System.out.print(diemThi);
                        break;
                    case COLUMN_INDEX_DIEMHP:
                        String diemHP = getCellValue(cell).toString();
                        diem.setTongDiem(Float.parseFloat(diemHP));
                        System.out.print(diemHP);
                        break;
                    case COLUMN_INDEX_LOP:
                        String lop = getCellValue(cell).toString();
                        diem.setTenLop(lop);
                        System.out.print(lop);
                        break;
                    default:
                        break;
                }
            }
            System.out.println();
            diemList.add(diem);
        }
        workbook.close();
        inputStream.close();

        return diemList;
    }

    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not an Excel file");
        }
        return workbook;
    }

    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                return cell.getStringCellValue();
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return null;
            case ERROR:
                return cell.getErrorCellValue();
            default:
                return null;
        }
    }
}


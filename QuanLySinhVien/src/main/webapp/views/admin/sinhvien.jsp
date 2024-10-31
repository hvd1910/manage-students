<%@page import="com.example.quanlysinhvien.models.Lop"%>
<%@page import="com.example.quanlysinhvien.models.SinhVien"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@include file="/templates/admin/inc/header.jsp" %>

<!-- Main Sidebar Container -->
<%@include file="/templates/admin/inc/menu.jsp" %>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0">Sinh viên</h1>
                </div><!-- /.col -->
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
                        <li class="breadcrumb-item active">Sinh viên</li>
                    </ol>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
        <div class="container-fluid">
            <%
                if(!"".equals(request.getParameter("msg"))){
                    String msg = request.getParameter("msg");
                    if("OK".equals(msg)){
            %>
            <div class="alert alert-success" role="alert">
                Xử lý thành công..!
            </div>
            <%
            } else if("ERROR".equals(msg)){
            %>
            <div class="alert alert-danger" role="alert">
                Xử lý thất bại..!
            </div>
            <%
                    }
                }
            %>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalSinhVien" onclick="openModal(null)">Thêm</button>
            <table class="table table-bordered" id="datatable">
                <thead class="thead-CCFFFF">
                <tr class="list-header">
                    <th scope="col">#</th>
                    <th scope="col">Mã Sinh viên</th>
                    <th scope="col">Tên Sinh viên</th>
                    <th scope="col">Địa chỉ</th>
                    <th scope="col">Số điện thoại</th>
                    <th scope="col">Lớp</th>
                    <th scope="col">Chức năng</th>
                </tr>
                </thead>
                <tbody>
                <%
                    if(request.getAttribute("sinhvienList") != null){
                        List<SinhVien> sinhvienList = (List<SinhVien>) request.getAttribute("sinhvienList");
                        if(sinhvienList.size() > 0){
                            for(SinhVien objSV : sinhvienList){
                %>
                <tr class="tr-hover">
                    <th scope="row"><input type="checkbox" name="vehicle1" value="Bike"></th>
                    <td><%=objSV.getMaSV() %></td>
                    <td><%=objSV.getTenSV() %></td>
                    <td><%=objSV.getDiaChi() %></td>
                    <td>0<%=objSV.getSdt() %></td>
                    <td><%=objSV.getLop().getTenLop() %></td>
                    <td>
                        <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#modalSinhVien"
                                onclick='openModal({
                                        "maSV": "<%=objSV.getMaSV()%>",
                                        "tenSV": "<%=objSV.getTenSV()%>",
                                        "diaChi": "<%=objSV.getDiaChi()%>",
                                        "sdt": "<%=objSV.getSdt()%>",
                                        "email": "<%=objSV.getEmail()%>",
                                        "maLop": "<%=objSV.getLop().getMaLop()%>",
                                        "tenLop": "<%=objSV.getLop().getTenLop()%>"
                                        })'
                        >
                            Cập nhật
                        </button>
                        <a href="<%= request.getContextPath() %>/admin/sinhvien?action=delete&maSV=<%= objSV.getMaSV() %>" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa điểm này?');">Xóa</a>
                    </td>
                </tr>
                <%
                            }
                        }
                    }
                %>
                </tbody>
            </table>


        </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->

    <!-- Modal -->
    <div class="modal fade" id="modalSinhVien" tabindex="-1" role="dialog" aria-labelledby="modalSinhVienLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalSinhVienLabel">Thêm sinh viên</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="sinhVienForm" action ="<%=request.getContextPath()%>/admin/sinhvien" method="post">
                        <!-- Hidden input để xác định chế độ -->
                        <input type="hidden" id="mode" name="action" value="add">

                        <div class="form-group row">
                            <label for="maSV" class="col-sm-3">Mã sinh viên</label>
                            <input type="text" class="form-control col-sm-8" id="maSV" name="masv" required disabled>
                            <input type="hidden" class="form-control col-sm-8" id="maSVupdate" name="maSVupdate"  >
                        </div>
                        <div class="form-group row">
                            <label for="tenSV" class="col-sm-3">Tên sinh viên</label>
                            <input type="text" class="form-control col-sm-8" id="tenSV" name="tensv" required>
                        </div>
                        <div class="form-group row">
                            <label for="diaChi" class="col-sm-3">Địa chỉ</label>
                            <input type="text" class="form-control col-sm-8" id="diaChi" name="diachi" required>
                        </div>
                        <div class="form-group row">
                            <label for="sdt" class="col-sm-3">Số điện thoại</label>
                            <input type="text" class="form-control col-sm-8" id="sdt" name="sdt" required>
                        </div>
                        <div class="form-group row">
                            <label for="email" class="col-sm-3">Email</label>
                            <input type="email" class="form-control col-sm-8" id="email" name="email" required>
                        </div>
                        <div class="form-group row">
                            <label for="lop" class="col-sm-3">Lớp</label>
                            <%
                                if(request.getAttribute("lopList") != null){
                                    List<Lop> lopList = (List<Lop>) request.getAttribute("lopList");
                                    if(lopList.size() > 0){
                            %>
                            <select class="form-control col-sm-8" id="lop" name="malop" required>
                                <%
                                    for(Lop objLop : lopList){
                                %>
                                <option value="<%=objLop.getMaLop()%>"><%=objLop.getTenLop()%></option>
                                <%
                                    }
                                %>
                            </select>
                            <%
                                    }
                                }
                            %>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Thoát</button>
                            <button type="submit" class="btn btn-primary" id="submitBtn">Thêm</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript">
    function openModal(sinhVien) {

        if (sinhVien) {

            // Chế độ cập nhật
            document.getElementById("mode").value = "update";
            document.getElementById("modalSinhVienLabel").innerText = "Cập nhật sinh viên";
            document.getElementById("submitBtn").innerText = "Cập nhật";
            // Điền dữ liệu từ đối tượng sinhVien vào form
            document.getElementById("maSV").value = sinhVien.maSV;
            document.getElementById("maSVupdate").value = sinhVien.maSV;
            document.getElementById("tenSV").value = sinhVien.tenSV;
            document.getElementById("diaChi").value = +sinhVien.diaChi;
            document.getElementById("sdt").value = '0'+ sinhVien.sdt;
            document.getElementById("email").value = sinhVien.email;
            document.getElementById("lop").value = sinhVien.maLop;
        } else {
            // Chế độ thêm mới
            document.getElementById("mode").value = "add";
            document.getElementById("modalSinhVienLabel").innerText = "Thêm sinh viên";
            document.getElementById("submitBtn").innerText = "Thêm";

            // Xóa sạch dữ liệu trong form
            document.getElementById("sinhVienForm").reset();
            document.getElementById("maSV").disabled = false;
        }
    }


</script>

<!-- /.content-wrapper -->
<%@include file="/templates/admin/inc/footer.jsp" %>

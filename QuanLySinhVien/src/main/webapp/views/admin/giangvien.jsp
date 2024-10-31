<%@page import="com.example.quanlysinhvien.models.GiangVien"%>
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
                    <h1 class="m-0">Giảng viên</h1>
                </div><!-- /.col -->
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
                        <li class="breadcrumb-item active">Giảng viên</li>
                    </ol>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
        <div class="container-fluid">
            <% if(!"".equals(request.getParameter("msg"))) {
                String msg = request.getParameter("msg");
                if("OK".equals(msg)) { %>
            <div class="alert alert-success" role="alert">
                Xử lý thành công..!
            </div>
            <% } else if ("ERROR".equals(msg)) { %>
            <div class="alert alert-danger" role="alert">
                Xử lý thất bại..!
            </div>
            <% } } %>

            <!-- Add or Update button -->
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalGiangVien" onclick="openModal(null)">Thêm</button>

            <table class="table table-bordered" id="datatable">
                <thead class="thead-CCFFFF">
                <tr class="list-header">
                    <th scope="col">#</th>
                    <th scope="col">Mã Giảng viên</th>
                    <th scope="col">Tên Giảng viên</th>
                    <th scope="col">Địa chỉ</th>
                    <th scope="col">Số điện thoại</th>
                    <th scope="col">Chức năng</th>
                </tr>
                </thead>
                <tbody>
                <% if(request.getAttribute("giangvienList") != null) {
                    List<GiangVien> giangvienList = (List<GiangVien>) request.getAttribute("giangvienList");
                    if(giangvienList.size() > 0) {
                        for(GiangVien objGV : giangvienList) { %>
                <tr class="tr-hover">
                    <th scope="row"><input type="checkbox" name="vehicle1" value="Bike"></th>
                    <td><%=objGV.getMaGV() %></td>
                    <td><%=objGV.getTenGV() %></td>
                    <td><%=objGV.getDiaChi() %></td>
                    <td>0<%=objGV.getSdt() %></td>
                    <td>
                        <!-- Update button -->
                        <button type="button" class="btn btn-warning"
                                data-toggle="modal" data-target="#modalGiangVien"
                                onclick="openModal({
                                        maGV: '<%=objGV.getMaGV()%>',
                                        tenGV: '<%=objGV.getTenGV()%>',
                                        diaChi: '<%=objGV.getDiaChi()%>',
                                        sdt: '<%=objGV.getSdt()%>',
                                        email: '<%=objGV.getEmail()%>'
                                        })">Cập nhật</button>
                        <!-- Delete button -->
                        <a href="<%= request.getContextPath() %>/admin/giangvien?action=delete&maGV=<%= objGV.getMaGV() %>" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa điểm này?');">Xóa</a>
                    </td>
                </tr>
                <%      }
                }
                } %>
                </tbody>
            </table>

        </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->

    <!-- Modal for Add/Update GiangVien -->
    <div class="modal fade" id="modalGiangVien" tabindex="-1" role="dialog" aria-labelledby="modalGiangVienLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalGiangVienLabel">Thêm Giảng Viên</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="giangVienForm" action="<%=request.getContextPath()%>/admin/giangvien" method="post">
                        <!-- Hidden field for handling updates -->
                        <input type="hidden" id="mode" name="action" value="add">

                        <div class="form-group row">
                            <label for="maGV" class="col-sm-3">Mã Giảng viên</label>
                            <input type="text" class="form-control col-sm-8" id="maGV" name="maGV" required>
                            <input type="hidden" class="form-control col-sm-8" id="maGVupdate" name="maGVupdate"  >
                        </div>
                        <div class="form-group row">
                            <label for="tenGV" class="col-sm-3">Tên Giảng viên</label>
                            <input type="text" class="form-control col-sm-8" id="tenGV" name="tenGV" required>
                        </div>
                        <div class="form-group row">
                            <label for="diaChi" class="col-sm-3">Địa chỉ</label>
                            <input type="text" class="form-control col-sm-8" id="diaChi" name="diaChi" required>
                        </div>
                        <div class="form-group row">
                            <label for="sdt" class="col-sm-3">Số điện thoại</label>
                            <input type="text" class="form-control col-sm-8" id="sdt" name="sdt" required>
                        </div>
                        <div class="form-group row">
                            <label for="email" class="col-sm-3">Email</label>
                            <input type="email" class="form-control col-sm-8" id="email" name="email" required>
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
    // Function to handle the form switch between add and update
    function openModal(giangVien) {

        if (giangVien) {
            // Chế độ cập nhật
            document.getElementById("mode").value = "update";
            document.getElementById("modalGiangVienLabel").innerText = "Cập nhật giảng viên";
            document.getElementById("submitBtn").innerText = "Cập nhật";

            // Điền dữ liệu từ đối tượng giangVien vào form
            document.getElementById("maGVupdate").value = giangVien.maGV;
            document.getElementById("maGV").value = giangVien.maGV;
            document.getElementById("tenGV").value = giangVien.tenGV;
            document.getElementById("diaChi").value = giangVien.diaChi;
            document.getElementById("sdt").value = '0' + giangVien.sdt;
            document.getElementById("email").value = giangVien.email;

            // Ẩn trường Mã Giảng viên khi cập nhật
            document.getElementById("maGV").disabled = true;
        } else {
            // Chế độ thêm mới
            document.getElementById("mode").value = "add";
            document.getElementById("modalGiangVienLabel").innerText = "Thêm giảng viên";
            document.getElementById("submitBtn").innerText = "Thêm";

            // Xóa sạch dữ liệu trong form
            document.getElementById("giangVienForm").reset();
            document.getElementById("maGV").disabled = false;
        }
    }

    // Reset form khi đóng modal
    $('#modalGiangVien').on('hidden.bs.modal', function () {
        document.getElementById("giangVienForm").reset();
        document.getElementById("maGV").disabled = false;
        document.getElementById("mode").value = "add";
        document.getElementById("modalGiangVienLabel").innerText = "Thêm giảng viên";
        document.getElementById("submitBtn").innerText = "Thêm";
    });
</script>

<%@include file="/templates/admin/inc/footer.jsp" %>

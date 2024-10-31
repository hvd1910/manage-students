<%@page import="com.example.quanlysinhvien.models.Diem"%>
<%@page import="com.example.quanlysinhvien.models.SinhVien"%>
<%@page import="com.example.quanlysinhvien.models.MonHoc"%>
<%@page import="java.util.List" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/templates/admin/inc/header.jsp" %>
<%@include file="/templates/admin/inc/menu.jsp" %>

<div class="content-wrapper">
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0">Danh Sách Điểm</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
                        <li class="breadcrumb-item active">Danh Sách Điểm</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>

    <section class="content">
        <div class="container-fluid">
            <%
                String msg = request.getParameter("msg");
                if (msg != null && !msg.isEmpty()) {
                    String alertClass = "alert-danger";
                    String alertMessage = "Xử lý thất bại..!";
                    if ("OK".equals(msg)) {
                        alertClass = "alert-success";
                        alertMessage = "Xử lý thành công..!";
                    }
            %>
            <div class="alert <%= alertClass %>" role="alert">
                <%= alertMessage %>
            </div>
            <% } %>
            <table class="table table-bordered" id="datatable">
                <thead class="thead-CCFFFF">
                <tr class="list-header">
                    <th scope="col">#</th>
                    <th scope="col">Mã BD</th>
                    <th scope="col">Mã Sinh Viên</th>
                    <th scope="col">Tên Sinh Viên</th>
                    <th scope="col">Môn Học</th>
                    <th scope="col">Học Kỳ</th>
                    <th scope="col">Điểm HS 1</th>
                    <th scope="col">Điểm HS 3</th>
                    <th scope="col">Điểm HS 6</th>
                    <th scope="col">Tổng Điểm</th>
                    <th scope="col">Chức năng</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Diem> diemList = (List<Diem>) request.getAttribute("diems");
                    if (diemList != null && !diemList.isEmpty()) {
                        for (Diem objDiem : diemList) {
                            SinhVien sinhVien = objDiem.getSinhvien();
                            MonHoc monHoc = objDiem.getMonhoc();
                %>
                <tr class="tr-hover">
                    <th scope="row"><input type="checkbox" name="selectedDiem" value="<%= objDiem.getMaBD() %>"></th>
                    <td><%= objDiem.getMaBD() %></td>
                    <td><%= sinhVien.getMaSV() %></td>
                    <td><%= sinhVien.getTenSV() %></td>
                    <td><%= monHoc.getTenMH() %></td>
                    <td><%= objDiem.getHocky().getTenHK() %></td>
                    <td><%= objDiem.getHeso1() %></td>
                    <td><%= objDiem.getHeso3() %></td>
                    <td><%= objDiem.getHeso6() %></td>
                    <td><%= objDiem.getTongDiem() %></td>

                    <td>
                        <a href="#" class="btn btn-warning suaDiem"
                           data-diem='{"maBD": "<%= objDiem.getMaBD() %>", "maSV": "<%= objDiem.getSinhvien().getMaSV() %>",
                   "tenSV": "<%= objDiem.getSinhvien().getTenSV() %>", "tenMH": "<%= objDiem.getMonhoc().getTenMH() %>",
                   "tenHK": "<%= objDiem.getHocky().getTenHK() %>", "heso1": "<%= objDiem.getHeso1() %>",
                   "heso3": "<%= objDiem.getHeso3() %>", "heso6": "<%= objDiem.getHeso6() %>",
                   "tongDiem": "<%= objDiem.getTongDiem() %>"}'
                           data-toggle="modal" data-target="#modalThem">Cập nhật</a>
                        <a href="<%= request.getContextPath() %>/admin/diem?action=delete&maBH=<%= objDiem.getMaBD() %>" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa điểm này?');">Xóa</a>
                    </td>
                </tr>
                <% } } %>
                </tbody>
            </table>
        </div>
    </section>

    <!-- Modal -->
    <div class="modal fade" id="modalThem" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Thêm Điểm</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="diemForm" action="${pageContext.request.contextPath}/admin/diem" method="post">
                        <!-- Hidden field to distinguish between add and update -->
                        <input type="hidden" name="action" id="formAction" value="add">

                        <div class="form-group row">
                            <label for="maBDindex" class="col-sm-3">Mã Bảng Điểm</label>
                            <input type="text" class="form-control col-sm-8" id="maBDindex" name="maBDindex" required disabled>
                        </div>

                        <input type="hidden"  id="maBD" name="maBD">

                        <div class="form-group row">
                            <label for="maSV" class="col-sm-3">Mã Sinh Viên</label>
                            <input type="text" class="form-control col-sm-8" id="maSV" name="maSV" required disabled>
                        </div>

                        <div class="form-group row">
                            <label for="tenSV" class="col-sm-3">Tên Sinh Viên</label>
                            <input type="text" class="form-control col-sm-8" id="tenSV" name="tenSV" required disabled>
                        </div>

                        <div class="form-group row">
                            <label for="tenMH" class="col-sm-3">Môn Học</label>
                            <input type="text" class="form-control col-sm-8" id="tenMH" name="tenMH" required disabled>
                        </div>
                        <div class="form-group row">
                            <label for="tenMH" class="col-sm-3"> Học Kỳ</label>
                            <input type="text" class="form-control col-sm-8" id="tenHK" name="tenHK" required disabled>
                        </div>

                        <div class="form-group row">
                            <label for="heso1" class="col-sm-3">Hệ Số 1</label>
                            <input type="text" class="form-control col-sm-8" id="heso1" name="heso1" required>
                        </div>

                        <div class="form-group row">
                            <label for="heso3" class="col-sm-3">Hệ Số 3</label>
                            <input type="text" class="form-control col-sm-8" id="heso3" name="heso3" required>
                        </div>

                        <div class="form-group row">
                            <label for="heso6" class="col-sm-3">Hệ Số 6</label>
                            <input type="text" class="form-control col-sm-8" id="heso6" name="heso6" required>
                        </div>

                        <div class="form-group row">
                            <label for="tongDiem" class="col-sm-3">Tổng Điểm</label>
                            <input type="text" class="form-control col-sm-8" id="tongDiem" name="tongDiem" required>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                            <button type="submit" class="btn btn-primary">Lưu</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
<%@include file="/templates/admin/inc/footer.jsp" %>

<script>
    $(document).ready(function() {
        // Khi nhấn nút 'Cập nhật'
        $('#datatable').on('click', '.suaDiem', function() {
            var objDiem = $(this).data('diem');


            $('#modalThem .modal-title').text('Cập nhật Điểm');
            $('#formAction').val('update');
            $('#maBD').val(objDiem.maBD); // Cập nhật mã BD
            $('#maBDindex').val(objDiem.maBD); // Chỉ hiển thị
            $('#maSV').val(objDiem.maSV); // Cập nhật mã sinh viên
            $('#tenSV').val(objDiem.tenSV); // Cập nhật tên sinh viên
            $('#tenMH').val(objDiem.tenMH); // Cập nhật tên môn học
            $('#tenHK').val(objDiem.tenHK); // Cập nhật tên học kỳ
            $('#heso1').val(objDiem.heso1); // Cập nhật hệ số 1
            $('#heso3').val(objDiem.heso3); // Cập nhật hệ số 3
            $('#heso6').val(objDiem.heso6); // Cập nhật hệ số 6
            $('#tongDiem').val(objDiem.tongDiem); // Cập nhật tổng điểm

            $('#modalThem').modal('show');
        });


    });
</script>

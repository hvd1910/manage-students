<%@page import="com.example.quanlysinhvien.models.GiangVien"%>
<%@page import="com.example.quanlysinhvien.models.TinChi"%>
<%@page import="com.example.quanlysinhvien.models.MonHoc"%>
<%@page import="com.example.quanlysinhvien.models.TheLoai"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Arrays" %>
<%@page import="java.util.Set" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/templates/admin/inc/header.jsp" %>
<%@include file="/templates/admin/inc/menu.jsp" %>

<div class="content-wrapper">
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0">Môn Học</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
                        <li class="breadcrumb-item active">Môn Học</li>
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
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalThem">Thêm</button>
            <table class="table table-bordered" id="datatable">
                <thead class="thead-CCFFFF">
                <tr class="list-header">
                    <th scope="col">#</th>
                    <th scope="col">Mã Môn học</th>
                    <th scope="col">Môn Học</th>
                    <th scope="col">Số Tín Chỉ</th>
                    <th scope="col">Thể Loại</th>
                    <th scope="col">Giảng Viên</th>
                    <th scope="col">Chức năng</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<MonHoc> monHocList = (List<MonHoc>) request.getAttribute("monHocList");
                    if (monHocList != null && !monHocList.isEmpty()) {
                        for (MonHoc objMH : monHocList) {
                %>
                <tr class="tr-hover">
                    <th scope="row"><input type="checkbox" name="selectedMonHoc" value="<%= objMH.getMaMH() %>"></th>
                    <td><%= objMH.getMaMH() %></td>
                    <td><%= objMH.getTenMH() %></td>
                    <td><%= objMH.getTinchi().getSoTC() %></td>
                    <td><%= objMH.getTheloai().getTenTL() %></td>
                    <td>
                        <%
                            for (GiangVien giangVien : objMH.getGiangviens()) {
                        %>
                        <%= giangVien.getTenGV() %>
                        <%
                            }
                        %>
                    </td>
                    <td>
                        <a href="#" class="btn btn-warning suaMenu" data-id="<%= objMH.getMaMH() %>" data-toggle="modal" data-target="#modalThem">Cập nhật</a>
                        <a href="<%= request.getContextPath() %>/admin/monhoc?action=delete&maMH=<%= objMH.getMaMH() %>" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa môn học này?');">Xóa</a>
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
                    <h5 class="modal-title" id="exampleModalLabel">Thêm Môn Học</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="monHocForm" action="${pageContext.request.contextPath}/admin/monhoc" method="post">
                        <!-- Hidden field to distinguish between add and update -->
                        <input type="hidden" name="action" id="formAction" value="add">

                        <div class="form-group row">
                            <label for="maMH" class="col-sm-3">Mã Môn Học</label>
                            <input type="text" class="form-control col-sm-8" id="maMH" name="maMH" required>
                        </div>
                        <div class="form-group row">
                            <label for="tenMH" class="col-sm-3">Tên Môn Học</label>
                            <input type="text" class="form-control col-sm-8" id="tenMH" name="tenMH" required>
                        </div>
                        <div class="form-group row">
                            <label for="maTC" class="col-sm-3">Số Tín Chỉ</label>
                            <select class="form-control col-sm-8" id="maTC" name="maTC" required>
                                <%
                                    List<TinChi> tinchiList = (List<TinChi>) request.getAttribute("tinchiList");
                                    if (tinchiList != null && !tinchiList.isEmpty()) {
                                        for (TinChi objTC : tinchiList) {
                                %>
                                <option value="<%= objTC.getMaTC() %>"><%= objTC.getSoTC() %></option>
                                <% } } %>
                            </select>
                        </div>
                        <div class="form-group row">
                            <label for="maTL" class="col-sm-3">Thể Loại</label>
                            <select class="form-control col-sm-8" id="maTL" name="maTL" required>
                                <%
                                    List<TheLoai> theloaiList = (List<TheLoai>) request.getAttribute("theloaiList");
                                    if (theloaiList != null && !theloaiList.isEmpty()) {
                                        for (TheLoai objTL : theloaiList) {
                                %>
                                <option value="<%= objTL.getMaTL() %>"><%= objTL.getTenTL() %></option>
                                <% } } %>
                            </select>
                        </div>
                        <div class="form-group row">
                            <label for="giangVienIds" class="col-sm-3">Giảng Viên</label>
                            <select class="form-control col-sm-8" id="giangVienIds" name="giangVienIds"  required>
                                <%
                                    List<GiangVien> giangvienList = (List<GiangVien>) request.getAttribute("giangvienList");
                                    if (giangvienList != null && !giangvienList.isEmpty()) {
                                        for (GiangVien objGV : giangvienList) {
                                %>
                                <option value="<%= objGV.getMaGV() %>"><%= objGV.getTenGV() %></option>
                                <% } } %>
                            </select>
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
        $('#datatable').on('click', '.suaMenu', function() {
            var row = $(this).closest('tr');
            var maMH = $(this).data('id');
            var tenMH = row.find('td:eq(1)').text().trim();
            var tinchi = row.find('td:eq(2)').text().trim();
            var theloai = row.find('td:eq(3)').text().trim();
            var giangviens = row.find('td:eq(4)').html(); // Nội dung HTML chứa <br>

            $('#modalThem .modal-title').text('Cập nhật Môn Học');
            $('#formAction').val('edit');
            $('#tenMH').val(tenMH);
            $('#maMH').val(maMH);
            $('#maTC').val(tinchi);
            $('#maTL').val(theloai);

            // Set giá trị cho giảng viên (cần logic tương ứng để xử lý)
            $('#giangVienIds').val(getGiangVienValues(giangviens));

            $('#modalThem').modal('show');
        });

        // Khi nhấn nút 'Thêm'
        $('#modalThem').on('hidden.bs.modal', function() {
            $('#monHocForm')[0].reset();
            $('#formAction').val('add');
            $('#modalThem .modal-title').text('Thêm Môn Học');
        });

        // Hàm để lấy giá trị giảng viên từ HTML
        function getGiangVienValues(giangviensHtml) {
            var giangVienValues = [];
            $(giangviensHtml).each(function() {
                giangVienValues.push($(this).text().trim());
            });
            return giangVienValues;
        }
    });
</script>
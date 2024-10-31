<%@page import="com.example.quanlysinhvien.models.Roles"%>
<%@page import="com.example.quanlysinhvien.models.TaiKhoan"%>
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
                    <h1 class="m-0">Tài Khoản</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
                        <li class="breadcrumb-item active">Tài khoản</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>

    <!-- Main content -->
    <section class="content">
        <div class="container-fluid">
            <%
                String msg = request.getParameter("msg");
                if ("OK".equals(msg)) {
            %>
            <div class="alert alert-success" role="alert">
                Xử lý thành công..!
            </div>
            <%
            } else if ("ERROR".equals(msg)) {
            %>
            <div class="alert alert-danger" role="alert">
                Xử lý thất bại..!
            </div>
            <%
                }
            %>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalTaiKhoan" onclick="openModal(null)">Thêm</button>
            <table class="table table-bordered" id="datatable">
                <thead class="thead-CCFFFF">
                <tr class="list-header">
                    <th scope="col">#</th>
                    <th scope="col">Tài khoản</th>
                    <th scope="col">Email</th>
                    <th scope="col">Loại tài khoản</th>
                    <th scope="col">Chức năng</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<TaiKhoan> taiKhoanList = (List<TaiKhoan>) request.getAttribute("taiKhoanList");
                    if (taiKhoanList != null && !taiKhoanList.isEmpty()) {
                        for (TaiKhoan objTK : taiKhoanList) {
                %>
                <tr class="tr-hover">
                    <th scope="row"><input type="checkbox" name="vehicle1" value="Bike"></th>
                    <td><%= objTK.getUsername() %></td>
                    <td><%= objTK.getEmail() %></td>
                    <td><%= objTK.getRole().getRole() %></td>
                    <td>
                        <button type="button" class="btn btn-warning"
                                data-toggle="modal" data-target="#modalTaiKhoan"
                                onclick="openModal({
                                        id: '<%= objTK.getId() %>',
                                        username: '<%= objTK.getUsername() %>',
                                        email: '<%= objTK.getEmail() %>',
                                        role: '<%= objTK.getRole().getId() %>'
                                        })">Cập nhật</button>
                        <a href="<%= request.getContextPath() %>/admin/taikhoan?action=delete&id=<%= objTK.getId() %>" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa điểm này?');">Xóa</a>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>
    </section>

    <!-- Modal for Add/Update TaiKhoan -->
    <div class="modal fade" id="modalTaiKhoan" tabindex="-1" role="dialog" aria-labelledby="modalTaiKhoanLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalTaiKhoanLabel">Tài Khoản</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="taiKhoanForm" action="<%= request.getContextPath() %>/admin/taikhoan" method="post">
                        <input type="hidden" id="mode" name="action" value="add">
                        <input type="hidden" id="id" name="id">

                        <div class="form-group row">
                            <label for="username" class="col-sm-3">Tài Khoản</label>
                            <input type="text" class="form-control col-sm-8" id="username" name="username" required>
                        </div>
                        <div class="form-group row">
                            <label for="password" class="col-sm-3">Mật Khẩu</label>
                            <input type="password" class="form-control col-sm-8" id="password" name="password" >
                        </div>
                        <div class="form-group row">
                            <label for="email" class="col-sm-3">Email</label>
                            <input type="email" class="form-control col-sm-8" id="email" name="email" required>
                        </div>
                        <div class="form-group row">
                            <label for="role" class="col-sm-3">Loại Tài Khoản</label>
                            <select class="form-control col-sm-8" id="role" name="role" required>
                                <%
                                    List<Roles> rolesList = (List<Roles>) request.getAttribute("rolesList");
                                    if (rolesList != null && !rolesList.isEmpty()) {
                                        for (Roles objR : rolesList) {
                                %>
                                <option value="<%= objR.getId() %>"><%= objR.getRole() %></option>
                                <%
                                        }
                                    }
                                %>
                            </select>
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
    function openModal(taiKhoan) {
        var modalTitle = document.querySelector('.modal-title');
        var submitBtn = document.getElementById('submitBtn');
        var modeField = document.getElementById('mode');
        var taiKhoanForm = document.getElementById('taiKhoanForm');

        if (taiKhoan) {
            // Update mode
            modeField.value = "update";
            modalTitle.innerText = "Cập nhật tài khoản";
            submitBtn.innerText = "Cập nhật";

            // Populate form fields
            document.getElementById('id').value = taiKhoan.id;
            document.getElementById('username').value = taiKhoan.username;
            document.getElementById('email').value = taiKhoan.email;
            document.getElementById('role').value = taiKhoan.role;

            // Disable the username field to prevent changes
        } else {
            // Add mode
            modeField.value = "add";
            modalTitle.innerText = "Thêm tài khoản";
            submitBtn.innerText = "Thêm";

            // Clear and enable form fields
            taiKhoanForm.reset();
            document.getElementById('password').required = true

        }
    }

    // Reset form when closing the modal
    $('#modalTaiKhoan').on('hidden.bs.modal', function () {
        document.getElementById('taiKhoanForm').reset();
        document.getElementById('mode').value = "add";
        document.getElementById('modalTaiKhoanLabel').innerText = "Tài Khoản";
        document.getElementById('submitBtn').innerText = "Thêm";
    });
</script>

<%@include file="/templates/admin/inc/footer.jsp" %>

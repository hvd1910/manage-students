<%@ page import="com.example.quanlysinhvien.models.TaiKhoan" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>

  <script type="text/javascript">
    // Chỉ thực hiện chuyển hướng nếu trang hiện tại là trang gốc "/"
    if (window.location.pathname === "/") {
      window.location.href = "/admin";
    }
  </script>
<%--  <script type="text/javascript">--%>
<%--    <%--%>
<%--      if (session.getAttribute("userInfor") != null) {--%>
<%--          TaiKhoan objTK = (TaiKhoan) session.getAttribute("userInfor");--%>
<%--          String userRole = objTK.getRole().getRole();--%>
<%--    %>--%>
<%--    // Chỉ thực hiện chuyển hướng nếu trang hiện tại là trang gốc "/"--%>
<%--    if (window.location.pathname === "/") {--%>
<%--      <%--%>
<%--          if ("Admin".equals(userRole)) {--%>
<%--      %>--%>
<%--      window.location.href = "/admin";--%>
<%--      <%--%>
<%--          } else {--%>
<%--      %>--%>
<%--      window.location.href = "/giangvien";--%>
<%--      <%--%>
<%--          }--%>
<%--      %>--%>
<%--    }--%>
<%--    <%--%>
<%--      } else { // No session or user not logged in--%>
<%--    %>--%>
<%--    // Nếu không có session hoặc user chưa đăng nhập, chuyển hướng đến /login--%>
<%--    window.location.href = "/auth/login";--%>
<%--    <%--%>
<%--      }--%>
<%--    %>--%>
<%--  </script>--%>
</head>

<body>
<h1><%= "Hello World!" %></h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>

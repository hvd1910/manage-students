<%@page import="com.example.quanlysinhvien.models.TheLoai"%>
<%@page import="com.example.quanlysinhvien.models.TinChi"%>
<%@page import="com.example.quanlysinhvien.models.GiangVien"%>
<%@page import="com.example.quanlysinhvien.models.NamHoc"%>
<%@page import="com.example.quanlysinhvien.models.HocKy"%>
<%@page import="com.example.quanlysinhvien.models.MonHoc"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@include file="/templates/admin/inc/header.jsp" %>

<!-- Main Sidebar Container -->
<%@include file="/templates/giangvien/menu.jsp" %>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0">Nhập điểm</h1>
                </div><!-- /.col -->
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
                        <li class="breadcrumb-item active">Nhập điểm</li>
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
                    }
                }
            %>
            <%
                if(!"".equals(request.getParameter("msg"))){
                    String msg = request.getParameter("msg");
                    if("ERROR".equals(msg)){
            %>
            <div class="alert alert-success" role="alert">
                Xử lý thất bại..!
            </div>
            <%
                    }
                }
            %>
            <form class="form-inline" method="post" action="<%=request.getContextPath()%>/giangvien/diem" enctype="multipart/form-data">
                <input type="hidden" name="action" id="formAction" value="add">

                <div class="container">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="col-mb-12">
                                <h3>Thông tin chung</h3><br>
                            </div>
                            <div class="col-sm-12">
                                <%
                                    if(request.getAttribute("hockyList") != null){
                                        List<HocKy> hockyList = (List<HocKy>) request.getAttribute("hockyList");
                                        if(hockyList.size() > 0){
                                %>
                                <div class="form-group mx-sm-3 mb-4">
                                    <label>Học kỳ:&nbsp;</label>
                                    <select class="form-control" id="maHocKy" name="maHocKy">
                                        <%
                                            for(HocKy objhk: hockyList){
                                        %>
                                        <option value="<%=objhk.getMaHK()%>"><%=objhk.getTenHK()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                <%
                                        }
                                    }
                                %>
                                <%
                                    if(request.getAttribute("namhocList") != null){
                                        List<NamHoc> namhocList = (List<NamHoc>) request.getAttribute("namhocList");
                                        if(namhocList.size() > 0){
                                %>
                                <div class="form-group mx-sm-3 mb-4">
                                    <label>Năm học:&nbsp;</label>
                                    <select class="form-control" id="maNamhoc" name="maNamhoc">
                                        <%
                                            for(NamHoc objNH : namhocList){
                                        %>
                                        <option value="<%=objNH.getMaNH()%>"><%=objNH.getTenNH() %></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                <%
                                        }
                                    }
                                %>
                                <%
                                    if(request.getAttribute("giangvienList") != null){
                                        List<GiangVien> giangvienList = (List<GiangVien>)request.getAttribute("giangvienList");
                                        if(giangvienList.size() > 0){
                                %>
                                <div class="form-group mx-sm-3 mb-4">
                                    <label>Giảng viên:&nbsp;</label>
                                    <select class="form-control" id="maGiangVien" name="maGiangVien">
                                        <%
                                            for(GiangVien objGV : giangvienList){
                                        %>
                                        <option value="<%=objGV.getMaGV()%>"><%=objGV.getTenGV()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                <%
                                        }
                                    }
                                %>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="col-mb-12">
                                <h3>Thông tin môn học</h3>
                            </div>

                            <div class="col-sm-12">
                                <%
                                    if(request.getAttribute("monhocList") != null){
                                        List<MonHoc> monhocList = (List<MonHoc>)request.getAttribute("monhocList");
                                        if(monhocList.size() > 0){
                                %>
                                <div class="form-group mx-sm-3 mb-4">
                                    <label>Môn học:&nbsp;</label>
                                    <select class="form-control" id="maMonHoc" name="maMonHoc">
                                        <%
                                            for(MonHoc objMH: monhocList){
                                        %>
                                        <option value="<%=objMH.getMaMH()%>"><%=objMH.getTenMH()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                <%
                                        }
                                    }
                                %>
                                <%
                                    if(request.getAttribute("tinchiList") != null){
                                        List<TinChi> tinchiList = (List<TinChi>) request.getAttribute("tinchiList");
                                        if(tinchiList.size() > 0){
                                %>
                                <div class="form-group mx-sm-3 mb-4">
                                    <label>Số tín chỉ:&nbsp;</label>
                                    <select class="form-control" id="maTinChi" name="maTinChi">
                                        <%
                                            for(TinChi objTC: tinchiList){
                                        %>
                                        <option value="<%=objTC.getMaTC()%>"><%=objTC.getSoTC()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                <%
                                        }
                                    }
                                %>
                                <%
                                    if(request.getAttribute("theloaiList") != null){
                                        List<TheLoai> theloaiList = (List<TheLoai>) request.getAttribute("theloaiList");
                                        if(theloaiList.size() > 0){
                                %>
                                <div class="form-group mx-sm-3 mb-4">
                                    <label>Thể loại:&nbsp;</label>
                                    <select class="form-control" id="maTheLoai" name="maTheLoai">
                                        <%
                                            for(TheLoai objTL : theloaiList){
                                        %>
                                        <option value="<%=objTL.getMaTL()%>"><%=objTL.getTenTL() %></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                <%
                                        }
                                    }
                                %>
                            </div>
                        </div>
                        <div class="form-group mb-12">
                            <div class="form-group mx-sm-3 mb-2">
                                <input type="file" name="fileExcel">
                            </div>
                            <div class="form-group mx-sm-3 mb-2">
                                <button type="submit" class="btn btn-primary">Nhập điểm</button>
                            </div>
                        </div>
                    </div>
                </div>

            </form>

        </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
</div>
<script type="text/javascript">
    $(document).ready(function(){
        $("form").submit(function(event){
            event.preventDefault();
            var formData = new FormData(this);
            $.ajax({
                url:"input",
                type: "post",
                data: formData,
                success: function(data){
                    var row = data;
                    for(i = 0;i < row.length;i++){
                        var column = row[i];
                        var eachrow = "<tr>";
                        for(j = 0; j < column.lenght;j++){
                            eachrow = eachrow+"<td>" + column[j]+ "</td>";
                        }
                        eachrow = eachrow + "</td>";
                        $('#tbody').append(eachrow);
                    }
                },
                cache:false,
                contentType: false,
                processData: false
            })
        })
    });
</script>
<!-- /.content-wrapper -->
<%@include file="/templates/admin/inc/footer.jsp" %>


<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Author" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Author Management</title>
    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
          type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">


    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body id="page-top">
<div id="wrapper">
    <!-- Sidebar -->
    <jsp:include page="Admin/Sidebar.jsp"></jsp:include>
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">
        <!-- Main Content -->
        <div id="content">
            <!-- Topbar -->
            <jsp:include page="Admin/Topbar.jsp"></jsp:include>

            <!-- Add author -->
            <div class="modal fade" id="addauthor" tabindex="-1"
                 role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Add Author</h5>
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form action="AuthorAdmin" method="post" class="user">
                            <div class="modal-body">
                                <div class="form-group">
                                    <label> First name </label> <input type="text" name="firstname" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Last name</label> <input type="text" name="lastname" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Date of birth</label> <input type="text" name="dob">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary"
                                        data-dismiss="modal">Close</button>
                                <button type = "submit" class="btn btn-primary btn-user btn-block">
                                    Save
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
                    <%--  Edit Author--%>
            <div class="modal fade" id="editauthor" tabindex="-1"
                 role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <form action="updateAuthor" method="post" class="user">
                            <div class="modal-body">
                                <div class="form-group">
                                    <label> ID </label> <input readonly type="text" name="authoridupdate"
                                                                       id="authoridupdate" class="form-control">
                                </div><div class="form-group">
                                    <label> First name </label> <input type="text" name="firstnameupdate"
                                                                        class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Last name</label> <input type="text" name="lastnameupdate"
                                                                     class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Date of birth</label> <input type="text" name="dobupdate">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary"
                                        data-dismiss="modal">Close</button>
                                <button type = "submit" class="btn btn-primary btn-user btn-block">
                                    Save
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Table book -->
            <div class="container-fluid">
                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800">List Author</h1>
                <button type="button" class="btn btn-primary" data-toggle="modal"
                        data-target="#addauthor">Add author</button>
                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%"
                                   cellspacing="0">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>First name</th>
                                    <th>Last name</th>
                                    <th>Date of birth</th>
                                    <th>EDIT</th>
                                    <th>DELETE</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    ArrayList<Author> authorList = (ArrayList<Author>)request.getAttribute("authorList");
                                    for(Author a:authorList){%>
                                <tr>
                                    <td><%=a.getId()%></td>
                                    <td><%=a.getFirstname()%></td>
                                    <td><%=a.getLastname()%></td>
                                    <td><%=a.getDob()%></td>
                                    <td>
                                        <button type="button" id="<%=a.getId()%>" class="btn btn-success" data-toggle="modal"
                                                data-target="#editauthor">UPDATE</button>
                                    </td>
                                    <td>
                                        <a  href="deleteAuthor?id=<%=a.getId()%>" class="btn btn-danger">DELETE</a>
                                    </td>
                                </tr>
                                <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script src="js/demo/chart-area-demo.js"></script>
<script src="js/demo/chart-pie-demo.js"></script>
<script>
    $('#editauthor').on('show.bs.modal', function (e) {
        $("#authoridupdate").val(e.relatedTarget.id);
    })
</script>
</body>
</html>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Book" %>
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
	<title>Book Management</title>
	<!-- Custom fonts for this template-->
	<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
		type="text/css">
	<link
		href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
		rel="stylesheet">

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
				



				<div class="container">
					<!-- Page Heading -->
					<div class="row" >
						<div class="album py-5 bg-light">
							<div class="container">
								<div class="row">
								<%
									ArrayList<Book> bookList = (ArrayList<Book>)request.getAttribute("booklist");
									for(Book b:bookList){%>
									<div class="col-3" style="margin-top: 20px">
										<div class="card shadow-sm">
											<img class="bd-placeholder-img card-img-top" src="data:image/png;base64,<%=b.getImage()%>" width="100%" height="225"  role="img" />
											<div class="card-body">
												<h3><%=b.getName()%></h3>
												<h4><%=b.getAuthor()%></h4>
												<div class="d-flex justify-content-between align-items-center">
													<div class="btn-group">
														<a href="addCart?id=<%=b.getId()%>" class="fas fa-cart-plus"></a>
													</div>
													<small class="text-muted"><%=b.getPrice()%></small>
												</div>
											</div>
										</div>
									</div>
								<%}%>
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
        $('#editbook').on('show.bs.modal', function (e) {
            $("#bookidupdate").val(e.relatedTarget.id);
        })
    </script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Sidebar -->
<ul
	class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
	id="accordionSidebar">

	<!-- Sidebar - Brand -->
	<a
		class="sidebar-brand d-flex align-items-center justify-content-center"
		href="">
		<div class="sidebar-brand-icon rotate-n-15">
			<i class="fas fa-laugh-wink"></i>
		</div>
		<div class="sidebar-brand-text mx-3">
			BookStore
		</div>
	</a>

	<!-- Divider -->
	<hr class="sidebar-divider my-0">

	<!-- Nav Item - Dashboard -->
	<li class="nav-item active"><a class="nav-link" href="<%=request.getContextPath()%>/mainPage">
			<i class="fas fa-fw fa-tachometer-alt"></i> <span>Products</span>
	</a></li>
	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Heading -->
	<div class="sidebar-heading">Interface</div>

	<!-- Nav Item - Pages Collapse Menu -->
	<li class="nav-item"><a class="nav-link collapsed" href="<%=request.getContextPath()%>/BookAdmin"
		 data-target="#collapseTwo"
		aria-controls="collapseTwo"> <i class="fas fa-fw fa-cog"></i> <span>List
				book</span>
	</a>
	</li>

	<!-- Nav Item - Utilities Collapse Menu -->
	<li class="nav-item"><a class="nav-link collapsed" href="<%=request.getContextPath()%>/AuthorAdmin"
							data-target="#collapseUtilities" aria-controls="collapseUtilities">
		<i class="fas fa-fw fa-wrench"></i>
		<span>List author</span>
	</a>
	</li>
	<li class="nav-item"><a class="nav-link collapsed" href="<%=request.getContextPath()%>/Publisher"
							data-target="#collapseUtilities" aria-controls="collapseUtilities"> <i
			class="fas fa-fw fa-wrench"></i> <span>List Category</span>
	</a>
	</li>
	<!-- Heading -->
</ul>
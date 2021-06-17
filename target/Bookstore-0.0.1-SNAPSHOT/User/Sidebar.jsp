<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

</ul>
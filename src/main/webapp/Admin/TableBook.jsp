<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Book" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">List Book</h1>
    <button type="button" class="btn btn-primary" data-toggle="modal"
            data-target="#addadminprofile">Add Admin Profile</button>
    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%"
                       cellspacing="0">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Description</th>
                        <th>Author</th>
                        <th>Category</th>
                        <th>Publisher</th>
                        <th>EDIT</th>
                        <th>DELETE</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <%
                            ArrayList<Book> bookList = (ArrayList<Book>)request.getAttribute("booklist1");
                            for(Book b:bookList){%>
                        <td><%=b.getId()%></td>
                        <td><%=b.getName()%></td>
                        <td><%=b.getPrice()%></td>
                        <td><%=b.getDescription()%></td>
                        <td><%=b.getAuthor()%></td>
                        <td><%=b.getCategory()%></td>
                        <td><%=b.getPublisher()%></td>
                        <td>
                            <button type="submit" class="btn btn-success">EDIT</button>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-danger">DELETE</button>
                        </td>
                        <%}%>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

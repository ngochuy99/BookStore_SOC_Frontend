<%@ page import="Model.Order" %>
<%@ page import="Model.Item" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-10 col-md-offset-1">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th class="text-center">Price</th>
                    <th class="text-center">Total</th>
                    <th> </th>
                </tr>
                </thead>
                <tbody>
                <%
                    Order order = (Order) session.getAttribute("order");
                    List<Item> items = order.getItems();
                    double subTotal = 0;
                    double ship = 10000;
                    for(Item item:items){%>
                <tr>
                    <td class="col-sm-8 col-md-6">
                        <div class="media">
                            <a class="thumbnail pull-left" href="#"> <img class="media-object" src="<%=item.getBook().getImage()%>" style="width: 72px; height: 72px;"> </a>
                            <div class="media-body">
                                <h4 class="media-heading"><a href="#"><%=item.getBook().getName()%></a></h4>
                                <h5 class="media-heading"> by <a href="#">Brand name</a></h5>
                                <span>Status: </span><span class="text-success"><strong>In Stock</strong></span>
                            </div>
                        </div></td>
                    <td class="col-sm-1 col-md-1" style="text-align: center">
                        <input type="email" class="form-control" id="exampleInputEmail1" value="<%=item.getQuantity()%>">
                    </td>
                    <td class="col-sm-1 col-md-1 text-center"><strong><%=item.getBook().getPrice()%></strong></td>
                    <td class="col-sm-1 col-md-1 text-center"><strong><%=item.getBook().getPrice() * item.getQuantity()%></strong></td>
                    <td class="col-sm-1 col-md-1">
                        <button type="button" class="btn btn-danger">
                            <span class="glyphicon glyphicon-remove"></span> Remove
                        </button></td>
                </tr>
                <%
                        subTotal += item.getBook().getPrice() * item.getQuantity();
                    }%>
<%--                <tr>--%>
<%--                    <td class="col-md-6">--%>
<%--                        <div class="media">--%>
<%--                            <a class="thumbnail pull-left" href="#"> <img class="media-object" src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png" style="width: 72px; height: 72px;"> </a>--%>
<%--                            <div class="media-body">--%>
<%--                                <h4 class="media-heading"><a href="#">Product name</a></h4>--%>
<%--                                <h5 class="media-heading"> by <a href="#">Brand name</a></h5>--%>
<%--                                <span>Status: </span><span class="text-warning"><strong>Leaves warehouse in 2 - 3 weeks</strong></span>--%>
<%--                            </div>--%>
<%--                        </div></td>--%>
<%--                    <td class="col-md-1" style="text-align: center">--%>
<%--                        <input type="email" class="form-control" id="exampleInputEmail1" value="2">--%>
<%--                    </td>--%>
<%--                    <td class="col-md-1 text-center"><strong>$4.99</strong></td>--%>
<%--                    <td class="col-md-1 text-center"><strong>$9.98</strong></td>--%>
<%--                    <td class="col-md-1">--%>
<%--                        <button type="button" class="btn btn-danger">--%>
<%--                            <span class="glyphicon glyphicon-remove"></span> Remove--%>
<%--                        </button></td>--%>
<%--                </tr>--%>
                <tr>
                    <td>   </td>
                    <td>   </td>
                    <td>   </td>
                    <td><h5>Subtotal</h5></td>
                    <td class="text-right"><h5><strong><%=subTotal%>></strong></h5></td>
                </tr>
                <tr>
                    <td>   </td>
                    <td>   </td>
                    <td>   </td>
                    <td><h5>Estimated shipping</h5></td>
                    <td class="text-right"><h5><strong><%=ship%></strong></h5></td>
                </tr>
                <tr>
                    <td>   </td>
                    <td>   </td>
                    <td>   </td>
                    <td><h3>Total</h3></td>
                    <td class="text-right"><h3><strong><%=ship + subTotal%></strong></h3></td>
                </tr>
                <tr>
                    <td>   </td>
                    <td>   </td>
                    <td>   </td>
                    <td>
                        <button type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
                        </button></td>
                    <td>
                        <button type="button" class="btn btn-success">
                            Checkout <span class="glyphicon glyphicon-play"></span>
                        </button></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
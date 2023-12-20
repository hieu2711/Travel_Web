<%-- 
    Document   : price
    Created on : Aug 20, 2023, 9:27:06 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1 class="text-center text-info mt-4">Quản lí giá Tours</h1>
<section class="container">
    <div>
        <a href="<c:url value="/addprice" />" class="btn btn-info" style="margin-top: 15px;margin-bottom:10px  " >Thêm Loại Tours</a>
    </div>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>ID</th>
                <th>Hình thức</th>
                <th>Giá người lớn</th>
                <th>Giá trẻ em</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <c:forEach items="${prices}" var="c">
                    <td> ${c.id}</td>
                    <td>${c.namePrice}</td>
                    <td>${c.priceAdult}</td>
                    <td>${c.priceChild}</td>
                    <td>
                        <c:url value="/api/addprice/${c.id}" var="api"/>
                        <a href="<c:url value="/addprice/${c.id}"/>" class="btn btn-info">Cập nhật</a>
                        <button class="btn btn-danger" onclick="deleteCategory('${api}')">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>
<script src="<c:url value="/js/main.js"/>"></script>

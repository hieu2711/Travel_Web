<%-- 
    Document   : imageTours
    Created on : Aug 19, 2023, 2:36:05 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1 class="text-center text-info mt-4">Quản lí hình ảnh Tours</h1>
<section class="container">
    <div>
        <a href="<c:url value="/addimage" />" class="btn btn-info" style="margin-top: 15px;margin-bottom:10px  " >Thêm hình ảnh Tours</a>
    </div>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>ID</th>
                <th>Hình ảnh</th>
                <th>Tên Tours</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <c:forEach items="${imageTours}" var="c">
                    <td>${c.id}</td>
                    <td>
                        <img src="${c.imageUrl}" alt="${c.id}" width="120"/>
                    </td>
                    <td>${c.toursId.name}</td>
                    <td>
                        <c:url value="/api/addimage/${c.id}" var="api"/>
                        <button class="btn btn-danger" onclick="deleteProduct('${api}')">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>
<script src="<c:url value="/js/main.js"/>"></script>
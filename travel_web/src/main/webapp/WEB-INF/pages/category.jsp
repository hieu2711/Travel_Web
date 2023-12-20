<%-- 
    Document   : category
    Created on : Aug 11, 2023, 4:55:12 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1 class="text-center text-info mt-4">Quản lí loại Tours</h1>
<section class="container">
    <div>
        <a href="<c:url value="/addcategory" />" class="btn btn-info" style="margin-top: 15px;margin-bottom:10px  " >Thêm Loại Tours</a>
    </div>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>ID</th>
                <th>Loại Tours</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <c:forEach items="${categories}" var="c">
                    <td> ${c.id}</td>
                    <td>${c.categoryName}</td>
                    <td>
                        <c:url value="/api/addcategory/${c.id}" var="api"/>
                        <a href="<c:url value="/addcategory/${c.id}"/>" class="btn btn-info">Cập nhật</a>
                        <button class="btn btn-danger" onclick="deleteCategory('${api}')">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>
<script src="<c:url value="/js/main.js"/>"></script>

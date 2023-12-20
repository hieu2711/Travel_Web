<%-- 
    Document   : account
    Created on : Aug 16, 2023, 10:43:44 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1 class="text-center text-info mt-4">Quản lí Tin tức</h1>
<section class="container">
    <div>
        <a href="<c:url value="/addnew" />" class="btn btn-info" style="margin-top: 15px;margin-bottom:10px  " >Thêm tin tức</a>
    </div>
    <table class="table table-hover">
        <thead>
            <tr>
                <th></th>
                <th>ID</th>
                <th>Tiêu đề</th>
                <th>Thời gian đăng</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <c:forEach items="${news}" var="c">
                    <td>
                        <img src="${c.image}" alt="${c.title}" width="120"/>
                    </td>
                    <td>${c.id}</td>
                    <td>${c.title}</td>
                    <td>${c.time}</td>
                    <td>
                        <c:url value="/api/addnew/${c.id}" var="api"/>
                        <a href="<c:url value="/addnew/${c.id}"/>" class="btn btn-info">Cập nhật</a>
                        <button class="btn btn-danger" onclick="deleteCategory('${api}')">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>
<script src="<c:url value="/js/main.js"/>"></script>



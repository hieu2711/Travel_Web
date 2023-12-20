<%-- 
    Document   : staff
    Created on : Aug 13, 2023, 2:17:25 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1 class="text-center text-info mt-4">Quản lí Nhân Viên</h1>
<section>
    <div class="d-flex justify-content-between align-items-center">
        <a href="<c:url value="/addstaff" />" class="btn btn-info" style="margin-top: 15px;margin-bottom:10px  " >Thêm Nhân viên</a>
        <form class="d-flex justify-content-end" action="${action}" >
        <input class="form-control me-2" type="text" name="kw" placeholder="Nhập từ khóa...">
        <button class="btn btn-primary" type="submit">Search</button>
    </form>
    </div>
    
    <table class="table table-hover">
        <thead>
            <tr>
                <th>ID</th>
                <th>Tên</th>
                <th>Giới tính</th>
                <th>Ngày sinh</th>
                <th>SDT</th>
                <th>Email</th>
                <th>CCCD</th>
                <th>Địa chỉ</th>
                <th>Ngày vào làm</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <c:forEach items="${staffs}" var="c">
                    <td> ${c.id}</td>
                    <td>${c.name}</td>
                    <td>
                        <c:choose>
                            <c:when test="${c.sex == 0}">
                                Nam
                            </c:when>
                            <c:when test="${c.sex == 1}">
                                Nữ
                            </c:when>
                            <c:otherwise>
                                Không xác định
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${c.birthday}</td>
                    <td> ${c.phone}</td>
                    <td>${c.email}</td>
                    <td> ${c.identification}</td>
                    <td>${c.address}</td>
                    <td>${c.first}</td>
                    <td>
                        <c:url value="/api/addstaff/${c.id}" var="api"/>
                        <a href="<c:url value="/addstaff/${c.id}"/>" class="btn btn-info">Cập nhật</a>
                        <button class="btn btn-danger" onclick="deleteProduct('${api}')">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>
<script src="<c:url value="/js/main.js"/>"></script>
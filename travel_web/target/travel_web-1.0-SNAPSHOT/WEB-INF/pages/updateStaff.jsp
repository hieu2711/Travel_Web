<%-- 
    Document   : updateStaff
    Created on : Aug 19, 2023, 12:48:44 AM
    Author     : Admin
--%>

<%-- 
    Document   : addStaff
    Created on : Aug 13, 2023, 2:26:59 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/addstaff" var="action"/>
<h1 class="text-center text-info mt-4">Quản lí Nhân viên</h1>
<form:form modelAttribute="addstaff" method="post" action="${action}" enctype="multipart/form-data">
    <form:hidden path="id" />
    <form:hidden path="userId" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Tên Tours" name="name" />
        <label for="name">Tên nhân viên</label>
        <form:errors path="name" element="div" cssClass="text-danger" />
    </div>         
    <div class="radio-group" style="display: flex; margin: 10px 0; ">
        <form:radiobutton path="sex" id="nam" value="0" style="margin: 0 10px;" />
        <label for="nam">Nam</label>

        <form:radiobutton path="sex" id="nu" value="1" style="margin: 0 10px;" />
        <label for="nu">Nữ</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="date" class="form-control" path="birthday" id="birthday" placeholder="Ngày sinh" name="birthday" />
        <label for="name">Ngày sinh</label>
        <form:errors path="birthday" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="number" class="form-control" path="phone" id="phone" placeholder="SDT" name="phone" />
        <label for="name">Số điện thoại</label>
        <form:errors path="phone" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="email" id="email" placeholder="Email" name="email" />
        <label for="name">Email</label>
        <form:errors path="email" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="number" class="form-control" path="identification" id="identification" placeholder="CCCD" name="identification" />
        <label for="name">CCCD</label>
        <form:errors path="identification" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="address" id="address" placeholder="Địa chỉ" name="address" />
        <label for="name" >Địa chỉ</label>
        <form:errors path="address" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="date" class="form-control" path="first" id="first" placeholder="Ngày vào làm" name="first" />
        <label for="name">Ngày vào làm</label>
        <form:errors path="first" element="div" cssClass="text-danger" />
    </div>
        <h2>Tài khoản</h2>
        <div class="form-floating mb-3 mt-3">
            <form:input type="text" class="form-control" path="userId.username" id="userId.username" placeholder="Tên đăng nhập" name="userId.username" />
            <label for="taiKhoan">Tên đăng nhập</label>
        </div>
        <div class="form-floating mb-3 mt-3">
            <form:input type="password" class="form-control" path="userId.password" id="userId.password" placeholder="Mật khẩu" name="userId.password" />
            <label for="matKhau">Mật khẩu</label>
        </div>
        <div class="form-group" style="margin: 10px 0;">
            <form:radiobutton path="userId.role" id="AD" value="ROLE_ADMIN" style="margin: 0 10px;" />
            <label for="AD">Admin</label>

            <form:radiobutton path="userId.role" id="NV" value="NV" style="margin: 0 10px;" />
            <label for="NV">Nhân viên</label>
        </div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info">
            <c:choose>
                <c:when test="${addstaff.id != null}">Cập nhật Nhân viên</c:when>
                <c:otherwise>Cập nhật Nhân viên</c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>

<%-- 
    Document   : addAccount
    Created on : Aug 16, 2023, 10:43:16 PM
    Author     : Admin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1 class="text-center text-info mt-4">Quản lí Tin tức</h1>
<c:url value="/addnew" var="action"/>
<form:form modelAttribute="addnew" action="${action}" method="post" enctype="multipart/form-data">
    <form:hidden path="id" />
    <form:hidden path="image" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="title" id="title" placeholder="Tiêu đề" name="title" />
        <label for="name">Tiêu đề</label>
         <form:errors path="title" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="content" id="content" placeholder="Nội dung" name="content" />
        <label for="priceAdult">Nội dung</label>
         <form:errors path="content" element="div" cssClass="text-danger" />
    </div>
      <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control" path="file" id="file" placeholder="Hình ảnh" name="image" />
        <label for="file">Hình ảnh</label>
        <c:if test="${addnew.image != null}">
            <img src="${addnew.image}" width="120" />
        </c:if>
             <form:errors path="file" element="div" cssClass="text-danger" />
    </div> 
    <div class="form-floating mb-3 mt-3">
        <form:input type="datetime-local" class="form-control" path="time" id="time" placeholder="Thời gian đăng" name="time" />
        <label for="priceChild">Thời gian đăng</label>
         <form:errors path="time" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info">
            <c:choose>
                <c:when test="${addnew.id != null}">Cập nhật Tin tức</c:when>
                <c:otherwise>Thêm Tin tức</c:otherwise>
            </c:choose>
        </button>
    </div>

</form:form>



<%-- 
    Document   : addCategory
    Created on : Aug 12, 2023, 7:16:36 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/addcategory" var="action"/>
<h1 class="text-center text-info mt-4">Quản lí loại Tours</h1>
<form:form modelAttribute="addcategory" action="${action}" method="post" enctype="multipart/form-data">
<form:hidden path="id" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="categoryName" id="categoryName" placeholder="Tên loại Tours" name="categoryName" />
        <label for="name">Tên loại Tours</label>
        <form:errors path="categoryName" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info">
            <c:choose>
                <c:when test="${addcategory.id != null}">Cập nhật loại Tours</c:when>
                <c:otherwise>Thêm loại Tours</c:otherwise>
            </c:choose>
        </button>
    </div>
    
</form:form>

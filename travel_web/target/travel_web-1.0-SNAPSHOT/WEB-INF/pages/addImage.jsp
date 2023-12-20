<%-- 
    Document   : addImage
    Created on : Aug 21, 2023, 12:02:58 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/addimage" var="action"/>
<h1 class="text-center text-info mt-4">Quản lí hình ảnh Tours</h1>
<form:form modelAttribute="addimage" action="${action}" method="post" enctype="multipart/form-data">
    <form:hidden path="id" />
    <form:hidden path="imageUrl" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control" path="file" id="file" placeholder="Hình ảnh" name="image" />
        <label for="file">Hình ảnh</label>
        <c:if test="${addimage.imageUrl != null}">
            <img src="${addnew.imageUrl}" width="120" />
        </c:if>
        <form:errors path="file" element="div" cssClass="text-danger" />
    </div> 
    <div class="form-floating">
        <form:select class="form-select" id="cate" name="cate" path="toursId.id">
            <c:forEach items="${listtours}" var="c">
                <c:choose>
                    <c:when test="${c.id == addimage.toursId.id}"><option value="${c.id}" selected>${c.name}</option></c:when>
                    <c:otherwise><option value="${c.id}">${c.name}</option></c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
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


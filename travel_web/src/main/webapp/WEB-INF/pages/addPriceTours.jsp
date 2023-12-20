<%-- 
    Document   : addPriceTours
    Created on : Aug 20, 2023, 9:36:46 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/addprice" var="action"/>
<h1 class="text-center text-info mt-4">Quản lí giá Tours</h1>
<form:form modelAttribute="addprice" action="${action}" method="post" enctype="multipart/form-data">
<form:hidden path="id" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="namePrice" id="namePrice" placeholder="Hình thức Tours" name="namePrice" />
        <label for="name">Hình thức Tours</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="priceAdult" id="priceAdult" placeholder="Giá người lớn" name="priceAdult" />
        <label for="name">Giá người lớn</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="priceChild" id="priceChild" placeholder="Giá trẻ em" name="priceChild" />
        <label for="name">Giá trẻ em</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info">
            <c:choose>
                <c:when test="${addprice.id != null}">Cập nhật giá Tours</c:when>
                <c:otherwise>Thêm giá Tours</c:otherwise>
            </c:choose>
        </button>
    </div>

</form:form>

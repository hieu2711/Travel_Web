<%-- 
    Document   : tours
    Created on : Aug 6, 2023, 7:37:00 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 class="text-center text-info mt-4">Quản lí Tours</h1>
<c:url value="/tours" var="action"/>
<form:form modelAttribute="tours" method="post" action="${action}" enctype="multipart/form-data">
    <form:hidden path="id" />
    <form:hidden path="image" />
    <%--    <form:hidden path="price.id" />--%>

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Tên Tours" name="name" />
        <label for="name">Tên Tours</label>
        <form:errors path="name" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating">
        <form:select class="form-select" id="cate" name="cate" path="tourCate.id">
            <c:forEach items="${categories}" var="c">
                <c:choose>
                    <c:when test="${c.id == tours.tourCate.id}"><option value="${c.id}" selected>${c.categoryName}</option></c:when>
                    <c:otherwise><option value="${c.id}">${c.categoryName}</option></c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="cate" class="form-label">Danh mục Tours</label>
    </div>
    <div class="form-floating">
        <form:select class="form-select" id="price" name="price" path="priceId.id">
            <c:forEach items="${price}" var="c">
                <c:choose>
                    <c:when test="${c.id == tours.priceId.id}"><option value="${c.id}" selected>${c.namePrice}</option></c:when>
                    <c:otherwise><option value="${c.id}">${c.namePrice}</option></c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="cate" class="form-label">Hình thức Tours</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control" path="file" id="file" placeholder="Hình ảnh" name="image" />
        <label for="file">Hình ảnh</label>
        <c:if test="${tours.image != null}">
            <img src="${tours.image}" width="120" />
        </c:if>
        <form:errors path="file" element="div" cssClass="text-danger" />
    </div>           
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="description" id="description" placeholder="Mô tả" name="description" />
        <label for="name">Mô tả</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="departure" id="departure" placeholder="Nơi khởi hành" name="departure" />
        <label for="name">Nơi khởi hành</label>
        <form:errors path="departure" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="destination" id="destination" placeholder="Nơi đến" name="destination" />
        <label for="name">Nơi đến</label>
        <form:errors path="destination" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="time" id="time" placeholder="Thời gian" name="time" />
        <label for="name">Thời gian</label>
        <form:errors path="time" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="datetime-local" class="form-control" path="timeStart" id="timeStart" placeholder="Thời gian bắt đầu" name="timeStart" />
        <label for="name" >Thời gian bắt đầu</label>
        <form:errors path="timeStart" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="vehicle" id="vehicle" placeholder="Phương tiện" name="vehicle" />
        <label for="name">Phương tiện</label>
        <form:errors path="vehicle" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="accommodation" id="accommodation" placeholder="Nơi ở" name="accommodation" />
        <label for="name">Nơi ở</label>
        <form:errors path="accommodation" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info">
            <c:choose>
                <c:when test="${tours.id != null}">Cập nhật Tours</c:when>
                <c:otherwise>Thêm Tours</c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>

<%-- 
    Document   : header
    Created on : Aug 6, 2023, 11:59:38 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<c:url value="/" var="action" />
<c:url value="/category" var="cate"/>
<c:url value="/price" var="price"/>
<c:url value="/staff" var="staff"/>
<c:url value="/news" var="news"/>
<c:url value="/imageTours" var="imagetours"/>
<c:url value="/chart" var="chart"/>

<nav class="navbar navbar-expand-sm bg-info navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Travel Website</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav me-auto" >
                <se:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="nav-item">
                        <a class="nav-link" style="color: black;" href="${action}">Quản lí Tours</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" style="color: black;" href="${cate}">Quản lí loại Tours</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" style="color: black;" href="${price}">Quản lí giá Tours</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" style="color: black;" href="${imagetours}">Quản lí hình ảnh Tours</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" style="color: black;" href="${news}">Tin tức du lịch</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" style="color: black;" href="${staff}">Quản lí Nhân Viên</a>
                    </li>
                      <li class="nav-item">
                        <a class="nav-link" style="color: black;" href="${chart}">Thống kê</a>
                    </li>
                </se:authorize>
            </ul>
            <ul class="nav">
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name != null}">
                            <se:authorize access="hasRole('ROLE_ADMIN')">
                                <a class="nav-link text-body" href="<c:url value="/" />">${pageContext.request.userPrincipal.name}!</a>
                            </se:authorize>
                            <se:authorize access="!hasRole('ROLE_ADMIN')">
                                <a class="nav-link text-body" href="<c:url value="/" />">${pageContext.request.userPrincipal.name}!</a>
                            </se:authorize>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link text-body" href="<c:url value="/login" />">Đăng nhập</a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name != null}">
                            <se:authorize access="hasRole('ROLE_ADMIN')">
                                <a class="nav-link" href="<c:url value="/logout" />">Đăng xuất</a>
                            </se:authorize>
                            <se:authorize access="!hasRole('ROLE_ADMIN')">
                                <a class="nav-link" href="<c:url value="/logout" />">Đăng xuất</a>
                            </se:authorize>
                        </c:when>
                    </c:choose>
                </li>
            </ul>

        </div>
    </div>
</nav>

<style>
    body {
        margin: 0;
        display: flex;
        flex-direction: column;
        min-height: 100vh;
    }
    footer {
        margin-top: auto;
        text-align: center;
    }
</style>
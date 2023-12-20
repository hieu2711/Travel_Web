<%-- 
    Document   : index
    Created on : Jul 8, 2023, 1:08:00 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<%@ taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<c:url value="/" var="action" />
<c:choose>
    <c:when test="${empty pageContext.request.userPrincipal}">
        <h1 style="text-align: center;margin-top: 20px">Chào mừng bạn đến với trang Admin!</h1>
    </c:when>
    <c:otherwise>
        <se:authorize access="hasRole('ROLE_ADMIN')">
            <section>
                <div class="d-flex justify-content-between align-items-center">
                    <a href="<c:url value="/tours" />" class="btn btn-info" style="margin-top: 15px;margin-bottom:10px  " >Thêm Tours</a>
                    <form class="d-flex justify-content-end" action="${action}" >
                        <input class="form-control me-2" type="text" name="kw" placeholder="Nhập từ khóa...">
                        <button class="btn btn-primary" type="submit">Search</button>
                    </form>
                </div>
                <c:if test="${counter > 1}">
                    <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>
                            <c:forEach begin="1" end="${counter}" var="i">
                                <c:url value="/" var="pageUrl">
                                    <c:param name="page" value="${i}"/>
                                </c:url>
                            <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                            </c:forEach>
                    </ul>
                </c:if>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th></th>
                            <th>ID</th>
                            <th>Tên Tours</th>
                            <th>Nơi đến</th>
                            <th>Thời gian</th>
                            <th>Giá Tours</th>
                            <th>Ngày bắt đầu</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <c:forEach items="${tours}" var="p">
                                <td>
                                    <img src="${p.image}" alt="${p.name}" width="120"/>
                                </td>
                                <td> ${p.id}</td>
                                <td>${p.name}</td>
                                <td>${p.destination}</td>
                                <td>${p.time}</td>
                                 <td id="price_${p.id}">${p.priceId.priceAdult}</td>
                                <td>${p.timeStart}</td>
                                <td>
                                    <c:url value="/api/tours/${p.id}" var="api"/>
                                    <a href="<c:url value="/tours/${p.id}"/>" class="btn btn-info">Cập nhật</a>
                                    <button class="btn btn-danger" onclick="deleteProduct('${api}')">Xóa</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </section>
        </se:authorize>
    </c:otherwise>
</c:choose>

<script src="<c:url value="/js/main.js"/>"></script>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        const priceElements = document.querySelectorAll("[id^='price_']");
        priceElements.forEach(element => {
            const priceValue = parseFloat(element.textContent);
            const formattedPrice = priceValue.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
            element.textContent = formattedPrice;
        });
    });
</script>

<%-- 
    Document   : header
    Created on : Aug 22, 2024, 4:08:48 PM
    Author     : lth7p
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/" var="action" />
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="javascript:void(0)">Smart Parking</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mynavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${action}">Trang chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="javascript:void(0)">Quản lí bãi xe</a>
                </li>
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/"/>">
                                ${pageContext.request.userPrincipal.name}
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/login"/>">Đăng nhập</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
            <form class="d-flex" action="${action}">
                <input class="form-control me-2" type="text" name="kw" placeholder="Nhập tên bãi xe">
                <button class="btn btn-primary" type="submit">Tìm</button>
            </form>
        </div>
    </div>
</nav>

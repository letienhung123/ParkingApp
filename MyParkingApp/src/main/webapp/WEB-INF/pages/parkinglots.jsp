<%-- 
    Document   : parkinglots
    Created on : Aug 22, 2024, 5:08:50 PM
    Author     : lth7p
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info">Quản Lí Bãi Xe</h1>
<c:url value="/parkinglots" var="action"/>
<form:form modelAttribute="parkinglot" method="post" action="${action}" enctype="multipart/form-data">
    <form:hidden path="parkingLotID"/>
    <form:hidden path="imageURL"/>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Nhập tên..." name="name"/>
        <label for="name">Tên bãi xe</label>
        <form:errors path="name" cssClass="text-danger" element="div"/>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="address" id="address" placeholder="Nhập địa chỉ..." name="address"/>
        <label for="address">Địa chỉ</label>
        <form:errors path="address" cssClass="text-danger" element="div"/>
    </div> 
    <div class="form-floating mb-3 mt-3">
        <form:input type="number" class="form-control" path="pricePerHour" id="pricePerHour" placeholder="Giá..." name="pricePerHour"/>
        <label for="pricePerHour">Giá dịch vụ</label>
        <form:errors path="pricePerHour" cssClass="text-danger" element="div"/>
    </div> 
    <div class="form-floating mb-3 mt-3">
        <form:textarea class="form-control" path="facilities" id="facilities" placeholder="Nhập..." name="facilities"/>
        <label for="facilities">Các dịch vụ kèm theo</label>
        <form:errors path="facilities" cssClass="text-danger" element="div"/>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control" path="file" id="file"/>
        <label for="file">Thêm hình ảnh</label>
        <form:errors path="file" cssClass="text-danger" element="div"/>
    </div> 
    <div class="form-floating mt-1">
        <button class="btn btn-info">
            <c:choose>
                <c:when test="${parkinglot.parkingLotID == null}">Tạo</c:when>
                <c:otherwise>Cập nhật</c:otherwise>
            </c:choose>
        </button>
    </div>       
</form:form>

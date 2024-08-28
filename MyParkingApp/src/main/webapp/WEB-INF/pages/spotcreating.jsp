<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info">Tạo Chỗ Đậu Xe</h1>
<c:url value="/parkinglots/${idcurr}/spotcreating" var="action"/>
<form:form modelAttribute="parkingspot" method="post" action="${action}">
    <form:hidden path="parkingLotID"/>
    <form:hidden path="status"/>
    <form:hidden path="reservationSet"/>
    <form:hidden path="spotID"/>
    <div class="form-floating mb-3 mt-3">
        <form:input type="number" class="form-control" path="level" id="level" placeholder="Nhập số tầng..." name="level"/>
        <label for="level">Số tầng</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="number" class="form-control" path="spotNumber" id="num" placeholder="Nhập số bãi cho mỗi tầng..." name="spotNumber"/>
        <label for="num">Số bãi cho mỗi tầng</label>
    </div>
    <div class="form-floating mt-1">
        <button class="btn btn-info">Tạo</button>
    </div>       
</form:form>

<%-- 
    Document   : index
    Created on : Aug 17, 2024, 10:39:23 PM
    Author     : lth7p
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container mt-4">
    <div>
        <a href="<c:url value="/parkinglots"/>" class="btn btn-info mt-1">Đăng kí bãi đậu</a>
    </div>
    <div class="row">
        <c:forEach items="${parkinglots}" var="p">
            <div class="col-md-4 mb-3 d-flex align-items-stretch">
                <a href="<c:url value='/parkinglots/${p.parkingLotID}/parkingspots'/>" style="color: black; text-decoration: none;">
                    <div class="card text-black mt-2 bg-light" style="width:100%">
                        <img class="card-img-top" src="${p.imageURL}" alt="Card image">
                        <div class="card-body" >
                            <h4 class="card-title">${p.name}</h4>
                            <p class="card-text">Địa chỉ: ${p.address} </p>
                            <p class="card-text">Tổng chỗ đậu: ${p.totalSpots} chỗ </p>
                            <p class="card-text">Giá: ${p.pricePerHour}/giờ </p>
                            <p class="card-text">Tiện ích: ${p.facilities} </p>
                            <p class="card-text">Số chỗ trống: ${p.emptySpots} </p>
                            <c:url value="/api/parkinglots/${p.parkingLotID}" var="apiDel" />
                            <a class="btn btn-danger" onclick="delParkingLot('${apiDel}', ${p.parkingLotID})">Xoá</a>
                            <a href="<c:url value="/parkinglots/${p.parkingLotID}"/>" class="btn btn-success">Cập nhật</a>
                            <a href="<c:url value="/parkinglots/${p.parkingLotID}/spotcreating"/>" class="btn btn-success">Tạo chỗ đỗ xe</a>
                        </div>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
<script src="<c:url value="/js/main.js"/>"></script>


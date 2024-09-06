<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<h1 class="text-center text-info">Đặt chỗ ở ${selectedParkingLot.name}</h1>
<div class="card" style="width: 50%">
    <div class="card-header bg-info">Thông tin bãi đậu xe</div>
    <div class="card-body">
        <div class="card-text">Tên: ${selectedParkingLot.name}</div> 
        <div class="card-text">Địa chỉ: ${selectedParkingLot.address}</div> 
        <div class="card-text">Tổng số chỗ đậu: ${selectedParkingLot.totalSpots}</div> 
    </div>
    <div class="card-footer">Giá mỗi giờ: ${selectedParkingLot.pricePerHour}</div>
</div>

<h3 class="text-center text-info">Khách hàng đang đỗ</h3>
<div class="row">
    <c:forEach items="${reservationsByLotID}" var="r" varStatus="status">
        <div class="col-md-4 mb-3">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title text-primary">${r.fulName}</h5>
                    <p class="card-text">Số điện thoại: ${r.phoneNum}</p>
                    <p class="card-text">Biển số xe: ${r.plate}</p>
                    <p class="card-text">Vị trí đỗ: ${r.location}</p>
                    
                    <p class="card-text">
                        ${timeRemainingMessages[status.index]}
                    </p>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<div class="container p-5 my-5" style="background: lightyellow">
    <h3 class="text-center text-info">Thông tin bãi đỗ</h3>
    <div class="row">
        <c:forEach items="${parkingspots}" var="spot" varStatus="status">
            <c:if test="${status.index % 8 == 0 && status.index != 0}">
            </div><div class="row">
            </c:if>
            <div class="col-md-1 mb-3 d-flex align-items-stretch text-center">
                <c:choose>
                    <c:when test="${spot.status == 'Booked'}">
                        <div class="card bg-primary text-black mt-2" style="width:70px">
                        </c:when>
                        <c:when test="${spot.status == 'In Use'}">
                            <div class="card bg-secondary text-black mt-2" style="width:70px">
                            </c:when>
                            <c:when test="${spot.status == 'Fixing'}">
                                <div class="card bg-warning text-black mt-2" style="width:70px">
                                </c:when>
                                <c:when test="${spot.status == 'Empty'}">
                                    <div class="card bg-light text-black mt-2" style="width:70px">
                                    </c:when>
                                    <c:otherwise>
                                        <div class="card text-black mt-2" style="width:100%">
                                        </c:otherwise>
                                    </c:choose>
                                    <div class="card-body">
                                        <h5 class="card-title">${spot.level}-${spot.spotNumber}</h5>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

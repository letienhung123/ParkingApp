<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 class="text-center text-info">Danh sách lịch sử đặt chỗ</h1>
<div class="container mt-3">  
    <table class="table table-dark table-hover">
        <thead>
            <tr>
                <th>Id</th>
                <th>Tên khách hàng</th>
                <th>Biển số xe</th>
                <th>Số điện thoại</th>
                <th>Ngày</th>
                <th>Số giờ đỗ</th>
                <th>Vị trí</th>
                <th>Tổng tiền</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${getReceipt}" var="r">
                <tr>
                    <td>${r.reservationID}</td>
                    <td>${r.fulName}</td>
                    <td>${r.plate}</td>
                    <td>${r.phoneNum}</td>
                    <td>${r.startTime}</td>
                    <td>
                        <c:set var="startTime" value="${r.startTime}" />
                        <c:set var="endTime" value="${r.endTime}" />
                        <%
                            java.util.Date startTime = (java.util.Date) pageContext.getAttribute("startTime");
                            java.util.Date endTime = (java.util.Date) pageContext.getAttribute("endTime");

                            long diffInMillies = endTime.getTime() - startTime.getTime();
                            long diffInMinutes = diffInMillies / (1000 * 60);
                            long hours = diffInMinutes / 60;
                            long minutes = diffInMinutes % 60;

                            out.print(hours + " giờ " + minutes + " phút");
                        %>
                    </td>

                    <td>${r.location}</td>
                    <td>${r.totalPrice}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

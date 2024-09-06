import React from 'react';
import { Card } from 'react-bootstrap';

const MyCardDetail = ({ parkingLot }) => {
    return (
        <Card className="bg-light shadow-lg rounded" style={{ width: '800px' }}>
            <Card.Header className="bg-primary text-white">Thông tin chi tiết</Card.Header>
            <Card.Body>
                <Card.Title>{parkingLot.name}</Card.Title>
                <Card.Text>Địa chỉ: {parkingLot.address}</Card.Text>
                <Card.Text>Tổng chỗ đỗ: {parkingLot.totalSpots} chỗ</Card.Text>
                <Card.Text>Giá: {parkingLot.pricePerHour}/giờ</Card.Text>
                <Card.Text>Tiện ích: {parkingLot.facilities}</Card.Text>
                <Card.Title>Còn {parkingLot.emptySpots} chỗ trống</Card.Title>
            </Card.Body>
        </Card>
    );
};

export default MyCardDetail;

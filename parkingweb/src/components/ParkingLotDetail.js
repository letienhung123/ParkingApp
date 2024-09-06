import { useEffect, useState } from "react";
import APIs, { endpoints } from "../configs/APIs";
import { useNavigate, useParams } from "react-router-dom";
import MySpinner from "../layout/MySpinner";
import { Card, Col, ListGroup, Row } from "react-bootstrap";
import MyCardDetail from "./MyCardDetail";

const ParkingLotDetail = () => {
    const [parkingSpots, setParkingSpots] = useState(null);
    const [parkingLot, setParkingLot] = useState(null);
    const { id } = useParams();
    const nav = useNavigate();

    const process = async () => {
        try {
            let res = await APIs.get(endpoints['getparkinglot'](id));
            setParkingLot(res.data);

            let spots = await APIs.get(endpoints['parkingspots'](id));
            setParkingSpots(spots.data);
        } catch (error) {
            console.error("Lỗi fetch api:", error);
        }
    }

    useEffect(() => {
        process();
    }, [id])

    if (parkingLot === null) {
        return <MySpinner />;
    }
    if (parkingSpots === null) {
        return <MySpinner />;
    }

    const getCardClass = (status) => {
        switch (status) {
            case 'Booked':
                return 'bg-primary text-white';
            case 'In Use':
                return 'bg-secondary text-white';
            case 'Fixing':
                return 'bg-warning text-dark';
            case 'Empty':
                return 'bg-light text-dark';
            default:
                return 'bg-light text-dark';
        }
    };

    const handleCardClick = (spot) => {
        nav(`/reservation/${spot.spotID}`, { state: { spot, parkingLot } });
    };

    return (
        <>
            <h1 className="text-center text-info">Bãi xe {parkingLot.name}</h1>
            <Row className="justify-content-between">
                <Col xs={12} md={6} lg={4} className="mb-3">
                    <MyCardDetail parkingLot={parkingLot} />
                </Col>
                <Col xs={12} md={6} lg={4} className="mb-3">
                    <Card className="bg-light shadow-lg rounded" style={{ maxWidth: '100%' }}>
                        <Card.Header className="bg-primary text-white">Thông tin chú thích</Card.Header>
                        <Card.Body>
                            <div className="d-flex align-items-center mb-2">
                                <Card className="bg-primary text-white me-3" style={{ width: '40px', height: '30px' }}></Card>
                                <span>Chỗ đậu đã được đặt</span>
                            </div>
                            <div className="d-flex align-items-center mb-2">
                                <Card className="bg-secondary text-white me-3" style={{ width: '40px', height: '30px' }}></Card>
                                <span>Chỗ đậu đang dùng</span>
                            </div>
                            <div className="d-flex align-items-center">
                                <Card className="bg-light text-white me-3" style={{ width: '40px', height: '30px' }}></Card>
                                <span>Chỗ đậu đang trống</span>
                            </div>
                        </Card.Body>
                    </Card>
                </Col>


            </Row>
            <h3 className="text-center text-info">Danh sách chỗ đậu</h3>
            <Row>
                {parkingSpots.map(spot => (
                    <Col xs={12} md={3} key={spot.spotID} className="mb-4">
                        <Card className={`mt-3 ${getCardClass(spot.status)}`} style={{ width: "30%" }} onClick={() => handleCardClick(spot)}>
                            <Card.Body>
                                <Card.Title className="text-center">{spot.level}-{spot.spotNumber}</Card.Title>
                            </Card.Body>
                        </Card>
                    </Col>
                ))}
            </Row>
        </>
    );
}

export default ParkingLotDetail;

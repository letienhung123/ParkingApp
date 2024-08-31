import { useEffect, useState } from "react";
import APIs, { endpoints } from "../configs/APIs";
import { Button, Card, Row, Col, Container } from "react-bootstrap";
import MySpinner from "../layout/MySpinner";
import { useSearchParams } from "react-router-dom";

const Home = () => {

    const [parkingLots, setParkingLots] = useState([]);
    const [q] = useSearchParams();

    const loadParkingLots = async () => {
        try {
            let e = endpoints['parkinglots'];

            let kw = q.get("kw");
            if(kw !== null)
                e = `${e}?kw=${kw}`;

            let res = await APIs.get(e);
            setParkingLots(res.data);
        } catch (error) {
            console.error("Lỗi lấy danh sách ParkingLots", error);
        }
    }

    useEffect(() => {
        loadParkingLots();
    }, [q])

    if(parkingLots ===  null)
        return <MySpinner></MySpinner>;

    return (
        <Container className="justify-content-center">
            <Row>
                {parkingLots.map(c => (
                    <Col xs={12} md={3} key={c.parkingLotID} className="mb-4">
                        <Card className="mt-3">
                            <Card.Img variant="top" src={c.imageURL} />
                            <Card.Body>
                                <Card.Title>{c.name}</Card.Title>
                                <Card.Text>Địa chỉ: {c.address}</Card.Text>
                                <Card.Text>Tổng chỗ đỗ: {c.totalSpots} chỗ</Card.Text>
                                <Card.Text>Giá: {c.pricePerHour}/giờ</Card.Text>
                                <Card.Text>Tiện ích: {c.facilities}</Card.Text>
                                <Card.Title>Còn {c.emptySpots} chỗ trống</Card.Title>
                                <Button variant="primary">Đặt chỗ</Button>
                            </Card.Body>
                        </Card>
                    </Col>
                ))}
            </Row>
        </Container>
    );
}

export default Home;

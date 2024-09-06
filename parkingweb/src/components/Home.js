import { useEffect, useState } from "react";
import APIs, { endpoints } from "../configs/APIs";
import { Button, Card, Row, Col, Container, Form } from "react-bootstrap";
import MySpinner from "../layout/MySpinner";
import { Link, useSearchParams } from "react-router-dom";

const Home = () => {
    const [parkingLots, setParkingLots] = useState([]);
    const [fromPrice, setFromPrice] = useState('');
    const [toPrice, setToPrice] = useState('');
    const [q, setQ] = useSearchParams();

    const loadParkingLots = async () => {
        try {
            let e = endpoints['parkinglots'];

            // Build query parameters
            const params = new URLSearchParams();
            const kw = q.get("kw");
            if (kw !== null) params.append("kw", kw);
            if (fromPrice) params.append("fromPrice", fromPrice);
            if (toPrice) params.append("toPrice", toPrice);

            if (params.toString()) {
                e = `${e}?${params.toString()}`;
            }

            let res = await APIs.get(e);
            setParkingLots(res.data);
        } catch (error) {
            console.error("Lỗi lấy danh sách ParkingLots", error);
        }
    }

    useEffect(() => {
        loadParkingLots();
    }, [q, fromPrice, toPrice]);

    const handlePriceChange = (event) => {
        const { name, value } = event.target;
        if (name === "fromPrice") {
            setFromPrice(value);
        } else if (name === "toPrice") {
            setToPrice(value);
        }
    }

    const applyFilters = () => {
        const newParams = new URLSearchParams(q.toString());
        if (fromPrice) newParams.set("fromPrice", fromPrice);
        if (toPrice) newParams.set("toPrice", toPrice);
        setQ(newParams);
    }

    if (parkingLots === null)
        return <MySpinner />;

    return (
        <Container className="justify-content-center">
            <Container className="mt-4">
                <Row>
                    <Col xs={12} md={6} className="d-flex align-items-center">
                        <Form className="d-flex">
                            <Form.Group controlId="formFromPrice" className="me-2">
                                <Form.Label className="visually-hidden">Giá từ</Form.Label>
                                <Form.Control type="number" name="fromPrice" value={fromPrice} onChange={handlePriceChange} placeholder="Giá từ"/>
                            </Form.Group>
                            <Form.Group controlId="formToPrice" className="me-2">
                                <Form.Label className="visually-hidden">Giá đến</Form.Label>
                                <Form.Control type="number" name="toPrice" value={toPrice} onChange={handlePriceChange} placeholder="Giá đến"/>
                            </Form.Group>
                            
                        </Form>
                    </Col>
                </Row>
            </Container>
            <Row className="mt-4">
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
                                <Button variant="primary" as={Link} to={`/parkinglot/${c.parkingLotID}`}>Đặt chỗ</Button>
                            </Card.Body>
                        </Card>
                    </Col>
                ))}
            </Row>
        </Container>
    );
}

export default Home;

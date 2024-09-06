import { Card, Col, Container, Row } from "react-bootstrap";
import { MyUserContext } from "../App";
import { useContext, useEffect, useState } from "react";
import { authApi, endpoints } from "../configs/APIs";

const UserProfile = () => {
    const [user] = useContext(MyUserContext);
    const [reservation, setReservation] = useState([]);
    const [countdown, setCountdown] = useState('');

    const [reservate, setReservate] = useState(null);
    const [spot, setSpot] = useState(null);
    const [parkingLot, setParkingLot] = useState(null);

    useEffect(() => {
        const savedReservation = localStorage.getItem('reservation');
        const savedSpot = localStorage.getItem('spot');
        const savedParkingLot = localStorage.getItem('parkingLot');

        if (savedReservation && savedSpot && savedParkingLot) {
            setReservate(JSON.parse(savedReservation));
            setSpot(JSON.parse(savedSpot));
            setParkingLot(JSON.parse(savedParkingLot));
        }
    }, []);

    const loadReservation = async () => {
        try {
            const res = await authApi().get(endpoints['reservations'](user.accountID));
            setReservation(res.data || []);
        } catch (error) {
            console.error("Error fetching reservations", error);
            setReservation([]);
        }
    }

    useEffect(() => {
        loadReservation();
    }, []);

    useEffect(() => {
        if (reservate) {
            const updateCountdown = async () => {
                const now = new Date();
                const startTime = new Date(reservate.startTime);
                const endTime = new Date(reservate.endTime);

                let timeDiff;
                let message = '';

                if (now < startTime) {
                    timeDiff = startTime - now;
                    message = `Thời gian check-in còn lại: ${convertTime(timeDiff)}`;
                } else if (now >= startTime && now <= endTime) {
                    timeDiff = endTime - now;
                    message = `Thời gian đỗ còn lại: ${convertTime(timeDiff)}`;

                    try {
                        await authApi().get(endpoints['updateInUse'](spot.spotID));
                        console.log('Chỗ đỗ xe đang được sử dụng.');
                    } catch (error) {
                        console.error('lỗi update InUse:', error);
                    }
                } else {

                    try {
                        await authApi().get(endpoints['updateEmpty'](spot.spotID));
                        try {
                            await authApi().get(endpoints['update_reservation'](user.accountID));
                            console.log('Yêu cầu cập nhật thành công.');
                        } catch (error) {
                            console.error('Lỗi cập nhật reservation:', error);
                        }

                        console.log('Chỗ đỗ xe hiện đang trống.');
                    } catch (error) {
                        console.error('lỗi update Empty:', error);
                    }
                    message = 'Thời gian đỗ đã kết thúc.';

                    localStorage.removeItem('reservation');
                    localStorage.removeItem('spot');
                    localStorage.removeItem('parkingLot');


                    setReservate(null);
                    setSpot(null);
                    setParkingLot(null);
                }

                setCountdown(message);
            };

            updateCountdown();
            const intervalId = setInterval(updateCountdown, 1000);

            return () => clearInterval(intervalId);
        }
    }, [reservate, spot]);

    const convertTime = (milliseconds) => {
        const differenceInSeconds = Math.floor(milliseconds / 1000);
        const differenceInMinutes = Math.floor(differenceInSeconds / 60);
        const hours = Math.floor(differenceInMinutes / 60);
        const minutes = differenceInMinutes % 60;
        const seconds = differenceInSeconds % 60;
        return `${hours} giờ ${minutes} phút ${seconds} giây`;
    };

    return (
        <Container className="mt-5">
            <Row className="d-flex justify-content-start">
                <Col md="6">
                    <Card>
                        <Card.Header as="h5">Thông tin cá nhân </Card.Header>
                        <Card.Body>
                            <Row>
                                <Col md="4">
                                    <Card.Img
                                        src={user.avatar || "https://via.placeholder.com/150"}
                                        alt="User profile picture"
                                        roundedCircle
                                    />
                                </Col>
                                <Col md="8">
                                    <Card.Title>{user.firstName} {user.lastName}</Card.Title>
                                    <Card.Text>
                                        <strong>Email:</strong> {user.email}
                                    </Card.Text>
                                    <Card.Text>
                                        <strong>Phone:</strong> {user.phoneNumber}
                                    </Card.Text>
                                    <Card.Text>
                                        <strong>Address:</strong>
                                    </Card.Text>
                                </Col>
                            </Row>
                        </Card.Body>
                    </Card>
                </Col>
                {(reservate && spot && parkingLot) && (
                    <Col md="6">
                        <Card>
                            <Card.Header as="h5">Trạng thái đỗ xe</Card.Header>
                            <Card.Body>
                                <Row>
                                    <Col md="8">
                                        <Card.Title>{parkingLot.name}</Card.Title>
                                        <Card.Text>
                                            <strong>Vị trí:</strong> {`${spot.level}-${spot.spotNumber}`}
                                        </Card.Text>
                                        <Card.Text>
                                            <strong>{countdown}</strong>
                                        </Card.Text>
                                    </Col>
                                </Row>
                            </Card.Body>
                        </Card>
                    </Col>
                )}
            </Row>
            <Row className="mt-4">
                <Col>
                    <h5>Lịch sử thuê bãi xe</h5>
                    {reservation.length > 0 ? (
                        reservation.map((item, index) => (
                            <Card className="mb-3" key={index}>
                                <Card.Body>
                                    <Row>
                                        <Col md="4"><strong>Tên bãi xe:</strong> {item.parkingLotName}</Col>
                                        <Col md="2"><strong>Số giờ thuê:</strong> {convertTime(new Date(item.endTime) - new Date(item.startTime))}</Col>
                                        <Col md="3"><strong>Tổng tiền:</strong> {item.totalPrice} VND</Col>
                                        <Col md="3"><strong>Trạng thái:</strong> {item.reservationStatus}</Col>
                                    </Row>
                                </Card.Body>
                            </Card>
                        ))
                    ) : (
                        <p>Không có lịch sử thuê bãi xe.</p>
                    )}
                </Col>
            </Row>
        </Container>
    );
}

export default UserProfile;

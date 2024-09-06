import { useState } from "react";
import { Button, Container, Form } from "react-bootstrap";
import DatePicker from "react-datepicker";
import { useLocation, useNavigate } from "react-router-dom";
import "react-datepicker/dist/react-datepicker.css";
import { authApi, endpoints } from "../configs/APIs";

const OrderForm = () => {
    const location = useLocation();
    const { spot, parkingLot } = location.state || {};
    const [startTime, setStartTime] = useState(null);
    const [endTime, setEndTime] = useState(null);
    const nav = useNavigate();

    const now = new Date();
    const currentHour = now.getHours();
    const currentMinutes = now.getMinutes();

    const [reservation, setReservation] = useState({
        "startTime": "", "endTime": "", "totalPrice": "",
        "reservationStatus": "Waiting", "parkingSpotId": spot.spotID
    })

    const calculateTotalPrice = (start, end) => {
        const startTime = new Date(start).getTime();
        const endTime = new Date(end).getTime();
        const durationInHours = Math.round((endTime - startTime) / (1000 * 60)); // đổi sang phút
        return (durationInHours / 60) * parkingLot.pricePerHour;
    };

    // const change = (date, field) => {
    //     setReservation({ ...reservation, [field]: date })
    // }
    const submit = (evt) => {
        evt.preventDefault();
        const updatedReservation = {
            ...reservation,
            startTime: startTime ? new Date(startTime).toISOString() : '',
            endTime: endTime ? new Date(endTime).toISOString() : '',
            totalPrice: calculateTotalPrice(startTime, endTime)
        };

        const process = async () => {
            let res = await authApi().post(endpoints['pay'], updatedReservation);
            console.info(updatedReservation.data);
            if (res.status === 201)
                localStorage.setItem('reservation', JSON.stringify(updatedReservation));
                localStorage.setItem('spot', JSON.stringify(spot));
                localStorage.setItem('parkingLot', JSON.stringify(parkingLot));

                nav('/user');
        }
        process();
    }


    return <><h1 className="text-center">Thông tin đặt chỗ</h1>
        <Container className="justify-content-center">
            <Form onSubmit={submit}>
                <Form.Group >
                    <Form.Label>Địa điểm</Form.Label>
                    <Form.Control type="text" value={parkingLot.name} readOnly />
                </Form.Group>
                <Form.Group >
                    <Form.Label>Vị trí đỗ</Form.Label>
                    <Form.Control type="text" value={`${spot.level}-${spot.spotNumber}`} readOnly />
                </Form.Group>
                <Form.Group controlId="formStartTime">
                        <Form.Label>Chọn thời gian bắt đầu:</Form.Label>
                        <p>
                        <DatePicker 
                                selected={startTime}
                                onChange={setStartTime}
                                showTimeSelect
                                showTimeSelectOnly
                                timeIntervals={15}
                                timeCaption="Thời gian"
                                dateFormat="h:mm aa"
                                placeholderText="Chọn giờ bắt đầu"
                                minTime={new Date().setHours(currentHour, currentMinutes)}
                                maxTime={new Date().setHours(23, 45)}
                                defaultValue={new Date()}
                            />
                            </p>
                    </Form.Group>
                    <Form.Group controlId="formStartTime">
                        <Form.Label className="font-weight-bold">Chọn thời gian kết thúc:</Form.Label>
                        <p>
                        <DatePicker
                                selected={endTime}
                                onChange={setEndTime}
                                showTimeSelect
                                showTimeSelectOnly
                                timeIntervals={30}
                                timeCaption="Thời gian"
                                dateFormat="h:mm aa"
                                placeholderText="Chọn giờ kết thúc"
                                minTime={new Date().setHours(currentHour, currentMinutes)}
                                maxTime={new Date().setHours(23, 45)}
                                defaultValue={new Date()}
                            />
                            </p>
                    </Form.Group>
                    <Form.Group >
                    <Form.Label>Vị trí đỗ</Form.Label>
                    <Form.Control type="text" value={`Tổng tiền: ${calculateTotalPrice(startTime, endTime)}`} readOnly />
                </Form.Group>
                    <Form.Group className="mb-3">
                    <Button variant="info" type="submit">Đặt chỗ</Button>
                </Form.Group>
            </Form>
        </Container>
    </>
}
export default OrderForm;
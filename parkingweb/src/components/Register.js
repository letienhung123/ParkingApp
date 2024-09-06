import { useRef, useState } from "react";
import { Alert, Button, Form, Modal, Spinner } from "react-bootstrap";
import APIs, { endpoints } from "../configs/APIs";
import { useNavigate } from "react-router-dom";


const Register = () => {
    const [user, setUser] = useState({
        "username": "", "password": "", "confirmPass": "", "firstName": "",
        "lastName": "", "email": "", "phone": ""
    });
    const avatar = useRef();
    const nav = useNavigate();
    const [err, setErr] = useState(null);
    const [loading,setLoading] = useState(false);

    const register = (evt) => {
        evt.preventDefault();

        const process = async () => {
            let form = new FormData();

            for (let field in user)
                if (field !== "confirmPass")
                    form.append(field, user[field]);

            form.append("avatar", avatar.current.files[0]);

            setLoading(true)
            let res = await APIs.post(endpoints['register'], form);
            if (res.status === 201)
                nav("/login")
        }
        if (user.password === user.confirmPass)
            process();
        else
            setErr("Nhập mật khẩu không khớp");
    }

    const change = (evt, field) => {
        setUser({ ...user, [field]: evt.target.value })
    }

    return (
        <>
            <h1 className="text-center text-inf mt-2">Đăng kí người dùng</h1>
            {err === null?"":<Alert variant="danger">{err}</Alert>}
            {loading === true?
         <Modal show={true} backdrop="static" keyboard={false} centered contentClassName="loading-modal">
         <Modal.Body className="d-flex justify-content-center align-items-center">
           <Spinner animation="border" role="status">
           </Spinner>
           <p className="sr-only">Loading...</p>
         </Modal.Body>
       </Modal>   
        :""}
            <Form onSubmit={register}>
                <Form.Group className="mb-3">
                    <Form.Label>Họ và tên lót</Form.Label>
                    <Form.Control value={user.firstName} onChange={e => change(e, "firstName")} type="text" placeholder="Họ và tên lót..." required />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Tên</Form.Label>
                    <Form.Control value={user.lastName} onChange={e => change(e, "lastName")} type="text" placeholder="Tên..." required />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Tên đăng nhập</Form.Label>
                    <Form.Control value={user.username} onChange={e => change(e, "username")} type="text" placeholder="Tên đăng nhập..." required />
                </Form.Group>
                <Form.Group className="mb-3" >
                    <Form.Label>Mật khẩu</Form.Label>
                    <Form.Control value={user.password} onChange={e => change(e, "password")} autoComplete="new-password" type="password" placeholder="Mật khẩu..." required />
                </Form.Group>
                <Form.Group className="mb-3" >
                    <Form.Label>Xác nhận mật khẩu</Form.Label>
                    <Form.Control value={user.confirmPass} onChange={e => change(e, "confirmPass")} autoComplete="new-password" type="password" placeholder="..." />
                </Form.Group>
                <Form.Group className="mb-3" >
                    <Form.Label>Email</Form.Label>
                    <Form.Control value={user.email} onChange={e => change(e, "email")} type="email" placeholder="..." />
                </Form.Group>
                <Form.Group className="mb-3" >
                    <Form.Label>Số điện thoại</Form.Label>
                    <Form.Control value={user.phone} onChange={e => change(e, "phone")} type="number" placeholder="..." />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Ảnh đại diện</Form.Label>
                    <Form.Control type="file" ref={avatar} />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Button variant="info" type="submit">Đăng Kí</Button>
                </Form.Group>
            </Form>
        </>)
}
export default Register;
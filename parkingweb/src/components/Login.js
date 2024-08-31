import { useContext, useState } from "react";
import { Button, Form } from "react-bootstrap";
import APIs, { authApi, endpoints } from "../configs/APIs";
import cookie from "react-cookies"
import { MyUserContext } from "../App";
import { Navigate, useNavigate } from "react-router-dom";

const Login = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const [username,setUsername] = useState();
    const [password,setPassword] = useState();
    const nav = useNavigate();

    const login = (evt) => {
        evt.preventDefault();

        const process = async () => {
            try {
                let res = await APIs.post(endpoints['login'], {
                    "username": username,
                    "password": password
                });
                cookie.save("token", res.data);
                // console.info(res.data);

                let user = await authApi().get(endpoints["current-user"]);
                cookie.save("user", user.data);
                console.info(user.data);

                dispatch({          //fetch lên dispacher thông báo thay đổi state
                    "type": "login",
                    "payload": user.data //****
                })

            }
            catch (ex) {
                console.error(ex)
            }
        }

        process();
    } 

    if (user!==null)
        return <Navigate to="/" />

    return (
        <>
            <h1 className="text-center text-info">Đăng nhập</h1>
            <Form onSubmit={login}>
                <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                    <Form.Label>Tên đăng nhập</Form.Label>
                    <Form.Control value={username} onChange={e => setUsername(e.target.value)}  type="text" placeholder="Tên đăng nhập..." />
                </Form.Group>
                <Form.Group className="mb-3" controlId="exampleForm.ControlInput2">
                    <Form.Label>Mật khẩu</Form.Label>
                    <Form.Control value={password} onChange={e => setPassword(e.target.value)}  type="password" placeholder="Mật khẩu..." />
                </Form.Group>
                <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                    <Button variant="info" type="submit">Đăng nhập</Button>
                </Form.Group>
            </Form>
        </>
    )
}
export default Login;
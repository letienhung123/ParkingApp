import { useContext, useState } from "react";
import { Button, Container, Form, Nav, Navbar } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { MyUserContext } from "../App";
const Header = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const [kw,setKw] = useState("");
    const nav = useNavigate(); //single page

    const search = (evt) => {
        evt.preventDefault();//single page
        nav(`/?kw=${kw}`)
    }

    const logout = () => {
        dispatch({
            type: "logout"
        })
    }

    return (<>
        <Navbar bg="dark" data-bs-theme="dark">
            <Container>
                <Navbar.Brand href="#home">Smart-Parking</Navbar.Brand>
                <Nav className="me-auto">
                    <Link className="nav-link" to="/">Trang chủ</Link>
                    <Link className="nav-link" href="#features">Features</Link>
                    <Link className="nav-link" href="#pricing">Hồ sơ cá nhân</Link>
                    {user === null ? <Link className="nav-link text-danger" to="/login">Đăng nhập</Link> : 
                    <>
                        <Link className="nav-link text-info" to="/">Chào {user.firstName} {user.lastName}!</Link>
                        <Button onClick={logout} variant="secondary">Đăng xuất</Button>
                    </>}
                </Nav>
                
                <Form onSubmit={search} className="d-flex">
                    <Form.Control
                        type="search"
                        placeholder="Nhập từ khoá..."
                        className="me-2"
                        aria-label="Search"
                        value={kw}
                        onChange={e => setKw(e.target.value)}
                    />
                    <Button type="submit" variant="outline-success">Tìm</Button>
                </Form>
            </Container>
        </Navbar>
    </>)
}
export default Header;
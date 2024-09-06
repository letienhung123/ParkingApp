import { Col, Container, Row } from "react-bootstrap";

const Footer = () => {
    return (
        <>
            <footer className="bg-dark text-white mt-4 p-4">
                <Container>
                    <Row>
                        <Col md={4}>
                            <h5>About Us</h5>
                            <p>Some information about the company.</p>
                        </Col>
                        <Col md={4}>
                            <h5>Contact Us</h5>
                            <p>Email: info@example.com</p>
                            <p>Phone: +123 456 7890</p>
                        </Col>
                    </Row>
                </Container>
            </footer>
        </>
    );
}
export default Footer;
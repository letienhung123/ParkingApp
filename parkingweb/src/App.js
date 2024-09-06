import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./layout/Header";
import Footer from "./layout/Footer";
import Home from "./components/Home";
import 'bootstrap/dist/css/bootstrap.min.css';
import Login from "./components/Login";
import { createContext, useReducer } from "react";
import MyUserReducer from "./reducers/MyUserReducer";
import cookie from "react-cookies"
import Register from "./components/Register";
import { Container } from "react-bootstrap";
import ParkingLotDetail from "./components/ParkingLotDetail";
import OrderForm from "./components/OrderForm";
import UserProfile from "./components/UserProfile";

export const MyUserContext = createContext();

const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, cookie.load("user") || null);//hkgkhgjh

  return (
    <MyUserContext.Provider value={[user, dispatch]}>
      <BrowserRouter>
        <Header />
        <Container>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/parkinglot/:id" element={<ParkingLotDetail />} />
            <Route path="/reservation/:id" element={<OrderForm />} />
            <Route path="/user" element={<UserProfile />} />
          </Routes>
        </Container>
        <Footer />
      </BrowserRouter>
    </MyUserContext.Provider>
  )
}
export default App;
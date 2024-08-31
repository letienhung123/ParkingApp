import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./layout/Header";
import Footer from "./layout/Footer";
import Home from "./components/Home";
import 'bootstrap/dist/css/bootstrap.min.css';
import Login from "./components/Login";
import { createContext, useReducer } from "react";
import MyUserReducer from "./reducers/MyUserReducer";
import cookie from "react-cookies"

export const MyUserContext = createContext();

const App = () =>{
  const[user, dispatch] = useReducer(MyUserReducer, cookie.load("user") || null);//hkgkhgjh

  return(
    <MyUserContext.Provider value={[user, dispatch]}>
      <BrowserRouter>
        <Header/>
          <Routes>
            <Route path="/" element={<Home />}/>
            <Route path="/login" element={<Login/>}/>
          </Routes>
        <Footer/>
      </BrowserRouter>
    </MyUserContext.Provider>
  )    
}
export default App;
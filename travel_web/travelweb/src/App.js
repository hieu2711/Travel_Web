import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./components/Home";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container } from "react-bootstrap";
import BaseLayout from "./layout/BaseLayout";
import Register from "./components/Register/Register";
import Details from "./components/Details/Details";
import Tours from "./components/Tours/Tours";
import { createContext, useReducer } from "react";
import MyUserReducer from "./reducers/MyUserReducer";
import cookie from "react-cookies";
import Login from "./components/Login/Login";
import News from "./components/News/News";
import ImageTours from "./components/ImageTours/ImageTours";
import 'moment/locale/vi';
import Receipts from "./components/Receipts/Receipts";

export const MyUserContext = createContext();



const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, cookie.load("user") || null);
  return (
    <MyUserContext.Provider value={[user, dispatch]}>
    <BrowserRouter>
      <Container>
        <Routes>
        <Route path="/" element={<BaseLayout><Home /></BaseLayout>} />
        <Route path="/details" element={<BaseLayout><Details /></BaseLayout>} />
        <Route path="/tours" element={<BaseLayout><Tours/></BaseLayout>} />
        <Route path="/tours/:toursId" element={<BaseLayout><Details/></BaseLayout>} />
        <Route path="/tours/receipts/:toursId" element={<BaseLayout><Receipts/></BaseLayout>} />
        <Route path="/news" element={<BaseLayout><News/></BaseLayout>} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/tours/image/:toursId" element={<ImageTours/>} />
          
        </Routes>
      </Container>
    </BrowserRouter>
    </MyUserContext.Provider>
  )
}
export default App;
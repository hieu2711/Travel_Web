import { useContext, useEffect } from "react";
import { useState } from "react";
import { Button, Container, Nav, NavDropdown, Navbar } from "react-bootstrap";
import Apis, { endpoints } from "../configs/Apis";
import MySpinner from "./MySpinner";
import { Link } from "react-router-dom";
import { MyUserContext } from "../App";
import './Header.css'

const Header = () => {
  const [user, dispatch] = useContext(MyUserContext);
  const [categories, setCategories] = useState(null)

  const loadCates = async () => {
    let res = await Apis.get(endpoints['categories'])
    setCategories(res.data);
  }

  useEffect(() => {
    loadCates();
  }, []);

  const logout = () => {
    dispatch({
      "type": "logout"
    })
  }

  if (categories === null)
    return <MySpinner />
  return (
    <Navbar expand="lg" className="bg-body-tertiary">
      <Container style={{ fontweight: '700' }}>
        <Navbar.Brand href="#home" >Travel Website</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto" style={{ textAlign: "center" }}>
            <Link style={{ width: "80px", marginTop: "12px", textDecoration: "none", color: "#000000", marginRight: "7px" }} href="#home" to="/">Trang chủ</Link>
            <Link style={{ width: "130px", marginTop: "12px", textDecoration: "none", color: "#000000" }} to="/tours">Danh sách Tours</Link>
            <NavDropdown title="Danh mục" id="basic-nav-dropdown" style={{ marginTop: "4px" }}>
              {categories.map(c => {
                let h = `/tours/?cateId=${c.id}`;
                return <Link className="dropdown-item" to={h} key={c.id}>{c.categoryName}</Link>
              })}
            </NavDropdown>
            <Link style={{ width: "110px", marginTop: "12px", textDecoration: "none", color: "#000000" }} to="/news">Tin tức du lịch</Link>
            <Link style={{ width: "80px", marginTop: "12px", textDecoration: "none", color: "#000000" }} to="/register">
              {user === null ? "Đăng kí" : null}</Link>
            {user === null ? <Link style={{ marginTop: "4px", marginLeft: "400px" }} className="nav-link text-body" to="/login">Đăng nhập</Link>
              : <>
                <img
                  style={{ marginLeft: "300px" }}
                  width={"45px"}
                  height={"45px"}
                  src={user.avatar}
                  className="rounded-circle small-avatar"
                  alt="Avatar"
                />
                <Link className="nav-link text-danger" to="/">{user.username}!</Link>
                <Link className="nav-link text-body" onClick={logout}>Đăng xuất</Link>
              </>}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  )
}
export default Header;
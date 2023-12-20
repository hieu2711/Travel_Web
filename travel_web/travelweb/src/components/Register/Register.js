import { Container, Form, Button, Alert } from 'react-bootstrap';
import './Register.css';
import { Link, useNavigate } from 'react-router-dom';
import { useRef, useState } from 'react';
import Apis, { endpoints } from '../../configs/Apis';
import MySpinner from '../../layout/MySpinner';
const Register = () => {
    const [user, setUser] = useState({
        "username": "",
        "password": "",
        "name": "",
        "identification": "",
        "email": "",
        "phonenumber": "",
        "address": "",
        "sex": "",
        "confirmPass": ""
    });

    const nav = useNavigate();
    const [err, setErr] = useState(null);
    const [loading, setLoading] = useState(false);
    const avatar = useRef();
    const [usernameExists, setUsernameExists] = useState(false);

    const checkUsernameExistence = async (username) => {
            try {
                const formData = new FormData(); // Tạo đối tượng FormData
                formData.append('username', username); // Thêm dữ liệu vào FormData
                const response = await Apis.post(endpoints['username-exist'], formData);
            } catch (error) {
                //setUsernameExists = true
                setUsernameExists(error.response.status === 400);
            }

    };

    const change = (evt, field) => {
        setUsernameExists(false)
        // setUser({...user, [field]: evt.target.value})
        setUser(current => {
            return { ...current, [field]: evt.target.value }
        })
        if (field === 'username') {
            checkUsernameExistence(evt.target.value);
        }
    }

    const register = (evt) => {
        evt.preventDefault();

        const process = async () => {
            let form = new FormData();

            for (let field in user)
                if (field !== "confirmPass")
                    form.append(field, user[field]);

            form.append("avatar", avatar.current.files[0]);
            console.log(usernameExists)
            setLoading(true)
            if (usernameExists === false) {
                let res = await Apis.post(endpoints['register'], form);
                if (res.status === 201) {
                    nav("/login");
                } else
                    setErr("Hệ thống bị lỗi!");
            }
            else {
                setErr("Tên đăng nhập đã tồn tại");
                setLoading(false)
                setUser({
                    "username": "",
                    "password": "",
                    "name": "",
                    "identification": "",
                    "email": "",
                    "phonenumber": "",
                    "address": "",
                    "sex": "",
                    "confirmPass": ""
                });
                setUsernameExists(false)
            }

        }

        if (user.password === user.confirmPass)
            process();
        else {
            { err && <div className="error-message">{err}</div> }
            setErr("Mật khẩu không khớp!");
        }
    }

    return (
        <Container>
            <Form onSubmit={register} style={{ border: "1px solid #ccc" }}>
                <h1>Đăng kí</h1>
                {err === null ? "" : <Alert variant="danger">{err}</Alert>}
                <Form.Group>
                    <Form.Label>Tên khách hàng</Form.Label>
                    <Form.Control value={user.name} onChange={(e) => change(e, "name")} style={{ marginLeft: "110px", width: "70%" }} type="text" placeholder="Tên khách hàng" name="name" required />
                </Form.Group>
                <div className="d-flex" style={{marginLeft:"220px"}}>
                    <Form.Check style={{ marginRight: "10px" }}
                        type="radio"
                        label="Nam"
                        value="0"
                        required
                        name="gender"
                        checked={user.sex === '0'}
                        onChange={(e) => change(e, "sex")}
                    />
                    <Form.Check
                        type="radio"
                        label="Nữ"
                        value="1"
                        required
                        name="gender"
                        style={{ marginRight: "30px" }}
                        checked={user.sex === '1'}
                        onChange={(e) => change(e, "sex")}
                    />
                    <Form.Label style={{ marginLeft: "30px" }}>Số điện thoại</Form.Label>
                    <Form.Control value={user.phonenumber} onChange={(e) => change(e, "phonenumber")} style={{ margin: "0", padding: "0", marginTop: "-5px", width: "220px", marginLeft: "10px" }} type="text" placeholder="Số điện thoại" name="phone" required />
                    <Form.Label style={{ marginLeft: "30px" }}>CMND</Form.Label>
                    <Form.Control value={user.identification} onChange={(e) => change(e, "identification")} style={{ margin: "0", padding: "0", marginTop: "-5px", width: "230px", marginLeft: "10px" }} type="text" placeholder="CMND" name="identification" required />
                </div>

                <Form.Group style={{display:"flex",alignItems:"center",marginTop:"40px"}} >
                    <Form.Label style={{marginRight:"180px"}}>Email</Form.Label>
                    <Form.Control value={user.email} onChange={(e) => change(e, "email")} style={{ margin:"0", width: "70%"}} type="email" placeholder="Email" name="email" required />
                </Form.Group>

                <Form.Group>
                    <Form.Label>Địa chỉ</Form.Label>
                    <Form.Control value={user.address} onChange={(e) => change(e, "address")} style={{ marginLeft: "170px", width: "70%" }} type="text" placeholder="Địa chỉ" name="address" required />
                </Form.Group>

                <Form.Group>
                    <Form.Label>Tên đăng nhập</Form.Label>
                    <Form.Control value={user.username} onChange={(e) => change(e, "username")}  style={{ marginLeft: "115px", width: "70%" }} type="text" placeholder="Username" name="username" required />
                    {usernameExists && <Alert variant="danger">Tên đăng nhập đã tồn tại  vui lòng đổi tên đăng nhập khác</Alert>}
                </Form.Group>

                <Form.Group>
                    <Form.Label>Mật khẩu</Form.Label>
                    <Form.Control value={user.password} onChange={(e) => change(e, "password")} disabled={usernameExists} style={{ marginLeft: "150px", width: "70%" }} type="password" placeholder="Password" name="password" required />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Xác nhận mật khẩu</Form.Label>
                    <Form.Control style={{ marginLeft: "80px", width: "70%" }} disabled={usernameExists} value={user.confirmPass} onChange={(e) => change(e, "confirmPass")} type="password" placeholder="Xác nhận mật khẩu" required />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Ảnh đại diện</Form.Label>
                    <Form.Control style={{ marginLeft: "213px", marginTop: "-40px" }} type="file" ref={avatar} required />
                </Form.Group>
                <Form.Group className="mb-3">
                    {loading === true ? <MySpinner /> : <Button style={{ width: "50%", marginLeft: "300px" }}  variant="info" type="submit">Đăng ký</Button>}
                </Form.Group>
                <div class="signup_link">Bạn đã có tài khoản? <Link to="/login">Đăng nhập</Link></div>
            </Form>
        </Container>
    )
}

export default Register;
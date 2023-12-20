import { Link, Navigate } from 'react-router-dom';
import './Login.css';
import { useContext, useState } from 'react';
import Apis, { authApi, endpoints } from '../../configs/Apis';
import { MyUserContext } from '../../App';
import cookie from "react-cookies";

const Login = () => {
  const [user, dispatch] = useContext(MyUserContext);
  const [username,setUsername] = useState();
  const [password,setPassword] = useState();
  const login = (evt) => {
    evt.preventDefault();

    const process = async () => {
      try {
        let res = await Apis.post(endpoints['login'],
        {
          "username":username,
          "password":password

      });
      cookie.save("token", res.data);
      console.log(res.data);
      let {data} = await authApi().get(endpoints['current-user']);
      cookie.save("user", data);
      console.log(data)
      dispatch({
        "type": "login",
        "payload": data
    });
      } catch (error) {
        console.error(error);
      }
      
    }
    process();
  }
  if (user !== null)
        return <Navigate to="/" />
    return (
        <div class="login">
        <h1>Đăng nhập</h1>
        <form onSubmit={login}>
          <div class="form-control">
            <input value={username} onChange={e => setUsername(e.target.value)} type="text" id="username" placeholder="Username" />
          </div>
          <div class="form-control">
            <input value={password} onChange={e => setPassword(e.target.value)} type="password" id="password" placeholder="Password" />
          </div>
          <input type="submit" value="Đăng nhập" />
          <div class="signup_link">Bạn chưa có tài khoản? <Link to="/register">Đăng ký</Link></div>
          <div class="signup_link" > <Link to="/">Quay lại</Link></div>
        </form>
      </div>
    )
}

export default Login;
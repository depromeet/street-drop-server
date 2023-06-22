import React, {useState} from "react";
import {useNavigate} from 'react-router-dom';
import axios from "axios";
import '../styles/Login.css';

function isValidEmail(emailStr) {
    var regExp =
        /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    if (emailStr.match(regExp) != null) {
        return true;
    } else {
        return false;
    }
}


function Login() {
    const [inputEmail, setInputEmail] = useState("");
    const [inputPassword, setInputPassword] = useState("");
    let [loginStatus, setLoginStatus] = useState("Login");
    const navigate = useNavigate();

    const handleLoginStatus = async () => {
        setLoginStatus("Login");
    }

    const handleEmailChange = (e) => {
        setInputEmail(e.target.value);
    }

    const handlePasswordChange = (e) => {
        setInputPassword(e.target.value);
    }

    const onClickLogin = () => {
        if (isValidEmail(inputEmail)) {
            if (loginStatus === "Verify") {
                axios.post("/api/auth", {
                    email: inputEmail
                }).then(res => {
                    console.log(res);
                    if (res.status === 200) {
                        console.log("이메일 전송 성공");
                        alert('이메일을 확인해주세요.')
                        handleLoginStatus();
                    }
                }).catch(err => {
                    console.log(err);
                })
            } else if (loginStatus === "Login") {
                axios.post("/api/users", {
                    email: inputEmail,
                }).then(res => {
                    console.log(res);
                    if (res.status === 200 || res.status === 201) {
                        const data = JSON.stringify({accessToken: res.data.access_token});
                        localStorage.setItem("user", JSON.stringify(data));
                        navigate("/");
                    }
                }).catch(err => {
                    console.log(err);
                })
            }
        } else {
            alert('이메일 형식이 올바르지 않습니다.');
        }

    }

    return (
        <div class="page-container">
            <div class="login-form-container shadow">
                <div class="login-form-right-side">
                    <div class="top-logo-wrap">

                    </div>
                    <h1>Street Drop Admin Login</h1>
                    <p>Let’s drop the music. This Website for Street Drop Admin Page</p>
                </div>
                <div class="login-form-left-side">

                    <div class="login-input-container">
                        <div class="login-input-wrap input-id">
                            <i class="far fa-envelope"></i>
                            <input placeholder="Email" type="text" name='input_email' value={inputEmail}
                                   onChange={handleEmailChange}/>
                        </div>
                        <div class="login-input-wrap input-pw">
                            <i class="fas fa-lock"></i>
                            <input placeholder="Password" type="password" name='input_password' value={inputPassword}
                                   onChange={handlePasswordChange}/>
                        </div>
                    </div>
                    <div class="login-btn-wrap">
                        <button class="login-btn" onClick={onClickLogin}>{loginStatus}</button>
                    </div>
                </div>
            </div>
        </div>
    )
}


export default Login;

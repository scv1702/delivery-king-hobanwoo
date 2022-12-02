import { Link } from "react-router-dom";
import Button from "../../components/Button/Button";
import Container from "../../components/Container/Container";
import styles from "./LoginPage.module.css";
import axios from "axios";
import React from "react";

function LoginPage() {
  function loginHandler() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    if (!username) {
      alert("아이디를 입력해주세요");
      document.getElementById("userid").focus();
    } else if (!password) {
      alert("비밀번호를 입력해주세요");
      document.getElementById("password").focus();
    } else {
      axios
        .post(
          "http://localhost:15010/session",
          {
            username,
            password,
          },
          {
            withCredentials: true,
          }
        )
        .then((res) => {
          alert(res.data.message);
          window.location.href = "/";
        })
        .catch((err) => {
          alert(err.response.data.message);
        });
    }
  }
  return (
    <Container className={styles.container}>
      <h1 className={styles.title}>로그인</h1>
      <div className={styles.alignCenter}>
        <h3 className={styles.subtitle}>아이디</h3>
        <input className={styles.inputbox} id="username"></input>
        <br></br>
        <br></br>
        <h3 className={styles.subtitle}>비밀번호</h3>
        <input
          className={styles.inputbox}
          id="password"
          type="password"
        ></input>
      </div>
      <div className={styles.link}>
        <Button as="div" onClick={loginHandler}>
          로그인
        </Button>
      </div>
    </Container>
  );
}

export default LoginPage;

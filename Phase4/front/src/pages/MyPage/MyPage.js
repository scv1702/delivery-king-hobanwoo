import { Link } from "react-router-dom";
import Container from "../../components/Container/Container";
import styles from "./MyPage.module.css";
import { useEffect, useState } from "react";
import axios from "axios";
import CouponList from "../../components/CouponList";
import MyOrderList from "../../components/MyOrderList";
import { Form } from "react-bootstrap";
function MyPage() {
  const [userInfo, setUserInfo] = useState({});

  useEffect(() => {
    axios
      .get("http://localhost:15010/users", {
        withCredentials: true,
      })
      .then((res) => {
        setUserInfo(res.data.user);
      });
  }, []);

  return (
    <>
      <div className={styles.header}>
        <Container className={styles.content}>
          <h1 className={styles.title}>내 프로필</h1>
        </Container>
      </div>
      <Container className={styles.content}>
        <Container className={styles.profile}>
          <h3>아이디</h3>
          <Form.Control type="text" value={userInfo.username} readOnly />
          <h3>학과명</h3>
          <Form.Control type="text" value={userInfo.dname} readOnly />
          <h3>전화번호</h3>
          <Form.Control type="text" value={userInfo.phoneNumber} readOnly />
        </Container>
        <MyOrderList />
        <CouponList />
      </Container>
    </>
  );
}

export default MyPage;

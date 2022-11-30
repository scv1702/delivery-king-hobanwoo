import { Link } from "react-router-dom";
import tablestyle from "./tableCss/style.css";
import bootstrap from "./tableCss/bootstrap.min.css";
import Container from "../components/Container";
import styles from "./MyPage.module.css";
import { useEffect, useState } from "react";
import axios from "axios";
import CouponList from "../components/CouponList";
import MyOrderList from "../components/MyOrderList";

function MyPage() {
  const [userInfo, setUserInfo] = useState({});
  const [myOrders, setMyOrders] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:3010/users", {
        withCredentials: true,
      })
      .then((res) => {
        setUserInfo(res.data.user);
      });
  }, []);

  return (
    <Container className={styles.container}>
      <h1 className={styles.title}>내 프로필</h1>
      <h3 className={styles.subtitle}>아이디</h3>
      <input
        id="id"
        className={styles.inputbox}
        value={userInfo.username}
        readOnly
      ></input>
      <h3 className={styles.subtitle}>학과명</h3>
      <input
        id="department"
        className={styles.inputbox}
        value={userInfo.dname}
        readOnly
      ></input>
      <h3 className={styles.subtitle}>전화번호</h3>
      <input
        id="phone"
        className={styles.inputbox}
        value={userInfo.phoneNumber}
        readOnly
      ></input>
      <CouponList />
      <MyOrderList />
    </Container>
  );
}

export default MyPage;

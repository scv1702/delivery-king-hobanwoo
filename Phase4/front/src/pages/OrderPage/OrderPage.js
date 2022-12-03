import { useState, useEffect } from "react";
import { Link, useSearchParams } from "react-router-dom";
import Button from "../../components/Button/Button";
import Container from "../../components/Container/Container";
import styles from "./OrderPage.module.css";
import axios from "axios";
import MenuList from "../../components/MenuList/MenuList";
import Carousel from "react-bootstrap/Carousel";
import card from "../../assets/card.png";

function OrderPage() {
  const [searchParams, setSearchParams] = useSearchParams();
  const storeId = searchParams.get("storeId");
  const [menuList, setMenuList] = useState([]);
  const [store, setStore] = useState(null);
  const [orderMenuList, setOrderMenuList] = useState([]);

  useEffect(() => {
    axios.get(`http://localhost:15010/stores/${storeId}/menu`).then((res) => {
      setMenuList(res.data);
    });
    axios.get(`http://localhost:15010/stores/${storeId}`).then((res) => {
      console.log(res.data);
      setStore(res.data);
    });
  }, []);

  return (
    <>
      <div className={styles.header}>
        <Container className={styles.content}>
          <h1 className={styles.title}>주문하기</h1>
        </Container>
      </div>
      <Container className={styles.container}>
        <MenuList menuList={menuList} storeId={storeId} order={true} />
        <h3 className={styles.subtitle}>결제 방식</h3>
        <select id="payType" className={styles.inputbox}>
          <option value="카드">카드</option>
          <option value="현금">현금</option>
        </select>
        <h3 className={styles.subtitle}>총 주문 금액</h3>
        <p style={{ marginLeft: "35%" }}>5000원</p>
        <h3 className={styles.subtitle}>배달팁</h3>
        <p style={{ marginLeft: "35%" }}>{store && store.deliveryFee}원</p>
        <div className={styles.link}>
          <Button as="div">주문하기</Button>
        </div>
      </Container>
    </>
  );
}

export default OrderPage;

import { Link } from "react-router-dom";
import Button from "../../components/Button/Button";
import Container from "../../components/Container/Container";
import styles from "./OrderPage.module.css";

function OrderPage() {
  return (
    <Container className={styles.container}>
      <h1 className={styles.title}>주문하기</h1>
      <h3 className={styles.subtitle}>메뉴이름</h3>
      <select id="department" className={styles.inputbox}>
        <option value="none">===메뉴를 선택해주세요===</option>
        <option value="떡볶이">떡볶이</option>
        <option value="순대">순대</option>
        <option value="튀김">튀김</option>
      </select>
      <h3 className={styles.subtitle}>주문수량</h3>
      <input
        className={styles.inputbox}
        placeholder="주문수량을 입력하세요"
      ></input>
      <h3 className={styles.subtitle}>결제 방식</h3>
      <select id="payType" className={styles.inputbox}>
        <option value="none">===결제방식을 선택해주세요===</option>
        <option value="카드">카드</option>
        <option value="현금">현금</option>
      </select>
      <div className={styles.link}>
        <Link to="/">
          <Button as="div">주문하기</Button>
        </Link>
      </div>
    </Container>
  );
}

export default OrderPage;

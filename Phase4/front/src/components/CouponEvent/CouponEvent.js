import styles from "./CouponEvent.module.css";
import Card from "../Card/Card";
import DateText from "../DateText";
import { Button } from "react-bootstrap";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function CouponEvent({ couponEvent }) {
  const navigate = useNavigate();

  function couponEventHandler() {
    axios
      .post(
        `http://localhost:15010/coupons?couponEventId=${couponEvent.couponEventId}`,
        {
          couponEventId: couponEvent.couponEventId,
        },
        { withCredentials: true }
      )
      .then((res) => {
        alert(res.data.message);
        navigate("/mypage");
      })
      .catch((err) => {
        alert(err.response.data.message);
      });
  }

  return (
    <Card className={styles.reviewItem}>
      <div className={styles.info}>
        <p className={styles.title}>이벤트 명: {couponEvent.description}</p>
        <p>남은 수량: {couponEvent.quantity}개</p>
        <p>할인율: {couponEvent.discountAmount}%</p>
        <p>최소 주문 금액: {couponEvent.minimumOrderAmount}원</p>
        <p className={styles.date}>
          만료 일자: <DateText value={couponEvent.expirationDate} />
        </p>
      </div>
      <Button onClick={() => couponEventHandler()} variant="primary">
        쿠폰 받기
      </Button>
    </Card>
  );
}

export default CouponEvent;

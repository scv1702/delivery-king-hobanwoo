import { useEffect, useState } from "react";
import ListPage from "../../components/ListPage/ListPage";
import Warn from "../../components/Warn/Warn";
import styles from "./ReviewListPage.module.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import CouponEvent from "../../components/CouponEvent/CouponEvent";

function CouponEventPage() {
  const navigate = useNavigate();
  const [couponEventList, setCouponEventList] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:15010/coupon-event").then((res) => {
      setCouponEventList(res.data);
    });
  }, []);

  return (
    <ListPage
      variant="community"
      title="쿠폰 이벤트"
      description="배달왕 호반우의 쿠폰 이벤트를 확인해보세요."
    >
      <p className={styles.count}>총 {couponEventList.length}개 쿠폰 이벤트</p>

      {couponEventList.length === 0 ? (
        <Warn
          style={{ margin: "5%" }}
          title="진행 중인 쿠폰 이벤트가 없습니다."
        />
      ) : (
        <div className={styles.reviewList}>
          {couponEventList &&
            couponEventList.map((couponEvent) => (
              <CouponEvent
                key={couponEvent.couponEventId}
                couponEvent={couponEvent}
              />
            ))}
        </div>
      )}
    </ListPage>
  );
}

export default CouponEventPage;

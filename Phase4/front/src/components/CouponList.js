import styles from "../pages/MyPage/MyPage.module.css";
import CouponItem from "./CouponItem";
import { useState, useEffect } from "react";
import axios from "axios";
import { Table } from "react-bootstrap";
import Warn from "./Warn/Warn";

function CouponList() {
  const [coupons, setCoupons] = useState([]);
  useEffect(() => {
    axios
      .get("http://localhost:3010/users/coupons", {
        withCredentials: true,
      })
      .then((res) => {
        setCoupons(res.data);
      });
  }, []);
  return (
    <>
      {coupons.length > 0 ? (
        <>
          <h3>내 쿠폰</h3>
          <Table responsive>
            <thead>
              <tr>
                <th scope="col">쿠폰 ID</th>
                <th scope="col">할인율</th>
                <th scope="col">유효일자</th>
                <th scope="col">최소 사용 금액</th>
                <th scope="col">상태</th>
              </tr>
            </thead>
            <tbody>
              {coupons.map((coupon, index) => (
                <CouponItem key={index} coupon={coupon} />
              ))}
            </tbody>
          </Table>
        </>
      ) : (
        <Warn style={{ margin: "5%" }} title="보유한 쿠폰이 없습니다." />
      )}
    </>
  );
}

export default CouponList;

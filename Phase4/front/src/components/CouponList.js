import styles from "../pages/MyPage/MyPage.module.css";
import CouponItem from "./CouponItem";
import { useState, useEffect } from "react";
import axios from "axios";
import { Table } from "react-bootstrap";

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
      <h1>내 쿠폰</h1>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th scope="col">쿠폰ID</th>
            <th scope="col">할인율</th>
            <th scope="col">유효일자</th>
            <th scope="col">최소사용금액</th>
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
  );
}

export default CouponList;

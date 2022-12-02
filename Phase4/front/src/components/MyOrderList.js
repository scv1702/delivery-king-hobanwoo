import MyOrderItem from "./MyOrderItem/MyOrderItem";
import { useEffect, useState } from "react";
import axios from "axios";
import Table from "react-bootstrap/Table";

function MyOrderList() {
  const [myOrders, setMyOrders] = useState([]);
  useEffect(() => {
    axios
      .get("http://localhost:15010/users/orders", { withCredentials: true })
      .then((res) => {
        setMyOrders(res.data);
      });
  }, []);

  return (
    <>
      <h1>내 주문</h1>
      <Table stred bordered hover>
        <thead>
          <tr>
            <th scope="col">주문ID</th>
            <th scope="col">주문 가게</th>
            <th scope="col">주문 일자</th>
            <th scope="col">주문 금액</th>
            <th scope="col">주문 상태</th>
            <th scope="col">리뷰 쓰기</th>
          </tr>
        </thead>
        <tbody>
          {myOrders.map((myOrder, index) => (
            <MyOrderItem key={index} myOrder={myOrder} />
          ))}
        </tbody>
      </Table>
    </>
  );
}

export default MyOrderList;

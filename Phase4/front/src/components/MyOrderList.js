import MyOrderItem from "./MyOrderItem";
import { useEffect, useState } from "react";
import axios from "axios";

function MyOrderList() {
  const [myOrders, setMyOrders] = useState([]);
  useEffect(() => {
    axios
      .get("http://localhost:3010/users/orders", { withCredentials: true })
      .then((res) => {
        console.log(res.data);
        setMyOrders(res.data);
      });
  }, []);

  return (
    <>
      <h1>내 주문</h1>
      <div className="custom-table-responsive">
        <table className="table custom-table">
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
        </table>
      </div>
    </>
  );
}

export default MyOrderList;

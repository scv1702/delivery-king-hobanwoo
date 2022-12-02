import MyOrderItem from "./MyOrderItem/MyOrderItem";
import { useEffect, useState } from "react";
import axios from "axios";
import Table from "react-bootstrap/Table";
import Warn from "./Warn/Warn";

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
      {myOrders.length > 0 ? (
        <>
          <h3>내 주문</h3>
          <Table responsive>
            <thead>
              <tr>
                <th scope="col">주문 ID</th>
                <th scope="col">주문 가게</th>
                <th scope="col">주문 일자</th>
                <th scope="col">주문 금액</th>
                <th scope="col">주문 상태</th>
                <th scope="col">리뷰 쓰기</th>
                <th scope="col">리뷰 삭제</th>
              </tr>
            </thead>
            <tbody>
              {myOrders.map((myOrder, index) => (
                <MyOrderItem key={index} myOrder={myOrder} />
              ))}
            </tbody>
          </Table>
        </>
      ) : (
        <Warn style={{ margin: "5%" }} title="주문 내역이 없습니다." />
      )}
    </>
  );
}

export default MyOrderList;

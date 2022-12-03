import DateText from "../DateText";
import axios from "axios";
import { Modal, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { useState } from "react";

function MyOrderItem({ myOrder }) {
  const navigate = useNavigate();
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const deleteHanler = (reviewId) => {
    axios
      .delete(`http://localhost:15010/reviews/${reviewId}`, {
        withCredentials: true,
      })
      .then((res) => {
        alert(res.data.message);
      })
      .catch((err) => {
        alert(err.response.data.message);
      });
  };

  const writeHandler = () => {
    navigate(`/createreview?orderId=${myOrder.orderId}`);
  };

  const totalPrice = myOrder.orderMenuList.reduce(
    (acc, cur) => acc + cur.price * cur.quantity,
    0
  );

  return (
    <>
      <tr scope="row">
        <td>{myOrder.store.storeName}</td>
        <td>
          <DateText value={myOrder.orderDate}></DateText>
        </td>
        <td>{myOrder.state}</td>
        <td>{myOrder.payment}</td>
        <td>
          <Button
            onClick={handleShow}
            as="input"
            type="button"
            size="sm"
            value="주문 상세"
            variant="secondary"
          />
        </td>
        {myOrder.review.length > 0 ? (
          <>
            <td>
              <Button
                onClick={() => {
                  deleteHanler(myOrder.review[0].reviewId);
                }}
                as="input"
                type="button"
                value="리뷰 삭제"
                size="sm"
                variant="danger"
              />
            </td>
          </>
        ) : (
          <>
            <td>
              <Button
                onClick={writeHandler}
                as="input"
                type="button"
                value="리뷰 쓰기"
                size="sm"
                variant="secondary"
              />
            </td>
          </>
        )}
      </tr>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header>
          <Modal.Title>주문 상세</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <div style={{ marginBottom: "2%" }}>
            {myOrder.orderMenuList.map((orderMenu, index) => (
              <>
                <p>메뉴 이름: {orderMenu.menuName}</p>
                <p>가격: {orderMenu.price}원</p>
                <p>수량: {orderMenu.quantity}개</p>
              </>
            ))}
          </div>
          <p>배달비: {myOrder.store.deliveryFee}원</p>
          <p>총 가격: {totalPrice + myOrder.store.deliveryFee}원</p>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            닫기
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default MyOrderItem;

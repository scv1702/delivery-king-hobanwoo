import DateText from "../DateText";
import axios from "axios";
import { Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

function MyOrderItem({ myOrder }) {
  const navigate = useNavigate();

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

  return (
    <>
      <tr scope="row">
        <td>{myOrder.orderId}</td>
        <td>{myOrder.store.storeName}</td>
        <td>
          <DateText value={myOrder.orderDate}></DateText>
        </td>
        <td>{myOrder.state}</td>
        <td>{myOrder.payment}</td>
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
              />
            </td>
          </>
        )}
      </tr>
    </>
  );
}

export default MyOrderItem;

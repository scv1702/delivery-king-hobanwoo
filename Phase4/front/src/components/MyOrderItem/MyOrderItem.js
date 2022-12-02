import DateText from "../DateText";
import axios from "axios";

const handleDelete = (reviewId) => {
  axios
    .delete(`http://localhost:15010/reviews/${reviewId}`)
    .then((res) => {
      alert(res.data.message);
    })
    .catch((err) => {
      alert(err.response.data.message);
    });
};

function MyOrderItem({ myOrder }) {
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
        {myOrder.isReviewed ? (
          <td>리뷰 완료</td>
        ) : (
          <td>
            <a href={`/createreview?orderId=${myOrder.orderId}`}>리뷰 쓰기</a>
          </td>
        )}
      </tr>
    </>
  );
}

export default MyOrderItem;

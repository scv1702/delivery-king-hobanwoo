import DateText from "./DateText";

function MyOrderItem({ myOrder }) {
  return (
    <>
      {console.log(myOrder)}
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
      <tr className="spacer">
        <td colspan="100"></td>
      </tr>
    </>
  );
}

export default MyOrderItem;

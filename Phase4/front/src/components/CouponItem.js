import DateText from "./DateText";

function CouponItem({ coupon }) {
  return (
    <>
      <tr scope="row">
        <td>{coupon.couponId}</td>
        <td>{coupon.discountAmount}%</td>
        <td>
          <DateText value={coupon.expirationDate}></DateText>
        </td>
        <td>{coupon.minimumOrderAmount}</td>
        <td>{coupon.state}</td>
      </tr>
    </>
  );
}

export default CouponItem;

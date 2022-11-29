import styles from "../pages/MyPage.module.css";
import CouponItem from "./CouponItem";

function CouponList({ coupons }) {
  return (
    <>
      <h1 className={styles.title}>내 쿠폰</h1>
      <div className="custom-table-responsive">
        <table className="table custom-table">
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
        </table>
      </div>
    </>
  );
}

export default CouponList;
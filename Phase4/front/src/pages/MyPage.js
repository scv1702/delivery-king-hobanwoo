import Container from "../components/Container";
import styles from "./MyPage.module.css";
import tablestyle from "./tableCss/style.css";
import bootstrap from "./tableCss/bootstrap.min.css";

function MyPage() {
  return (
    <Container className={styles.container}>
      <h1 className={styles.title}>내 프로필</h1>

      <h3 className={styles.subtitle}>아이디</h3>
      <input id="id" className={styles.inputbox} value="user" readOnly></input>
      <h3 className={styles.subtitle}>학과명</h3>
      <input
        id="department"
        className={styles.inputbox}
        value="컴퓨터학부"
        readOnly
      ></input>
      <h3 className={styles.subtitle}>전화번호</h3>
      <input
        id="phone"
        className={styles.inputbox}
        value="010-123-456"
        readOnly
      ></input>

      <h1 className={styles.title}>내 쿠폰</h1>
      <div class="custom-table-responsive">
        <table class="table custom-table">
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
            <tr scope="row">
              <td>1392</td>
              <td>30%</td>
              <td>2022-12-21</td>
              <td>10000</td>
              <td>미사용</td>
            </tr>
            <tr class="spacer">
              <td colspan="100"></td>
            </tr>
            <tr>
              <td>9841</td>
              <td>3000원</td>
              <td>2023-01-13</td>
              <td>10000</td>
              <td>미사용</td>
            </tr>
            <tr class="spacer">
              <td colspan="100"></td>
            </tr>
            <tr>
              <td>9841</td>
              <td>3000원</td>
              <td>2023-01-13</td>
              <td>10000</td>
              <td>미사용</td>
            </tr>
            <tr class="spacer">
              <td colspan="100"></td>
            </tr>
          </tbody>
        </table>
      </div>
    </Container>
  );
}

export default MyPage;

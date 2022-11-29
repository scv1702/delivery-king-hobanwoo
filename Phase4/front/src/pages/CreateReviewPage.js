import Button from "../components/Button";
import Container from "../components/Container";
import styles from "./CreateReviewPage.module.css";
import classNames from "classnames";

function CreateReviewException() {
  var createReview = document.getElementById("reviewContents").value;

  if (!createReview) {
    alert("리뷰 내용을 작성해주세요");
    document.getElementById("reviewContents").focus();
  } else {
    window.location.href = "/";
  }
}

function SignupPage() {
  return (
    <Container className={styles.container}>
      <h1 className={styles.title}>리뷰 작성</h1>
      <form>
        <select id="orderList" className={styles.inputbox}>
          <option value="none">=== 리뷰를 기다리는 주문내역 ===</option>
          <option value="정돈카츠">정돈카츠</option>
          <option value="마사">마사</option>
          <option value="신서울족발보쌈">신서울족발보쌈</option>
        </select>
        <div
          className={classNames(
            styles.star_rating,
            styles.subtitle,
            styles.leftmove
          )}
        >
          <input
            type="radio"
            id="5-stars"
            name="rating"
            value="5"
            v-model="ratings"
          />
          <label for="5-stars" class="star pr-4">
            ★
          </label>
          <input
            type="radio"
            id="4-stars"
            name="rating"
            value="4"
            v-model="ratings"
          />
          <label for="4-stars" class="star">
            ★
          </label>
          <input
            type="radio"
            id="3-stars"
            name="rating"
            value="3"
            v-model="ratings"
          />
          <label for="3-stars" class="star">
            ★
          </label>
          <input
            type="radio"
            id="2-stars"
            name="rating"
            value="2"
            v-model="ratings"
          />
          <label for="2-stars" class="star">
            ★
          </label>
          <input
            type="radio"
            id="1-star"
            name="rating"
            value="1"
            v-model="ratings"
          />
          <label for="1-star" class="star">
            ★
          </label>
        </div>
        <textarea
          id="reviewContents"
          rows="7"
          className={styles.inputbox}
          placeholder="음식의 맛, 양, 포장 상태 등 음식에 대한 리뷰를 남겨주세요."
        ></textarea>
      </form>
      <div className={styles.link}>
        <Button id="createReview" onClick={CreateReviewException} as="div">
          리뷰쓰기
        </Button>
      </div>
    </Container>
  );
}

export default SignupPage;

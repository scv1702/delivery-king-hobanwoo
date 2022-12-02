import Button from "../../components/Button/Button";
import Container from "../../components/Container/Container";
import styles from "./CreateReviewPage.module.css";
import classNames from "classnames";
import axios from "axios";
import { useSearchParams } from "react-router-dom";

function CreateReviewPage() {
  const [searchParams, setSearchParams] = useSearchParams();
  const orderId = searchParams.get("orderId");

  function CreateReviewException() {
    var comments = document.getElementById("reviewContents").value;
    var starRating = document.querySelector(
      'input[name="rating"]:checked'
    ).value;
    if (!comments) {
      alert("리뷰 내용을 작성해주세요!");
      document.getElementById("reviewContents").focus();
    } else if (!starRating) {
      alert("별점을 입력해주세요!");
    } else {
      axios
        .post(
          `http://localhost:15010/reviews?orderId=${orderId}`,
          {
            comments,
            starRating,
          },
          { withCredentials: true }
        )
        .then((res) => {
          alert(res.data.message);
          window.location.href = "/reviews";
        })
        .catch((err) => {
          alert(err.response.data.message);
        });
    }
  }

  return (
    <Container className={styles.container}>
      <h1 className={styles.title}>리뷰 작성</h1>
      <form>
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

export default CreateReviewPage;

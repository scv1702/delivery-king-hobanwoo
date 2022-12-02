import styles from "./ReviewItem.module.css";
import Card from "../Card/Card";
import { Link } from "react-router-dom";
import DateText from "../DateText";

function ReviewItem({ review }) {
  return (
    <Card className={styles.reviewItem} key={review.comments}>
      <div className={styles.info}>
        별점: {review.starRating}
        <p className={styles.title}>
          <Link to={`/reviews/${review.reviewId}`}>{review.comments}</Link>
        </p>
        <p className={styles.date}>
          <DateText value={review.createdAt} />
        </p>
      </div>
      <div className={styles.writer}>{review.user.username}</div>
    </Card>
  );
}

export default ReviewItem;
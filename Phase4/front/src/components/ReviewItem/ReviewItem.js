import styles from "./ReviewItem.module.css";
import Card from "../Card/Card";
import DateText from "../DateText";
import Tags from "../Tags/Tags";

function ReviewItem({ review }) {
  return (
    <Card className={styles.reviewItem} key={review.comments}>
      <div className={styles.info}>
        <p>별점: {review.starRating}</p>
        <p className={styles.title}>{review.comments}</p>
        <div>
          {review.orderMenuList?.map((orderMenu, index) => {
            return <Tags key={index} values={[orderMenu.menuName]} />;
          })}
        </div>
        <br></br>
        <br></br>
        <p className={styles.date}>
          <DateText value={review.createdAt} />
        </p>
      </div>
      <div className={styles.writer}>{review.user.username}</div>
    </Card>
  );
}

export default ReviewItem;

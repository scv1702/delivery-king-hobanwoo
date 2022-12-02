import classNames from "classnames";
import { Navigate, useParams } from "react-router-dom";
import Avatar from "../../components/Avatar/Avatar";
import Card from "../../components/Card/Card";
import Container from "../../components/Container/Container";
import DateText from "../../components/DateText";
import Lined from "../../components/Lined/Lined";
import Warn from "../../components/Warn/Warn";
import styles from "./ReviewPage.module.css";

function ReviewPage({ review }) {
  const { reviewId } = useParams();

  return (
    <>
      <div className={styles.header}>
        <Container>
          <div className={styles.review}>
            <div className={styles.reviewInfo}>
              <div className={styles.content}>
                <div className={styles.title}>
                  {review.title}
                  {review.answers > 0 && (
                    <span className={styles.count}>
                      {review.answers.length}
                    </span>
                  )}
                </div>
                <div className={styles.date}>
                  <DateText value={review.createdAt} />
                </div>
              </div>
              <Writer className={styles.author} writer={review.writer} />
            </div>
            <p
              className={styles.content}
              dangerouslySetInnerHTML={{ __html: review.content }}
            />
          </div>
        </Container>
      </div>
      <Container className={styles.answers}>
        <h2 className={styles.count}>
          <Lined>{review.answers.length}개 리뷰</Lined>
        </h2>
        {review.answers.length > 0 ? (
          review.answers.map((answer) => (
            <Answer
              key={answer.id}
              className={styles.answerItem}
              answer={answer}
            />
          ))
        ) : (
          <Warn
            title="리뷰를 기다리고 있어요."
            description="이 가게의 첫 번째 리뷰를 달아주시겠어요?"
          />
        )}
      </Container>
    </>
  );
}

function Writer({ className, writer }) {
  return (
    <div className={classNames(className, styles.writer)}>
      <div className={styles.info}>
        <div className={styles.name}>{writer.name}</div>
        <div className={styles.level}>{writer.level}</div>
      </div>
      <Avatar photo={writer.profile.photo} name={writer.name} />
    </div>
  );
}

function Answer({ className, answer }) {
  return (
    <Card className={classNames(styles.answer, className)} key={answer.id}>
      <p dangerouslySetInnerHTML={{ __html: answer.content }} />
      <div className={styles.answerInfo}>
        <div className={styles.date}>
          <DateText value={answer.createdAt} />
        </div>
        <Writer writer={answer.writer} />
      </div>
    </Card>
  );
}

export default ReviewPage;

import Button from "../../components/Button/Button";
import Container from "../../components/Container/Container";
import Warn from "../../components/Warn/Warn";
import styles from "./NotFoundPage.module.css";
import { Link } from "react-router-dom";

function NotFoundPage() {
  return (
    <Container className={styles.container}>
      <Warn
        variant="big"
        title="존재하지 않는 페이지에요."
        description="올바른 주소가 맞는지 다시 한 번 확인해 주세요."
      />
      <div className={styles.link}>
        <Link to="/">
          <Button as="div">홈으로 가기</Button>
        </Link>
      </div>
    </Container>
  );
}

export default NotFoundPage;

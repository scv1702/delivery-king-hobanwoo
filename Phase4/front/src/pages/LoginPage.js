import { Link } from 'react-router-dom';
import Button from '../components/Button';
import Container from '../components/Container';
import styles from './LoginPage.module.css';

function LoginPage() {

  return (
    <Container className={styles.container}>
        <h1 className={styles.title}>로그인</h1>
        <h3 className={styles.subtitle}>아이디</h3>
        <input className={styles.inputbox}
          placeholder="아이디를 입력하세요"
        ></input>
        <br></br>
        <br></br>
        <h3 className={styles.subtitle}>비밀번호</h3>
        <input className={styles.inputbox}
          placeholder="비밀번호를 입력하세요"
        ></input>
        <div className={styles.link}>
            <Link to="/">
              <Button as="div">로그인</Button>
            </Link>
        </div>
    </Container>
  );
}

export default LoginPage;

import axios from "axios";
import Button from "../../components/Button/Button";
import Container from "../../components/Container/Container";
import styles from "../LoginPage/LoginPage.module.css";

function SignupException() {
  var username = document.getElementById("username").value;
  var password = document.getElementById("password").value;
  var passwordCheck = document.getElementById("passwordCheck").value;
  var department = document.getElementById("department").value;
  var phoneNumber = document.getElementById("phoneNumber").value;

  if (!username) {
    alert("아이디를 입력해주세요");
    document.getElementById("username").focus();
  } else if (!password) {
    alert("비밀번호를 입력해주세요");
    document.getElementById("password").focus();
  } else if (!department) {
    alert("학과를 입력해주세요");
    document.getElementById("department").focus();
  } else if (!phoneNumber) {
    alert("전화번호를 입력해주세요");
    document.getElementById("phoneNumber").focus();
  } else if (password !== passwordCheck) {
    alert("비밀번호가 일치하지 않습니다");
    document.getElementById("passwordCheck").focus();
  } else {
    axios
      .post("http://localhost:15010/users", {
        username,
        dname: department,
        password,
        phoneNumber,
      })
      .then((res) => {
        alert(res.data.message);
        if (res.data.success) {
          window.location.href = "/";
        }
      });
  }
}

function SignupPage() {
  return (
    <>
      <div className={styles.header}>
        <Container className={styles.content}>
          <h1 className={styles.title}>회원가입</h1>
        </Container>
      </div>
      <Container>
        <div className={styles.alignCenter}>
          <h3 className={styles.subtitle}>아이디</h3>
          <form>
            <input id="username" className={styles.inputbox}></input>
            <h3 className={styles.subtitle}>비밀번호</h3>
            <input id="password" className={styles.inputbox}></input>
            <h3 className={styles.subtitle}>비밀번호 확인</h3>
            <input id="passwordCheck" className={styles.inputbox}></input>
            <h3 className={styles.subtitle}>학과명</h3>
            <select id="department" className={styles.inputbox}>
              <option value="사회과학대학">사회과학대학</option>
              <option value="자연과학대학">자연과학대학</option>
              <option value="경상대학">경상대학</option>
              <option value="공과대학">공과대학</option>
              <option value="IT대학">IT대학</option>
              <option value="농업생명과학대">농업생명과학대</option>
              <option value="예술대학">예술대학</option>
              <option value="사범대학">사범대학</option>
              <option value="의과대학">의과대학</option>
              <option value="치과대학">치과대학</option>
              <option value="수의과대학">수의과대학</option>
              <option value="생활과학대학">생활과학대학</option>
              <option value="간호대학">간호대학</option>
              <option value="약학대학">약학대학</option>
              <option value="생태환경대학">생태환경대학</option>
              <option value="과학기술대학">과학기술대학</option>
              <option value="행정학부">행정학부</option>
              <option value="자율전공부">자율전공부</option>
              <option value="융합학부">융합학부</option>
            </select>
            <h3 className={styles.subtitle}>전화번호</h3>
            <input id="phoneNumber" className={styles.inputbox}></input>
          </form>
        </div>
        <div className={styles.link}>
          <Button id="signup" onClick={SignupException} as="div">
            회원가입
          </Button>
        </div>
      </Container>
    </>
  );
}

export default SignupPage;

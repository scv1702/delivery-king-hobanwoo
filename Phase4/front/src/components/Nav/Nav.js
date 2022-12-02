import { Link, NavLink } from "react-router-dom";
import Container from "./../Container/Container";
import UserMenu from "../UserMenu/UserMenu";
import logoImg from "../../assets/hanwooLogo.png";
import styles from "./Nav.module.css";
// 리액트에서는 인라인스타일을 지정할때 HTML과 다르게 문자열이 아니라
// 객체로 스타일 속성값을 지정해주어야 한다.
// 속성: '값' 형식으로 작성

function getLinkStyle({ isActive }) {
  // 해당 Menu가 액티브상태이면 underline 적용
  return {
    textDecoration: isActive ? "underline" : "",
  };
}

// Link 컴포넌트에서는 to 라는 Prop을 사용한다.
function Nav() {
  return (
    <div className={styles.nav}>
      <Container className={styles.container}>
        <Link to="/">
          <img className={styles.marginImg} src={logoImg} alt="Codethat Logo" />
        </Link>
        <ul className={styles.menu}>
          <li>
            <NavLink style={getLinkStyle} to="/stores">
              가게 목록
            </NavLink>
          </li>
          <li>
            <NavLink style={getLinkStyle} to="reviews">
              리뷰
            </NavLink>
          </li>
          <li>
            <UserMenu />
          </li>
        </ul>
      </Container>
    </div>
  );
}

export default Nav;

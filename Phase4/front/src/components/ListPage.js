import classNames from "classnames";
import Container from "./Container";
import styles from "./ListPage.module.css";
import store from "../assets/hotpot.png";
import community from "../assets/community.svg";
import home from "../assets/hobanwoo.png";

const ICON = {
  home: {
    src: home,
    alt: "홈페이지",
  },
  store: {
    src: store,
    alt: "가게",
  },
  community: {
    src: community,
    alt: "말풍선",
  },
};

function ListPage({
  variant = "home",
  title = "",
  description = "",
  children,
}) {
  const icon = ICON[variant] || ICON.store;
  if (variant === "home") {
    return (
      <>
        <div className={classNames(styles.bg, styles[variant])}>
          <div className={styles.texts}>
            <h1 className={styles.homeheading}>찾아가던</h1>
            <h1 className={styles.homeheading}>맛집을</h1>
            <h1 className={styles.homeheading}>원하는 곳에서!</h1>
            <p className={styles.homedescription}>
              배달이 안되던 동네 맛집까지
            </p>
            <p className={styles.homedescription}>직접 배달해드립니다.</p>
          </div>
          <img className={styles.bigicon} src={icon.src} alt={icon.alt} />
        </div>
        <Container className={styles.container}>{children}</Container>
      </>
    );
  }
  return (
    <>
      <div className={classNames(styles.bg, styles[variant])}>
        <img className={styles.icon} src={icon.src} alt={icon.alt} />
        <div className={styles.texts}>
          <h1 className={styles.heading}>{title}</h1>
          <p className={styles.description}>{description}</p>
        </div>
      </div>
      <Container className={styles.container}>{children}</Container>
    </>
  );
}

export default ListPage;

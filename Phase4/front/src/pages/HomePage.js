import { Helmet } from "react-helmet";
import styles from "./HomePage.module.css";
import { Link } from "react-router-dom";
import ListPage from "../components/ListPage";
import Card from "../components/Card";
import bossam from "../assets/bossam.png";
import pasta from "../assets/pasta.png";
import tteokbok from "../assets/tteokbokki.png";
import blackNoodle from "../assets/jjajangmyeon.png";
import sushi from "../assets/sushi.png";
import noodle from "../assets/noodles.png";
import stew from "../assets/stew.png";
import dessert from "../assets/dessert.png";
import hamburger from "../assets/hamburger.png";
import schoolfood from "../assets/schoolfood.png";

function HomePage() {
  const categorys = [
    "중식",
    "분식",
    "돈까스,회,일식",
    "족발,보쌈",
    "아시안",
    "양식",
    "찜,탕,찌개",
    "패스트푸드",
    "카페,디저트",
    "학식",
  ];
  return (
    // npm install --save react-helmet 해서 헬멧으로 title명 바꾸기 기능 추가
    <>
      <Helmet>
        <title>경북대 제휴 가게 배달앱, 배달왕 호반우</title>
      </Helmet>
      <ListPage variant="home">
        <div className={styles.categoryList}>
          <Card className={styles.categoryItem}>
            <div className={styles.CardCenter}>
              <Link to={`/stores?keyword=중식`}>
                <img src={blackNoodle} alt="짜장면" />
                <h2>중식</h2>
              </Link>
            </div>
          </Card>
          <Card className={styles.categoryItem}>
            <div className={styles.CardCenter}>
              <Link to={`/stores?keyword=분식`}>
                <img src={tteokbok} alt="떡볶이" />
                <h2>분식</h2>
              </Link>
            </div>
          </Card>
          <Card className={styles.categoryItem}>
            <div className={styles.CardCenter}>
              <Link to={`/category/${categorys[2]}`}>
                <img src={sushi} alt="스시" />
                <h2>돈까스·회·일식</h2>
              </Link>
            </div>
          </Card>
          <Card className={styles.categoryItem}>
            <div className={styles.CardCenter}>
              <Link to={`/category/${categorys[3]}`}>
                <img src={bossam} alt="보쌈" />
                <h2>족발·보쌈</h2>
              </Link>
            </div>
          </Card>
          <Card className={styles.categoryItem}>
            <div className={styles.CardCenter}>
              <Link to={`/stores?keyword=아시안`}>
                <img src={noodle} alt="쌀국수" />
                <h2>아시안</h2>
              </Link>
            </div>
          </Card>
          <Card className={styles.categoryItem}>
            <div className={styles.CardCenter}>
              <Link to={`/stores?keyword=양식`}>
                <img src={pasta} alt="파스타" />
              </Link>
              <h2>양식</h2>
            </div>
          </Card>
          <Card className={styles.categoryItem}>
            <div className={styles.CardCenter}>
              <Link to={`/category/${categorys[6]}`}>
                <img src={stew} alt="탕,찌개,찜" />
              </Link>
              <h2>찜·탕·찌개</h2>
            </div>
          </Card>
          <Card className={styles.categoryItem}>
            <div className={styles.CardCenter}>
              <Link to={`/category/${categorys[7]}`}>
                <img src={hamburger} alt="패스트푸드" />
                <h2>패스트푸드</h2>
              </Link>
            </div>
          </Card>
          <Card className={styles.categoryItem}>
            <div className={styles.CardCenter}>
              <Link to={`/category/${categorys[8]}`}>
                <img src={dessert} alt="디저트" />
                <h2>카페·디저트</h2>
              </Link>
            </div>
          </Card>
          <Card className={styles.categoryItem}>
            <div className={styles.CardCenter}>
              <Link to={`/category/${categorys[9]}`}>
                <img src={schoolfood} alt="학식" />
                <h2>학식</h2>
              </Link>
            </div>
          </Card>
        </div>
      </ListPage>
    </>
  );
}

export default HomePage;

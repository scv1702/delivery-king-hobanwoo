import Button from "../../components/Button/Button";
import Container from "../../components/Container/Container";
import Card from "../../components/Card/Card";
import styles from "./StorePage.module.css";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import { useState, useEffect } from "react";
import ReviewItem from "../../components/ReviewItem/ReviewItem";
import ReviewStyles from "../ReviewListPage/ReviewListPage.module.css";
import classNames from "classnames";
import MenuItem from "../../components/MenuItem/MenuItem";

function StorePage() {
  const { storeId } = useParams();
  const [store, setStore] = useState(null);
  const [menuList, setMenuList] = useState([]);
  const [reviewList, setReviewList] = useState([]);

  useEffect(() => {
    if (storeId) {
      axios
        .get(`http://localhost:15010/stores/${storeId}/reviews`, {})
        .then((res) => {
          setReviewList(res.data);
        });

      axios.get(`http://localhost:15010/stores/${storeId}`).then((res) => {
        setStore(res.data);
      });

      axios.get(`http://localhost:15010/stores/${storeId}/menu`).then((res) => {
        setMenuList(res.data);
      });
    }
  }, []);

  const navigate = useNavigate();

  const handleOrderClick = () => {
    navigate("/order");
  };

  return (
    <>
      {store && (
        <>
          <div className={styles.header}>
            <Container className={styles.content}>
              <h1 className={styles.title}>{store && store.storeName}</h1>
              <Button variant="round" onClick={handleOrderClick}>
                + 주문 하기
              </Button>
              &nbsp;&nbsp;&nbsp;&nbsp;
              <Button variant="round">+ 가게 담기</Button>
              <p className={styles.summary}>{store.description}</p>
            </Container>
          </div>
          <Container className={styles.container}>
            <div className={styles.menuList}>
              {menuList &&
                menuList.map((menu, index) => {
                  return (
                    <MenuItem
                      menu={menu}
                      storeId={storeId}
                      menuId={index}
                      key={menu.menuId}
                    />
                  );
                })}
            </div>
          </Container>
          <Container className={styles.content}>
            <h1 className={styles.reviewtitle}>리뷰</h1>
          </Container>
          <Container>
            <div
              className={classNames(
                ReviewStyles.reviewList,
                ReviewStyles.bottomMargin
              )}
            >
              {reviewList.map((review) => (
                <ReviewItem key={review.reviewId} review={review} />
              ))}
            </div>
          </Container>
        </>
      )}
    </>
  );
}

export default StorePage;

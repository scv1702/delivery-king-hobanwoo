import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { deleteWishlist, getWishlist } from "../api";
import Button from "../components/Button";
import Container from "../components/Container";
import StoreItem from "../components/StoreItem";
import Warn from "../components/Warn";
import closeButton from "../assets/closeButton.svg";
import styles from "./WishlistPage.module.css";

function WishlistPage() {
  const [stores, setStores] = useState([]);

  const handleDelete = (storeSlug) => {
    deleteWishlist(storeSlug);
    const nextStores = getWishlist();
    setStores(nextStores);
  };

  useEffect(() => {
    const nextStores = getWishlist();
    setStores(nextStores);
  }, []);

  return (
    <Container className={styles.container}>
      <h1 className={styles.title}>나의 장바구니</h1>
      {stores.length === 0 ? (
        <>
          <Warn
            className={styles.emptyList}
            title="담아 놓은 가게가 없어요."
            description="관심있는 가게를 찾아보세요."
          />
          <div className={styles.link}>
            <Link to="/stores">
              <Button as="div">가게 찾아보기</Button>
            </Link>
          </div>
        </>
      ) : (
        <ul className={styles.items}>
          {stores.map((store) => (
            <li key={store.slug} className={styles.item}>
              <StoreItem store={store} />
              <img
                className={styles.delete}
                src={closeButton}
                alt="닫기"
                onClick={() => handleDelete(store.slug)}
              />
            </li>
          ))}
        </ul>
      )}
    </Container>
  );
}

export default WishlistPage;

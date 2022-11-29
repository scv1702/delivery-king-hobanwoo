import { addWishlist, getStoreBySlug } from "../api";
import Button from "../components/Button";
import Container from "../components/Container";
import Card from "../components/Card";
import StoreIcon from "../components/StoreIcon";
import getStoreColor from "../utils/getStoreColor";
import styles from "./StorePage.module.css";
import { Navigate, useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useState, useEffect } from "react";

function StorePage() {
  // 리액트 라우터 dom 에서 제공하는 커스텀 훅인 useParams()
  // useParams()가 리턴하는 객체에는 현재 경로의 파라미터들이 저장되어 있다.
  // 이 객체에 우리가 정의한 storeSlug라는 값도 저장되어 있기에 디스트럭처링으로 storeSlug값을 가져온다.
  const { storeSlug } = useParams();
  const navigate = useNavigate();
  const store = getStoreBySlug(storeSlug); // store 변수 !
  const storeColor = getStoreColor(store?.code);
  // storePage를 렌더링할 때 storeSlug값에 해당하는 store를 찾을 수 없으면
  // store의 값이 없다면 "/stores"로 가는 경로의 Navigate 컴포넌트를 리턴
  // 이 컴포넌트를 렌더링하면 리액트 라우터는 to Prop에 지정된 경로로 이동시킴
  if (!store) {
    return <Navigate to="/stores" />;
  }

  const headerStyle = {
    borderTopColor: storeColor,
  };

  // 코스 담기 버튼을 눌렀을 때 실행할 함수
  const handleAddWishlistClick = () => {
    addWishlist(store?.slug); // 위시리스트에 추가한 다음
    // useNavigate 커스텀 훅을 사용해 코드로 페이지 이동
    // 이동할 경로를 파라미터로 넘겨준다.
    // 코드를 사용해서 페이지를 이동해야 될 경우 useNavigate 커스텀 훅을 사용
    navigate("/wishlist");
  };

  // 주문 하기 버튼을 눌렀을 때 실행할 함수
  const handleOrderClick = () => {
    navigate("/order");
  };

  return (
    <>
      <div className={styles.header} style={headerStyle}>
        <Container className={styles.content}>
          <StoreIcon photoUrl={store.photoUrl} />
          <h1 className={styles.title}>{store.title}</h1>
          <Button variant="round" onClick={handleOrderClick}>
            + 주문 하기
          </Button>
          &nbsp;&nbsp;&nbsp;&nbsp;
          <Button variant="round" onClick={handleAddWishlistClick}>
            + 가게 담기
          </Button>
          <p className={styles.summary}>{store.summary}</p>
        </Container>
      </div>
      <Container className={styles.topics}>
        {store.map((store) => (
          <Card className={styles.topic} key={store.storeId}>
            <h3 className={styles.title}>{store.storeName}</h3>
            <p className={styles.summary}>{store.description}</p>
          </Card>
        ))}
      </Container>
    </>
  );
}

export default StorePage;

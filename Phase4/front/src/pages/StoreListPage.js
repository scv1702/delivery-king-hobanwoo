import { useState, useEffect } from "react";
import ListPage from "../components/ListPage";
import Warn from "../components/Warn";
import StoreItem from "../components/StoreItem";
import { getStores } from "../api";
import styles from "./StoreListPage.module.css";
import searchBarStyles from "../components/SearchBar.module.css";
import searchIcon from "../assets/search.svg";
import { useParams, useSearchParams } from "react-router-dom";
import axios from "axios";

function StoreListPage() {
  const [storeList, setStoreList] = useState([]);
  // 리액트 라우터에서는 쿼리 파라미터 값을 가져오고 싶을 때
  // useSearchParams라는 훅을 사용할 수 있다.
  // useState랑 비슷하나, 생성된 state가 객체라는 점이 다름!
  const [searchParams, setSearchParams] = useSearchParams(); // state가 객체로 생성된다. searchParams는 객체!
  const initKeyword = searchParams.get("keyword"); // get함수를 통해 안에있는 값을 가져올 수 있다.
  const category = searchParams.get("category");
  const [keyword, setKeyword] = useState(initKeyword || "");
  // getStores()라는 함수로 코스 목록 데이터를 가져와서
  // (각 요소마다 StoresItem이라는 컴포넌트로 렌더링 해주는 함수임.)
  const stores = getStores(initKeyword);

  useEffect(() => {
    if (category) {
      axios
        .get(`http://localhost:3010/stores?category=${category}`)
        .then((res) => {
          setStoreList(res.data);
        })
        .catch((err) => {
          console.error(err);
        });
    } else {
      axios
        .get("http://localhost:3010/stores")
        .then((res) => {
          setStoreList(res.data);
        })
        .catch((err) => {
          console.error(err);
        });
    }
  }, []);

  const handleKeywordChange = (e) => {
    setKeyword(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault(); // submit했을때 웹브라우저가 저절로 페이지를 이동하는 것을 방지
    // 원하는 쿼리 파라미터를 객체의 프로퍼티로 넘겨준다.
    setSearchParams(
      keyword
        ? {
            keyword, // keyword값이 없다면 빈 객체 {}가 넘겨질 수 있도록 삼항연산자 구성
          } // keyword: keyword 로 해도되고 둘이 같으니까 keyword 하나만 적어줘도 된다.
        : {}
    ); // 파라미터로 객체를 받는다.
  };

  return (
    <ListPage
      variant="store"
      title="호반우의 제휴 가게"
      description="제휴 가게를 할인된 가격으로 즐기세요."
    >
      <form className={searchBarStyles.form} onSubmit={handleSubmit}>
        <input
          name="keyword"
          value={keyword}
          onChange={handleKeywordChange}
          placeholder="검색으로 가게 찾기"
        ></input>
        <button type="submit">
          <img src={searchIcon} alt="검색" />
        </button>
      </form>

      <p className={styles.count}>총 {stores.length}개 가게</p>

      {initKeyword && stores.length === 0 ? (
        <Warn
          className={styles.emptyList}
          title="조건에 맞는 가게가 없어요."
          description="올바른 검색어가 맞는지 다시 한 번 확인해 주세요."
        />
      ) : (
        <div className={styles.storeList}>
          {storeList.map((store) => (
            <StoreItem key={store.storeId} store={store} />
          ))}
        </div>
      )}
    </ListPage>
  );
}

export default StoreListPage;

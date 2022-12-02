import { useState, useEffect } from "react";
import ListPage from "../../components/ListPage/ListPage";
import Warn from "../../components/Warn/Warn";
import StoreItem from "../../components/StoreItem/StoreItem";
import styles from "./StoreListPage.module.css";
import searchBarStyles from "../../components/SearchBar.module.css";
import searchIcon from "../../assets/search.svg";
import { useSearchParams } from "react-router-dom";
import axios from "axios";

function StoreListPage() {
  const [searchParams, setSearchParams] = useSearchParams();
  const initKeyword = searchParams.get("keyword");
  const category = searchParams.get("category");

  const [keyword, setKeyword] = useState(initKeyword || "");
  const [storeList, setStoreList] = useState([]);

  useEffect(() => {
    if (category) {
      axios
        .get(`http://localhost:15010/stores?category=${category}`)
        .then((res) => {
          setStoreList(res.data);
        })
        .catch((err) => {
          console.error(err);
        });
    } else {
      axios
        .get("http://localhost:15010/stores")
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

      <p className={storeList.count}>총 {storeList.length}개 가게</p>

      {initKeyword && storeList.length === 0 ? (
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

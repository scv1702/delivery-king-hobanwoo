import { useEffect, useState } from "react";
import ListPage from "../../components/ListPage/ListPage";
import Warn from "../../components/Warn/Warn";
import styles from "./ReviewListPage.module.css";
import searchBarStyles from "../../components/SearchBar.module.css";
import searchIcon from "../../assets/search.svg";
import { useNavigate, useSearchParams } from "react-router-dom";
import axios from "axios";
import ReviewItem from "../../components/ReviewItem/ReviewItem";

function ReviewListPage() {
  const navigate = useNavigate();
  // 리액트 라우터에서는 쿼리 파라미터 값을 가져오고 싶을 때
  // useSearchParams라는 훅을 사용할 수 있다.
  // useState랑 비슷하나, 생성된 state가 객체라는 점이 다름!
  const [reviewList, setReviewList] = useState([]);
  const [searchParams, setSearchParams] = useSearchParams(); // state가 객체로 생성된다. searchParams는 객체!
  const initKeyword = searchParams.get("keyword"); // get함수를 통해 안에있는 값을 가져올 수 있다.
  const [keyword, setKeyword] = useState(initKeyword || "");

  useEffect(() => {
    axios.get("http://localhost:15010/reviews").then((res) => {
      setReviewList(res.data);
    });
  }, []);

  const handleKeywordChange = (e) => setKeyword(e.target.value);

  const handleSubmit = (e) => {
    e.preventDefault(); // submit했을때 웹브라우저가 저절로 페이지를 이동하는 것을 방지

    // 원하는 쿼리 파라미터를 객체의 프로퍼티로 넘겨준다.
    setSearchParams(
      keyword
        ? {
            keyword, // keyword값이 없다면 빈 객체 {}가 넘겨질 수 있도록 삼항연산자 구성
          }
        : {}
    ); // 파라미터로 객체를 받는다.
  };

  return (
    <ListPage
      variant="community"
      title="리뷰"
      description="배달왕 호반우의 리뷰들을 확인해보세요."
    >
      <form className={searchBarStyles.form} onSubmit={handleSubmit}>
        <input
          name="keyword"
          value={keyword}
          placeholder="검색으로 리뷰 찾기"
          onChange={handleKeywordChange}
        />
        <button type="submit">
          <img src={searchIcon} alt="검색" />
        </button>
      </form>

      <p className={styles.count}>총 {reviewList.length}개 리뷰</p>

      {initKeyword && reviewList.length === 0 ? (
        <Warn
          className={styles.emptyList}
          title="조건에 맞는 리뷰가 없어요."
          description="올바른 검색어가 맞는지 다시 한 번 확인해 주세요."
        />
      ) : (
        <div className={styles.reviewList}>
          {reviewList &&
            reviewList.map((review) => (
              <ReviewItem key={review.reviewId} review={review} />
            ))}
        </div>
      )}
    </ListPage>
  );
}

export default ReviewListPage;

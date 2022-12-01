import { BrowserRouter, Routes, Route } from "react-router-dom";
import App from "./components/App";
import HomePage from "./pages/HomePage";
import StorePage from "./pages/StorePage";
import StoreListPage from "./pages/StoreListPage";
import WishlistPage from "./pages/WishlistPage";
import ReviewListPage from "./pages/ReviewListPage";
import ReviewPage from "./pages/ReviewPage";
import NotFoundPage from "./pages/NotFoundPage";
import LoginPage from "./pages/LoginPage";
import OrderPage from "./pages/OrderPage";
import SignupPage from "./pages/SignupPage";
import MyPage from "./pages/MyPage";
import CreateReviewPage from "./pages/CreateReviewPage";
function Main() {
  // 아래 Route path=":storeSlug(경로 파라미터)"를 통해 경로에서 사용하는 동적인 값인 storeSlug라는 변수를 파라미터로써 useParams()를 이용해 동적인 경로로 사용하고 있다.
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />}>
          <Route index element={<HomePage />} />
          <Route path="stores">
            <Route index element={<StoreListPage />} />
            <Route path=":storeId" element={<StorePage />} />
          </Route>
          <Route path="reviews">
            <Route index element={<ReviewListPage />} />
            <Route path=":reviewId" element={<ReviewPage />} />
          </Route>
          <Route path="wishlist" element={<WishlistPage />} />
          <Route path="order" element={<OrderPage />} />
          <Route path="login" element={<LoginPage />} />
          <Route path="signup" element={<SignupPage />} />
          <Route path="mypage" element={<MyPage />} />
          <Route path="createreview" element={<CreateReviewPage />} />
          <Route path="*" element={<NotFoundPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default Main;

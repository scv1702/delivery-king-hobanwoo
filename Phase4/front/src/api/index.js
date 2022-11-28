import { stores, reviews } from "./mock.json";

function filterByKeyword(items, keyword) {
  const lowered = keyword.toLowerCase();
  var obj1 = items.filter((data) =>
    data.language.toLowerCase().includes(keyword)
  );
  var obj2 = items.filter((data) => data.title.toLowerCase().includes(lowered));
  Object.assign(obj1, obj2);
  return obj1;
}

export function getStores(keyword) {
  if (!keyword) return stores;
  return filterByKeyword(stores, keyword);
}

export function getStoreBySlug(storeSlug) {
  return stores.find((store) => store.slug === storeSlug);
}

export function getStoreByCategory(storeCatogory) {
  return stores.find((store) => store.category === storeCatogory);
}

export function getReviews(keyword) {
  if (!keyword) return reviews;
  return filterByKeyword(reviews, keyword);
}

export function getReviewById(reviewId) {
  return reviews.find((review) => review.id === reviewId);
}

const WISHLIST_KEY = "codethat-wishlist";
const wishlist = JSON.parse(localStorage.getItem(WISHLIST_KEY) || "{}");

export function addWishlist(storeSlug) {
  wishlist[storeSlug] = true;
  localStorage.setItem(WISHLIST_KEY, JSON.stringify(wishlist));
}

export function deleteWishlist(storeSlug) {
  delete wishlist[storeSlug];
  localStorage.setItem(WISHLIST_KEY, JSON.stringify(wishlist));
}

export function getWishlist() {
  return stores.filter((store) => wishlist[store.slug]);
}

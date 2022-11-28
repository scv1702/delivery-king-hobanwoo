import Card from "./Card";
import StoreIcon from "./StoreIcon";
import Tags from "./Tags";
import getStoreColor from "../utils/getStoreColor";
import styles from "./StoreItem.module.css";
import { Link } from "react-router-dom";

// const DIFFICULTY = [
//   "중식",
//   "분식",
//   "양식",
//   "돈까스,회,일식",
//   "족발,보쌈",
//   "아시안",
//   "양식",
//   "찜,탕,찌개",
//   "패스트푸드",
//   "카페,디저트",
//   "학식",
// ];

function StoreItem({ store }) {
  const showSummary = store.summary && store.title !== store.summary;
  const storeColor = getStoreColor(store.code);
  const thumbStyle = {
    borderColor: storeColor,
  };

  return (
    <Card className={styles.storeItem}>
      <div className={styles.thumb} style={thumbStyle}>
        <StoreIcon photoUrl={store.photoUrl} />
      </div>
      <div className={styles.content}>
        <h2 className={styles.title}>
          <Link to={`/stores/${store.slug}`}>{store.title}</Link>
        </h2>
        <p className={styles.description}>{showSummary && store.summary}</p>
        <div>
          <Tags values={[store.language]} />
        </div>
      </div>
    </Card>
  );
}

export default StoreItem;
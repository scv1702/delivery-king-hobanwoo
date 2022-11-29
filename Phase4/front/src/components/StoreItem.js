import Card from "./Card";
import StoreIcon from "./StoreIcon";
import Tags from "./Tags";
import getStoreColor from "../utils/getStoreColor";
import styles from "./StoreItem.module.css";
import { Link } from "react-router-dom";

function StoreItem({ store }) {
  const storeColor = getStoreColor(store.code);
  const thumbStyle = {
    borderColor: storeColor,
  };

  return (
    <Card className={styles.storeItem}>
      <div className={styles.thumb} style={thumbStyle}>
        <StoreIcon photoUrl={`imgs/${store.storeId}-1.jpg`} />
      </div>
      <div className={styles.content}>
        <h2 className={styles.title}>
          <Link to={`/stores/${store.slug}`}>{store.storeName}</Link>
        </h2>
        <p className={styles.description}>{store.description}</p>
        <div>
          <Tags values={[store.foodCategory]} />
        </div>
      </div>
    </Card>
  );
}

export default StoreItem;

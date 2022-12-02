import Card from "../Card/Card";
import StoreIcon from "../StoreIcon/StoreIcon";
import Tags from "../Tags/Tags";
import styles from "./StoreItem.module.css";
import { Link } from "react-router-dom";

function StoreItem({ store }) {
  return (
    <Card className={styles.storeItem}>
      <div className={styles.thumb}>
        <StoreIcon photoUrl={`imgs/${store.storeId}-1.jpg`} />
      </div>
      <div className={styles.content}>
        <h2 className={styles.title}>
          <Link to={`/stores/${store.storeId}`}>{store.storeName}</Link>
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

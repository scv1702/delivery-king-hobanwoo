import Card from "../Card/Card";
import StoreIcon from "../StoreIcon/StoreIcon";
import styles from "./MenuItem.module.css";
import { Link } from "react-router-dom";

function MenuItem({ menu, storeId, menuId }) {
  return (
    <Card className={styles.menuItem}>
      <div className={styles.thumb}>
        <StoreIcon photoUrl={`../imgs/${storeId}-${menuId + 1}.jpg`} />
      </div>
      <div className={styles.content}>
        <h2 className={styles.title}>{menu.menuName}</h2>
        <p className={styles.description}>{menu.description}</p>
        <div>가격: {menu.price}</div>
      </div>
    </Card>
  );
}

export default MenuItem;

import { Button } from "react-bootstrap";
import Card from "../Card/Card";
import StoreIcon from "../StoreIcon/StoreIcon";
import styles from "./MenuItem.module.css";

function MenuItem({ menu, storeId, menuId }) {
  function test() {
    alert("hi");
  }
  return (
    <Card className={styles.menuItem}>
      <div className={styles.thumb}>
        <StoreIcon photoUrl={`../imgs/${storeId}-${menuId + 1}.jpg`} />
      </div>
      <div className={styles.content}>
        <h2 className={styles.title}>{menu.menuName}</h2>
        <p className={styles.description}>{menu.description}</p>
        <div>가격: {menu.price}원</div>
      </div>
    </Card>
  );
}

export default MenuItem;

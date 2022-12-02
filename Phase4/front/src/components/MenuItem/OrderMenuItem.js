import { Button, ButtonGroup } from "react-bootstrap";
import Card from "../Card/Card";
import StoreIcon from "../StoreIcon/StoreIcon";
import styles from "./MenuItem.module.css";
import { useState } from "react";

function OrderMenuItem({ menu, storeId, menuId, order }) {
  const [count, setCount] = useState(1);

  function incrementClick() {
    setCount(count + 1);
  }

  function decrementClick() {
    if (count > 1) {
      setCount(count - 1);
    }
  }

  return (
    <Card className={styles.menuItem}>
      <div className={styles.thumb}>
        <StoreIcon photoUrl={`../imgs/${storeId}-${menuId + 1}.jpg`} />
      </div>
      <div className={styles.content}>
        <h2 className={styles.title}>{menu.menuName}</h2>
        <p className={styles.description}>{menu.description}</p>
        <div>가격: {menu.price * count}원</div>
      </div>
      <div className={styles.count}>
        <div className={styles.button}>
          <ButtonGroup className="mb-2">
            <Button
              onClick={incrementClick}
              as="input"
              type="button"
              value="추가"
            />
            <Button
              as="input"
              type="button"
              value={count}
              disabled
              variant="outline-primary"
            />{" "}
            <Button
              onClick={decrementClick}
              as="input"
              type="button"
              value="삭제"
            />
          </ButtonGroup>
        </div>
      </div>
    </Card>
  );
}

export default OrderMenuItem;

import { Button, ButtonGroup } from "react-bootstrap";
import Card from "../Card/Card";
import StoreIcon from "../StoreIcon/StoreIcon";
import styles from "./MenuItem.module.css";
import { useState, useEffect } from "react";

function OrderMenuItem({ menu, storeId, menuId, handleTotalMenuCount }) {
  const [count, setCount] = useState(0);
  const [addMinus, setAddMinus] = useState(null);

  function incrementClick() {
    setCount(count + 1);
    setAddMinus(0); // 0이면 plus
  }

  function decrementClick() {
    if (count > 0) {
      setCount(count - 1);
      setAddMinus(1); // 1이면 minus
    }
  }

  useEffect(() => {
    handleTotalMenuCount({ menu, count, addMinus });
  }, [count]);

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
      <div className={styles.count}>
        <div className={styles.button}>
          <ButtonGroup className="mb-2">
            <Button
              onClick={incrementClick}
              as="input"
              type="button"
              value="추가"
              variant="secondary"
            />
            <Button
              as="input"
              type="button"
              value={count}
              disabled
              variant="outline-secondary"
            />{" "}
            <Button
              onClick={decrementClick}
              as="input"
              type="button"
              value="삭제"
              variant="secondary"
            />
          </ButtonGroup>
        </div>
      </div>
    </Card>
  );
}

export default OrderMenuItem;

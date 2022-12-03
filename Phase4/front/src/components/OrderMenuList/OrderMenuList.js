import styles from "../MenuList//MenuList.module.css";
import Container from "../Container/Container";
import OrderMenuItem from "../MenuItem/OrderMenuItem";

function OrderMenuList({ menuList, storeId }) {
  const [orderMenuList, setOrderMenuList] = useState([]);

  menuList.map((menu, index) => {
    const newOrderMenuList = [...orderMenuList];
    newOrderMenuList.push({ quantity: 0, menuId: menu.menuId });
    setOrderMenuList(newOrderMenuList);
  });

  return (
    <Container className={styles.container}>
      <div className={styles.menuList}>
        {menuList &&
          menuList.map((menu, index) => {
            return (
              <OrderMenuItem
                menu={menu}
                storeId={storeId}
                menuId={index}
                key={menu.menuId}
              />
            );
          })}
      </div>
    </Container>
  );
}

export default OrderMenuList;

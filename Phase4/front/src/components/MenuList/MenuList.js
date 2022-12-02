import styles from "./MenuList.module.css";
import Container from "../Container/Container";
import MenuItem from "../MenuItem/MenuItem";
import OrderMenuItem from "../MenuItem/OrderMenuItem";

function MenuList({ menuList, storeId, order }) {
  return (
    <Container className={styles.container}>
      <div className={styles.menuList}>
        {menuList &&
          menuList.map((menu, index) => {
            if (order) {
              return (
                <OrderMenuItem
                  menu={menu}
                  storeId={storeId}
                  menuId={index}
                  key={menu.menuId}
                />
              );
            } else {
              return (
                <MenuItem
                  menu={menu}
                  storeId={storeId}
                  menuId={index}
                  key={menu.menuId}
                />
              );
            }
          })}
      </div>
    </Container>
  );
}

export default MenuList;

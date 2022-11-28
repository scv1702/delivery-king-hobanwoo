import database from "../database";
import { Menu } from "../@types/Menu";

type MenuDto = {
  MENU_ID: number;
  STORE_ID: number;
  MNAME: string;
  DESCRIPTION: string;
  IMAGE: string;
  PRICE: number;
};

class MenuModel {
  getMenuByMenuName = async (menuName: string): Promise<Menu | undefined> => {
    const sql = `SELECT * FROM MENU WHERE MNAME = '${menuName}'`;
    const conn = await database.getConnection();
    const result = (await conn.execute<MenuDto>(sql)).rows;
    if (result) {
      const { MENU_ID, STORE_ID, MNAME, DESCRIPTION, IMAGE, PRICE } = result[0];
      return {
        menuId: MENU_ID,
        storeId: STORE_ID,
        menuName: MNAME,
        description: DESCRIPTION,
        image: IMAGE,
        price: PRICE,
      };
    }
    return undefined;
  };
  getMenuListByStoreId = async (
    storeId: number
  ): Promise<Array<Menu> | undefined> => {
    const sql = `SELECT * FROM MENU WHERE STORE_ID = ${storeId}`;
    const conn = await database.getConnection();
    const result = (await conn.execute<MenuDto>(sql)).rows;
    if (result) {
      return result.map((menu: MenuDto) => {
        return {
          menuId: menu.MENU_ID,
          storeId: menu.STORE_ID,
          menuName: menu.MNAME,
          description: menu.DESCRIPTION,
          image: menu.IMAGE,
          price: menu.PRICE,
        };
      });
    }
    return undefined;
  };
}

export default new MenuModel();

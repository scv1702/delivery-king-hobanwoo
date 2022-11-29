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
  getMenuList = async (sql: string): Promise<Menu[] | undefined> => {
    const conn = await database.getConnection();
    const result = (await conn.execute<MenuDto>(sql)).rows;
    return result?.map((menu: MenuDto) => {
      return {
        menuId: menu.MENU_ID,
        storeId: menu.STORE_ID,
        menuName: menu.MNAME,
        description: menu.DESCRIPTION,
        image: menu.IMAGE,
        price: menu.PRICE,
      };
    });
  };
  getMenuByMenuName = async (menuName: string): Promise<Menu | undefined> => {
    const sql = `SELECT * FROM MENU WHERE MNAME = '${menuName}'`;
    const result = await this.getMenuList(sql);
    return result?.[0];
  };
  getMenuListByStoreId = async (
    storeId: number
  ): Promise<Menu[] | undefined> => {
    const sql = `SELECT * FROM MENU WHERE STORE_ID = ${storeId}`;
    return await this.getMenuList(sql);
  };
}

export default new MenuModel();

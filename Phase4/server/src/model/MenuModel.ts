import database from "../database";

type MenuDto = {
  MENU_ID: number;
  STORE_ID: number;
  MNAME: string;
  DESCRIPTION: string;
  IMAGE: string;
  PRICE: number;
};

class MenuModel {
  getMenuByMenuName = async (menuName: string) => {
    const sql = `SELECT * FROM MENU WHERE MNAME = '${menuName}'`;
    const conn = await database.getConnection();
    const result = (await conn.execute<MenuDto>(sql))?.rows?.[0];
    if (result) {
      return {
        menuId: result.MENU_ID,
        storeId: result.STORE_ID,
        menuName: result.MNAME,
        description: result.DESCRIPTION,
        image: result.IMAGE,
        price: result.PRICE,
      };
    }
    return undefined;
  };
  getMenuListByStoreId = async (storeId: number) => {
    const sql = `SELECT * FROM MENU WHERE STORE_ID = ${storeId}`;
    const conn = await database.getConnection();
    const result = (await conn.execute<MenuDto>(sql))?.rows;
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
    return [];
  };
}

export default new MenuModel();

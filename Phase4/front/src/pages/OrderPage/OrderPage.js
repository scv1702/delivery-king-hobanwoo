import { useState, useEffect } from "react";
import { Link, useSearchParams } from "react-router-dom";
import Button from "../../components/Button/Button";
import Container from "../../components/Container/Container";
import styles from "./OrderPage.module.css";
import axios from "axios";
import MenuList from "../../components/MenuList/MenuList";
import { useNavigate } from "react-router-dom";

function OrderPage() {
  const [searchParams, setSearchParams] = useSearchParams();
  const navigate = useNavigate();
  const storeId = searchParams.get("storeId");
  const [menuList, setMenuList] = useState([]);
  const [store, setStore] = useState(null);
  const [totalPrice, setTotalPrice] = useState(0);
  const [menuData, setMenuData] = useState([]);

  useEffect(() => {
    axios.get(`http://localhost:15010/stores/${storeId}/menu`).then((res) => {
      setMenuList(res.data);
    });
    axios.get(`http://localhost:15010/stores/${storeId}`).then((res) => {
      console.log(res.data);
      setStore(res.data);
    });
  }, []);

  // 메뉴들이 잘 선택되어 들어오는지 확인
  useEffect(() => {
    console.log("Menu useEffect", menuData);
  }, [menuData]);

  function orderHandler() {
    const payment = document.getElementById("payment").value;
    const orderMenuList = menuData.map((orderMenu) => {
      return {
        quantity: orderMenu.count,
        menuName: orderMenu.menu.menuName,
      };
    });
    const filteredOrderMenuList = orderMenuList.filter(
      (orderMenu) => orderMenu.quantity > 0
    );
    axios
      .post(
        `http://localhost:15010/orders`,
        {
          storeId,
          orderMenuList: filteredOrderMenuList,
          payment,
        },
        { withCredentials: true }
      )
      .then((res) => {
        alert(res.data.message);
        navigate("/mypage");
      })
      .catch((err) => {
        alert(err.response.data.message);
      });
  }

  const handleTotalMenuCount = (pass) => {
    console.log("pass data:", pass);
    if (pass.addMinus == 0) {
      setTotalPrice(totalPrice + pass.menu.price);
    } else if (pass.addMinus == 1) {
      setTotalPrice(totalPrice - pass.menu.price);
    }
    const newData = [...menuData];
    const exist = newData.find((item) => pass.menu.menuId === item.menu.menuId);

    //같은 데이터가 존재하지 않는다면 넣어주고
    if (!exist) {
      newData.push(pass);
    }
    //같은 데이터가 존재한다면 count값만 갱신
    else {
      newData.forEach((item, index) => {
        if (item.menu.menuId === pass.menu.menuId) {
          newData[index].count = pass.count;
          newData[index].addMinus = pass.addMinus;
        }
      });
    }
    setMenuData(newData);
  };

  return (
    <>
      <div className={styles.header}>
        <Container className={styles.content}>
          <h1 className={styles.title}>주문하기</h1>
        </Container>
      </div>
      <Container className={styles.container}>
        <MenuList
          menuList={menuList}
          storeId={storeId}
          order={true}
          handleTotalMenuCount={handleTotalMenuCount}
        />
        <h3 className={styles.subtitle}>결제 방식</h3>
        <select id="payment" className={styles.inputbox}>
          <option value="카드">카드</option>
          <option value="현금">현금</option>
        </select>
        <h3 className={styles.subtitle}>총 주문 금액</h3>
        <p style={{ marginLeft: "35%" }}>{totalPrice}원</p>
        <h3 className={styles.subtitle}>배달팁</h3>
        <p style={{ marginLeft: "35%" }}>{store && store.deliveryFee}원</p>
        <div className={styles.link}>
          <Button
            onClick={() => {
              orderHandler();
            }}
            as="div"
          >
            주문하기
          </Button>
        </div>
      </Container>
    </>
  );
}

export default OrderPage;

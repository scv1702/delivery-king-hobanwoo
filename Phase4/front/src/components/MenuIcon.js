import classNames from "classnames";
import styles from "../components/StoreIcon/StoreIcon.module.css";

function MenuIcon({ className, photoUrl = "default" }) {
  return (
    <img
      align="left"
      className={classNames(styles.storeIcon, className)}
      src={photoUrl}
      alt={photoUrl}
    />
  );
}

export default MenuIcon;

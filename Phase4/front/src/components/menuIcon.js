import classNames from "classnames";
import styles from "./StoreIcon.module.css";

function menuIcon({ className, photoUrl = "default" }) {
  return (
    <img
      align="left"
      className={classNames(styles.storeIcon, className)}
      src={photoUrl}
      alt={photoUrl}
    />
  );
}

export default menuIcon;

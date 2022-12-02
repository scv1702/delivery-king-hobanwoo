import classNames from "classnames";
import styles from "./StoreIcon.module.css";

function StoreIcon({ className, photoUrl = "default" }) {
  return (
    <img
      className={classNames(styles.storeIcon, className)}
      src={photoUrl}
      alt={photoUrl}
    />
  );
}

export default StoreIcon;

import classNames from "classnames";
import warnIcon from "../../assets/warn.svg";
import styles from "./Warn.module.css";

function Warn({
  className,
  style,
  variant = "",
  title = "",
  description = "",
}) {
  return (
    <div
      style={style}
      className={classNames(styles.warn, styles[variant], className)}
    >
      <img className={styles.icon} src={warnIcon} alt="경고" />
      <h2 className={styles.title}>{title}</h2>
      <p className={styles.description}>{description}</p>
    </div>
  );
}

export default Warn;

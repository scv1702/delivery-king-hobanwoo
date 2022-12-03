import styles from "./Tags.module.css";

function Tags({ values = [] }) {
  function tagReturn(value) {
    return (
      <li className={styles.emptyline} key={value}>
        {value}
      </li>
    );
  }
  return (
    <div className={styles.float}>
      <ul className={styles.tags}>{values.map((value) => tagReturn(value))}</ul>
    </div>
  );
}

export default Tags;

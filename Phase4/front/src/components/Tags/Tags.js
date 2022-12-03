import styles from "./Tags.module.css";

var firstElement = false;

function Tags({ values = [] }) {
  function tagReturn(value) {
    if (!firstElement) {
      firstElement = true;
      return <li key={value}>{value}</li>;
    } else {
      return (
        <li className={styles.emptyline} key={value}>
          {value}
        </li>
      );
    }
  }
  return (
    <div className={styles.float}>
      <ul className={styles.tags}>{values.map((value) => tagReturn(value))}</ul>
    </div>
  );
}

export default Tags;

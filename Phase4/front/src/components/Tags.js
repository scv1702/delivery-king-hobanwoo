import styles from './Tags.module.css';

function Tags({ values = [] }) {
  return (
    <ul className={styles.tags}>
      {values.map((value) => (
        <li key={value}>{value}</li>
      ))}
    </ul>
  );
}

export default Tags;

import styles from './Lined.module.css';

function Lined({ children }) {
  return <span className={styles.lined}>{children}</span>;
}

export default Lined;

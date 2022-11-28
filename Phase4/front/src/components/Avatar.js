import styles from './Avatar.module.css';

function Avatar({ photo, name }) {
  return <img className={styles.avatar} src={photo} alt={name} title={name} />;
}

export default Avatar;

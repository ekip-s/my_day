import styles from "./Header.module.css"

const Header = () => {
    return <header className={styles.header}>
        <div className={styles.wrapper}>
            <div className={styles.logo}>
                <p>My Day</p>
            </div>
        </div>
    </header>
}

export default Header;
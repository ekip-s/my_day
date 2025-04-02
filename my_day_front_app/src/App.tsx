import { BrowserRouter as Router, Navigate, Route, Routes } from 'react-router';
import styles from './App.module.css'
import {AuthProvider} from "./keycloak/AuthContext.tsx";
import ProtectedRoute from "./keycloak/ProtectedRoute.tsx";
import Header from "./organisms/header/Header.tsx";
import Footer from "./organisms/footer/Footer.tsx";

function App() {

  return (
    <AuthProvider>
      <Router>
        <div className={styles.wrapper}>
          <Header />
          <main className={styles.main}>
            <Routes>
              <Route element={<ProtectedRoute />}>
                <Route path="/" element={<Navigate to="/home" />} />
              </Route>
            </Routes>
          </main>
          <Footer />
        </div>
      </Router>
    </AuthProvider>
  )
}

export default App

// страница графика;
// страниа управления событиями;

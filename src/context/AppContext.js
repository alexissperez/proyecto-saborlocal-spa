import React, { createContext, useState, useEffect } from 'react';
import { getToken, getUser, isAuthenticated } from '../services/authService';

export const AppContext = createContext();

export const AppProvider = ({ children }) => {
  const [carrito, setCarrito] = useState([]);
  const [auth, setAuth] = useState({
    loading: true,
    isAuthenticated: false,
    user: null
  });

  // Verificar sesión al iniciar la app
  useEffect(() => {
    const token = getToken();
    const user = getUser();
    setAuth({
      loading: false,
      isAuthenticated: isAuthenticated(),
      user: user
    });
  }, []);

  const login = (user, token) => {
    localStorage.setItem('token', token);
    localStorage.setItem('user', JSON.stringify(user));
    setAuth({ loading: false, isAuthenticated: true, user });
  };

  const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    setAuth({ loading: false, isAuthenticated: false, user: null });
  };

  const agregarAlCarrito = (producto) => {
    setCarrito(prev => [...prev, producto]);
  };

  const vaciarCarrito = () => {
    setCarrito([]);
  };

  return (
    <AppContext.Provider
      value={{
        carrito,
        agregarAlCarrito,
        vaciarCarrito,
        auth,
        login,
        logout
      }}
    >
      {children}
    </AppContext.Provider>
  );
};

export default AppContext;
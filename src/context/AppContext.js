import React, { createContext, useState } from 'react';

export const AppContext = createContext();

export const AppProvider = ({ children }) => {
  const [carrito, setCarrito] = useState([]);

  const agregarAlCarrito = (producto) => {
    setCarrito(prev => [...prev, producto]);
  };

  const vaciarCarrito = () => {
    setCarrito([]);
  };

  return (
    <AppContext.Provider value={{ carrito, agregarAlCarrito, vaciarCarrito }}>
      {children}
    </AppContext.Provider>
  );
};

// opcional: export por defecto para compatibilidad con imports default
export default AppContext;

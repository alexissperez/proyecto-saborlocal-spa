import React, { createContext, useState } from 'react';

export const AppContext = createContext();

export const AppProvider = ({ children }) => {
  const [carrito, setCarrito] = useState([]);

  const agregarAlCarrito = (producto) => {
    setCarrito([...carrito, producto]);
  };

  return (
    <AppContext.Provider value={{ carrito, agregarAlCarrito }}>
      {children}
    </AppContext.Provider>
  );
};

import React, { createContext, useState } from 'react';

export const AppContext = createContext();

export const AppProvider = ({ children }) => {
  // El estado carrito es un array de productos
  const [carrito, setCarrito] = useState([]);

  // Función para agregar productos
  const agregarAlCarrito = (producto) => {
    setCarrito([...carrito, producto]);
  };

  // Nueva función para vaciar el carrito
  const vaciarCarrito = () => {
    setCarrito([]);
  };

  return (
    <AppContext.Provider value={{ carrito, agregarAlCarrito, vaciarCarrito }}>
      {children}
    </AppContext.Provider>
  );
};


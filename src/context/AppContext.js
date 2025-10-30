import React, { createContext, useState, useEffect } from 'react';

export const AppContext = createContext();

export const AppProvider = ({ children }) => {
  const [carrito, setCarrito] = useState(() => {
    try {
      const saved = localStorage.getItem('carrito');
      return saved ? JSON.parse(saved) : [];
    } catch {
      return [];
    }
  });

  useEffect(() => {
    try {
      localStorage.setItem('carrito', JSON.stringify(carrito));
    } catch {}
  }, [carrito]);

  const agregarAlCarrito = (producto) => {
    setCarrito(prev => [...prev, producto]);
  };

  const quitarDelCarrito = (id) => {
    setCarrito(prev => prev.filter(p => p.id !== id));
  };

  const limpiarCarrito = () => setCarrito([]);

  const contador = carrito.length;

  return (
    <AppContext.Provider value={{ carrito, contador, agregarAlCarrito, quitarDelCarrito, limpiarCarrito }}>
      {children}
    </AppContext.Provider>
  );
};
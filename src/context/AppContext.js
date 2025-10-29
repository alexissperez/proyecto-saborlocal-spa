import React, { createContext, useState } from 'react';

// Creamos el contexto
export const AppContext = createContext();

// Proveedor del contexto, que envuelve la aplicación
export const AppProvider = ({ children }) => {
  // Estado global: contador del carrito (puedes agregar más estados)
  const [contador, setContador] = useState(0);

  // Función para incrementar el contador (agregar al carrito)
  const incrementar = () => setContador(contador + 1);

  // Retornamos el provider que da acceso al estado/funciones globales
  return (
    <AppContext.Provider value={{ contador, incrementar }}>
      {children}
    </AppContext.Provider>
  );
};

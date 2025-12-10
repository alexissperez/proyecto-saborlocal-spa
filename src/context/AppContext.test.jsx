import React from 'react';
import { renderHook, act } from '@testing-library/react';
import { AppProvider, AppContext } from './AppContext';

const wrapper = ({ children }) => <AppProvider>{children}</AppProvider>;

test('agregar y eliminar productos del carrito', () => {
  const { result } = renderHook(() => React.useContext(AppContext), { wrapper });

  act(() => {
    result.current.agregarAlCarrito({ id: 1, nombre: 'Queso', precio: 2500 });
    result.current.agregarAlCarrito({ id: 2, nombre: 'Pan', precio: 1000 });
  });

  expect(result.current.carrito).toHaveLength(2);

  act(() => {
    result.current.eliminarDelCarrito(0);
  });

  expect(result.current.carrito).toHaveLength(1);
  expect(result.current.carrito[0].nombre).toBe('Pan');
});

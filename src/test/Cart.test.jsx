import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import Cart from '../components/products/Cart';
import { AppContext } from '../context/AppContext';

test('muestra título y total vacío si carrito está vacío', () => {
  render(
    <AppContext.Provider value={{ carrito: [] }}>
      <Cart />
    </AppContext.Provider>
  );
  expect(screen.getByText(/Productos en el carrito/i)).toBeInTheDocument();
  expect(screen.getByText(/Total a pagar/i)).toBeInTheDocument();
});

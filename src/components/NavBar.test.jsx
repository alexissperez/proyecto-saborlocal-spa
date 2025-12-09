import React from 'react';
import { render, screen } from '@testing-library/react';
import NavBar from '../components/NavBar';
import { AppContext } from '../context/AppContext';
import { BrowserRouter } from 'react-router-dom';

test('NavBar muestra nombre y enlaces', () => {
  render(
    <BrowserRouter>
      <AppContext.Provider value={{ carrito: [] }}>
        <NavBar />
      </AppContext.Provider>
    </BrowserRouter>
  );
  expect(screen.getByText(/SaborLocal/i)).toBeInTheDocument();
  expect(screen.getByText(/Catálogo/i)).toBeInTheDocument();
  expect(screen.getByText(/Contacto/i)).toBeInTheDocument();
});

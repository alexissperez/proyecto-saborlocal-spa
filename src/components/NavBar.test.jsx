import React from 'react';
import { render, screen } from '@testing-library/react';
import NavBar from '../components/NavBar';
import { AppContext } from '../context/AppContext';
import { BrowserRouter } from 'react-router-dom';

test('NavBar muestra nombre y enlaces', () => {
  const mockContext = {
    carrito: [],
    auth: { isAuthenticated: false, loading: false, user: null },
    login: jest.fn(),
    logout: jest.fn(),
    agregarAlCarrito: jest.fn(),
    eliminarDelCarrito: jest.fn(),
    vaciarCarrito: jest.fn(),
  };

  render(
    <BrowserRouter>
      <AppContext.Provider value={mockContext}>
        <NavBar />
      </AppContext.Provider>
    </BrowserRouter>
  );

  expect(screen.getByText(/SaborLocal/i)).toBeInTheDocument();
  expect(screen.getByText(/Inicio/i)).toBeInTheDocument();
  expect(screen.getByText(/Productos/i)).toBeInTheDocument();
  expect(screen.getByText(/Contacto/i)).toBeInTheDocument();
});

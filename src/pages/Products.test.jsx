import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import Products from './Products';
import { AppProvider } from '../context/AppContext';

// Función para renderizar el componente dentro del contexto
const renderWithProvider = () =>
  render(
    <AppProvider>
      <Products />
    </AppProvider>
  );

// Testea render de catálogo
test('renders catálogo de productos', () => {
  renderWithProvider();
  // Busca el texto "Catálogo de Productos"
  expect(screen.getByText(/Catálogo de Productos/i)).toBeInTheDocument();
});

// Testea render de los filtros por categoría
test('muestra botones de filtro', () => {
  renderWithProvider();
  // Busca el filtro "Todos"
  expect(screen.getByRole('button', { name: /Todos/i })).toBeInTheDocument();
  // Cambia la categoría para verificar que los botones existen
  expect(screen.getByRole('button', { name: /Lácteos/i })).toBeInTheDocument();
});

// Testea interacción en el botón agregar al carrito
test('agrega producto al carrito con alert', () => {
  // Simula window.alert para chequear que se llame
  window.alert = jest.fn();
  renderWithProvider();
  // Busca el botón "Agregar al carrito"
  const agregarBtn = screen.getByRole('button', { name: /Agregar al carrito/i });
  // Evento click en el botón
  fireEvent.click(agregarBtn);
  // Verifica que alert haya sido llamado
  expect(window.alert).toHaveBeenCalledTimes(1);
  expect(window.alert).toHaveBeenCalledWith(expect.stringContaining('Agregaste'));
});

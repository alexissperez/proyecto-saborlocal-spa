import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import Products from '../pages/Products';
import { AppProvider } from '../context/AppContext';

const renderWithProvider = () =>
  render(
    <AppProvider>
      <Products />
    </AppProvider>
  );

test('muestra detalles de un producto específico', () => {
  renderWithProvider();
  expect(screen.getByText(/Queso de campo/i)).toBeInTheDocument();
  expect(screen.getByText(/\$[1-9][0-9]*/)).toBeInTheDocument();
});

test('filtra correctamente por categoría', () => {
  renderWithProvider();
  fireEvent.click(screen.getByRole('button', { name: /Lácteos/i }));
  expect(screen.getByText(/Queso/i)).toBeInTheDocument();
  expect(screen.queryByText(/Pan integral/i)).not.toBeInTheDocument();
});

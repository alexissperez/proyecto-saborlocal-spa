import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import Contact from '../pages/Contact';

test('formulario muestra errores y éxito', () => {
  render(<Contact />);
  const enviarBtn = screen.getByRole('button', { name: /Enviar/i });

  // Intenta enviar el formulario vacío
  fireEvent.click(enviarBtn);

  // Valida los textos de error
  expect(screen.getByText(/obligatorio/i)).toBeInTheDocument();
  expect(screen.getByText(/correo/i)).toBeInTheDocument();

  // Si no encuentra los label, prueba con el placeholder o el role
  fireEvent.change(screen.getByLabelText(/Nombre/i), { target: { value: 'Juan' } });
  fireEvent.change(screen.getByLabelText(/Correo/i), { target: { value: 'juan@mail.com' } });
  fireEvent.change(screen.getByLabelText(/Mensaje/i), { target: { value: 'Hola' } });

  fireEvent.click(enviarBtn);

  // Verifica mensaje de éxito, ajustando el texto si es distinto
  expect(screen.getByText(/éxito/i)).toBeInTheDocument();
});

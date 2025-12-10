import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import Contact from './Contact';

test('formulario muestra errores y luego mensaje de éxito', () => {
  render(<Contact />);

  const enviarBtn = screen.getByRole('button', { name: /enviar mensaje/i });

  // Intenta enviar el formulario vacío
  fireEvent.click(enviarBtn);

  // Valida los textos de error
  expect(screen.getByText(/El nombre es obligatorio/i)).toBeInTheDocument();
  expect(screen.getByText(/Correo inválido/i)).toBeInTheDocument();
  expect(screen.getByText(/El mensaje es obligatorio/i)).toBeInTheDocument();

  // Completa los campos
  fireEvent.change(screen.getByLabelText(/Nombre/i), {
    target: { value: 'Juan' },
  });
  fireEvent.change(screen.getByLabelText(/Correo electrónico/i), {
    target: { value: 'juan@mail.com' },
  });
  fireEvent.change(screen.getByLabelText(/Mensaje/i), {
    target: { value: 'Hola' },
  });

  // Envía de nuevo
  fireEvent.click(enviarBtn);

  // Verifica mensaje de éxito
  expect(
    screen.getByText(/mensaje enviado con éxito/i)
  ).toBeInTheDocument();
});

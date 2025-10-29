import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import Contact from './Contact';

// Testea el formulario de contacto, mostrando validaciones y mensaje de éxito
test('formulario muestra errores y éxito', () => {
  render(<Contact />);
  const enviarBtn = screen.getByRole('button', { name: /Enviar/i });
  // Intenta enviar el formulario vacío
  fireEvent.click(enviarBtn);

  // Chequea todos los errores de validación
  expect(screen.getByText(/El nombre es obligatorio/i)).toBeInTheDocument();
  expect(screen.getByText(/Correo inválido/i)).toBeInTheDocument();
  expect(screen.getByText(/El mensaje es obligatorio/i)).toBeInTheDocument();

  // Rellena el formulario correctamente
  fireEvent.change(screen.getByLabelText(/Nombre/i), { target: { value: 'Juan' } });
  fireEvent.change(screen.getByLabelText(/Correo electrónico/i), { target: { value: 'juan@mail.com' } });
  fireEvent.change(screen.getByLabelText(/Mensaje/i), { target: { value: 'Hola' } });

  // Envia otra vez
  fireEvent.click(enviarBtn);

  // Verifica mensaje de éxito
  expect(screen.getByText(/¡Mensaje enviado con éxito/i)).toBeInTheDocument();
});

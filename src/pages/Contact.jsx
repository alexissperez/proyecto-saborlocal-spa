import React, { useState } from 'react';
import { Form, Button, Alert } from 'react-bootstrap';

// Página de contacto con validaciones y feedback usuario
const Contact = () => {
  // Estado para los campos del formulario
  const [form, setForm] = useState({ nombre: '', correo: '', mensaje: '' });
  // Estado para mostrar mensaje de éxito cuando el envío es correcto
  const [mensajeExito, setMensajeExito] = useState('');
  // Estado para los errores de validación
  const [errores, setErrores] = useState({});

  // Función de validación de campos
  const validate = () => {
    const errs = {};
    if (!form.nombre) errs.nombre = 'El nombre es obligatorio.';
    if (!form.correo || !/\S+@\S+\.\S+/.test(form.correo)) errs.correo = 'Correo inválido.';
    if (!form.mensaje) errs.mensaje = 'El mensaje es obligatorio.';
    return errs;
  };

  // Maneja el cambio de cada campo del formulario
  const handleChange = e => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  // Maneja el envío del formulario y aplica la validación
  const handleSubmit = e => {
    e.preventDefault();
    const val = validate();
    if (Object.keys(val).length === 0) {
      // Si no hay errores, mostramos éxito y reseteamos el formulario
      setMensajeExito('¡Mensaje enviado con éxito (simulado)!');
      setForm({ nombre: '', correo: '', mensaje: '' });
      setErrores({});
    } else {
      // Si hay errores, los almacenamos para mostrar al usuario
      setErrores(val);
      setMensajeExito('');
    }
  };

  return (
    <div>
      <h2>Contacto</h2>
      <Form onSubmit={handleSubmit}>
        {/* Campo Nombre */}
        <Form.Group className="mb-3">
          <Form.Label>Nombre</Form.Label>
          <Form.Control
            type="text"
            name="nombre"
            value={form.nombre}
            onChange={handleChange}
            isInvalid={!!errores.nombre}
          />
          <Form.Control.Feedback type="invalid">{errores.nombre}</Form.Control.Feedback>
        </Form.Group>
        {/* Campo Correo */}
        <Form.Group className="mb-3">
          <Form.Label>Correo electrónico</Form.Label>
          <Form.Control
            type="email"
            name="correo"
            value={form.correo}
            onChange={handleChange}
            isInvalid={!!errores.correo}
          />
          <Form.Control.Feedback type="invalid">{errores.correo}</Form.Control.Feedback>
        </Form.Group>
        {/* Campo Mensaje */}
        <Form.Group className="mb-3">
          <Form.Label>Mensaje</Form.Label>
          <Form.Control
            as="textarea"
            name="mensaje"
            value={form.mensaje}
            onChange={handleChange}
            isInvalid={!!errores.mensaje}
          />
          <Form.Control.Feedback type="invalid">{errores.mensaje}</Form.Control.Feedback>
        </Form.Group>
        {/* Botón de envío */}
        <Button variant="success" type="submit">Enviar</Button>
      </Form>
      {/* Mensaje de éxito visible cuando aplica */}
      {mensajeExito && <Alert className="mt-3" variant="success">{mensajeExito}</Alert>}
    </div>
  );
};

export default Contact;


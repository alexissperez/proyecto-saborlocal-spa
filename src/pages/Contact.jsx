import React, { useState } from 'react';
import { Container, Form, Button, Alert } from 'react-bootstrap';

const Contact = () => {
  const [form, setForm] = useState({ nombre: '', correo: '', mensaje: '' });
  const [mensajeExito, setMensajeExito] = useState('');
  const [errores, setErrores] = useState({});

  const validate = () => {
    const errs = {};
    if (!form.nombre.trim()) errs.nombre = 'El nombre es obligatorio.';
    if (!form.correo || !/\S+@\S+\.\S+/.test(form.correo)) errs.correo = 'Correo inválido.';
    if (!form.mensaje.trim()) errs.mensaje = 'El mensaje es obligatorio.';
    return errs;
  };

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const val = validate();
    if (Object.keys(val).length === 0) {
      setMensajeExito('¡Mensaje enviado con éxito (simulado)!');
      setForm({ nombre: '', correo: '', mensaje: '' });
      setErrores({});
    } else {
      setErrores(val);
      setMensajeExito('');
    }
  };

  return (
    <Container style={{ maxWidth: 640 }}>
      <h2 className="mb-3">Contacto</h2>
      <p className="text-muted mb-4">
        Déjanos tu mensaje y un representante de SaborLocal se pondrá en contacto contigo.
      </p>

      <Form onSubmit={handleSubmit} noValidate>
        <Form.Group className="mb-3">
          <Form.Label>Nombre</Form.Label>
          <Form.Control
            type="text"
            name="nombre"
            value={form.nombre}
            onChange={handleChange}
            isInvalid={!!errores.nombre}
          />
          <Form.Control.Feedback type="invalid">
            {errores.nombre}
          </Form.Control.Feedback>
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label>Correo electrónico</Form.Label>
          <Form.Control
            type="email"
            name="correo"
            value={form.correo}
            onChange={handleChange}
            isInvalid={!!errores.correo}
          />
          <Form.Control.Feedback type="invalid">
            {errores.correo}
          </Form.Control.Feedback>
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label>Mensaje</Form.Label>
          <Form.Control
            as="textarea"
            rows={4}
            name="mensaje"
            value={form.mensaje}
            onChange={handleChange}
            isInvalid={!!errores.mensaje}
          />
          <Form.Control.Feedback type="invalid">
            {errores.mensaje}
          </Form.Control.Feedback>
        </Form.Group>

        <Button variant="success" type="submit">
          Enviar
        </Button>
      </Form>

      {mensajeExito && (
        <Alert className="mt-3" variant="success">
          {mensajeExito}
        </Alert>
      )}
    </Container>
  );
};

export default Contact;

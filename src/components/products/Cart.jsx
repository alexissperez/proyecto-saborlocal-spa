// src/components/products/Cart.jsx
import React, { useContext } from 'react';
import { Row, Col, Button } from 'react-bootstrap';
import { AppContext } from '../../context/AppContext';

const Cart = () => {
  const { carrito, eliminarDelCarrito } = useContext(AppContext);

  const total = carrito.reduce((acc, item) => {
  const raw = item.precio ?? item.price ?? 0;
  return acc + Number(raw);
}, 0);

  if (carrito.length === 0) {
    return <p>Tu carrito está vacío.</p>;
  }

  return (
    <>
      {carrito.map((item, index) => (
        <Row key={index} className="align-items-center mb-2">
          <Col xs={6}>{item.nombre || item.name}</Col>
          <Col xs={3} className="text-end">
            {`$${Number(item.precio ?? item.price).toLocaleString('es-CL')}`}
          </Col>
          <Col xs={3} className="text-end">
            <Button
              variant="outline-danger"
              size="sm"
              onClick={() => eliminarDelCarrito(index)}
            >
              Eliminar
            </Button>
          </Col>
        </Row>
      ))}

      <hr />

      <p className="mb-1">
        <strong>Total</strong>
      </p>
      <p>
        Monto a pagar:{' '}
        <strong>{`$${total.toLocaleString('es-CL')}`}</strong>
      </p>
    </>
  );
};

export default Cart;

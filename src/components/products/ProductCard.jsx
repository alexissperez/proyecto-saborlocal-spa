import React from 'react';
import { Card, Button } from 'react-bootstrap';

const ProductCard = ({ producto, onAgregar }) => {
  return (
    <Card>
      <Card.Body>
        <Card.Title>{producto.nombre}</Card.Title>
        <Card.Text>
          <strong>Categoría:</strong> {producto.categoria}<br />
          <strong>Productor:</strong> {producto.productor}<br />
          <strong>Precio:</strong> ${producto.precio}
        </Card.Text>
        <Button className="btn-cafe" onClick={() => onAgregar(producto)}>
        Agregar al carrito
        </Button>

      </Card.Body>
    </Card>
  );
};

export default ProductCard;

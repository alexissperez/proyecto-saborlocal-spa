import React from 'react';
import { Card, Button } from 'react-bootstrap';

const ProductCard = ({ producto, onAgregar }) => (
  <Card className="h-100">
    <Card.Img
      variant="top"
      src={producto.imagen || producto.imageUrl} // ajusta el nombre según tu mock
      alt={producto.nombre || producto.name}
      style={{ objectFit: 'cover', height: '180px' }}
    />
    <Card.Body className="d-flex flex-column">
      <Card.Title>{producto.nombre || producto.name}</Card.Title>
      <Card.Text>
        <strong>Categoría:</strong> {producto.categoria || producto.category}<br/>
        <strong>Productor:</strong> {producto.productor || producto.producer}<br/>
        <strong>Precio:</strong> ${producto.precio || producto.price}
      </Card.Text>
      <Button
        variant="dark"
        className="mt-auto"
        onClick={() => onAgregar(producto)}
      >
        Agregar al carrito
      </Button>
    </Card.Body>
  </Card>
);

export default ProductCard;


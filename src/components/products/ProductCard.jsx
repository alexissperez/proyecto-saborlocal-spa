import React from 'react';
import { Card, Button } from 'react-bootstrap';

// ...existing code...
const ProductCard = ({ producto, onAgregar }) => {
  // Depuración: ver ruta que llega
  console.log('ProductCard imagen:', producto.id, producto.imagen);

  return (
    <Card>
      <Card.Body>
        <Card.Title>{producto.nombre}</Card.Title>
        <Card.Text>
          <strong>Categoría:</strong> {producto.categoria}<br />
          <strong>Productor:</strong> {producto.productor}<br />
          <strong>Precio:</strong> ${producto.precio}
        </Card.Text>

        <img
          src={producto.imagen}
          alt={producto.nombre}
          onError={(e) => {
            console.warn('imagen no encontrada:', producto.imagen);
            e.currentTarget.src = '/images/products/placeholder.png';
          }}
          style={{ width: 150, objectFit: 'cover' }}
        />

        <Button className="btn-cafe" onClick={() => onAgregar(producto)}>
          Agregar al carrito
        </Button>
      </Card.Body>
    </Card>
  );
};

// Agregar esta línea:
export default ProductCard;
// ...existing code...
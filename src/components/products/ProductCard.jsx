import React, { useContext } from 'react';
import { Card, Button } from 'react-bootstrap';
import { AppContext } from '../../context/AppContext';

// ...existing code...
const ProductCard = ({ producto, onAgregar }) => {
  const { agregarAlCarrito } = useContext(AppContext);

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

        <Button
          className="btn-cafe"
          onClick={() => {
            if (onAgregar) onAgregar(producto); // compatibilidad con prop
            else agregarAlCarrito(producto);     // uso por defecto con contexto
          }}
        >
          Agregar al carrito
        </Button>
      </Card.Body>
    </Card>
  );
};

export default ProductCard;

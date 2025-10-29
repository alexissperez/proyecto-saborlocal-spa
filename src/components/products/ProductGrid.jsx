import React from 'react';
import { Row, Col } from 'react-bootstrap';
import ProductCard from './ProductCard';

// Este componente recibe una lista de productos y una función para agregar carrito
const ProductGrid = ({ productos, onAgregar }) => {
  return (
    <Row>
      {/* Iteramos sobre la lista de productos y mostramos un ProductCard por cada uno */}
      {productos.map(producto => (
        <Col key={producto.id} sm={12} md={6} lg={4} className="mb-4">
          {/* ProductCard representa cada producto individual */}
          <ProductCard producto={producto} onAgregar={onAgregar} />
        </Col>
      ))}
    </Row>
  );
};

export default ProductGrid;

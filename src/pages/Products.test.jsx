import React, { useContext, useMemo, useState } from 'react';
import { Container, Row, Col, Badge } from 'react-bootstrap';
import { products, categories } from '../data/gaming.mock';
import { AppContext } from '../context/AppContext';
import Filters from '../components/products/Filters';
import ProductGrid from '../components/products/ProductGrid';

const Products = () => {
  const { agregarAlCarrito, carrito } = useContext(AppContext);
  const [categoriaSeleccionada, setCategoriaSeleccionada] = useState('Todos');

  const productosFiltrados = useMemo(() => {
    if (categoriaSeleccionada === 'Todos') return products;
    return products.filter((p) => p.categoria === categoriaSeleccionada);
  }, [categoriaSeleccionada]);

  const handleAgregar = (producto) => {
    agregarAlCarrito(producto);
  };

  return (
    <main>
      <Container className="mt-4 mb-5">
        <Row className="align-items-center mb-3">
          <Col>
            <h2 className="mb-1">Catálogo SaborLocal</h2>
            <p className="text-muted mb-0">
              Lácteos, conservas y panadería artesanal de productores locales.
            </p>
          </Col>
          <Col xs="auto">
            <Badge bg="success" pill>
              En carrito: {carrito.length}
            </Badge>
          </Col>
        </Row>

        <div className="catalog-filters mb-4">
          <Filters
            categorias={['Todos', ...categories]}
            categoriaSeleccionada={categoriaSeleccionada}
            setCategoriaSeleccionada={setCategoriaSeleccionada}
            totalProductos={products.length}
          />
        </div>

        <ProductGrid
          productos={productosFiltrados}
          onAgregar={handleAgregar}
        />
      </Container>
    </main>
  );
};

export default Products;

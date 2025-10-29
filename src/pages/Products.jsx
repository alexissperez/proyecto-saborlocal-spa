import React, { useContext, useState } from 'react';
import products from '../data/gaming.mock';
import { AppContext } from '../context/AppContext';
import { Row, Col, ButtonGroup, Button } from 'react-bootstrap';
import ProductCard from '../components/products/ProductCard';
import ProductGrid from '../components/products/ProductGrid';

const Products = () => {
  const { incrementar } = useContext(AppContext);
  // Estado para la categoría seleccionada (filtro)
  const [categoriaSeleccionada, setCategoriaSeleccionada] = useState('Todos');

  // Obtener categorías únicas desde productos
  const categorias = ['Todos', ...new Set(products.map(p => p.categoria))];

  // Filtrar productos según categoría seleccionada
  const productosFiltrados =
    categoriaSeleccionada === 'Todos'
      ? products
      : products.filter(p => p.categoria === categoriaSeleccionada);

  // Función para manejar agregar al carrito
  const handleAgregar = (producto) => {
    incrementar();
    alert(`Agregaste ${producto.nombre} al carrito`);
  };

  return (
    <div>
      <h2>Catálogo de Productos</h2>

      {/* Botones de filtro */}
      <ButtonGroup className="mb-3">
        {categorias.map(cat => (
          <Button
            key={cat}
            variant={cat === categoriaSeleccionada ? 'primary' : 'outline-primary'}
            onClick={() => setCategoriaSeleccionada(cat)}
          >
            {cat}
          </Button>
        ))}
      </ButtonGroup>

      {/* Listado de productos filtrados */}
      <Row>
        {productosFiltrados.map(producto => (
          <Col key={producto.id} sm={12} md={6} lg={4} className="mb-4">
            <ProductCard producto={producto} onAgregar={handleAgregar} />
            <ProductGrid productos={productosFiltrados} onAgregar={handleAgregar} />
          </Col>
        ))}
      </Row>
    </div>
  );
};

export default Products;


// src/pages/Products.jsx
import React, { useContext, useMemo, useState } from 'react';
import { Container } from 'react-bootstrap';
import { products, categories } from '../data/gaming.mock';
import { AppContext } from '../context/AppContext';
import Filters from '../components/products/Filters';
import ProductGrid from '../components/products/ProductGrid';
import bannerImg from '../assets/Imagen-centro.png';

const Products = () => {
  const { agregarAlCarrito } = useContext(AppContext);
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
      {/* Banner superior */}
      <div
        className="forgot-banner"
        style={{ backgroundImage: `url(${bannerImg})` }}
      >
        <div className="forgot-banner-inner">
          <h2>Catálogo SaborLocal</h2>
          <p className="text-muted mb-0">
            Productos artesanales de café, lácteos, conservas, panadería, especias y más.
          </p>
        </div>
      </div>

      {/* Contenido del catálogo sin título repetido */}
      <Container className="mt-4 mb-5">
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

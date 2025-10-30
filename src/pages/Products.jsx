import React, { useContext, useState } from 'react';
import { products } from '../data/gaming.mock';
import { AppContext } from '../context/AppContext';
import { ButtonGroup, Button } from 'react-bootstrap';
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
const { agregarAlCarrito } = useContext(AppContext);

const handleAgregar = (producto) => {
  agregarAlCarrito(producto);
};


  return (
    <div>
      <h2>Catálogo de Productos</h2>

      {/* Botones de filtro */}
      <ButtonGroup className="mb-3">
        {categorias.map(cat => (
          <Button
            key={cat}
            className="btn-verde-oscuro"
            variant={cat === categoriaSeleccionada ? 'verde-oscuro' : 'outline-verde-oscuro'}
            style={{
              backgroundColor: cat === categoriaSeleccionada ? '#206a2c' : 'transparent',
              color: cat === categoriaSeleccionada ? 'white' : '#206a2c',
              borderColor: '#155320'
            }}
            onClick={() => setCategoriaSeleccionada(cat)}
            >
            {cat}
          </Button>
        ))}
      </ButtonGroup>
      {/* Solo una vez el grid de productos */}
      <ProductGrid productos={productosFiltrados} onAgregar={handleAgregar} />
    </div>
  );
};

export default Products;



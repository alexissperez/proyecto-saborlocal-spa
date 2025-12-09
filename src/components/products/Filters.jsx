import React from 'react';
import { ButtonGroup, Button, Badge } from 'react-bootstrap';

const Filters = ({
  categorias,
  categoriaSeleccionada,
  setCategoriaSeleccionada,
  totalProductos
}) => {
  const isAll = categoriaSeleccionada === 'Todos';

  const handleChange = (cat) => {
    setCategoriaSeleccionada(cat);
  };

  return (
    <div className="d-flex align-items-center justify-content-between mb-3">
      <ButtonGroup>
        <Button
          variant={isAll ? 'primary' : 'outline-primary'}
          onClick={() => handleChange('Todos')}
        >
          Todos{' '}
          {typeof totalProductos === 'number' && (
            <Badge
              bg={isAll ? 'light' : 'primary'}
              className="ms-1"
            >
              {totalProductos}
            </Badge>
          )}
        </Button>

        {categorias.map((cat) => {
          const active = categoriaSeleccionada === cat;
          return (
            <Button
              key={cat}
              variant={active ? 'primary' : 'outline-primary'}
              onClick={() => handleChange(cat)}
            >
              {cat}
            </Button>
          );
        })}
      </ButtonGroup>
    </div>
  );
};

export default Filters;

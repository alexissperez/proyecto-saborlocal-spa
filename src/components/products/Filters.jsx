import React from 'react';
import { ButtonGroup, Button } from 'react-bootstrap';

const Filters = ({ categorias, categoriaSeleccionada, setCategoriaSeleccionada }) => (
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
);

export default Filters;

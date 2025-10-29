import React, { useContext } from 'react';
import { Navbar, Nav, Container, Badge } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { AppContext } from '../context/AppContext';
import '../App.css'; // Asegúrate de importar tu CSS

const NavBar = () => {
  const { contador } = useContext(AppContext);

  return (
    <Navbar className="bg-cafe shadow-sm" expand="lg">
      <Container>
        <Navbar.Brand as={Link} to="/" >SaborLocal</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse>
          <Nav className="me-auto">
            <Nav.Link as={Link} to="/">Inicio</Nav.Link>
            <Nav.Link as={Link} to="/products">Catálogo</Nav.Link>
            <Nav.Link as={Link} to="/contact">Contacto</Nav.Link>
          </Nav>
          <Badge className="badge-cafe ms-2">Carrito: {contador}</Badge>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default NavBar;


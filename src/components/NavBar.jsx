import React, { useContext, useState } from 'react';
import { Navbar, Nav, Container, Badge, Modal, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { AppContext } from '../context/AppContext';
import Cart from './products/Cart';
import '../App.css';

const NavBar = () => {
  const { carrito } = useContext(AppContext);
  const [showCart, setShowCart] = useState(false);

  return (
    <Navbar className="bg-cafe shadow-sm" expand="lg">
      <Container>
        <Navbar.Brand as={Link} to="/">SaborLocal</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse>
          <Nav className="me-auto">
            <Nav.Link as={Link} to="/">Inicio</Nav.Link>
            <Nav.Link as={Link} to="/products">Catálogo</Nav.Link>
            <Nav.Link as={Link} to="/contact">Contacto</Nav.Link>
          </Nav>
          <Badge
            className="badge-cafe ms-2"
            style={{ backgroundColor: '#206a2c', fontSize: '1em', cursor: 'pointer' }}
            onClick={() => setShowCart(true)}
          >
            Carrito: {carrito.length}
          </Badge>
        </Navbar.Collapse>
      </Container>
      <Modal show={showCart} onHide={() => setShowCart(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Mi Carrito</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Cart />
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowCart(false)}>Cerrar</Button>
        </Modal.Footer>
      </Modal>
    </Navbar>
  );
};

export default NavBar;



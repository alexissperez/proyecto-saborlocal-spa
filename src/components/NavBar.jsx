// src/components/NavBar.jsx
import React, { useContext, useState } from 'react';
import {
  Navbar,
  Nav,
  Container,
  NavDropdown,
  Modal,
  Button,
} from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import { AppContext } from '../context/AppContext';
import Cart from './products/Cart';
import '../App.css';

const NavBar = () => {
  const { carrito, auth, logout } = useContext(AppContext);
  const [showCart, setShowCart] = useState(false);
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <>
      <Navbar className="navbar-saborlocal shadow-sm" expand="lg" variant="light">
        <Container>
          <Navbar.Brand
            as={Link}
            to="/"
            className="fw-bold"
            style={{ color: '#000' }}
          >
            Sabor<span className="text-accent">Local</span>
          </Navbar.Brand>

          <Navbar.Toggle aria-controls="main-navbar" />
          <Navbar.Collapse id="main-navbar">
            <Nav className="me-auto">
              <Nav.Link as={Link} to="/">Inicio</Nav.Link>

              <NavDropdown title="Productos" id="productos-dropdown">
                <NavDropdown.Item as={Link} to="/products">
                  Todos
                </NavDropdown.Item>
                <NavDropdown.Item as={Link} to="/products">
                  Lácteos
                </NavDropdown.Item>
                <NavDropdown.Item as={Link} to="/products">
                  Conservas
                </NavDropdown.Item>
                <NavDropdown.Item as={Link} to="/products">
                  Panadería
                </NavDropdown.Item>
              </NavDropdown>

              <Nav.Link as={Link} to="/contact">Contacto</Nav.Link>
            </Nav>

            <div className="d-flex align-items-center gap-3">
              {/* Lupa decorativa */}
              <button type="button" className="icon-button">
                🔍
              </button>

              {/* Usuario / login transparente */}
              {auth.isAuthenticated ? (
                <Button
                  variant="outline-secondary"
                  size="sm"
                  onClick={handleLogout}
                >
                  Cerrar sesión
                </Button>
              ) : (
                <button
                  type="button"
                  className="icon-button"
                  onClick={() => navigate('/login')}
                >
                  👤
                </button>
              )}

              {/* Carrito con contador */}
              <button
                type="button"
                className="icon-button position-relative"
                onClick={() => setShowCart(true)}
              >
                🛒
                {carrito.length > 0 && (
                  <span className="cart-badge">
                    {carrito.length}
                  </span>
                )}
              </button>
            </div>
          </Navbar.Collapse>
        </Container>
      </Navbar>

      <Modal show={showCart} onHide={() => setShowCart(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Mi Carrito</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Cart />
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowCart(false)}>
            Cerrar
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default NavBar;

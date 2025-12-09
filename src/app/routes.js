import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from '../pages/Home';
import Products from '../pages/Products';
import Contact from '../pages/Contact';
import LoginPage from '../pages/LoginPage';
import PrivateRoute from '../components/PrivateRoute';
import NavBar from '../components/NavBar';

const AppRoutes = () => (
  <BrowserRouter>
    <NavBar />
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<LoginPage />} />

      {/* Temporalmente SIN PrivateRoute para poder ver /products */}
      <Route path="/products" element={<Products />} />

      {/* Cuando el backend de login funcione, vuelves a esto:
      <Route
        path="/products"
        element={
          <PrivateRoute>
            <Products />
          </PrivateRoute>
        }
      />
      */}

      <Route path="/contact" element={<Contact />} />
    </Routes>
  </BrowserRouter>
);

export default AppRoutes;





import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Home from '../pages/Home';
import Products from '../pages/Products';
import Contact from '../pages/Contact';
import LoginPage from '../pages/LoginPage';
import PrivateRoute from '../components/PrivateRoute';

const AppRoutes = () => (
  <Routes>
    <Route path="/" element={<Home />} />
    <Route path="/login" element={<LoginPage />} />
    <Route
      path="/products"
      element={
        <PrivateRoute>
          <Products />
        </PrivateRoute>
      }
    />
    <Route path="/contact" element={<Contact />} />
  </Routes>
);

export default AppRoutes;




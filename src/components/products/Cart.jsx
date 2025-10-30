import React, { useContext } from 'react';
import { AppContext } from '../../context/AppContext';

const Cart = () => {
  const { carrito } = useContext(AppContext);
  const total = carrito.reduce((sum, prod) => sum + prod.precio, 0);

  return (
    <div>
      <h4>Productos en el carrito</h4>
      <ul>
        {carrito.map((prod, i) => (
          <li key={i}>
            {prod.nombre} - ${prod.precio}
          </li>
        ))}
      </ul>
      <strong>Total a pagar: ${total}</strong>
    </div>
  );
};

export default Cart;



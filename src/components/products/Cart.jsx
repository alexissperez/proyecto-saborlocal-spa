import React, { useContext } from 'react';
import { AppContext } from '../../context/AppContext';

const Cart = () => {
  const { carrito, vaciarCarrito } = useContext(AppContext);

  return (
    <div>
      <h4>Productos en el carrito</h4>
      <ul>
        {carrito.map((producto, i) => (
          <li key={i}>{producto.nombre} - ${producto.precio}</li>
        ))}
      </ul>
      <strong>Total a pagar: ${carrito.reduce((acc, item) => acc + item.precio, 0)}</strong>

      {/* Aquí va el botón para vaciar el carrito */}
      <button onClick={vaciarCarrito}>Vaciar carrito</button>
    </div>
  );
};

export default Cart;


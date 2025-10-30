import React, { useContext } from 'react';
import { AppContext } from '../../context/AppContext';

const Cart = () => {
  const { carrito, vaciarCarrito } = useContext(AppContext);

  return (
    <div>
      <h3>Mi Carrito</h3>
      <hr />
      <h4>Productos en el carrito</h4>
      <ul>
        {carrito.map((producto, i) => (
          <li key={i}>
            {producto.nombre} - ${producto.precio}
          </li>
        ))}
      </ul>
      <strong>Total a pagar: ${carrito.reduce((acc, item) => acc + item.precio, 0)}</strong>
      <br /><br />

      {/* Botón abajo centrado */}
      <button style={{marginTop: "16px"}} onClick={vaciarCarrito}>Vaciar carrito</button>

      {/* Aquí abajo podrías poner el botón Cerrar */}
    </div>
  );
};

export default Cart;

// src/components/products/Cart.jsx
import React, { useContext, useMemo } from 'react';
import { AppContext } from '../../context/AppContext';

const Cart = () => {
  const { carrito } = useContext(AppContext);

  const total = useMemo(
    () =>
      carrito.reduce(
        (acc, item) => acc + Number(item.precio ?? item.price ?? 0),
        0
      ),
    [carrito]
  );

  return (
    <div>
      {/* Lista de artículos */}
      {carrito.length === 0 ? (
        <p className="text-muted mb-3">Tu carrito está vacío.</p>
      ) : (
        <ul className="list-unstyled mb-3">
          {carrito.map((item, index) => (
            <li
              key={`${item.id}-${index}`}
              className="d-flex justify-content-between mb-1"
            >
              <span>{item.nombre || item.name}</span>
              <span>
                ${Number(item.precio ?? item.price).toLocaleString('es-CL')}
              </span>
            </li>
          ))}
        </ul>
      )}

      {/* Resumen superior */}
      <div className="d-flex justify-content-between mb-2">
        <span>
          {carrito.length === 0
            ? '0 artículos'
            : `${carrito.length} artículo${carrito.length > 1 ? 's' : ''}`}
        </span>
        <span>
          ${total.toLocaleString('es-CL')}
        </span>
      </div>

      <hr />

      {/* Total */}
      <div className="mb-3">
        <strong>Total</strong>
        <div className="d-flex justify-content-between">
          <span>Monto a pagar:</span>
          <span>
            ${total.toLocaleString('es-CL')}
          </span>
        </div>
      </div>

      <button
        type="button"
        className="btn btn-dark w-100"
        disabled={carrito.length === 0}
      >
        Tramitar pedido
      </button>
    </div>
  );
};

export default Cart;

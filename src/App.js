import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import { AppProvider } from './context/AppContext'; // Importa tu context
import NavBar from './components/NavBar';            // Barra de navegación
import AppRoutes from './app/routes';                // Rutas

// Componente principal. Envuelve la app con el Context y el Router.
function App() {
  return (
    <AppProvider>
      <BrowserRouter>
        <NavBar />      {/* Barra arriba en todas las páginas */}
        <div className="container mt-4">
          <AppRoutes /> {/* Aquí se muestran las páginas según la ruta */}
        </div>
      </BrowserRouter>
    </AppProvider>
  );
}

export default App;


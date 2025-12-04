import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import { AppProvider } from './context/AppContext';
import NavBar from './components/NavBar';
import AppRoutes from './app/routes';
import { login as apiLogin } from './services/authService';

function App() {
  return (
    <AppProvider>
      <BrowserRouter>
        <NavBar />
        <div className="container mt-4">
          <AppRoutes />
        </div>
      </BrowserRouter>
    </AppProvider>
  );
}

export default App;

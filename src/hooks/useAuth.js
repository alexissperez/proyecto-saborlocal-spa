import { useContext } from 'react';
import { AppContext } from '../context/AppContext';

export const useAuth = () => {
  const context = useContext(AppContext);
  if (!context) {
    throw new Error('useAuth debe usarse dentro de AppProvider');
  }
  return context.auth;
};

export const useAuthActions = () => {
  const context = useContext(AppContext);
  if (!context) {
    throw new Error('useAuthActions debe usarse dentro de AppProvider');
  }
  return { login: context.login, logout: context.logout };
};

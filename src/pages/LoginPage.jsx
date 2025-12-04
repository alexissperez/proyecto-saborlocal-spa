import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { login as apiLogin } from '../services/authService';
import { useAuthActions } from '../hooks/useAuth';

const LoginPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const { login } = useAuthActions();
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    try {
      const data = await apiLogin({ email, password });
      login({ email: data.email, rol: data.rol }, data.token);
      navigate('/products');
    } catch (err) {
      setError(err.message);
    }
  };

  return (
    <div className="row justify-content-center mt-5">
      <div className="col-md-4">
        <h2>Iniciar sesión</h2>
        {error && <div className="alert alert-danger">{error}</div>}
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <input type="email" className="form-control" placeholder="Email" value={email} onChange={e => setEmail(e.target.value)} required />
          </div>
          <div className="mb-3">
            <input type="password" className="form-control" placeholder="Contraseña" value={password} onChange={e => setPassword(e.target.value)} required />
          </div>
          <button type="submit" className="btn btn-primary">Entrar</button>
        </form>
      </div>
    </div>
  );
};

export default LoginPage;

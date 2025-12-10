// src/pages/RegisterPage.jsx
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import bannerImg from '../assets/Imagen-centro.png';

const RegisterPage = () => {
  const [form, setForm] = useState({
    tratamiento: '',
    nombre: '',
    apellidos: '',
    email: '',
    emailConfirm: '',
    password: '',
    fechaNacimiento: '',
  });

  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setForm({
      ...form,
      [name]: type === 'checkbox' ? checked : value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setError('');

    // Validar que los correos coincidan
    if (form.email !== form.emailConfirm) {
      setError('Los correos electrónicos no coinciden.');
      return;
    }

    // Registro simulado (sin backend por ahora)
    alert('Cuenta creada correctamente');
    navigate('/login');
  };

  return (
    <>
      {/* Banner superior */}
      <div
        className="forgot-banner"
        style={{ backgroundImage: `url(${bannerImg})` }}
      >
        <div className="forgot-banner-inner">
          <h2>Crear una cuenta</h2>
          <p>
            <Link to="/" className="link-saborlocal-soft">
              Inicio
            </Link>{' '}
            / <span>Crear una cuenta</span>
          </p>
        </div>
      </div>

      <main className="login-wrapper">
        <div className="login-panel">
          <div className="mb-4" style={{ fontSize: '0.95rem' }}>
            ¿Ya tiene una cuenta?{' '}
            <Link to="/login" className="link-saborlocal">
              ¡Inicie sesión!
            </Link>
          </div>

          {error && (
            <div className="alert alert-danger py-2 mb-3">
              {error}
            </div>
          )}

          <div className="border rounded bg-white p-4">
            <form onSubmit={handleSubmit}>
              {/* Nombre */}
              <div className="mb-3 row">
                <label className="col-sm-3 col-form-label">Nombre</label>
                <div className="col-sm-9">
                  <input
                    type="text"
                    name="nombre"
                    className="form-control"
                    value={form.nombre}
                    onChange={handleChange}
                    required
                  />
                </div>
              </div>

              {/* Apellidos */}
              <div className="mb-3 row">
                <label className="col-sm-3 col-form-label">Apellidos</label>
                <div className="col-sm-9">
                  <input
                    type="text"
                    name="apellidos"
                    className="form-control"
                    value={form.apellidos}
                    onChange={handleChange}
                    required
                  />
                </div>
              </div>

              {/* Email */}
              <div className="mb-3 row">
                <label className="col-sm-3 col-form-label">
                  Dirección de correo electrónico
                </label>
                <div className="col-sm-9">
                  <input
                    type="email"
                    name="email"
                    className="form-control"
                    value={form.email}
                    onChange={handleChange}
                    required
                  />
                </div>
              </div>

              {/* Repite tu correo */}
              <div className="mb-3 row">
                <label className="col-sm-3 col-form-label">
                  Repite dirección de correo electrónico
                </label>
                <div className="col-sm-9">
                  <input
                    type="email"
                    name="emailConfirm"
                    className="form-control"
                    value={form.emailConfirm}
                    onChange={handleChange}
                    required
                  />
                </div>
              </div>

              {/* Contraseña con MOSTRAR */}
              <div className="mb-3 row">
                <label className="col-sm-3 col-form-label">Contraseña</label>
                <div className="col-sm-9 d-flex">
                  <input
                    type={showPassword ? 'text' : 'password'}
                    name="password"
                    className="form-control"
                    value={form.password}
                    onChange={handleChange}
                    required
                    minLength={6}
                  />
                  <button
                    type="button"
                    className="btn btn-outline-secondary ms-2"
                    style={{ whiteSpace: 'nowrap' }}
                    onClick={() => setShowPassword((prev) => !prev)}
                  >
                    {showPassword ? 'OCULTAR' : 'MOSTRAR'}
                  </button>
                </div>
              </div>

              {/* Fecha de nacimiento */}
              <div className="mb-4 row">
                <label className="col-sm-3 col-form-label">
                  Fecha de nacimiento
                </label>
                <div className="col-sm-6">
                  <input
                    type="date"
                    name="fechaNacimiento"
                    className="form-control"
                    value={form.fechaNacimiento}
                    onChange={handleChange}
                  />
                  <small className="text-muted">
                    Ejemplo: 31/05/1970
                  </small>
                </div>
                <div
                  className="col-sm-3 text-muted"
                  style={{ fontSize: '0.85rem' }}
                >
                  Opcional
                </div>
              </div>

              {/* Botón guardar */}
              <div className="text-center">
                <button type="submit" className="btn btn-dark px-5">
                  Guardar
                </button>
              </div>
            </form>
          </div>
        </div>
      </main>
    </>
  );
};

export default RegisterPage;

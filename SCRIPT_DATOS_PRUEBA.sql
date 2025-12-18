-- Script para cargar datos de prueba en SaborLocal
-- Ejecutar en H2 Console en http://localhost:8081/h2-console

-- ========== LIMPIAR DATOS EXISTENTES ==========
-- Primero eliminar productos (tiene FK a productores)
DELETE FROM productos;

-- Luego eliminar productores
DELETE FROM productores;

-- Resetear sequences
ALTER TABLE productos ALTER COLUMN id RESTART WITH 1;
ALTER TABLE productores ALTER COLUMN id RESTART WITH 1;

-- ========== PRODUCTORES ==========
INSERT INTO productores (nombre, email, telefono, empresa, registro_sanitario, activo) 
VALUES 
('Granja Los Andes', 'granja.andes@example.com', '555-0001', 'Agricultura Los Andes', 'RS-2024-001', true),
('Frutos del Valle', 'frutos.valle@example.com', '555-0002', 'Conservas Artesanales', 'RS-2024-002', true),
('Panadería El Pueblo', 'panaderia.pueblo@example.com', '555-0003', 'Pan Artesanal', 'RS-2024-003', true),
('Hermanas del Campo', 'hermanas.campo@example.com', '555-0004', 'Productos Lácteos', 'RS-2024-004', true),
('Caficultores del Sur', 'cafe.sur@example.com', '555-0005', 'Café de Especialidad', 'RS-2024-005', true),
('Lechería Santa Rosa', 'lecheria.rosa@example.com', '555-0006', 'Productos Lácteos', 'RS-2024-006', true),
('Molino Los Andes', 'molino.andes@example.com', '555-0007', 'Especias y Condimentos', 'RS-2024-007', true),
('Costa Viva', 'costa.viva@example.com', '555-0008', 'Productos del Mar', 'RS-2024-008', true),
('Mapu Sabores', 'mapu.sabores@example.com', '555-0009', 'Especias Tradicionales', 'RS-2024-009', true),
('Condimentos El Fogón', 'fogon.condimentos@example.com', '555-0010', 'Mezclas y Condimentos', 'RS-2024-010', true),
('Raíces del Valle', 'raices.valle@example.com', '555-0011', 'Especias Orgánicas', 'RS-2024-011', true),
('Hierbas La Quebrada', 'hierbas.quebrada@example.com', '555-0012', 'Hierbas Aromáticas', 'RS-2024-012', true);

-- ========== PRODUCTOS ==========
INSERT INTO productos (nombre, descripcion, categoria, precio, stock, disponible, productor_id) 
VALUES 
('Queso Fresco', 'Queso fresco artesanal de leche de vaca', 'Lácteos', 2500, 50, true, 1),
('Bebida Probiótica Kefir', 'Bebida probiótica fermentada natural', 'Lácteos', 3990, 40, true, 4),
('Mantequilla de Campo', 'Mantequilla artesanal de granja', 'Lácteos', 2990, 35, true, 4),
('Leche de campo entera', 'Leche entera fresca de granja', 'Lácteos', 2100, 100, true, 6),
('Mermelada de Frutilla', 'Mermelada casera de frutilla', 'Conservas', 1800, 60, true, 2),
('Mermelada de Frambuesa', 'Mermelada casera de frambuesa', 'Conservas', 1800, 55, true, 2),
('Mermelada de frutos rojos', 'Mermelada de mezcla de frutos rojos', 'Conservas', 3800, 45, true, 2),
('Pan masa madre', 'Pan artesanal con masa madre', 'Panadería', 1200, 80, true, 3),
('Café de grano tostado', 'Café de grano tostado medium', 'Café', 6900, 30, true, 5),
('Mix de Pimientas del Mundo', 'Mezcla de 4 pimientas premium', 'Especias', 4590, 25, true, 7),
('Sal de Mar con Hierbas', 'Sal de mar aromatizada con hierbas', 'Especias', 3490, 40, true, 8),
('Merkén Ahumado Artesanal', 'Merkén ahumado tradicional', 'Especias', 2990, 35, true, 9),
('Mezcla para Asado Campesino', 'Condimentos para asado', 'Especias', 3890, 20, true, 10),
('Cúrcuma Molida Orgánica', 'Cúrcuma orgánica molida fresca', 'Especias', 3290, 30, true, 11),
('Orégano de Cordillera', 'Orégano silvestre de cordillera', 'Especias', 2590, 50, true, 12);

-- ========== USUARIO ADMIN DE PRUEBA ==========
-- Email: admin@test.com
-- Password: admin123 (hash BCrypt)
INSERT INTO clientes (nombre, email, password, rol, telefono, direccion) 
VALUES 
('Admin User', 'admin@test.com', '$2a$10$slYQmyNdGzin7olVi9YU2OPST9/PgBkqquzi.Ss7KIUgO2t0jKMUm', 'ADMIN', '555-9999', 'Calle Admin 123');

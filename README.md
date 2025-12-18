# 🌾 SABORLOCAL - API REST Backend

Sistema de gestión de productos agrícolas con autenticación JWT, CRUD completo y documentación automática.

---

## 📋 Índice

1. [Características](#características)
2. [Requisitos](#requisitos)
3. [Instalación](#instalación)
4. [Ejecución](#ejecución)
5. [Documentación](#documentación)
6. [Testing](#testing)
7. [Estructura del Proyecto](#estructura-del-proyecto)
8. [API Endpoints](#api-endpoints)
9. [Ejemplos de Uso](#ejemplos-de-uso)
10. [Tecnologías](#tecnologías)

---

## ✨ Características

- ✅ **Autenticación JWT** - Login y registro seguro
- ✅ **CRUD Completo** - Gestión de 5 entidades (Clientes, Productos, Pedidos, Entregas, Productores)
- ✅ **Relaciones JPA** - OneToMany y ManyToOne correctamente implementadas
- ✅ **Validaciones** - En modelos y controllers
- ✅ **Swagger/OpenAPI** - Documentación automática interactiva
- ✅ **Manejo de Errores** - GlobalExceptionHandler
- ✅ **CORS Habilitado** - Para frontend en puerto 3000
- ✅ **Tests Unitarios** - Cobertura de services y controllers
- ✅ **H2 Database** - Base de datos en archivo local

---

## 🔧 Requisitos

- **Java 17+** (verificar: `java -version`)
- **Maven 3.8+** (verificar: `mvn -version`)
- **Git** (verificar: `git --version`)
- **VS Code** (recomendado) con extensiones:
  - Extension Pack for Java
  - Spring Boot Extension Pack
  - REST Client (opcional)

---

## 📥 Instalación

### 1. Clonar o descargar el proyecto

```bash
git clone https://github.com/tuusuario/saborlocal.git
cd saborlocal
```

### 2. Descargar dependencias

```bash
./mvnw clean install
```

Si prefieres limpiar el caché:

```bash
./mvnw clean install -U
```

### 3. Verificar la compilación

```bash
./mvnw compile
```

Debería terminar con: `BUILD SUCCESS` ✅

---

## 🚀 Ejecución

### Opción 1: Desde terminal

```bash
./mvnw spring-boot:run
```

### Opción 2: Desde VS Code

1. Abre la paleta de comandos: `Ctrl+Shift+P`
2. Busca: `Java: Spring Boot Dashboard`
3. Haz clic en el botón play (▶️) junto a `saborlocal`

### Verificar que está ejecutándose

Deberías ver en la consola:

```
2024-12-17 10:30:00 - Started SaborlocalApplication in 5.234 seconds
```

Y el servidor estará disponible en: `http://localhost:8081`

---

## 📚 Documentación

### 1. Swagger UI (Interactivo)

Acceso: **http://localhost:8081/swagger-ui.html**

Características:
- Ver todos los endpoints
- Probar endpoints (Try it out)
- Ver modelos y esquemas
- Descargar especificación OpenAPI

### 2. Documentación en Markdown

Ubicados en la raíz del proyecto:

| Documento | Descripción |
|---|---|
| [PLAN_IMPLEMENTACION.md](./PLAN_IMPLEMENTACION.md) | Plan detallado de implementación |
| [RESUMEN_EJECUTIVO.md](./RESUMEN_EJECUTIVO.md) | Resumen ejecutivo del proyecto |
| [DIAGRAMA_ENTIDADES.md](./DIAGRAMA_ENTIDADES.md) | Diagrama ER y relaciones JPA |
| [API_ENDPOINTS.md](./API_ENDPOINTS.md) | Documentación completa de endpoints |
| [GUIA_TESTING.md](./GUIA_TESTING.md) | Guía de pruebas unitarias |
| [CHECKLIST_VALIDACION.md](./CHECKLIST_VALIDACION.md) | Checklist de validación |

---

## 🧪 Testing

### Ejecutar todos los tests

```bash
./mvnw test
```

### Ejecutar tests de una clase específica

```bash
./mvnw test -Dtest=ProductoServiceTest
```

### Ejecutar un método específico

```bash
./mvnw test -Dtest=ProductoServiceTest#testCrearProducto
```

### Generar reporte de cobertura

```bash
./mvnw clean test jacoco:report
```

Reporte disponible en: `target/site/jacoco/index.html`

### Tests implementados

- ProductoServiceTest (7 tests)
- ClienteServiceTest (3 tests)
- PedidoServiceTest (4 tests)
- ProductoControllerTest (6 tests)
- **Total: 20+ tests**

---

## 📁 Estructura del Proyecto

```
saborlocal/
├── src/
│   ├── main/
│   │   ├── java/com/SaborLocalSpa/saborlocal/
│   │   │   ├── config/
│   │   │   │   ├── JwtAuthenticationFilter.java
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── ClienteController.java
│   │   │   │   ├── ProductoController.java
│   │   │   │   ├── ProductorController.java
│   │   │   │   ├── PedidoController.java
│   │   │   │   └── EntregaController.java
│   │   │   ├── dto/
│   │   │   │   ├── LoginRequest.java
│   │   │   │   ├── LoginResponse.java
│   │   │   │   └── RegisterRequest.java
│   │   │   ├── exception/
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   └── NotFoundException.java
│   │   │   ├── model/
│   │   │   │   ├── Cliente.java
│   │   │   │   ├── Producto.java
│   │   │   │   ├── Productor.java
│   │   │   │   ├── Pedido.java
│   │   │   │   └── Entrega.java
│   │   │   ├── repository/
│   │   │   │   ├── ClienteRepository.java
│   │   │   │   ├── ProductoRepository.java
│   │   │   │   ├── ProductorRepository.java
│   │   │   │   ├── PedidoRepository.java
│   │   │   │   └── EntregaRepository.java
│   │   │   ├── security/
│   │   │   │   ├── JwtUtil.java
│   │   │   │   ├── CustomUserDetails.java
│   │   │   │   └── CustomUserDetailsService.java
│   │   │   ├── service/
│   │   │   │   ├── ClienteService.java
│   │   │   │   ├── ProductoService.java
│   │   │   │   ├── ProductorService.java
│   │   │   │   ├── PedidoService.java
│   │   │   │   └── EntregaService.java
│   │   │   └── SaborlocalApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/SaborLocalSpa/saborlocal/
│           ├── service/
│           └── controller/
├── data/                          # Base de datos H2
│   └── saborlocal.mv.db
├── target/                        # Archivos compilados
├── pom.xml                        # Dependencias Maven
├── mvnw & mvnw.cmd               # Maven Wrapper
└── [DOCUMENTACIÓN](./README.md)   # Este archivo
```

---

## 🔌 API Endpoints

### Autenticación (sin protección)

```
POST   /auth/login          → Login
POST   /auth/register       → Registro
```

### Productos (sin protección - GET)

```
GET    /api/productos       → Listar todos
GET    /api/productos/{id}  → Obtener por ID
GET    /api/productos/search?q=... → Buscar
POST   /api/productos       → Crear (protegido)
PUT    /api/productos/{id}  → Actualizar (protegido)
DELETE /api/productos/{id}  → Eliminar (protegido)
```

### Productores (sin protección - GET)

```
GET    /api/productores          → Listar todos
GET    /api/productores/{id}     → Obtener por ID
POST   /api/productores          → Crear (protegido)
PUT    /api/productores/{id}     → Actualizar (protegido)
DELETE /api/productores/{id}     → Eliminar (protegido)
```

### Clientes (protegido)

```
GET    /api/clientes          → Listar
GET    /api/clientes/{id}     → Obtener por ID
POST   /api/clientes          → Crear
PUT    /api/clientes/{id}     → Actualizar
DELETE /api/clientes/{id}     → Eliminar
```

### Pedidos (protegido)

```
GET    /api/pedidos                    → Listar
GET    /api/pedidos/{id}               → Obtener por ID
POST   /api/pedidos                    → Crear
PUT    /api/pedidos/{id}               → Actualizar
DELETE /api/pedidos/{id}               → Eliminar
GET    /api/pedidos/cliente/{id}       → Pedidos de cliente
PATCH  /api/pedidos/{id}/estado        → Cambiar estado
```

### Entregas (protegido)

```
GET    /api/entregas                   → Listar
GET    /api/entregas/{id}              → Obtener por ID
POST   /api/entregas                   → Crear
PUT    /api/entregas/{id}              → Actualizar
DELETE /api/entregas/{id}              → Eliminar
GET    /api/entregas/pedido/{id}       → Entregas de pedido
PATCH  /api/entregas/{id}/estado       → Cambiar estado
```

---

## 💡 Ejemplos de Uso

### 1. Registrar usuario

```bash
curl -X POST http://localhost:8081/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Pérez",
    "email": "juan@example.com",
    "password": "password123",
    "telefono": "3101234567",
    "direccion": "Calle 1, Apt 1"
  }'
```

**Respuesta (201 Created):**
```json
{
  "id": 1,
  "nombre": "Juan Pérez",
  "email": "juan@example.com",
  "rol": "USER"
}
```

### 2. Login

```bash
curl -X POST http://localhost:8081/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "juan@example.com",
    "password": "password123"
  }'
```

**Respuesta (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "email": "juan@example.com",
  "nombre": "Juan Pérez",
  "rol": "USER"
}
```

### 3. Obtener productos (sin autenticación)

```bash
curl -X GET http://localhost:8081/api/productos
```

### 4. Crear pedido (con autenticación)

```bash
curl -X POST http://localhost:8081/api/pedidos \
  -H "Authorization: Bearer {JWT_TOKEN}" \
  -H "Content-Type: application/json" \
  -d '{
    "cliente": { "id": 1 },
    "producto": { "id": 1 },
    "cantidad": 5,
    "total": 27.50
  }'
```

### 5. Cambiar estado de entrega

```bash
curl -X PATCH http://localhost:8081/api/entregas/1/estado \
  -H "Authorization: Bearer {JWT_TOKEN}" \
  -H "Content-Type: application/json" \
  -d '{
    "nuevoEstado": "ENTREGADO"
  }'
```

---

## 🛠️ Tecnologías

### Backend
- **Java 17** - Lenguaje de programación
- **Spring Boot 3.5.7** - Framework principal
- **Spring Security** - Autenticación y autorización
- **JWT (JJWT)** - Tokens de seguridad
- **Spring Data JPA** - Persistencia de datos
- **H2 Database** - Base de datos embebida

### Documentación
- **Springdoc OpenAPI** - Swagger/OpenAPI automático
- **Lombok** - Anotaciones para reducir boilerplate

### Testing
- **JUnit 5** - Framework de testing
- **Mockito** - Mocking de dependencias
- **Spring Boot Test** - Testing integrado

### Build
- **Maven 3.8+** - Gestor de dependencias
- **JaCoCo** - Cobertura de código

---

## 📝 Configuración

### application.properties

```properties
# Puerto
server.port=8081

# Base de datos
spring.datasource.url=jdbc:h2:file:./data/saborlocal
spring.jpa.hibernate.ddl-auto=update

# JWT
jwt.secret=miClaveSuperSegura12345678
jwt.expiration=86400000

# Swagger
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.enabled=true
```

---

## 🐛 Solución de Problemas

### Error: "Port 8081 is already in use"

```bash
# Windows
netstat -ano | findstr :8081
taskkill /PID {PID} /F

# Linux/Mac
lsof -i :8081
kill -9 {PID}
```

### Error: "Cannot connect to database"

```bash
# Verificar si el archivo de base de datos está corrupto
rm -rf ./data/

# Reiniciar la aplicación
./mvnw spring-boot:run
```

### Error: "Invalid JWT token"

- Verifica que el token no ha expirado
- Verifica que la clave secreta en application.properties es correcta
- Obtén un nuevo token con `/auth/login`

---

## 🚀 Deployment

### Crear JAR ejecutable

```bash
./mvnw clean package
```

JAR estará en: `target/saborlocal-0.0.1-SNAPSHOT.jar`

### Ejecutar JAR

```bash
java -jar target/saborlocal-0.0.1-SNAPSHOT.jar
```

### Variables de entorno para producción

```bash
export SERVER_PORT=8080
export JWT_SECRET=tu_clave_super_segura
export DATABASE_URL=jdbc:mysql://host:3306/saborlocal
export DATABASE_USER=root
export DATABASE_PASSWORD=password

java -jar saborlocal-0.0.1-SNAPSHOT.jar
```

---

## 📞 Contacto y Soporte

- **Documentación**: Ver archivos `.md` en la raíz
- **Issues**: Reportar en GitHub Issues
- **Email**: soporte@saborlocal.com

---

## 📄 Licencia

Este proyecto es privado. Todos los derechos reservados © 2024 SaborLocal.

---

## ✅ Checklist Rápido

- [ ] Java 17 instalado
- [ ] Maven configurado
- [ ] Proyecto clonado
- [ ] `./mvnw clean install` ejecutado
- [ ] `./mvnw spring-boot:run` funcionando
- [ ] Swagger accesible en http://localhost:8081/swagger-ui.html
- [ ] Tests pasando: `./mvnw test`
- [ ] Base de datos creada en `./data/`

---

**Última actualización:** 17/12/2025  
**Versión:** 1.0.0  
**Estado:** Production Ready ✅


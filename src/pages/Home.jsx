import { Container, Row, Col, Card, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const Home = () => (
  <main>
    <Container className="mt-5">
      {/* Hero */}
      <section className="section-hero mb-5">
        <div>
          <h1 className="display-4 fw-bold" style={{ color: '#d2691e' }}>
            Bienvenidos a SaborLocal
          </h1>
          <p className="lead" style={{ color: '#2f4f2f' }}>
            Vive la experiencia de consumir productos de gran calidad y sabor.
            Apoya a emprendedores rurales adquiriendo productos de temporada y libres de conservantes.
          </p>
          <p className="h5" style={{ color: '#22543d' }}>
            ¡Descubre, saborea y comparte el verdadero sabor local!
          </p>

          <Button
            as={Link}
            to="/login"
            variant="success"
            className="mt-3"
          >
            Iniciar sesión
          </Button>
        </div>
        {/* ... resto de la sección hero y cards ... */}
      </section>
    </Container>
  </main>
);

export default Home;


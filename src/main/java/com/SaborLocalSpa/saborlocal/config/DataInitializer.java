package com.SaborLocalSpa.saborlocal.config;

import com.SaborLocalSpa.saborlocal.model.*;
import com.SaborLocalSpa.saborlocal.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ClienteRepository clienteRepository;
    private final ProductorRepository productorRepository;
    private final ProductoRepository productoRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(ClienteRepository clienteRepository, 
                            ProductorRepository productorRepository,
                            ProductoRepository productoRepository,
                            PasswordEncoder passwordEncoder) {
        this.clienteRepository = clienteRepository;
        this.productorRepository = productorRepository;
        this.productoRepository = productoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Si admin existe, eliminarlo y recrearlo con password correcto
        var adminOpt = clienteRepository.findByEmail("admin@test.com");
        if (adminOpt.isPresent()) {
            clienteRepository.delete(adminOpt.get());
            System.out.println("✅ Admin anterior eliminado");
        }

        // Crear admin con password correcto
        Cliente admin = new Cliente();
        admin.setNombre("Admin User");
        admin.setEmail("admin@test.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRol("ADMIN");
        admin.setTelefono("555-9999");
        admin.setDireccion("Calle Admin 123");
        clienteRepository.save(admin);
        System.out.println("✅ Admin creado: admin@test.com / admin123");

        // Si BD tiene otros datos, no hacer más
        if (clienteRepository.count() > 1) {
            return;
        }

        // Crear usuario regular
        Cliente usuario = new Cliente();
        usuario.setNombre("Cliente Prueba");
        usuario.setEmail("cliente@test.com");
        usuario.setPassword(passwordEncoder.encode("cliente123"));
        usuario.setRol("USER");
        usuario.setTelefono("555-1234");
        usuario.setDireccion("Calle Cliente 456");
        clienteRepository.save(usuario);

        // Crear productores
        Productor panaderia = new Productor();
        panaderia.setNombre("Panadería El Pan");
        panaderia.setEmail("panaderia@test.com");
        panaderia.setTelefono("555-0001");
        panaderia.setEmpresa("Panadería El Pan SPA");
        panaderia.setActivo(true);
        productorRepository.save(panaderia);

        Productor cafe = new Productor();
        cafe.setNombre("Café Premium");
        cafe.setEmail("cafe@test.com");
        cafe.setTelefono("555-0002");
        cafe.setEmpresa("Café Premium SPA");
        cafe.setActivo(true);
        productorRepository.save(cafe);

        Productor lacteos = new Productor();
        lacteos.setNombre("Lacteos Frescos");
        lacteos.setEmail("lacteos@test.com");
        lacteos.setTelefono("555-0003");
        lacteos.setEmpresa("Lacteos Frescos SPA");
        lacteos.setActivo(true);
        productorRepository.save(lacteos);

        // Crear productos
        Producto pan = new Producto();
        pan.setNombre("Pan Integral");
        pan.setDescripcion("Pan integral casero");
        pan.setCategoria("Panaderia");
        pan.setPrecio(new BigDecimal("2.50"));
        pan.setStock(50);
        pan.setDisponible(true);
        pan.setProductor(panaderia);
        productoRepository.save(pan);

        Producto cafe_molido = new Producto();
        cafe_molido.setNombre("Cafe Molido");
        cafe_molido.setDescripcion("Cafe molido premium");
        cafe_molido.setCategoria("Cafe");
        cafe_molido.setPrecio(new BigDecimal("5.99"));
        cafe_molido.setStock(30);
        cafe_molido.setDisponible(true);
        cafe_molido.setProductor(cafe);
        productoRepository.save(cafe_molido);

        Producto leche = new Producto();
        leche.setNombre("Leche Fresca");
        leche.setDescripcion("Leche fresca de vaca");
        leche.setCategoria("Lacteos");
        leche.setPrecio(new BigDecimal("1.50"));
        leche.setStock(100);
        leche.setDisponible(true);
        leche.setProductor(lacteos);
        productoRepository.save(leche);

        Producto queso = new Producto();
        queso.setNombre("Queso Fresco");
        queso.setDescripcion("Queso casero artesanal");
        queso.setCategoria("Lacteos");
        queso.setPrecio(new BigDecimal("8.99"));
        queso.setStock(20);
        queso.setDisponible(true);
        queso.setProductor(lacteos);
        productoRepository.save(queso);

        System.out.println("✅ Base de datos inicializada con datos de prueba");
        System.out.println("📝 Admin: admin@test.com / admin123");
        System.out.println("📝 Usuario: cliente@test.com / cliente123");
    }
}

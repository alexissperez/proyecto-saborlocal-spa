// ...existing code...
// Lista de productos de ejemplo para tu catálogo local de SaborLocal
const products = [
  {
    id: 1,
    nombre: "Queso Fresco",
    categoria: "Lácteos",
    precio: 2500,
    productor: "Granja Los Andes",
    imagen: "/images/products/queso-fresco.png"
  },

  {
    id: 2,
    nombre: "Mermelada de Frutilla",
    categoria: "Conservas",
    precio: 1800,
    productor: "Frutos del Valle",
    imagen: "/images/products/mermelada-frutilla.png"
  },

  {
    id: 3,
    nombre: "Pan masa madre",
    categoria: "Panadería",
    precio: 1200,
    productor: "Panadería El Pueblo",
    imagen: "/images/products/pan-masa-madre.png"
  },
  
  {
    id: 4,
    nombre: "Mermelada de Frambuesa",
    categoria: "Conservas",
    precio: 1800,
    productor: "Frutos del Valle",
    imagen: "/images/products/mermelada-frambuesa.png"
  },
];

// Hacemos disponible la lista para importarla en otros componentes
export default products;
// ...existing code...
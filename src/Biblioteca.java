import java.util.ArrayList;

class Biblioteca {
    private String nombre;
    private String registrobiblioteca;
    private ArrayList<Libro> libros;

    public Biblioteca(String nombre, String registrobiblioteca) {
        this.nombre = nombre;
        this.registrobiblioteca = registrobiblioteca;
        this.libros = new ArrayList<>();
    }

    public void añadirLibro(Libro libro) {
        libros.add(libro);
    }

    public void mostrarLibros() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros en la biblioteca.");
        } else {
            for (Libro libro : libros) {
                libro.mostrarInfo();
            }
        }
    }
}
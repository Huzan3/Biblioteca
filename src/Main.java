public class Main {
    public static void main(String[] args) {
        // Crear libros
        Libro libro1 = new Libro("Cien años de soledad", "978-3-16-148410-0", "Gabriel García Márquez", 1967, "Ficción");
        Libro libro2 = new Libro("Don Quijote de la Mancha", "978-0-14-243723-0", "Miguel de Cervantes", 1605, "Ficción");

        Biblioteca biblioteca = new Biblioteca("Biblioteca Nacional", "12345");

        biblioteca.añadirLibro(libro1);
        biblioteca.añadirLibro(libro2);

        biblioteca.mostrarLibros();
    }
}
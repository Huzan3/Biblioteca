import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {
    private String nombre;
    private List<Libro> registroBiblioteca;

    public Biblioteca() {
    }

    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.registroBiblioteca = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        registroBiblioteca.add(libro);
        System.out.println(" Libro añadido: " + libro);
    }

    public void mostrarLibros() {
        System.out.println("\n === LIBROS EN '" + nombre + "' === ");
        if (registroBiblioteca.isEmpty()) {
            System.out.println(" No hay libros aún.");
        } else {
            System.out.println("Total: " + registroBiblioteca.size() + " libros\n");
            for (Libro libro : registroBiblioteca) {
                System.out.println(libro);
                System.out.println("─".repeat(50));
            }
        }
        System.out.println();
    }

    public void agregarLibroInteractivo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("ISBN: ");
        String isbn = sc.nextLine();
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        System.out.print("Año: ");
        int año = sc.nextInt();
        sc.nextLine(); // limpiar buffer
        System.out.print("Tipo: ");
        String tipo = sc.nextLine();

        Libro nuevo = new Libro(titulo, isbn, autor, año, tipo);
        agregarLibro(nuevo);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Biblioteca biblio = new Biblioteca("Biblioteca Grok ⚡");

        // Ejemplos iniciales
        biblio.agregarLibro(new Libro("El Quijote", "978-84-663-XXXX-X", "Cervantes", 1605, "Clásico"));
        biblio.agregarLibro(new Libro("1984", "978-0-452-28423-4", "Orwell", 1949, "Distopía"));

        int opcion;
        do {
            System.out.println("MENÚ BIBLIOTECA");
            System.out.println("1. Añadir libro");
            System.out.println("2. Mostrar todos");
            System.out.println("3. Salir");
            System.out.print("Elige: ");
            opcion = sc.nextInt();
            sc.nextLine(); // buffer

            switch (opcion) {
                case 1:
                    biblio.agregarLibroInteractivo();
                    break;
                case 2:
                    biblio.mostrarLibros();
                    break;
                case 3:
                    System.out.println("¡Adiós!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 3);

        sc.close();
    }
}
// git add .
// git status
// git commit -m'cambios dell biblioteca'
// git push
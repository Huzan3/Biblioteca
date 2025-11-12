import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class Biblioteca {
    private String nombre;
    private List<Libro> registroBiblioteca;

    // Constructor
    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.registroBiblioteca = new ArrayList<>();
    }

    // Añadir libro
    public void agregarLibro(Libro libro) {
        registroBiblioteca.add(libro);
        System.out.println(" Libro añadido: " + libro);
    }

    // Mostrar todos
    public void mostrarLibros() {
        System.out.println("\n === LIBROS EN '" + nombre + "' === ");
        if (registroBiblioteca.isEmpty()) {
            System.out.println(" No hay libros aún.");
        } else {
            System.out.println("Total: " + registroBiblioteca.size() + " libros\n");
            for (int i = 0; i < registroBiblioteca.size(); i++) {
                System.out.println((i + 1) + ". " + registroBiblioteca.get(i));
                System.out.println("─".repeat(50));
            }
        }
        System.out.println();
    }

    // NUEVO: Buscar libros (por título, autor o ISBN)
    public void buscarLibros(String termino) {
        System.out.println("\n RESULTADOS PARA: '" + termino + "'");
        boolean encontrado = false;
        for (int i = 0; i < registroBiblioteca.size(); i++) {
            Libro libro = registroBiblioteca.get(i);
            if (libro.getTitulo().toLowerCase().contains(termino.toLowerCase()) ||
                    libro.getAutor().toLowerCase().contains(termino.toLowerCase()) ||
                    libro.getIsbn().toLowerCase().contains(termino.toLowerCase())) {
                System.out.println((i + 1) + ". " + libro);
                System.out.println("─".repeat(50));
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron libros.");
        }
        System.out.println();
    }

    // NUEVO: Eliminar libro (por índice, 1-based)
    public void eliminarLibro(int indice) {
        if (indice < 1 || indice > registroBiblioteca.size()) {
            System.out.println("Índice inválido.");
            return;
        }
        Libro eliminado = registroBiblioteca.remove(indice - 1);
        System.out.println("Libro eliminado: " + eliminado);
    }

    // NUEVO: Guardar en archivo (simple TXT)
    public void guardarEnArchivo(String archivo) {
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write("Biblioteca: " + nombre + "\n");
            writer.write("Total libros: " + registroBiblioteca.size() + "\n\n");
            for (Libro libro : registroBiblioteca) {
                writer.write(libro.toString() + "\n");
                writer.write("---\n");
            }
            System.out.println("Guardado en '" + archivo + "' exitosamente!");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    // Añadir interactivo
    public void agregarLibroInteractivo(Scanner sc) {
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("ISBN: ");
        String isbn = sc.nextLine();
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        System.out.print("Año: ");
        int año = Integer.parseInt(sc.nextLine());
        System.out.print("Tipo: ");
        String tipo = sc.nextLine();

        Libro nuevo = new Libro(titulo, isbn, autor, año, tipo);
        agregarLibro(nuevo);
    }

    // ¡MENÚ PRINCIPAL INTERACTIVO ACTUALIZADO!
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Biblioteca biblio = new Biblioteca("Biblioteca Grok ⚡");

        // Ejemplos iniciales
        biblio.agregarLibro(new Libro("El Quijote", "978-84-663-XXXX-X", "Cervantes", 1605, "Clásico"));
        biblio.agregarLibro(new Libro("1984", "978-0-452-28423-4", "Orwell", 1949, "Distopía"));
        biblio.agregarLibro(new Libro("Clean Code", "978-0-13-235088-4", "Uncle Bob", 2008, "Técnico"));

        int opcion;
        do {
            System.out.println("MENÚ BIBLIOTECA");
            System.out.println("1. Añadir libro");
            System.out.println("2. Mostrar todos");
            System.out.println("3. Buscar libros");
            System.out.println("4. Eliminar libro");
            System.out.println("5. Guardar en archivo");
            System.out.println("6. Salir");
            System.out.print("Elige: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    biblio.agregarLibroInteractivo(sc);
                    break;
                case 2:
                    biblio.mostrarLibros();
                    break;
                case 3:
                    System.out.print("Término de búsqueda: ");
                    String termino = sc.nextLine();
                    biblio.buscarLibros(termino);
                    break;
                case 4:
                    biblio.mostrarLibros(); // Mostrar para elegir
                    System.out.print("Índice a eliminar: ");
                    int indice = Integer.parseInt(sc.nextLine());
                    biblio.eliminarLibro(indice);
                    break;
                case 5:
                    System.out.print("Nombre del archivo (ej: biblio.txt): ");
                    String archivo = sc.nextLine();
                    biblio.guardarEnArchivo(archivo);
                    break;
                case 6:
                    System.out.println("¡Adiós!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 6);

        sc.close();
    }
}
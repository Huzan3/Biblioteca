import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class Biblioteca {
    private String nombre;
    private List<Libro> registroBiblioteca;
    private static final String ARCHIVO_PERSISTENTE = "biblioteca.txt";

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

    // Buscar libros (por título, autor o ISBN)
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
            System.out.println(" No se encontraron libros.");
        }
        System.out.println();
    }

    // Eliminar libro (por índice, 1-based)
    public void eliminarLibro(int indice) {
        if (indice < 1 || indice > registroBiblioteca.size()) {
            System.out.println(" Índice inválido.");
            return;
        }
        Libro eliminado = registroBiblioteca.remove(indice - 1);
        System.out.println("🗑 Libro eliminado: " + eliminado);
    }

    // Guardar en archivo (siempre el mismo)
    public void guardarEnArchivo() {
        try (FileWriter writer = new FileWriter(ARCHIVO_PERSISTENTE)) {
            writer.write("Biblioteca: " + nombre + "\n");
            writer.write("Total libros: " + registroBiblioteca.size() + "\n\n");
            for (Libro libro : registroBiblioteca) {
                writer.write(libro.toString() + "\n");
                writer.write("---\n");
            }
            System.out.println(" Guardado en '" + ARCHIVO_PERSISTENTE + "' exitosamente!");
        } catch (IOException e) {
            System.out.println(" Error al guardar: " + e.getMessage());
        }
    }

    // Cargar desde archivo (siempre el mismo)
    public void cargarDesdeArchivo() {
        try (Scanner fileSc = new Scanner(new File(ARCHIVO_PERSISTENTE))) {
            // Saltar primera línea (Biblioteca:)
            if (fileSc.hasNextLine()) fileSc.nextLine();
            // Saltar segunda (Total libros:)
            if (fileSc.hasNextLine()) fileSc.nextLine();
            // Saltar línea vacía si hay
            if (fileSc.hasNextLine() && fileSc.nextLine().trim().isEmpty()) {
                // ya saltada
            }

            while (fileSc.hasNextLine()) {
                String line = fileSc.nextLine().trim();
                if (line.isEmpty() || !line.startsWith("📖")) continue;

                // Parsear la línea
                String[] parts = line.split("\\|");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }

                String titulo = parts[0].substring(2).trim(); // Después de 📖
                String isbn = parts[1].substring("ISBN: ".length()).trim();
                String autor = parts[2].substring("Autor: ".length()).trim();
                String añoStr = parts[3].substring("Año: ".length()).trim();
                int año = Integer.parseInt(añoStr);
                String tipo = parts[4].substring("Tipo: ".length()).trim();

                Libro libro = new Libro(titulo, isbn, autor, año, tipo);
                registroBiblioteca.add(libro);

                // Saltar la línea --- si hay
                if (fileSc.hasNextLine()) fileSc.nextLine();
            }
            System.out.println(" Cargados " + registroBiblioteca.size() + " libros desde '" + ARCHIVO_PERSISTENTE + "'.");
        } catch (FileNotFoundException e) {
            System.out.println(" Archivo no encontrado. Iniciando con ejemplos iniciales.");
            // Añadir ejemplos iniciales solo la primera vez
            agregarLibro(new Libro("El Quijote", "978-84-663-XXXX-X", "Cervantes", 1605, "Clásico"));
            agregarLibro(new Libro("1984", "978-0-452-28423-4", "Orwell", 1949, "Distopía"));
            agregarLibro(new Libro("Clean Code", "978-0-13-235088-4", "Uncle Bob", 2008, "Técnico"));
            guardarEnArchivo(); // Guardar iniciales
        } catch (Exception e) {
            System.out.println(" Error al cargar: " + e.getMessage());
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

    // ¡MENÚ PRINCIPAL INTERACTIVO!
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Biblioteca biblio = new Biblioteca("Biblioteca Grok ⚡");

        // Cargar al inicio
        biblio.cargarDesdeArchivo();

        int opcion;
        do {
            System.out.println("🔥 MENÚ BIBLIOTECA");
            System.out.println("1. Añadir libro");
            System.out.println("2. Mostrar todos");
            System.out.println("3. Buscar libros");
            System.out.println("4. Eliminar libro");
            System.out.println("5. Guardar cambios ahora");
            System.out.println("6. Salir (guarda automáticamente)");
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
                    biblio.guardarEnArchivo();
                    break;
                case 6:
                    biblio.guardarEnArchivo(); // Auto-guardar al salir
                    System.out.println("👋 ¡Adiós!");
                    break;
                default:
                    System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 6);

        sc.close();
    }
}
public class Libro {
    private String titulo;
    private String isbn;
    private String autor;
    private int año;
    private String tipoLibro;

    // Constructor
    public Libro(String titulo, String isbn, String autor, int año, String tipoLibro) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.año = año;
        this.tipoLibro = tipoLibro;
    }

    // toString para mostrar fácilmente
    @Override
    public String toString() {
        return "" + titulo +
                " | ISBN: " + isbn +
                " | Autor: " + autor +
                " | Año: " + año +
                " | Tipo: " + tipoLibro;
    }
}
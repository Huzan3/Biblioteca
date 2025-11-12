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

    // Getters (para búsquedas y más)
    public String getTitulo() { return titulo; }
    public String getIsbn() { return isbn; }
    public String getAutor() { return autor; }
    public int getAño() { return año; }
    public String getTipoLibro() { return tipoLibro; }

    // toString para mostrar fácilmente
    @Override
    public String toString() {
        return "📖 " + titulo +
                " | ISBN: " + isbn +
                " | Autor: " + autor +
                " | Año: " + año +
                " | Tipo: " + tipoLibro;
    }
}
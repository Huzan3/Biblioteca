public class Libro {
    private String titulo;
    private String isbn;
    private String autor;
    private int año;
    private String tipolibro;

    public Libro() {
    }

    public Libro(String isbn, String titulo, String autor, int año, String tipolibro) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.año = año;
        this.tipolibro = tipolibro;
    }
    public void mostrarInfo() {
        System.out.println("Titulo: " + titulo + ", ISBN: " + isbn + ", Autor: " + autor +
                ", Año: " + año + ", Tipo de libro: " + tipolibro);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getTipolibro() {
        return tipolibro;
    }

    public void setTipolibro(String tipolibro) {
        this.tipolibro = tipolibro;
    }
}

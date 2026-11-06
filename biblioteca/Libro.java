import java.util.ArrayList;

/**
 * Representa un libro de la biblioteca.
 * Permite almacenar los datos del libro y su historial
 * de prestamos.
 *
 * @author Ivar Tobias Kaczuba
 * @version 1.0
 */
public class Libro {
    private String titulo;
    private int edicion;
    private String editorial;
    private int anio;
    private ArrayList<Prestamo> prestamos;

    /**
     * Constructor que inicializa el libro sin prestamos.
     */
    public Libro(String p_titulo, int p_edicion, String p_editorial, int p_anio) {
        this.setTitulo(p_titulo);
        this.setEdicion(p_edicion);
        this.setEditorial(p_editorial);
        this.setAnio(p_anio);
        this.setPrestamo(new ArrayList<Prestamo>());
    }

    /**
     * Constructor que recibe una coleccion de prestamos.
     */
    public Libro(String p_titulo, int p_edicion, String p_editorial, int p_anio, ArrayList<Prestamo> p_prestamos) {
        this.setTitulo(p_titulo);
        this.setEdicion(p_edicion);
        this.setEditorial(p_editorial);
        this.setAnio(p_anio);
        this.setPrestamo(p_prestamos);
    }

    // Setters

    private void setTitulo(String p_titulo) {
        this.titulo = p_titulo;
    }

    private void setEdicion(int p_edicion) {
        this.edicion = p_edicion;
    }

    private void setEditorial(String p_editorial) {
        this.editorial = p_editorial;
    }

    private void setAnio(int p_anio) {
        this.anio = p_anio;
    }

    private void setPrestamo(ArrayList<Prestamo> p_prestamos) {
        this.prestamos = p_prestamos;
    }

    // Getters

    public String getTitulo() {
        return this.titulo;
    }

    public int getEdicion() {
        return this.edicion;
    }

    public String getEditorial() {
        return this.editorial;
    }

    public int getAnio() {
        return this.anio;
    }

    public ArrayList<Prestamo> getPrestamo() {
        return this.prestamos;
    }

    /**
     * Agrega un prestamo al historial del libro.
     *
     * @param p_prestamo prestamo a registrar
     * @return true si se agrega correctamente
     */
    public boolean agregarPrestamo(Prestamo p_prestamo) {
        return this.getPrestamo().add(p_prestamo);
    }

    /**
     * Retorna el ultimo prestamo registrado.
     *
     * @return ultimo prestamo o null si no existen prestamos
     */
    public Prestamo ultimoPrestamo() {
        if (this.getPrestamo().isEmpty()) {
            return null;
        }

        return this.getPrestamo().get(this.getPrestamo().size() - 1);
    }

    /**
     * Verifica si el libro se encuentra prestado.
     *
     * @return true si el libro esta prestado
     */
    public boolean prestado() {
        Prestamo ultimo = this.ultimoPrestamo();

        if (ultimo != null) {
            return ultimo.getFechaDevolucion() == null;
        }

        return false;
    }

    /**
     * Retorna la descripcion del libro.
     *
     * @return titulo del libro
     */
    @Override
    public String toString() {
        return "Titulo: " + this.getTitulo();
    }
}

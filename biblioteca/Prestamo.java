
import java.util.Calendar;

/**
 * Representa un préstamo de un libro realizado por un socio.
 * @author Luque Tourn Emmanuel
 */
public class Prestamo {
    private Calendar fechaRetiro;
    private Calendar fechaDevolucion;
    private Libro libro;
    private Socio socio;

    /**
     * Crea un préstamo y establece su fecha de retiro, libro y socio.
     * La fecha de devolución se inicializa en null.
     *
     * @param p_fechaRetiro fecha en la que se retira el libro
     * @param p_libro libro prestado
     * @param p_socio socio que realiza el préstamo
     */
    public Prestamo(Calendar p_fechaRetiro, Libro p_libro, Socio p_socio) {
        this.setFechaRetiro(p_fechaRetiro);
        this.setLibro(p_libro);
        this.setSocio(p_socio);
        this.setFechaDevolucion(null);
    }

    /** Establece la fecha de retiro del libro. */
    private void setFechaRetiro(Calendar p_fechaRetiro) {
        this.fechaRetiro = p_fechaRetiro;
    }

    /** Establece el libro prestado. */
    private void setLibro(Libro p_libro) {
        this.libro = p_libro;
    }

    /** Establece el socio que realiza el préstamo. */
    private void setSocio(Socio p_socio) {
        this.socio = p_socio;
    }

    /** Establece la fecha de devolución. */
    private void setFechaDevolucion(Calendar p_fecha) {
        this.fechaDevolucion = p_fecha;
    }

    /** @return fecha en la que se retiró el libro */
    public Calendar getFechaRetiro() {
        return this.fechaRetiro;
    }

    /** @return libro prestado */
    public Libro getLibro() {
        return this.libro;
    }

    /** @return socio que realizó el préstamo */
    public Socio getSocio() {
        return this.socio;
    }

    /** @return fecha de devolución o null si aún no se devolvió */
    public Calendar getFechaDevolucion() {
        return this.fechaDevolucion;
    }

    /**
     * Registra la fecha en la que se devuelve el libro.
     * @param p_fecha fecha de devolución
     */
    public void registrarFechaDevolucion(Calendar p_fecha) {
        this.setFechaDevolucion(p_fecha);
    }

    /**
     * Comprueba si el préstamo está vencido en una fecha determinada.
     * @param p_fechaActual fecha en la que se verifica el vencimiento
     * @return true si el préstamo está vencido, false en caso contrario
     */
    public boolean vencido(Calendar p_fechaActual) {
        if (this.getFechaDevolucion() != null) {
            return false;
        }

        Calendar fechaVencimiento = (Calendar) this.getFechaRetiro().clone();
        fechaVencimiento.add(Calendar.DAY_OF_YEAR, this.getSocio().getDiasPrestamo());
        return p_fechaActual.after(fechaVencimiento);
    }

    /**
     * Devuelve una descripción del préstamo, incluyendo las fechas,
     * el libro y el socio correspondiente.
     * @return información del préstamo en formato de texto
     */
    @Override
    public String toString() {
        String retiro = String.format("%1$tY/%1$tm/%1$td", this.getFechaRetiro());
        String devolucion;

        if (this.getFechaDevolucion() == null) {
            devolucion = "Pendiente";
        } else {
            devolucion = String.format("%1$tY/%1$tm/%1$td", this.getFechaDevolucion());
        }

        return "Retiro: " + retiro + " - Devolución: " + devolucion + "\nLibro: " + this.getLibro().getTitulo() + "\nSocio: " + this.getSocio().getNombre();
    }
}

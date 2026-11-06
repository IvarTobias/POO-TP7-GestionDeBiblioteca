
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Representa un socio de la biblioteca.
 * Es una clase abstracta que sirve como base para otros tipos de socios.
 *
 * @author Luque Tourn Emmanuel
 * @version 1.2
 */
public abstract class Socio {
    private int dniSocio;
    private String nombre;
    private int diasPrestamo;
    private ArrayList<Prestamo> prestamos;

    /**
     * Crea un socio con una lista de préstamos existente.
     *
     * @param p_dniSocio DNI del socio
     * @param p_nombre nombre del socio
     * @param p_diasPrestamo cantidad de días permitidos para los préstamos
     * @param p_prestamos lista de préstamos del socio
     */
    public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo, ArrayList<Prestamo> p_prestamos) {
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
        this.setPrestamos(p_prestamos);
    }

    /**
     * Crea un socio sin préstamos registrados.
     *
     * @param p_dniSocio DNI del socio
     * @param p_nombre nombre del socio
     * @param p_diasPrestamo cantidad de días permitidos para los préstamos
     */
    public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo) {
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
        this.setPrestamos(new ArrayList<Prestamo>());
    }

    /** Establece el DNI del socio. */
    public void setDniSocio(int p_dniSocio) {
        this.dniSocio = p_dniSocio;
    }

    /** Establece el nombre del socio. */
    public void setNombre(String p_nombre) {
        this.nombre = p_nombre;
    }

    /** Establece los días permitidos para los préstamos. */
    public void setDiasPrestamo(int p_diasPrestamo) {
        this.diasPrestamo = p_diasPrestamo;
    }

    /** @return DNI del socio */
    public int getDniSocio() {
        return dniSocio;
    }

    /** @return nombre del socio */
    public String getNombre() {
        return nombre;
    }

    /** @return cantidad de días permitidos para los préstamos */
    public int getDiasPrestamo() {
        return diasPrestamo;
    }

    /** @return lista de préstamos del socio */
    public ArrayList<Prestamo> getPrestamos() {
        return prestamos;
    }

    /** Establece la lista de préstamos del socio. */
    public void setPrestamos(ArrayList<Prestamo> p_prestamos) {
        this.prestamos = p_prestamos;
    }

    /**
     * Agrega un préstamo a la lista del socio.
     *
     * @param p_prestamo préstamo que se desea agregar
     * @return true si se agregó correctamente
     */
    public boolean agregarPrestamo(Prestamo p_prestamo) {
        return this.prestamos.add(p_prestamo);
    }

    /**
     * Quita un préstamo de la lista del socio.
     *
     * @param p_prestamo préstamo que se desea quitar
     * @return true si se eliminó el préstamo
     */
    public boolean quitarPrestamo(Prestamo p_prestamo) {
        return this.prestamos.remove(p_prestamo);
    }

    /**
     * Cuenta los préstamos registrados del socio.
     * @return cantidad de préstamos
     */
    public int cantLibrosPrestados() {
        return this.getPrestamos().size();
    }

    /**
     * Comprueba si el socio puede solicitar un nuevo préstamo.
     * @return false si tiene algún préstamo vencido, true en caso contrario
     */
    public boolean puedePedir() {
        Calendar hoy = Calendar.getInstance();
        for (Prestamo prestamo : this.prestamos) {
            if (prestamo.vencido(hoy)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Devuelve una descripción del socio y sus préstamos.
     * @return texto con el DNI, nombre, tipo de socio y cantidad de préstamos
     */
    @Override
    public String toString() {
        return "D.N.I.: " + this.getDniSocio() + " || " + this.getNombre() + " (" + this.soyDeLaClase() + ") || Libros Prestados: " + this.cantLibrosPrestados();
    }

    /**
     * Indica el tipo de socio.
     * @return descripción del tipo de socio
     */
    public abstract String soyDeLaClase();
}

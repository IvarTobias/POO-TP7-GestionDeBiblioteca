
/**
 * Representa a un socio estudiante de la biblioteca.
 * Hereda de la clase Socio.
 *
 * @author Francisco Meza Arrua
 * @version 
 */
public class Estudiante extends Socio
{
    private String carrera;

    /**
     * Constructor de la clase Estudiante.
     *
     * @param p_dniSocio DNI del estudiante
     * @param p_nombre nombre del estudiante
     * @param p_carrera carrera que estudia
     */
    public Estudiante(int p_dniSocio, String p_nombre,
                      String p_carrera)
    {
        super(p_dniSocio, p_nombre, 20);
        this.setCarrera(p_carrera);
    }

    /**
     * Asigna la carrera del estudiante.
     *
     * @param p_carrera carrera del estudiante
     */
    private void setCarrera(String p_carrera)
    {
        this.carrera = p_carrera;
    }

    /**
     * Devuelve la carrera del estudiante.
     *
     * @return carrera del estudiante
     */
    public String getCarrera()
    {
        return this.carrera;
    }

    /**
     * Devuelve el tipo de socio.
     *
     * @return la palabra Estudiante
     */
    @Override
    public String soyDeLaClase()
    {
        return "Estudiante";
    }

    /**
     * Verifica si el estudiante puede solicitar otro libro.
     *
     * @return true si no tiene prestamos vencidos
     *         y posee menos de tres libros
     */
    @Override
    public boolean puedePedir()
    {
        return super.puedePedir()
               && this.cantLibrosPrestados() < 3;
    }
}

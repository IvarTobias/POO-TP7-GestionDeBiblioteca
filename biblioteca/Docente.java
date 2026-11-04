/**
 * Representa a un socio docente de la biblioteca.
 * Hereda de la clase Socio.
 *
 * @author Francisco Meza Arrua
 * @version 1.0
 */
public class Docente extends Socio
{
    private String area;

    /**
     * Constructor de la clase Docente.
     *
     * @param p_dniSocio DNI del docente
     * @param p_nombre nombre del docente
     * @param p_area area del docente
     */
    public Docente(int p_dniSocio, String p_nombre,
                   String p_area)
    {
        super(p_dniSocio, p_nombre, 5);
        this.setArea(p_area);
    }

    /**
     * Asigna el area del docente.
     *
     * @param p_area area del docente
     */
    private void setArea(String p_area)
    {
        this.area = p_area;
    }

    /**
     * Devuelve el area del docente.
     *
     * @return area del docente
     */
    public String getArea()
    {
        return this.area;
    }

    /**
     * Devuelve el tipo de socio.
     *
     * @return la palabra Docente
     */
    @Override
    public String soyDeLaClase()
    {
        return "Docente";
    }

    /**
     * Verifica si el docente puede solicitar un libro.
     *
     * @return true si no tiene prestamos vencidos
     */
    @Override
    public boolean puedePedir()
    {
        return super.puedePedir();
    }

    /**
     * Agrega dias al plazo de prestamo del docente.
     *
     * @param p_dias cantidad de dias que se agregan
     */
    public void cambiarDiasDePrestamo(int p_dias)
    {
        this.setDiasPrestamo(this.getDiasPrestamo() + p_dias);
    }

    /**
     * Verifica si el docente nunca tuvo ni tiene
     * prestamos vencidos.
     *
     * @return true si siempre fue responsable
     */
    public boolean esResponsable()
    {
        return false;
    }
}

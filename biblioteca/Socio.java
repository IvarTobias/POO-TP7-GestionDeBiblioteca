import java.util.ArrayList;
import java.util.Calendar;

//Version 1.0 27/10
public abstract class Socio{
    private int dniSocio;
    private String nombre;
    private int diasPrestamo;
    private ArrayList<Prestamo> prestamos;

   
    public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo, ArrayList<Prestamo> p_prestamos){
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
        this.setPrestamos(p_prestamos);
    }


    public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo){
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
        this.setPrestamos(new ArrayList<Prestamo>());
    }

    public void setDniSocio(int p_dniSocio){
        this.dniSocio = p_dniSocio;
    }

    public void setNombre(String p_nombre){
        this.nombre = p_nombre;
    }

    public void setDiasPrestamo(int p_diasPrestamo){
        this.diasPrestamo = p_diasPrestamo;
    }

    public int getDniSocio(){
        return dniSocio;
    }

    public String getNombre(){
        return nombre;
    }

    public int getDiasPrestamo(){
        return diasPrestamo;
    }

    public ArrayList<Prestamo> getPrestamos(){
        return prestamos;
    }

    public void setPrestamos(ArrayList<Prestamo> p_prestamos){
        this.prestamos = p_prestamos;
    }


    public boolean agregarPrestamo(Prestamo p_prestamo){
        return this.prestamos.add(p_prestamo);
    }

    
    public boolean quitarPrestamo(Prestamo p_prestamo){
        return this.prestamos.remove(p_prestamo);
    }

    
    public int cantLibrosPrestados(){
        return this.getPrestamos().size();
    }

    //probar despues de que se cree prestamos
    public boolean puedePedir(){
        Calendar hoy = Calendar.getInstance();
        for (Prestamo prestamo : this.prestamos) {
            if (prestamo.vencido(hoy)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "D.N.I.: " + this.getDniSocio() + " || " + this.getNombre() + " (" + this.soyDeLaClase() + ") || Libros Prestados: " + this.cantLibrosPrestados();
    }

    public abstract String soyDeLaClase();
}
import java.util.Calendar;

public class Prestamo{
    // variables de instancia
    private Calendar fechaRetiro;
    private Calendar fechaDevolucion;
    private Libro libro;
    private Socio socio;

    public Prestamo(Calendar p_fechaRetiro, Libro p_libro, Socio p_socio){
        // inicializa variables de instancia
        this.setFechaRetiro(p_fechaRetiro);
        this.setLibro(p_libro);
        this.setSocio(p_socio);
        this.setFechaDevolucion(null);
    }

    private void setFechaRetiro(Calendar p_fechaRetiro){
        this.fechaRetiro = p_fechaRetiro;
    }

    private void setLibro(Libro p_libro){
        this.libro = p_libro;
    }

    private void setSocio(Socio p_socio){
        this.socio = p_socio;
    }

    private void setFechaDevolucion(Calendar p_fecha){
        this.fechaDevolucion = p_fecha;
    }

    public Calendar getFechaRetiro(){
        return  this.fechaRetiro;
    }

    public Libro getLibro(){
        return this.libro;
    }

    public Socio getSocio(){
        return this.socio;
    }

    public Calendar getFechaDevolucion(){
        return this.fechaDevolucion;
    }

    //recibir una fecha y guardarla como fecha de devolucion
    public void registrarFechaDevolucion(Calendar p_fecha){
        this.setFechaDevolucion(p_fecha);
    }

    //metodo para saber si un prestamo esta vencido en cierta fecha
    public boolean vencido(Calendar p_fechaActual) {
        if (this.getFechaDevolucion() != null){
            return false;
        }

        Calendar fechaVencimiento = (Calendar) this.getFechaRetiro().clone(); //copiamos la fecha en la que se retiro el libro y despues vamos a usar esto para calcular cuando vence
        fechaVencimiento.add(Calendar.DAY_OF_YEAR, this.getSocio().getDiasPrestamo()); //aca sumamos
        return p_fechaActual.after(fechaVencimiento);
    }

    @Override
    public String toString() {
        String retiro = String.format("%1$tY/%1$tm/%1$td", this.getFechaRetiro()); //es para que el formato de la fecha se vea bien 
        String devolucion;

        if (this.getFechaDevolucion() == null) {
            devolucion = "Pendiente";
        } else {
            devolucion = String.format("%1$tY/%1$tm/%1$td", this.getFechaDevolucion());//es para que el formato de la fecha se vea bien 
        }

        return "Retiro: " + retiro + " - Devolución: " + devolucion + "\nLibro: " + this.getLibro().getTitulo() + "\nSocio: " + this.getSocio().getNombre();
    }

}
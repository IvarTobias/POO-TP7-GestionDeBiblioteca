import java.util.*;

public class Biblioteca {
    // ================= Atributos
    private String nombre;
    private HashMap<Integer, Socio> socios; // socios por DNI
    private ArrayList<Libro> libros;

    /**
     * Constructor que instancia los atributos del objeto de la clase Biblioteca
     *
     * @param p_nombre es el nombre de la biblioteca
     */
    public Biblioteca(String p_nombre) {
        this.setNombre(p_nombre);
        this.setSocios(new HashMap<>());
        this.setLibros(new ArrayList<>());
    }

    /**
     * Constructor que instancia los atributos del objeto de la clase Biblioteca
     *
     * @param p_nombre nombre
     * @param p_socios mapa de socios (dni->socio)
     * @param p_libros lista de libros
     */
    public Biblioteca(String p_nombre, HashMap<Integer, Socio> p_socios, ArrayList<Libro> p_libros) {
        this.setNombre(p_nombre);
        this.setSocios(p_socios);
        this.setLibros(p_libros);
    }

    // ================= Setters
    private void setNombre(String p_nombre) {
        this.nombre = p_nombre;
    }

    private void setSocios(HashMap<Integer, Socio> p_Socios) {
        this.socios = p_Socios;
    }

    private void setLibros(ArrayList<Libro> p_libros) {
        this.libros = p_libros;
    }

    // ================= Getters
    public String getNombre() {
        return this.nombre;
    }

    public HashMap<Integer, Socio> getSocios() {
        return this.socios;
    }

    public ArrayList<Libro> getLibros() {
        return this.libros;
    }

    // ================= Métodos de gestión

    /**
     * Agrega un socio al mapa (clave: DNI)
     *
     * @param p_socio socio a agregar
     */
    public void agregarSocio(Socio p_socio) {
        if (p_socio == null) return;
        
        if (this.getSocios().containsKey(p_socio.getDniSocio())) {
            System.out.println("El socio ya se encuentra registrado.");
        } else {
            this.getSocios().put(p_socio.getDniSocio(), p_socio);
            System.out.println("El socio fue registrado correctamente.");
        }
    }

    /**
     * Quita un socio del mapa
     *
     * @param p_socio socio a quitar
     */
    public void quitarSocio(Socio p_socio) {
        if (p_socio == null) return;
        
        if (this.getSocios().remove(p_socio.getDniSocio()) != null) {
            System.out.println("Se dio de baja al socio: " + p_socio.getNombre());
        } else {
            System.out.println("El socio que desea eliminar no existe.");
        }
    }

    public int cantidadDeSociosPorTipo(String p_objeto) {
        int contador = 0;

        for (Socio s : this.getSocios().values()) {
            if (s.soyDeLaClase().equalsIgnoreCase(p_objeto)) {
                contador++;
            }
        }

        return contador;
    }

  public ArrayList<Prestamo> prestamosVencidos() {
       ArrayList<Prestamo> vencidos = new ArrayList<Prestamo>();

       Calendar hoy = Calendar.getInstance();

       for (Socio socio : this.getSocios().values()) {
        for (Prestamo prestamo : socio.getPrestamos()) {

            if (prestamo.vencido(hoy)) {
                vencidos.add(prestamo);
            }
        }
         }

       return vencidos;
       }

    public ArrayList<Docente> docentesResponsables() {
        ArrayList<Docente> docentes = new ArrayList<Docente>();

        for (Socio socio : this.getSocios().values()) {
            if (socio instanceof Docente) {
                Docente docente = (Docente) socio;

                if (docente.esResponsable()) {
                    docentes.add(docente);
                }
            }
        }

        return docentes;
    }

    public String quienTieneElLibro(Libro p_libro) {
        for (Socio socio : this.getSocios().values()) {
            for (Prestamo prestamo : socio.getPrestamos()) {
                if (prestamo.getLibro().getTitulo().equalsIgnoreCase(p_libro.getTitulo())
                && prestamo.getFechaDevolucion() == null) {

                    return socio.getNombre();
                }
            }
        }

        return "Ningún socio tiene este libro prestado.";
    }

    /**
     * Lista de todos los Socios de la Biblioteca.
     *
     * @return devuelve un String de socios según formato1.
     */
    public String listaDeSocios(){
        String listaAux = "Lista de Socios: \n";
        int cant = 1;

        for(Socio socio: this.getSocios().values()){
            listaAux += ( cant + ") D.N.I: " + socio.getDniSocio() + " || " + socio.getNombre() + "(" + socio.soyDeLaClase() + ")" + 
                " || Libros Prestados: " +  socio.cantLibrosPrestados()) + "\n";

            cant += 1;
        

        }
        return listaAux;
    }

    /**
     * Busca un Socio de la Biblioteca.
     * 
     * @param p_dni el dni del socio que se busca.
     * 
     * @return devuelve el Socio que tiene el dni pasado como parámetro, o null si no lo encuentra.
     */
    public Socio buscarSocio(int p_dni) {
        if(this.getSocios().containsKey(p_dni)) {
            return this.getSocios().get(p_dni);
        }
        return null;
    }

    /**
     * Lista de todos los Titulos de la Biblioteca.
     * 
     * @return devuelve un String con la lista de los títulos con los que cuenta la Biblioteca.
     */
    public String listaDeTitulos(){
        String titulos = "";

        for(Libro libro: this.getLibros()) {
            if(!titulos.contains(libro.getTitulo())) {
                titulos += libro.getTitulo() + "\n";
            }
            

        }
        
    

        return titulos;
    }

    /**
     * Lista de todos los Libros de la Biblioteca.
     * 
     * @return  devuelve un String de libros según formato2.
     */
    public String listaDeLibros() {
        String listaLibros = "Lista de Libros: \n";
        int cant = 1;

        for(Libro libro: this.getLibros()) {
            listaLibros += cant + ") Titulo:" + libro.getTitulo() + " || Prestado:" + ((libro.prestado()) ? "Si": "No") + "\n";
            cant += 1;
        }

        return listaLibros;
    }

    /**
     * Lista de todos los Docentes de la Biblioteca.
     * 
     * @return  devuelve un String de docentes según formato3.
     */
    public String listaDeDocentesResponsables() {
        String listaDocentes = "Lista de Docentes Responsables: \n";

        for(Socio socio: this.getSocios().values()) {
            if (socio instanceof Docente) {
                Docente docente = (Docente) socio; 

                if(docente.esResponsable()) {
                    listaDocentes += "* D.N.I: " + socio.getDniSocio() + " || " + socio.getNombre() + "(" + docente.soyDeLaClase() + ")" + 
                    " || Libros Prestados: " +  socio.cantLibrosPrestados() + "\n";
                }

            }
        }

        return listaDocentes;
    }

}

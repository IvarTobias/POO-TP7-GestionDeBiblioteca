public Biblioteca(String p_nombre, HashMap<Integer, Socio> p_socios, ArrayList<Libro> p_libros) {
        this.setNombre(p_nombre);
        this.setSocios(p_socios);
        this.setLibros(p_libros);
    }

    // ================= Setters (privados como en tu guía)
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
        // CAMBIO: en vez de containsValue (O(n) y depende de equals),
        // usamos la clave DNI para evitar duplicados de forma determinística.
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
        // CAMBIO: eliminamos por clave DNI directamente.
        if (this.getSocios().remove(p_socio.getDniSocio()) != null) {
            System.out.println("Se dio de baja al socio: " + p_socio.getNombre());
        } else {
            System.out.println("El socio que desea eliminar no existe.");
        }
    }


public int cantidadDeSociosPorTipo(String p_objeto) {
    int contador = 0;

    for (Socio s : socios) {
        if (s.getTipo().equalsIgnoreCase(p_objeto)) {
            contador++;
        }
    }

    return contador;
}

public ArrayList<Prestamo> prestamosVencidos() {
    ArrayList<Prestamo> vencidos = new ArrayList<Prestamo>();

    Calendar hoy = Calendar.getInstance();

    for (Prestamo p : prestamos) {

        Calendar fechaVencimiento = (Calendar) p.getFechaRetiro().clone();
        fechaVencimiento.add(Calendar.DAY_OF_MONTH, 15);

        if (hoy.after(fechaVencimiento) &&
            !p.isDevuelto()) {

            vencidos.add(p);
        }
    }

    return vencidos;
}

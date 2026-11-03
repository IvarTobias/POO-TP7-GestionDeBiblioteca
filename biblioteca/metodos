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

 import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;

/**
 * Interfaz grafica para gestionar una Biblioteca.
 * Utiliza las clases Biblioteca, Socio, Estudiante, Docente,
 * Libro y Prestamo del proyecto.
 *
 * @author
 * @version 1.0
 */
public class InterfazBiblioteca extends JFrame {

    private Biblioteca biblioteca;

    private DefaultTableModel modeloSocios;
    private DefaultTableModel modeloLibros;
    private DefaultTableModel modeloPrestamos;

    private JTable tablaSocios;
    private JTable tablaLibros;
    private JTable tablaPrestamos;

    /**
     * Construye y muestra la interfaz de la biblioteca recibida.
     * @param p_biblioteca biblioteca que administrara la interfaz
     */
    public InterfazBiblioteca(Biblioteca p_biblioteca) {
        if (p_biblioteca == null) {
            throw new IllegalArgumentException("La biblioteca no puede ser null.");
        }

        this.biblioteca = p_biblioteca;
        this.configurarVentana();
        this.crearInterfaz();
        this.actualizarTablas();
        this.setVisible(true);
    }

    private void configurarVentana() {
        this.setTitle("Sistema de Gestion de Biblioteca - " + biblioteca.getNombre());
        this.setSize(900, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void crearInterfaz() {
        JTabbedPane pestanias = new JTabbedPane();

        pestanias.addTab("Socios", this.crearPanelSocios());
        pestanias.addTab("Libros", this.crearPanelLibros());
        pestanias.addTab("Prestamos", this.crearPanelPrestamos());
        pestanias.addTab("Consultas", this.crearPanelConsultas());

        this.add(pestanias, BorderLayout.CENTER);
    }

    private JPanel crearPanelSocios() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));

        modeloSocios = new DefaultTableModel(
            new Object[] {"DNI", "Nombre", "Tipo", "Prestamos", "Dias de prestamo"}, 0
        ) {
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaSocios = new JTable(modeloSocios);
        panel.add(new JScrollPane(tablaSocios), BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton estudiante = new JButton("Agregar estudiante");
        JButton docente = new JButton("Agregar docente");
        JButton actualizar = new JButton("Actualizar");

        estudiante.addActionListener(e -> this.agregarEstudiante());
        docente.addActionListener(e -> this.agregarDocente());
        actualizar.addActionListener(e -> this.actualizarTablas());

        botones.add(estudiante);
        botones.add(docente);
        botones.add(actualizar);
        panel.add(botones, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelLibros() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));

        modeloLibros = new DefaultTableModel(
            new Object[] {"Titulo", "Edicion", "Editorial", "Anio", "Estado"}, 0
        ) {
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaLibros = new JTable(modeloLibros);
        panel.add(new JScrollPane(tablaLibros), BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton agregar = new JButton("Agregar libro");
        JButton actualizar = new JButton("Actualizar");

        agregar.addActionListener(e -> this.agregarLibro());
        actualizar.addActionListener(e -> this.actualizarTablas());

        botones.add(agregar);
        botones.add(actualizar);
        panel.add(botones, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelPrestamos() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));

        modeloPrestamos = new DefaultTableModel(
            new Object[] {"DNI socio", "Socio", "Libro", "Fecha de retiro", "Vencimiento", "Estado"}, 0
        ) {
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaPrestamos = new JTable(modeloPrestamos);
        panel.add(new JScrollPane(tablaPrestamos), BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton prestar = new JButton("Registrar prestamo");
        JButton devolver = new JButton("Registrar devolucion");
        JButton actualizar = new JButton("Actualizar");

        prestar.addActionListener(e -> this.registrarPrestamo());
        devolver.addActionListener(e -> this.registrarDevolucion());
        actualizar.addActionListener(e -> this.actualizarTablas());

        botones.add(prestar);
        botones.add(devolver);
        botones.add(actualizar);
        panel.add(botones, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelConsultas() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 25));

        JButton buscarSocio = new JButton("Buscar socio por DNI");
        JButton quienTiene = new JButton("Consultar quien tiene un libro");
        JButton vencidos = new JButton("Mostrar prestamos vencidos");
        JButton responsables = new JButton("Docentes responsables");
        JButton titulos = new JButton("Mostrar titulos");
        JButton listaSocios = new JButton("Mostrar lista de socios");

        buscarSocio.addActionListener(e -> this.consultarSocio());
        quienTiene.addActionListener(e -> this.consultarQuienTiene());
        vencidos.addActionListener(e -> this.mostrarPrestamosVencidos());
        responsables.addActionListener(e -> this.mostrarDocentesResponsables());
        titulos.addActionListener(e -> this.mostrarTitulos());
        listaSocios.addActionListener(e -> this.mostrarListaSocios());

        panel.add(buscarSocio);
        panel.add(quienTiene);
        panel.add(vencidos);
        panel.add(responsables);
        panel.add(titulos);
        panel.add(listaSocios);

        return panel;
    }

    private void agregarEstudiante() {
        String dniTexto = pedirTexto("DNI del estudiante:");
        if (dniTexto == null) return;

        String nombre = pedirTexto("Nombre del estudiante:");
        if (nombre == null) return;

        String carrera = pedirTexto("Carrera:");
        if (carrera == null) return;

        try {
            int dni = Integer.parseInt(dniTexto);
            if (biblioteca.buscarSocio(dni) != null) {
                mostrarMensaje("Ya existe un socio con ese DNI.");
                return;
            }

            biblioteca.agregarSocio(new Estudiante(dni, nombre, carrera));
            this.actualizarTablas();
            mostrarMensaje("Estudiante agregado correctamente.");
        } catch (NumberFormatException ex) {
            mostrarMensaje("El DNI debe ser un numero entero.");
        }
    }

    private void agregarDocente() {
        String dniTexto = pedirTexto("DNI del docente:");
        if (dniTexto == null) return;

        String nombre = pedirTexto("Nombre del docente:");
        if (nombre == null) return;

        String area = pedirTexto("Area del docente:");
        if (area == null) return;

        try {
            int dni = Integer.parseInt(dniTexto);
            if (biblioteca.buscarSocio(dni) != null) {
                mostrarMensaje("Ya existe un socio con ese DNI.");
                return;
            }

            biblioteca.agregarSocio(new Docente(dni, nombre, area));
            this.actualizarTablas();
            mostrarMensaje("Docente agregado correctamente.");
        } catch (NumberFormatException ex) {
            mostrarMensaje("El DNI debe ser un numero entero.");
        }
    }

    private void agregarLibro() {
        String titulo = pedirTexto("Titulo del libro:");
        if (titulo == null) return;

        String edicionTexto = pedirTexto("Numero de edicion:");
        if (edicionTexto == null) return;

        String editorial = pedirTexto("Editorial:");
        if (editorial == null) return;

        String anioTexto = pedirTexto("Anio de publicacion:");
        if (anioTexto == null) return;

        try {
            int edicion = Integer.parseInt(edicionTexto);
            int anio = Integer.parseInt(anioTexto);

            biblioteca.getLibros().add(new Libro(titulo, edicion, editorial, anio));
            this.actualizarTablas();
            mostrarMensaje("Libro agregado correctamente.");
        } catch (NumberFormatException ex) {
            mostrarMensaje("La edicion y el anio deben ser numeros enteros.");
        }
    }

    private void registrarPrestamo() {
        String dniTexto = pedirTexto("DNI del socio:");
        if (dniTexto == null) return;

        Socio socio;
        try {
            socio = biblioteca.buscarSocio(Integer.parseInt(dniTexto));
        } catch (NumberFormatException ex) {
            mostrarMensaje("El DNI debe ser un numero entero.");
            return;
        }

        if (socio == null) {
            mostrarMensaje("No existe un socio con ese DNI.");
            return;
        }

        String titulo = pedirTexto("Titulo exacto del libro:");
        if (titulo == null) return;

        Libro libro = this.buscarLibro(titulo);
        if (libro == null) {
            mostrarMensaje("No se encontro ese libro en la biblioteca.");
            return;
        }

        if (libro.prestado()) {
            mostrarMensaje("Ese libro ya se encuentra prestado.");
            return;
        }

        if (!socio.puedePedir()) {
            mostrarMensaje("El socio no puede pedir otro libro. Verifique sus prestamos o limites.");
            return;
        }

        Prestamo prestamo = new Prestamo(Calendar.getInstance(), libro, socio);
        socio.agregarPrestamo(prestamo);
        libro.agregarPrestamo(prestamo);

        this.actualizarTablas();
        mostrarMensaje("Prestamo registrado correctamente.");
    }

    private void registrarDevolucion() {
        String titulo = pedirTexto("Titulo exacto del libro que se devuelve:");
        if (titulo == null) return;

        Libro libro = this.buscarLibro(titulo);
        if (libro == null) {
            mostrarMensaje("No se encontro ese libro.");
            return;
        }

        Prestamo ultimo = libro.ultimoPrestamo();
        if (ultimo == null || ultimo.getFechaDevolucion() != null) {
            mostrarMensaje("El libro no tiene un prestamo activo.");
            return;
        }

        ultimo.registrarFechaDevolucion(Calendar.getInstance());
        this.actualizarTablas();
        mostrarMensaje("Devolucion registrada correctamente.");
    }

    private Libro buscarLibro(String p_titulo) {
        for (Libro libro : biblioteca.getLibros()) {
            if (libro.getTitulo().equalsIgnoreCase(p_titulo.trim())) {
                return libro;
            }
        }
        return null;
    }

    private void consultarSocio() {
        String dniTexto = pedirTexto("Ingrese el DNI:");
        if (dniTexto == null) return;

        try {
            Socio socio = biblioteca.buscarSocio(Integer.parseInt(dniTexto));
            if (socio == null) {
                mostrarMensaje("No se encontro ningun socio con ese DNI.");
            } else {
                mostrarMensaje(socio.toString());
            }
        } catch (NumberFormatException ex) {
            mostrarMensaje("El DNI debe ser un numero entero.");
        }
    }

    private void consultarQuienTiene() {
        String titulo = pedirTexto("Titulo exacto del libro:");
        if (titulo == null) return;

        Libro libro = this.buscarLibro(titulo);
        if (libro == null) {
            mostrarMensaje("No se encontro ese libro.");
        } else {
            mostrarMensaje(biblioteca.quienTieneElLibro(libro));
        }
    }

    private void mostrarPrestamosVencidos() {
        String resultado = "PRESTAMOS VENCIDOS\n\n";
        int cantidad = 0;
        Calendar hoy = Calendar.getInstance();

        for (Socio socio : biblioteca.getSocios().values()) {
            for (Prestamo prestamo : socio.getPrestamos()) {
                if (prestamo.vencido(hoy)) {
                    resultado += socio.getNombre() + " - " + prestamo.getLibro().getTitulo()
                        + " - Retiro: " + fecha(prestamo.getFechaRetiro()) + "\n";
                    cantidad++;
                }
            }
        }

        if (cantidad == 0) {
            resultado += "No hay prestamos vencidos.";
        }
        mostrarMensaje(resultado);
    }

    private void mostrarDocentesResponsables() {
        ArrayList<Docente> docentes = biblioteca.docentesResponsables();
        String resultado = "DOCENTES RESPONSABLES\n\n";

        if (docentes.isEmpty()) {
            resultado += "No hay docentes responsables registrados.";
        } else {
            for (Docente docente : docentes) {
                resultado += docente.toString() + "\n";
            }
        }
        mostrarMensaje(resultado);
    }

    private void mostrarTitulos() {
        String titulos = biblioteca.listaDeTitulos();
        if (titulos.trim().isEmpty()) {
            titulos = "No hay libros registrados.";
        }
        mostrarMensaje("TITULOS DE LA BIBLIOTECA\n\n" + titulos);
    }

    private void mostrarListaSocios() {
        String socios = biblioteca.listaDeSocios();
        if (biblioteca.getSocios().isEmpty()) {
            socios = "No hay socios registrados.";
        }
        mostrarMensaje(socios);
    }

    private void actualizarTablas() {
        if (modeloSocios != null) {
            modeloSocios.setRowCount(0);
            for (Socio socio : biblioteca.getSocios().values()) {
                modeloSocios.addRow(new Object[] {
                    socio.getDniSocio(), socio.getNombre(), socio.soyDeLaClase(),
                    socio.cantLibrosPrestados(), socio.getDiasPrestamo()
                });
            }
        }

        if (modeloLibros != null) {
            modeloLibros.setRowCount(0);
            for (Libro libro : biblioteca.getLibros()) {
                modeloLibros.addRow(new Object[] {
                    libro.getTitulo(), libro.getEdicion(), libro.getEditorial(),
                    libro.getAnio(), libro.prestado() ? "Prestado" : "Disponible"
                });
            }
        }

        if (modeloPrestamos != null) {
            modeloPrestamos.setRowCount(0);
            Calendar hoy = Calendar.getInstance();

            for (Socio socio : biblioteca.getSocios().values()) {
                for (Prestamo prestamo : socio.getPrestamos()) {
                    Calendar vencimiento = (Calendar) prestamo.getFechaRetiro().clone();
                    vencimiento.add(Calendar.DAY_OF_YEAR, socio.getDiasPrestamo());

                    String estado;
                    if (prestamo.getFechaDevolucion() != null) {
                        estado = "Devuelto";
                    } else if (prestamo.vencido(hoy)) {
                        estado = "Vencido";
                    } else {
                        estado = "En prestamo";
                    }

                    modeloPrestamos.addRow(new Object[] {
                        socio.getDniSocio(), socio.getNombre(),
                        prestamo.getLibro().getTitulo(),
                        fecha(prestamo.getFechaRetiro()), fecha(vencimiento), estado
                    });
                }
            }
        }
    }

    private static String fecha(Calendar p_fecha) {
        return String.format("%1$02d/%1$02d/%1$04d",
            p_fecha.get(Calendar.DAY_OF_MONTH),
            p_fecha.get(Calendar.MONTH) + 1,
            p_fecha.get(Calendar.YEAR));
    }

    private static String pedirTexto(String mensaje) {
        String valor = JOptionPane.showInputDialog(null, mensaje);
        if (valor == null) return null;
        valor = valor.trim();
        if (valor.isEmpty()) {
            mostrarMensaje("El campo no puede quedar vacio.");
            return null;
        }
        return valor;
    }

    private static void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    /**
     * Permite ejecutar la interfaz directamente desde BlueJ.
     */
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca("Biblioteca");
        new InterfazBiblioteca(biblioteca);
    }}

import java.util.Calendar;

/**
 * Clase ejecutable del TP7 Integrador.
 * Permite comprobar el funcionamiento del sistema
 * de Gestion de Biblioteca.
 *
 * @author Equipo TP7
 * @version 1.0
 */
public class GestionBiblioteca {
    public static void main(String[] args) {

        // Crear la biblioteca
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central");

        // Crear estudiantes
        Estudiante estudiante1 = new Estudiante(45678912, "Juan Perez", "Sistemas");
        Estudiante estudiante2 = new Estudiante(47896521, "Maria Lopez", "Ingenieria");

        // Crear docentes
        Docente docente1 = new Docente(25123456, "Carlos Gomez", "Informatica");
        Docente docente2 = new Docente(28987654, "Ana Martinez", "Matematica");

        // Registrar socios
        biblioteca.agregarSocio(estudiante1);
        biblioteca.agregarSocio(estudiante2);
        biblioteca.agregarSocio(docente1);
        biblioteca.agregarSocio(docente2);

        // Crear libros
        Libro libro1 = new Libro("Programando con JAVA", 1, "Pearson", 2020);
        Libro libro2 = new Libro("Bases de Datos", 2, "Alfaomega", 2019);
        Libro libro3 = new Libro("Algoritmos y Estructuras de Datos", 1, "McGraw Hill", 2021);
        Libro libro4 = new Libro("Programando con JAVA", 2, "Pearson", 2022);

        // Registrar libros en la biblioteca
        biblioteca.getLibros().add(libro1);
        biblioteca.getLibros().add(libro2);
        biblioteca.getLibros().add(libro3);
        biblioteca.getLibros().add(libro4);

        // Fechas para realizar prestamos
        Calendar hoy = Calendar.getInstance();

        Calendar haceDosDias = (Calendar) hoy.clone();
        haceDosDias.add(Calendar.DAY_OF_YEAR, -2);

        Calendar haceDiezDias = (Calendar) hoy.clone();
        haceDiezDias.add(Calendar.DAY_OF_YEAR, -10);

        Calendar haceTreintaDias = (Calendar) hoy.clone();
        haceTreintaDias.add(Calendar.DAY_OF_YEAR, -30);

        // Prestamo 1: estudiante con un libro
        Prestamo prestamo1 = new Prestamo(haceDosDias, libro1, estudiante1);
        libro1.agregarPrestamo(prestamo1);
        estudiante1.agregarPrestamo(prestamo1);

        // Prestamo 2: docente con un libro
        Prestamo prestamo2 = new Prestamo(haceDosDias, libro2, docente1);
        libro2.agregarPrestamo(prestamo2);
        docente1.agregarPrestamo(prestamo2);

        // Prestamo 3: estudiante con prestamo vencido
        Prestamo prestamo3 = new Prestamo(haceTreintaDias, libro3, estudiante2);
        libro3.agregarPrestamo(prestamo3);
        estudiante2.agregarPrestamo(prestamo3);

        // Prestamo 4: docente que devolvio a tiempo
        Prestamo prestamo4 = new Prestamo(haceDiezDias, libro4, docente2);
        libro4.agregarPrestamo(prestamo4);
        docente2.agregarPrestamo(prestamo4);

        Calendar fechaDevolucion = (Calendar) haceDiezDias.clone();
        fechaDevolucion.add(Calendar.DAY_OF_YEAR, 3);
        prestamo4.registrarFechaDevolucion(fechaDevolucion);

        // Mostrar resultados
        System.out.println("====================================");
        System.out.println("       GESTION DE BIBLIOTECA");
        System.out.println("====================================");

        // Cantidad de estudiantes
        System.out.println("\nCANTIDAD DE ESTUDIANTES:");
        System.out.println(
            biblioteca.cantidadDeSociosPorTipo("Estudiante"));

        // Lista de socios
        System.out.println("\n" + biblioteca.listaDeSocios());

        // Lista de libros
        System.out.println("\n" + biblioteca.listaDeLibros());

        // Titulos sin repetir
        System.out.println("\nLISTA DE TITULOS:");
        System.out.println(biblioteca.listaDeTitulos());

        // Docentes responsables
        System.out.println("\n" +
            biblioteca.listaDeDocentesResponsables());

        // Quien tiene el libro solicitado
        System.out.println("\n¿QUIEN TIENE PROGRAMANDO CON JAVA?");
        System.out.println(
            biblioteca.quienTieneElLibro(libro1));

        // Prestamos vencidos
        System.out.println("\nPRESTAMOS VENCIDOS:");

        for (Prestamo prestamo : biblioteca.prestamosVencidos()) {
            System.out.println(prestamo);
            System.out.println("----------------------------");
        }

        // Verificar si los socios pueden pedir libros
        System.out.println("\nSOCIOS HABILITADOS PARA PEDIR:");

        System.out.println(estudiante1.getNombre() + ": " +
            estudiante1.puedePedir());

        System.out.println(estudiante2.getNombre() + ": " +
            estudiante2.puedePedir());

        System.out.println(docente1.getNombre() + ": " +
            docente1.puedePedir());

        System.out.println(docente2.getNombre() + ": " +
            docente2.puedePedir());

        System.out.println("\nFIN DEL PROGRAMA");
    }
}

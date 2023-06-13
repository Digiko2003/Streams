//By Diego Arturo Enriquez Mercado
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamsTxt {

    public static void main(String[] args) {

        String[] nombresMujeres = leerArchivo("nombres_mujeres.txt");
        String[] nombresHombres = leerArchivo("nombres_hombres.txt");
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while (!salir) {

            System.out.println("1. Convertir a mayúsculas los nombres de mujeres");
            System.out.println("2. Nombre popular");
            System.out.println("3. Mujeres y hombres que empiecen con la letra A");
            System.out.println("4. Salir");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Convertir a mayúsculas los nombres de mujeres");

                        // Convertir a mayúsculas los nombres de mujeres
                        Stream<String> nombresMujeresMayusculas = Arrays.stream(nombresMujeres)
                                .map(String::toUpperCase);
                        System.out.println("Nombres de mujeres en mayúsculas:");
                        nombresMujeresMayusculas.forEach(System.out::println);
                        break;
                    //------------------------------------
                    case 2:
                        System.out.println("Nombre popular");
                        // Pedir al usuario que ingrese un nombre y comprobar si está entre los más populares
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("Ingrese un nombre: ");
                        String nombreIngresado = scanner.nextLine();

                        boolean nombrePopularMujer = Arrays.stream(nombresMujeres)
                                .anyMatch(nombre -> nombre.equalsIgnoreCase(nombreIngresado));
                        boolean nombrePopularHombre = Arrays.stream(nombresHombres)
                                .anyMatch(nombre -> nombre.equalsIgnoreCase(nombreIngresado));

                        if (nombrePopularMujer) {
                            System.out.println("El nombre ingresado es popular entre las niñas.");
                        } else if (nombrePopularHombre) {
                            System.out.println("El nombre ingresado es popular entre los niños.");
                        } else {
                            System.out.println("El nombre ingresado no es popular.");
                        }
                        break;
                    //------------------------------------
                    case 3:
                        System.out.println("Mujeres y hombres que empiecen con la letra A:");
                        // Combinar los arreglos de mujeres y hombres y mostrar los nombres que empiecen con la letra A
                        Stream<String> nombresCombinados = Stream.concat(Arrays.stream(nombresMujeres), Arrays.stream(nombresHombres));
                        System.out.println("Nombres que empiezan con la letra A:");
                        nombresCombinados.filter(nombre -> nombre.startsWith("A"))
                                .forEach(System.out::println);
                        break;
                    //------------------------------------
                    case 4:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }

    }
    private static String[] leerArchivo(String nombreArchivo) {
        try {
            Path path = Path.of(nombreArchivo);
            return Files.lines(path)
                    .toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }
}


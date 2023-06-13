import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String[] nombresMujeres = leerArchivo("nombres_mujeres.txt");
        String[] nombresHombres = leerArchivo("nombres_hombres.txt");
        Stream<String> nombresMujeresMayusculas = Arrays.stream(nombresMujeres)
                .map(String::toUpperCase);
        System.out.println("Nombres de mujeres en mayúsculas:");
        nombresMujeresMayusculas.forEach(System.out::println);
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
        Stream<String> nombresCombinados = Stream.concat(Arrays.stream(nombresMujeres), Arrays.stream(nombresHombres));
        System.out.println("Nombres que empiezan con la letra A:");
        nombresCombinados.filter(nombre -> nombre.startsWith("A"))
                .forEach(System.out::println);
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

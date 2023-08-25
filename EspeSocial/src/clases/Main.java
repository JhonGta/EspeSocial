package clases;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RedSocial redSocial = new RedSocial();

        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Agregar usuario");
            System.out.println("2. Agregar conexión");
            System.out.println("3. Encontrar amigos en común");
            System.out.println("4. Calcular centralidad de grado");
            System.out.println("5. Encontrar comunidades");
            System.out.println("6. Visualizar red");
            System.out.println("7. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el nombre del usuario: ");
                    String nombreUsuario = scanner.nextLine();
                    redSocial.agregarUsuario(nombreUsuario);
                    System.out.println("Usuario agregado: " + nombreUsuario);
                    break;
                case 2:
                    System.out.print("Ingresa el nombre del primer usuario: ");
                    String usuario1 = scanner.nextLine();
                    System.out.print("Ingresa el nombre del segundo usuario: ");
                    String usuario2 = scanner.nextLine();
                    redSocial.agregarConexion(usuario1, usuario2);
                    System.out.println("Conexión agregada entre " + usuario1 + " y " + usuario2);
                    break;
                case 3:
                	 System.out.print("Ingresa el nombre del primer usuario: ");
                	    String usr1 = scanner.nextLine();
                	    System.out.print("Ingresa el nombre del segundo usuario: ");
                	    String usr2 = scanner.nextLine();
                	    List<String> nombresAmigosComunes = redSocial.encontrarNombresAmigosEnComun(usr1, usr2);
                	    System.out.println("Amigos en común entre " + usr1 + " y " + usr2 + ": " + nombresAmigosComunes);
                	    break;
                case 4:
                    System.out.print("Ingresa el nombre del usuario: ");
                    String usr = scanner.nextLine();
                    double centralidad = redSocial.calcularCentralidadDeGrado(usr);
                    System.out.println("Centralidad de grado de " + usr + ": " + centralidad);
                    break;
                case 5:
                	List<List<Usuario>> comunidades = redSocial.encontrarComunidades();
                    System.out.println("Comunidades encontradas:");
                    redSocial.visualizarComunidades(comunidades);
                    break;
                case 6:
                    System.out.println("Red social:");
                    redSocial.visualizarRed();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida");
            }}}}
package view;

import model.User;
import service.UserService;

import java.util.Scanner;

public class MenuUser {

    static String mainMenuMessage = ("=================== \n"
            + "\n Mini red social \n"
            + "1. Registrarse \n"
            + "2. Iniciar sesión \n"
            + "3. Salir \n");

    public static void mainMenu(Scanner sc) {
        int option = 0;

        do {
            System.out.println(mainMenuMessage);

            // Validación de entrada
            while (!sc.hasNextInt()) {
                System.out.println("Por favor ingresa un número válido.");
                sc.next(); // limpiar entrada no válida
            }

            option = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (option) {
                case 1:
                    UserService.createUser(sc);
                    break;
                case 2:
                    User resultado = UserService.login(sc);
                    if (resultado != null && resultado.getUserId() > 0) {
                        MenuMessaege.mostrarMenu(resultado, sc); // Supone que ya existe
                    } else {
                        System.out.println("Inicio de sesión fallido.");
                    }
                    break;
                case 3:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }

        } while (option != 3);
    }
}

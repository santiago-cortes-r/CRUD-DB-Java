package service;


import dao.UserDAO;
import model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Scanner;
import java.util.List;

public class UserService {

    static String emailMessage = "Indica tu email:";
    static String passwordMessage = "Indica tu password:";
    static String fullNameUser = "Indica tu nombre completo:";

    public static void createUser(Scanner sc) {
        System.out.println(fullNameUser);
        String fullName = sc.nextLine();
        System.out.println(emailMessage);
        String email = sc.nextLine();
        System.out.println(passwordMessage);
        String password = sc.nextLine();

        password = getMd5Hash(password);

        User newUser = new User(0, email, password, fullName);
        UserDAO.createUserOnDB(newUser);
    }

    public static void listUsers() {
        List<User> users = UserDAO.listUsersOnDB();
        for (User u : users) {
            System.out.println("ID: " + u.getUserId());
            System.out.println("Nombre: " + u.getFullName());
            System.out.println("Email: " + u.getEmail());
            System.out.println("--------------------------");
        }
    }

    public static void editUser(User user, Scanner sc) {
        System.out.println(fullNameUser);
        String fullName = sc.nextLine();
        System.out.println(emailMessage);
        String email = sc.nextLine();
        System.out.println(passwordMessage);
        String password = sc.nextLine();

        String passwordHashed = getMd5Hash(password);
        User updatedUser = new User(user.getUserId(), email, passwordHashed, fullName);
        UserDAO.editUserOnDB(updatedUser);
    }

    public static User login(Scanner sc) {
        System.out.println(emailMessage);
        String email = sc.nextLine();
        System.out.println(passwordMessage);
        String password = sc.nextLine();

        String passwordHashed = getMd5Hash(password);
        return UserDAO.loginDB(new User(email, passwordHashed));
    }

    public static String getMd5Hash(String password) {
        return DigestUtils.md5Hex(password);
    }
}

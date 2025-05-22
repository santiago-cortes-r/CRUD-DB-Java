import model.User;
import service.UserService;
import view.MenuUser;

import java.util.Scanner;

public class Initial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MenuUser.mainMenu(sc);
        sc.close();
    }
}
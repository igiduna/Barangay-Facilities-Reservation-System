import java.util.InputMismatchException;
import java.util.Scanner;

public class adminLogin {

    static Scanner scnr = new Scanner(System.in);
    static Admin a = new Admin();

    public static String email, username, cpNumber, password, receiptUserName;

    public static void adminStart() throws InterruptedException {

        boolean run = true;

        newLine(5);

        while (run) {

            System.out.print("""
                    \n\t\t\t\t\t\t\t\t\t\s\s\sADMIN
                    \n\t\t\t\t\t\t\t\t\s[1] Admin Login
                    \t\t\t\t\t\t\t\t\s[2] Admin Registration (Create new account)
                    \t\t\t\t\t\t\t\t\s[3] Exit
                    \n\t\t\t\t\t\t\t\t\t\s>>\s""");

            try {
                int choice = scnr.nextInt();

                switch (choice) {
                    case 1:
                        scnr.nextLine();
                        adminLogin();
                        break;

                    case 2:
                        scnr.nextLine();
                        adminReg();
                        break;

                    case 3:
                        systemStart.systemStart(); // babalik sa type of user
                        break;

                    default:
                        System.out.println("\n\t\t\t\t\t\t\t\t\s\s\s\s\s\sInvalid Input");
                        scnr.nextLine();
                        Thread.sleep(1000);
                        ClearConsole.clearConsole();
                        adminStart();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\n\t\t\t\t\t\t\t\t\s\s\s\s\s\sInvalid Input");
                scnr.nextLine();
                Thread.sleep(1000);
                ClearConsole.clearConsole();
                adminStart();
            }
        }
    }

    public static void adminReg() throws InterruptedException {

        ClearConsole.clearConsole();
        newLine(5);

        System.out.println("\t\t\t\t\t\t\t\t\t\sUSER INFORMATION\n");
        System.out.print("\t\t\t\t\t\t\sEnter username: ");
        String username = scnr.nextLine().trim();

        receiptUserName = username;

        System.out.print("\t\t\t\t\t\t\sEnter Phone Number: ");
        String cpNumber = scnr.next().trim();
        while (!(cpNumber.length() == 11 && cpNumber.startsWith("09"))) {
            System.out.print("\t\t\t\t\t\t\sPlease enter a valid phone number: ");
            cpNumber = scnr.next().trim();
        }

        System.out.print("\t\t\t\t\t\t\sEnter Email: ");
        String email = scnr.next().trim();
        while (!email.endsWith("@gmail.com")) {
            System.out.print("\t\t\t\t\t\t\sPlease enter a valid email: ");
            email = scnr.next().trim();
        }

        if (Admin.adminAccounts.containsKey(email)) {
            System.out.println("\t\t\t\t\t\t\sAccount with this email already exists!");
            scnr.nextLine();
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            adminMenu.adminMenu();

        } else {
            System.out.print("\t\t\t\t\t\t\sCreate Your Password: ");
            String password = scnr.next().trim();
            Admin newAdmin = new Admin(username, password, cpNumber, email);

            while (!(password.length() >= 8)) {
                System.out.println("\t\t\t\t\t\t\sPasswords should be 8 characters above.");
                System.out.print("\t\t\t\t\t\t\sCreate Your Password: ");
                password = scnr.next().trim();
                newAdmin = new Admin(username, password, cpNumber, email);
            }

            Admin.adminAccounts.put(email, newAdmin);
            System.out.println(
                    "\n                                                                 Account Created Successfully!");
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            adminMenu.adminMenu();
        }
    }

    public static void adminLogin() throws InterruptedException {

        ClearConsole.clearConsole();
        newLine(5);

        System.out.println("\t\t\t\t\t\t\t\t\t\s\s\s\s\sUSER LOGIN\n");
        System.out.print("\t\t\t\t\t\t\sEnter Email: ");
        email = scnr.next().trim();

        if (Admin.adminAccounts.containsKey(email)) {
            System.out.println("\n\t\t\t\t\t\t\t\t\s\s\sEmail is Already Registered!");
            Thread.sleep(1000);
            adminMenu.adminMenu();

        } else {
            scnr.nextLine();
            System.out.println("\t\t\t\t\t\t\sThis account is not yet registered!");
            System.out.println("\t\t\t\t\t\t\sPlease register first!\n");
            scnr.nextLine();
            adminReg();
        }
    }

    private static void newLine(int x) {
        for (int i = 0; i < x; i++) {
            System.out.println("\n");
        }
    }

}

import java.util.Scanner;
import java.util.InputMismatchException;

public class guestLogin {

    static Scanner scnr = new Scanner(System.in);
    static Guest g = new Guest();

    public static String email, username, cpNumber, receiptGuestName;
    public static String currentGuestUsername;

    public static void guestStart() throws InterruptedException {

        boolean run = true;

        newLine(5);

        while (run) {
            System.out.print("""
                    \n\t\t\t\t\t\t\t\t\t\s\s\sGUEST
                    \n\t\t\t\t\t\t\t\t\s[1] Guest Login
                    \t\t\t\t\t\t\t\t\s[2] Guest Registration (Create new account)
                    \t\t\t\t\t\t\t\t\s[3] Exit
                    \n\t\t\t\t\t\t\t\t\t\s>>\s""");

            try {
                int choice = scnr.nextInt();

                switch (choice) {
                    case 1:
                        scnr.nextLine();
                        guestLogin();
                        break;
                    case 2:
                        scnr.nextLine();
                        guestReg();
                        break;
                    case 3:
                        systemStart.systemStart();
                        break;
                    default:
                        System.out.println("\n\t\t\t\t\t\t\t\t\s\s\s\s\s\sInvalid Input");
                        scnr.nextLine();
                        Thread.sleep(1000);
                        ClearConsole.clearConsole();
                        guestStart();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\n\t\t\t\t\t\t\t\t\s\s\s\s\s\sInvalid Input");
                scnr.nextLine();
                Thread.sleep(1000);
                ClearConsole.clearConsole();
                guestStart();
            }
        }
    }

    public static void guestReg() throws InterruptedException {

        ClearConsole.clearConsole();
        newLine(5);

        System.out.println("\n\t\t\t\t\t\t\t\t\t\s\s\sUSER INFORMATION\n");
        System.out.print("\t\t\t\t\t\t\sEnter username: ");
        username = scnr.nextLine().trim();

        System.out.print("\t\t\t\t\t\t\sEnter Phone Number: ");
        cpNumber = scnr.next().trim();

        while (!(cpNumber.length() == 11 && cpNumber.startsWith("09"))) {
            System.out.print("\t\t\t\t\t\t\sPlease enter a valid phone number: ");
            cpNumber = scnr.next().trim();
        }

        System.out.print("\t\t\t\t\t\t\sEnter Email: ");
        scnr.nextLine();
        email = scnr.next().trim();
        while (!email.endsWith("@gmail.com")) {
            System.out.print("\t\t\t\t\t\t\sPlease enter a valid email: ");
            email = scnr.next().trim();
        }

        if (Guest.accountExists(email)) {
            System.out.println("\t\t\t\t\t\t\sAccount with this email already exists!");
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            guestStart();
        } else {
            System.out.print("\t\t\t\t\t\t\sCreate Your Password: ");
            String password = scnr.next().trim();

            while (!(password.length() >= 8)) {
                System.out.println("\t\t\t\t\t\t\sPasswords should be 8 characters above.");
                System.out.print("\t\t\t\t\t\t\sCreate Your Password: ");
                password = scnr.next().trim();
            }

            // Add the new guest account
            Guest.addGuestAccount(username, email, cpNumber, password);

            // Set the current guest username
            currentGuestUsername = username;

            System.out.println(
                    "\n                                                                    Account Created Successfully!");
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            guestMenu.guestMenu(); // Redirect to the guest menu
        }
    }

    public static void guestLogin() throws InterruptedException {

        ClearConsole.clearConsole();
        newLine(5);

        System.out.println("\t\t\t\t\t\t\t\t\t\s\s\s\s\sUSER LOGIN\n");
        System.out.print("\t\t\t\t\t\t\sEnter Email: ");
        email = scnr.next().trim();

        if (Guest.accountExists(email)) {

            System.out.println("\n\t\t\t\t\t\t\t\t\s\s\sLogin Successful!");
            currentGuestUsername = Guest.guestAccounts.get(email).getUsername();
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            guestMenu.guestMenu(); // Redirect to the guest menu
        } else {
            System.out.println("\n\t\t\t\t\t\t\t\t\s\s\sThis account is not yet registered!");
            scnr.nextLine();
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            guestReg(); // Redirect to registration
        }
    }

    private static void newLine(int x) {
        for (int i = 0; i < x; i++) {
            System.out.println("\n");
        }
    }

}

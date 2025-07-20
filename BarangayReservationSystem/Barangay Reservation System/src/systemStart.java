import java.util.InputMismatchException;
import java.util.Scanner;

public class systemStart {

    public static void systemStart() throws InterruptedException {

        Scanner scnr = new Scanner(System.in);

        ClearConsole.clearConsole();

        while (true) {

            newLine(5);
            System.out.println("\t\t\t\t\t\t\tBarangay Community Center Reservation System!");
            System.out.print(
                    """
                            \n\t\t\t\t\t\t\t\t\s\s\s\sWhich user are you?
                            \n\t\t\t\t\t\t\t\t\s[1] Admin
                            \t\t\t\t\t\t\t\t\s[2] Guest
                            \n\t\t\t\t\t\t\t\t\t\s>>\s""");
            try {
                int choice = scnr.nextInt();
                switch (choice) {
                    case 1:
                        ClearConsole.clearConsole();
                        adminLogin.adminStart();
                        break;
                    case 2:
                        ClearConsole.clearConsole();
                        guestLogin.guestStart();
                        break;
                    default:
                        System.out.println("\n\t\t\t\t\t\t\t\t\s\s\s\s\s\sInvalid Input");
                        scnr.nextLine();
                        Thread.sleep(1000);
                        ClearConsole.clearConsole();
                }
            } catch (InputMismatchException e) {
                System.out.println("\n\t\t\t\t\t\t\t\t\s\s\s\s\s\sInvalid Input");
                scnr.nextLine();
                Thread.sleep(1000);
                ClearConsole.clearConsole();
            }
        }
    }

    private static void newLine(int x) {
        for (int i = 0; i < x; i++) {
            System.out.println("\n");
        }
    }

}

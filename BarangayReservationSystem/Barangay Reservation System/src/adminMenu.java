import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class adminMenu {

    static Scanner scnr = new Scanner(System.in);
    static Admin a = new Admin();

    public static ArrayList<String> faciAdd = new ArrayList<>(); // my arraylist for adding facilities
    static HashMap<String, HashMap<String, String>> guestReservations = new HashMap<>(); // my hashmap for guest
    static HashMap<String, HashMap<String, String>> pendingReservations = new HashMap<>(); // reservations
    static double receiptPaymentAmount;
    static String addFaci;

    public static void adminMenu() throws InterruptedException {

        boolean run = true;

        ClearConsole.clearConsole();
        newLine(5);

        while (run) {

            int guestReservationCount = 0;
            for (Map.Entry<String, HashMap<String, String>> entry : guestReservations.entrySet()) {
                guestReservationCount += entry.getValue().size(); // count all reservations for each guest
            }

            int pendingReservationCount = 0;
            for (Map.Entry<String, HashMap<String, String>> entry : pendingReservations.entrySet()) {
                pendingReservationCount += entry.getValue().size();
            }

            System.out.println("\t\t\t\t\t\t\t\t\t\s\s\sADMIN MENU\n");
            System.out.println("\n\t\t\t\t\t\t\sWelcome, Admin :)");
            System.out.println("\t\t\t\t\t\t\sGuests with reservations: " + guestReservationCount);
            System.out.println("\t\t\t\t\t\t\sPending reservations: " + pendingReservationCount);

            System.out.print("""
                    \n\t\t\t\t\t\t\s[1] View Reservation
                    \t\t\t\t\t\t\s[2] View Available Facilities
                    \t\t\t\t\t\t\s[3] Add Facility
                    \t\t\t\t\t\t\s[4] Remove Reservations
                    \t\t\t\t\t\t\s[5] Approve Reservations
                    \t\t\t\t\t\t\s[6] Logout
                    \n\t\t\t\t\t\t\t\t\t\s>>\s""");
            try {
                int choice = scnr.nextInt();

                switch (choice) {
                    case 1:
                        ClearConsole.clearConsole();
                        viewRes();
                        break;

                    case 2:
                        ClearConsole.clearConsole();
                        viewAvail();
                        break;

                    case 3:
                        ClearConsole.clearConsole();
                        addFaci();
                        break;

                    case 4:
                        ClearConsole.clearConsole();
                        removeFaci();
                        break;

                    case 5:
                        ClearConsole.clearConsole();
                        approveRsrv();
                        break;

                    case 6:
                        ClearConsole.clearConsole();
                        adminLogin.adminStart();
                        break;

                    default:
                        System.out.println(
                                "\n                                                                         Invalid Input");
                        scnr.nextLine();
                        Thread.sleep(1000);
                        ClearConsole.clearConsole();
                        adminMenu();
                }
            } catch (InputMismatchException e) {
                System.out.println(
                        "\n                                                                         Invalid Input");
                scnr.nextLine();
                Thread.sleep(1000);
                ClearConsole.clearConsole();
                adminMenu();
            }
        }
    }

    public static void viewRes() throws InterruptedException {

        newLine(5);

        if (guestReservations.isEmpty()) {
            System.out.println("\t\t\t\t\t\t\t\t\tNo guest reservations found.");
            scnr.nextLine();
            scnr.nextLine();
            ClearConsole.clearConsole();
            return;
        }

        System.out.println("\t\t\t\t\t\t\t\t\t\s\s\sGUEST RESERVATIONS");
        int counter = 1;

        for (Map.Entry<String, HashMap<String, String>> guestEntry : guestReservations.entrySet()) {
            String guestKey = guestEntry.getKey();
            HashMap<String, String> rsrvFaci = guestEntry.getValue();
            System.out.println("\t\t\t\t\t\t\t\tGuest: " + guestKey);

            if (rsrvFaci.isEmpty()) {
                System.out.println("\t\t\t\t\t\t\t\t\t\s\sNo reserved facilities.");

            } else {
                System.out.print("\t\t\t\t\t\t\t\t");
                for (Map.Entry<String, String> faciEntry : rsrvFaci.entrySet()) {
                    System.out.println("" + counter + ". Facility: " + faciEntry.getKey() +
                            " | Details: \n\t\t\t\t\t\t\t\t" + faciEntry.getValue());
                    System.out.println();
                    counter++;
                }
            }
            System.out.println();
        }

        scnr.nextLine();
        scnr.nextLine();
        ClearConsole.clearConsole();
        newLine(5);
        adminMenu();
    }

    public static void viewAvail() throws InterruptedException {

        newLine(5);
        System.out.println("\t\t\t\t\t\t\t\t\t\s\s\sAVAILABLE FACILITIES");

        if (faciAdd.isEmpty()) {
            System.out.println("""
                    \n\t\t\t\t\t\t\t\t--------------------------------------------
                    \t\t\t\t\t\t\t\tThere are no currently available facilities.
                    \t\t\t\t\t\t\t\t--------------------------------------------
                    """);
        } else {
            int i = 1;
            for (String avail : faciAdd) {
                System.out.println("\t\t\t\t\t\t\s" + i + ". " + avail);
                i++;
            }
        }

        scnr.nextLine();
        ClearConsole.clearConsole();
    }

    public static void addFaci() throws InterruptedException {

        newLine(5);

        System.out.println("\t\t\t\t\t\t\t\t\t\s\s\sADDING FACILITIES\n");
        System.out.print("\t\t\t\t\t\t\t\s\s\sEnter Facility: ");
        scnr.nextLine();
        addFaci = scnr.nextLine();

        if (faciAdd.contains(addFaci)) {
            System.out.println("\n\t\t\t\t\t\t\t\t\s\sThis Facility Is Already Available!");
            scnr.nextLine();
            ClearConsole.clearConsole();
        } else {
            if (addFaci.isEmpty()) {
                System.out.println("\n\t\t\t\t\t\t\t\t\s\s\s\s\s\sYou didn't add anything!!!");
                Thread.sleep(1000);
                ClearConsole.clearConsole();
            } else {
                faciAdd.add(addFaci);
                System.out.println("\n\t\t\t\t\t\t\t\t\s\s\s\s\s\sFacility Added Successfully!");
                scnr.nextLine();
                ClearConsole.clearConsole();
            }
        }
        System.out.print("""
                \n\n\n\n\n\n\n\n\n\n\t\t\t\t\t\t\t\t\sDo you want to add another facility?
                \n\t\t\t\t\t\t\t\t\s[1] Yes
                \t\t\t\t\t\t\t\t\s[2] No
                \n\t\t\t\t\t\t\t\t\t\s>>\s""");
        try {
            int choice = scnr.nextInt();
            switch (choice) {
                case 1 -> {
                    ClearConsole.clearConsole();
                    addFaci();
                }
                case 2 -> adminMenu();
                default -> {
                    System.out.println("\t\t\t\t\t\t\sInvalid input. Please Try Again!");
                    ClearConsole.clearConsole();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("\t\t\t\t\t\t\sInvalid input. Please enter a valid number.");
            scnr.nextLine();
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            newLine(5);
        }

    }

    public static void removeFaci() throws InterruptedException {

        newLine(5);

        if (guestReservations.isEmpty()) {
            System.out.println("\t\t\t\t\t\t\t\t\tNo reservations found.");
            scnr.nextLine();
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            newLine(5);
            return;
        }

        System.out.println("\n\t\t\t\t\t\t\t\t\t\s\s\sGUEST RESERVATIONS");
        int reservationCounter = 1; // counter sa mga reservations
        HashMap<Integer, String> reservationMap = new HashMap<>(); // map to track reservations by index

        // display all reservations
        System.out.print("\t\t\t\t\t\t\t\t");
        for (Map.Entry<String, HashMap<String, String>> guestEntry : guestReservations.entrySet()) {
            String guestKey = guestEntry.getKey();
            HashMap<String, String> rsrvFaci = guestEntry.getValue();

            System.out.println("Guest: " + guestKey);
            if (rsrvFaci.isEmpty()) {
                System.out.println(" \t\t\t\t\t\t\t\t\s\s\s\sNo reserved facilities.");
                Thread.sleep(1000);
                ClearConsole.clearConsole();
                newLine(5);
            } else {
                System.out.print("\t\t\t\t\t\t\t\t");
                for (Map.Entry<String, String> faciEntry : rsrvFaci.entrySet()) {
                    System.out.println(reservationCounter + ". Facility: " + faciEntry.getKey() +
                            " | Details: " + faciEntry.getValue() +
                            " | Reserved by: " + guestKey);
                    reservationMap.put(reservationCounter, guestKey + ":" + faciEntry.getKey());
                    reservationCounter++;
                }
            }
        }

        if (reservationMap.isEmpty()) {
            System.out.println("\t\t\t\t\t\tNo reservations available to remove.");
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            scnr.nextLine();
            newLine(5);
            return;
        }
        System.out.print("\n\t\t\t\t\t\t\sEnter the number of the reservation you want to remove (0 to cancel): ");

        try {
            int choice = scnr.nextInt();
            scnr.nextLine();

            if (choice == 0) {
                System.out.println(" \t\t\t\t\t\t\t\t\s\s\s\sNo changes made.");
                scnr.nextLine();
                Thread.sleep(1000);
                ClearConsole.clearConsole();
                newLine(5);
                return;
            }

            if (reservationMap.containsKey(choice)) {
                String[] reservationDetails = reservationMap.get(choice).split(":");
                String guestKey = reservationDetails[0];
                String facility = reservationDetails[1];

                // remove the selected reservation
                guestReservations.get(guestKey).remove(facility);
                if (guestReservations.get(guestKey).isEmpty()) {
                    guestReservations.remove(guestKey); // remove guest if no reservations remain
                }
                System.out.println("\t\t\t\t\t\t\t\s\s\sReservation removed successfully!");
                Thread.sleep(1000);
                ClearConsole.clearConsole();
                newLine(5);
            } else {
                System.out.println(" \t\t\t\t\t\t\t\t\s\s\s\sInvalid input.");
                scnr.nextLine();
                Thread.sleep(1000);
                ClearConsole.clearConsole();
                newLine(5);
            }
        } catch (InputMismatchException e) {
            System.out.println(" Invalid input.");
            scnr.nextLine();
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            newLine(5);
        }
    }

    public static void approveRsrv() throws InterruptedException {

        newLine(5);

        if (pendingReservations.isEmpty()) {
            System.out.println("\t\t\t\t\t\t\t\t\s\s\s\sNo pending reservations.");
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            scnr.nextLine();
            newLine(5);
            return;
        }

        while (true) {
            System.out.println("\n\t\t\t\t\t\t\t\t\sPENDING RESERVATIONS\n");

            int counter = 1;
            HashMap<Integer, Map.Entry<String, Map.Entry<String, String>>> reservationMap = new HashMap<>();

            // Display all pending reservations
            for (Map.Entry<String, HashMap<String, String>> entry : pendingReservations.entrySet()) {
                String guestKey = entry.getKey();
                HashMap<String, String> reservations = entry.getValue();

                for (Map.Entry<String, String> resEntry : reservations.entrySet()) {
                    System.out.println("\t\t\t\t\t\t" + counter + ". Guest: " + guestKey);
                    System.out.println("\t\t\t\t\t\t\s   Facility: " + resEntry.getKey());
                    System.out.println("\t\t\t\t\t\t\s   Details: " + resEntry.getValue());
                    reservationMap.put(counter, Map.entry(guestKey, resEntry));
                    System.out.println();
                    counter++;
                }
            }

            if (reservationMap.isEmpty()) {
                System.out.println("\n\t\t\t\t\t\t\t\t\sNo more pending reservations.");
                Thread.sleep(1000);
                ClearConsole.clearConsole();
                newLine(5);
                return;
            }

            System.out.print("\n\t\t\t\t\t\t\sSelect a reservation to approve/reject (0 to exit): ");
            int choice;
            try {
                choice = scnr.nextInt();
                scnr.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\n\t\t\t\t\t\t\t\t\sInvalid input. Please enter a valid number.");
                scnr.nextLine();
                Thread.sleep(1000);
                ClearConsole.clearConsole();
                continue;
            }

            if (choice == 0) {
                System.out.println("\n\t\t\t\t\t\t\t\t\sReturning to the admin menu...");
                Thread.sleep(1000);
                ClearConsole.clearConsole();
                newLine(5);
                return;
            }

            if (!reservationMap.containsKey(choice)) {
                System.out.println("\n\t\t\t\t\t\t\t\t\sInvalid choice. Please try again.");
                Thread.sleep(1000);
                ClearConsole.clearConsole();
                continue;
            }

            // Process the selected reservation
            Map.Entry<String, Map.Entry<String, String>> selectedReservation = reservationMap.get(choice);
            String guestKey = selectedReservation.getKey();
            String facility = selectedReservation.getValue().getKey();
            String details = selectedReservation.getValue().getValue();

            ClearConsole.clearConsole();
            newLine(5);

            System.out.println("\n\t\t\t\t\t\t\t\t\sYou selected:");
            System.out.println("\t\t\t\t\t\t\sGuest: " + guestKey);
            System.out.println("\t\t\t\t\t\t\sFacility: " + facility);
            System.out.println("\t\t\t\t\t\t\sDetails: " + details);

            System.out.print("""
                    \n\t\t\t\t\t\t\t\t\sWhat would you like to do?
                    \t\t\t\t\t\t\t\t\s[1] Approve
                    \t\t\t\t\t\t\t\t\s[2] Reject
                    \n\t\t\t\t\t\t\t\t\s>>\s""");
            int action;
            try {
                action = scnr.nextInt();
                scnr.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\n\t\t\t\t\t\t\t\t\sInvalid input. Please enter a valid number.");
                scnr.nextLine();
                Thread.sleep(1000);
                ClearConsole.clearConsole();
                continue;
            }

            if (action == 1) {
                System.out.println(
                        "\t\t\t\t\t\t\t\t\sApproving reservation for Guest: " + guestKey + ", Facility: " + facility);
                guestReservations.putIfAbsent(guestKey, new HashMap<>());
                guestReservations.get(guestKey).put(facility, details + " (Approved)");
                pendingReservations.get(guestKey).remove(facility);
                if (pendingReservations.get(guestKey).isEmpty()) {
                    pendingReservations.remove(guestKey);
                }
                System.out.println("\t\t\t\t\t\t\t\t\sReservation approved!");

                // Process payment
                processPayment(guestKey, facility);

            } else if (action == 2) {
                System.out.println(
                        "\t\t\t\t\t\t\t\t\sRejecting reservation for Guest: " + guestKey + ", Facility: " + facility);
                pendingReservations.get(guestKey).remove(facility);
                if (pendingReservations.get(guestKey).isEmpty()) {
                    pendingReservations.remove(guestKey);
                }
                System.out.println("\t\t\t\t\t\t\t\t\sReservation rejected!");
            } else {
                System.out.println("\n\t\t\t\t\t\t\t\t\sInvalid input. Please try again.");
                Thread.sleep(1000);
                ClearConsole.clearConsole();
                continue;
            }

            Thread.sleep(1000);
            ClearConsole.clearConsole();
            newLine(5);
        }
    }

    public static void processPayment(String guestKey, String facility) throws InterruptedException {

        ClearConsole.clearConsole();
        newLine(5);

        System.out.println("\t\t\t\t\t\t\sProcessing payment for Guest: " + guestKey + ", Facility: " + facility);

        if (!guestReservations.containsKey(guestKey) || !guestReservations.get(guestKey).containsKey(facility)) {
            System.out.println("\t\t\t\t\t\t\sReservation not found.");
            return;
        }

        String reservationDetails = guestReservations.get(guestKey).get(facility);
        System.out.println("\t\t\t\t\t\t\sReservation Details: " + "\t\t\t\t\t\t\s" + reservationDetails);

        if (reservationDetails.contains("Payment Status: Paid")) {
            System.out.println("\t\t\t\t\t\t\sThis reservation has already been paid.");
            return;
        }

        // extract the total fee from the reservation details

        System.out.printf("\t\t\t\t\t\t\sTotal Fee: %.2f%n", guestMenu.totalFee);

        double paymentAmount = 0.0;

        while (paymentAmount < guestMenu.totalFee) {
            System.out.printf("\n\n\n\n\n\t\t\t\t\t\t\t\sYou still owe: %.2f%n", guestMenu.totalFee - paymentAmount);
            System.out.print("\n\t\t\t\t\t\t\t\sEnter payment amount: ");

            try {
                // read input as a string and parse it to a double
                String input = scnr.nextLine().trim();
                double enteredAmount = Double.parseDouble(input);

                if (enteredAmount <= 0) {
                    System.out.println("\t\t\t\t\t\t\sPayment amount must be greater than zero. Please try again.");
                    continue;
                }

                paymentAmount += enteredAmount;

                if (paymentAmount < guestMenu.totalFee) {
                    System.out.printf("\t\t\t\t\t\t\sPartial payment received. Remaining balance: %.2f%n",
                            guestMenu.totalFee - paymentAmount);
                    Thread.sleep(1000);
                    ClearConsole.clearConsole();
                    newLine(5);
                }
            } catch (NumberFormatException e) {
                System.out.println("\t\t\t\t\t\t\sInvalid input. Please enter a valid numeric amount.");
                Thread.sleep(1000);
                ClearConsole.clearConsole();
            }
        }

        double change = paymentAmount - guestMenu.totalFee;
        System.out.printf("\n\t\t\t\t\t\t\t\sPayment successful! Change: %.2f%n", change);
        Receipt.receipt();
        // update the reservation details to mark it as paid
        reservationDetails = reservationDetails.replace("Payment Status: Pending", "Payment Status: Paid");
        guestReservations.get(guestKey).put(facility, reservationDetails);
        System.out.println("\t\t\t\t\t\t\t\sReservation updated successfully!");
        scnr.nextLine();
        ClearConsole.clearConsole();
        newLine(5);
    }

    private static void newLine(int x) {
        for (int i = 0; i < x; i++) {
            System.out.println("\n");
        }
    }

}

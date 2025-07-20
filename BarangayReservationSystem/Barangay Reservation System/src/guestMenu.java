import java.util.Scanner;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class guestMenu extends adminMenu {

    static Scanner scnr = new Scanner(System.in);
    static Guest g = new Guest();
    static Admin a = new Admin();

    // Store reservations by guest email instead of username
    static HashMap<String, HashMap<String, String>> guestReservations = new HashMap<>();

    static int chairSeats, speakers, tables;
    static double totalFee, hourFee, durationInHours, hourlyRate;
    static String receiptStartDate, receiptEndDate, facility, reservationDate;
    static int reservedChair, reservedSpeaker, reservedTable;

    static HashMap<String, Double> guestTotalFees = new HashMap<>();
    static HashMap<String, Double> facilityRates = new HashMap<>();

    static {
        facilityRates.put(addFaci, 50.0);
    }

    public static void guestMenu() throws InterruptedException {

        boolean run = true;

        newLine(5);

        while (run) {

            System.out.println("\t\t\t\t\t\t\t\t\t\s\s\sGUEST MENU");
            System.out
                    .println("\n\t\t\t\t\t\t\t\t\sWelcome, Guest " + guestLogin.currentGuestUsername);
            System.out.print("""
                    \n\t\t\t\t\t\t\t\t\s[1] View My Reservations
                    \t\t\t\t\t\t\t\t\s[2] View Available Facilities
                    \t\t\t\t\t\t\t\t\s[3] View Contact Information
                    \t\t\t\t\t\t\t\t\s[4] Cancel Reservations
                    \t\t\t\t\t\t\t\t\s[5] Logout
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
                        viewContact();
                        break;

                    case 4:
                        ClearConsole.clearConsole();
                        cancelRes();
                        break;

                    case 5:
                        ClearConsole.clearConsole();
                        guestLogin.guestStart();
                        break;

                    default:
                        System.out.println(
                                "\n                                                                         Invalid Input");
                        scnr.nextLine();
                        Thread.sleep(1000);
                        ClearConsole.clearConsole();
                        guestMenu();
                }
            } catch (InputMismatchException e) {
                System.out.println(
                        "\n                                                                         Invalid Input");
                scnr.nextLine();
                Thread.sleep(1000);
                ClearConsole.clearConsole();
                guestMenu();
            }
        }
    }

    public static void viewRes() throws InterruptedException {
        newLine(5);

        // Use email as the unique identifier
        String guestEmail = guestLogin.email;

        if (!guestReservations.containsKey(guestEmail) || guestReservations.get(guestEmail).isEmpty()) {
            System.out.println("\n\t\t\t\t\t\t\t\t\tNo reservations found.");
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            newLine(5);
            return;
        }

        HashMap<String, String> rsrvFaci = guestReservations.get(guestEmail);
        System.out.println("\n\t\t\t\t\t\t\t\t\t\s\s\sMY RESERVATIONS");
        int counter = 1;

        for (Map.Entry<String, String> entry : rsrvFaci.entrySet()) {
            System.out.println("\t\t\t\t\t\t\t" + counter + ". " + entry.getKey() + " - " + entry.getValue());
            counter++;
        }
        scnr.nextLine();
        scnr.nextLine();
        ClearConsole.clearConsole();
        newLine(5);
    }

    public static void viewAvail() throws InterruptedException {

        newLine(5);

        int counter = 1;

        System.out.println("\n\t\t\t\t\t\t\t\t\s\s\sAVAILABLE FACILITIES");
        System.out.print("");
        for (String avail : adminMenu.faciAdd) {
            System.out.println("\t\t\t\t\t\t" + counter + ". " + avail);
            counter++;
        }

        if (adminMenu.faciAdd.isEmpty()) {
            System.out.println("\n\t\t\t\t\t\t\t\tThere are no currently available facilities.");
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            newLine(5);
        }

        scnr.nextLine();
        scnr.nextLine();
        ClearConsole.clearConsole();
        newLine(5);
    }

    public static void viewContact() throws InterruptedException {

        newLine(5);

        System.out.println("\t\t\t\t\t\t\t\t\s\s\sADMIN CONTACT INFORMATION\n");

        int adminCounter = 1;
        for (Map.Entry<String, Admin> entry : Admin.adminAccounts.entrySet()) {
            Admin admin = entry.getValue();
            System.out.println("\t\t\t\t\t\t\t" + adminCounter + ". Admin Username: " + admin.username);
            System.out.println("\t\t\t\t\t\t\t   Admin Contact Number: " + admin.cpNumber + "\n");
            adminCounter++;
        }

        if (Admin.adminAccounts.isEmpty()) {
            System.out.println("\n\t\t\t\t\t\t\tNo admins are currently available.");
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            scnr.nextLine();
            newLine(5);
            return;
        }

        System.out.print("\t\t\t\t\t\t\tSelect an admin:\s");
        int choice;
        try {
            choice = scnr.nextInt();
            scnr.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("\n\t\t\t\t\t\t\tInvalid input. Please enter a valid number.");
            scnr.nextLine();
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            newLine(5);
            return;
        }

        if (choice < 1 || choice > Admin.adminAccounts.size()) {
            System.out.println("\n\t\t\t\t\t\t\tInvalid choice. Please try again.");
            return;
        }

        Admin selectedAdmin = (Admin) Admin.adminAccounts.values().toArray()[choice - 1];

        ClearConsole.clearConsole();

        newLine(5);

        System.out.println("\t\t\t\t\t\t\tYou selected Admin: " + selectedAdmin.username);
        System.out.print("""
                \n\t\t\t\t\t\t\tWhat would you like to do?\n
                \t\t\t\t\t\t\t[1] View the cost of rented equipment and hourly rates
                \t\t\t\t\t\t\t[2] Reserve a facility
                \t\t\t\t\t\t\t[3] Go back to the guest menu
                \n\t\t\t\t\t\t\t\t\t>>\s""");

        try {
            int action = scnr.nextInt();
            scnr.nextLine();

            switch (action) {
                case 1:
                    ClearConsole.clearConsole();
                    newLine(5);
                    System.out.println("""
                            \n\t\t\t\t\t\t\tCOST OF RENTED EQUIPMENT AND HOURLY RATES
                            \t\t\t\t\t\t\tChair Fee: Php 10.00  per chair
                            \t\t\t\t\t\t\tSpeaker Fee: Php 100.00 per speaker
                            \t\t\t\t\t\t\tTable Fee: Php 50.00 per table
                            \t\t\t\t\t\t\tHourly Rate of Facilities: Php 250.00 per hour.""");
                    scnr.nextLine();
                    ClearConsole.clearConsole();
                    newLine(5);
                    break;

                case 2:
                    ClearConsole.clearConsole();
                    newLine(5);
                    faciRes();
                    break;

                case 3:
                    System.out.println("\n\t\t\t\t\t\t\t\tReturning to the guest menu...");
                    Thread.sleep(1000);
                    ClearConsole.clearConsole();
                    newLine(5);
                    break;

                default:
                    System.out.println("\n\t\t\t\t\t\t\t\tInvalid Input");
                    Thread.sleep(1000);
                    ClearConsole.clearConsole();
            }
        } catch (InputMismatchException e) {
            System.out.println("\n\t\t\t\t\t\t\t\tInvalid Input");
            scnr.nextLine();
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            newLine(5);
            return;
        }
    }

    public static void cancelRes() throws InterruptedException {
        newLine(5);

        String guestEmail = guestLogin.email;

        if (!guestReservations.containsKey(guestEmail) || guestReservations.get(guestEmail).isEmpty()) {
            System.out.println("\n\t\t\t\t\t\t\t\t\tNo pending reservations found.");
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            newLine(5);
            return;
        }

        HashMap<String, String> pendingRes = guestReservations.get(guestEmail);
        System.out.println("\t\t\t\t\t\t\t\tMY PENDING RESERVATIONS\n");
        int counter = 1;
        HashMap<Integer, String> reservationMap = new HashMap<>();

        for (Map.Entry<String, String> entry : pendingRes.entrySet()) {
            System.out.println("\t\t\t\t\t\t" + counter + ". " + entry.getKey() + " - " + entry.getValue());
            reservationMap.put(counter, entry.getKey());
            counter++;
        }

        System.out.print("\n\t\t\t\t\t\tEnter the number of the reservation you want to cancel: ");
        int choice;
        try {
            choice = scnr.nextInt();
            scnr.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("\n\t\t\t\t\t\t\tInvalid input. Please enter a valid number.");
            scnr.nextLine();
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            newLine(5);
            return;
        }

        if (!reservationMap.containsKey(choice)) {
            System.out.println("\n\t\t\t\t\t\t\tInvalid choice. Please try again.");
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            newLine(5);
            return;
        }

        String facilityToCancel = reservationMap.get(choice);

        pendingRes.remove(facilityToCancel);

        if (pendingRes.isEmpty()) {
            guestReservations.remove(guestEmail);
        }

        System.out
                .println("\n\t\t\t\t\t\t\t\tReservation for " + facilityToCancel + " has been successfully canceled.");
        Thread.sleep(1000);
        ClearConsole.clearConsole();
        newLine(5);
    }

    public static void faciRes() throws InterruptedException {

        String guestEmail = guestLogin.email;

        System.out.println("\n\t\t\t\t\t\t\t\tAVAILABLE FACILITIES");
        int counter = 1;
        HashMap<Integer, String> facilityMap = new HashMap<>();

        for (String facility : adminMenu.faciAdd) {
            System.out.println("\t\t\t\t\t\t" + counter + ". " + facility);
            facilityMap.put(counter, facility);
            counter++;
        }

        if (adminMenu.faciAdd.isEmpty()) {
            System.out.println("\n\t\t\t\t\t\tThere are no currently available facilities.");
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            return;
        }

        System.out.print("\n\t\t\t\t\t\tSelect the number of the facility you want to reserve: ");
        int facilityChoice;
        try {
            facilityChoice = scnr.nextInt();
            scnr.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("\n\t\t\t\t\t\tInvalid input. Please enter a valid number.");
            scnr.nextLine();
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            newLine(5);
            return;
        }

        while (!facilityMap.containsKey(facilityChoice)) {
            System.out.println("\n\t\t\t\t\t\tInvalid choice. Please select a valid facility number.");
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            newLine(5);
            System.out.print("\n\t\t\t\t\t\tSelect the number of the facility you want to reserve: ");
            try {
                facilityChoice = scnr.nextInt();
                scnr.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\n\t\t\t\t\t\t\t\tInvalid input. Please enter a valid number.");
                scnr.nextLine();
                Thread.sleep(1000);
                ClearConsole.clearConsole();
                newLine(5);
                return;
            }
        }

        facility = facilityMap.get(facilityChoice);

        LocalDate today = LocalDate.now();
        LocalDate resDate;
        boolean validDate = false;
        reservationDate = null;

        while (!validDate) {
            System.out.print("\n\t\t\t\t\t\tEnter date of reservation (YYYY-MM-DD): ");
            reservationDate = scnr.nextLine();

            try {
                resDate = LocalDate.parse(reservationDate, DateTimeFormatter.ISO_LOCAL_DATE);
                today = LocalDate.now();

                if (resDate.isBefore(today)) {
                    System.out.println(
                            "\n\t\t\t\t\t\tYou cannot reserve a facility for a past date. Please enter a valid date.");
                    Thread.sleep(1000);
                    ClearConsole.clearConsole();
                    newLine(5);
                } else {
                    validDate = true;
                }
            } catch (DateTimeParseException e) {
                System.out.println(
                        "\n\t\t\t\t\t\tInvalid date format. Please enter the date in YYYY-MM-DD format.");
                Thread.sleep(1000);
                ClearConsole.clearConsole();
                newLine(5);
            }
        }

        System.out.print("\n\t\t\t\t\t\tEnter start time of reservation (HH:MM): ");
        String startTime = scnr.nextLine();
        receiptStartDate = startTime;
        LocalTime start;
        try {
            start = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            System.out.println("\n\t\t\t\t\t\tInvalid time format. Please enter the time in HH:MM format.");
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            newLine(5);
            return;
        }

        System.out.print("\n\t\t\t\t\t\tEnter end time of reservation (HH:MM): ");
        String endTime = scnr.nextLine();
        receiptEndDate = endTime;
        LocalTime end;
        try {
            end = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:mm"));
            if (end.isBefore(start)) {
                System.out.println(
                        "\n\t\t\t\t\t\tEnd time cannot be before start time. Please enter a valid time.");
                Thread.sleep(1000);
                ClearConsole.clearConsole();
                newLine(5);
                return;
            }
        } catch (DateTimeParseException e) {
            System.out.println("\n\t\t\t\t\t\tInvalid time format. Please enter the time in HH:MM format.");
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            newLine(5);
            return;
        }

        if (isTimeConflict(facility, reservationDate, start, end)) {
            System.out.println(
                    "\n\t\t\t\t\t\tThe selected time slot is already reserved. Please choose a different time.");
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            newLine(5);
            return;
        }

        System.out.print("\n\t\t\t\t\t\tEnter the number of chair seats to reserve: ");
        chairSeats = scnr.nextInt();
        reservedChair = chairSeats;

        System.out.print("\n\t\t\t\t\t\tEnter the number of speakers to reserve: ");
        speakers = scnr.nextInt();
        reservedSpeaker = speakers;

        System.out.print("\n\t\t\t\t\t\tEnter the number of tables to reserve: ");
        tables = scnr.nextInt();
        reservedTable = tables;
        scnr.nextLine();

        hourlyRate = facilityRates.getOrDefault(facility, 250.0);
        if (hourlyRate == 0.0) {
            System.out.println(
                    "\n\t\t\t\t\t\t\t\tHourly rate for this facility is not set. Please contact the admin.");
            return;
        }

        double durationInMinutes = java.time.Duration.between(start, end).toMinutes();
        durationInHours = durationInMinutes / 60.0;
        hourFee = durationInHours * hourlyRate;

        totalFee = ((chairSeats * 10.00) + (speakers * 100.00) + (tables * 50.00)) + hourFee;

        System.out.println("\n\t\t\t\t\t\t\tReservation Duration: " + durationInHours + " hours");
        System.out.println("\t\t\t\t\t\t\tHourly Rate: " + hourlyRate);
        System.out.println("\t\t\t\t\t\t\tTotal Fee: " + totalFee);

        System.out.print("""
                \n\t\t\t\t\t\tDo you want to proceed with this reservation?
                \t\t\t\t\t\t[1] Yes
                \t\t\t\t\t\t[2] No
                \n\t\t\t\t\t\t\t\t>>\s""");

        try {
            int choice = scnr.nextInt();
            scnr.nextLine();
            ClearConsole.clearConsole();

            if (choice == 1) {
                String reservationDetails = "\n\t\t\t\t\t\tDate: " + reservationDate +
                        ",\n\t\t\t\t\t\tStart Time: " + startTime +
                        ",\n\t\t\t\t\t\tEnd Time: " + endTime +
                        ",\n\t\t\t\t\t\tDuration: " + durationInHours + " hours" +
                        ",\n\t\t\t\t\t\tChair Seats: " + chairSeats +
                        ",\n\t\t\t\t\t\tSpeakers: " + speakers +
                        ",\n\t\t\t\t\t\tTables: " + tables +
                        ",\n\t\t\t\t\t\tTotal Fee: " + totalFee;

                HashMap<String, String> pendingRes = guestReservations.getOrDefault(guestEmail, new HashMap<>());
                pendingRes.put(facility, reservationDetails + " (Pending Approval)");
                guestReservations.put(guestEmail, pendingRes);

                HashMap<String, String> adminPendingRes = adminMenu.pendingReservations.getOrDefault(guestEmail,
                        new HashMap<>());
                adminPendingRes.put(facility, reservationDetails + " (Pending Approval)");
                adminMenu.pendingReservations.put(guestEmail, adminPendingRes);

                System.out.println(
                        "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t\t\t\tReservation submitted for admin approval!");
                Thread.sleep(1000);
                ClearConsole.clearConsole();
                newLine(5);
            } else {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t\t\t\tReservation canceled.");
                scnr.nextLine();
                Thread.sleep(1000);
                ClearConsole.clearConsole();
                newLine(5);
            }
        } catch (InputMismatchException e) {
            System.out.println("\n\t\t\t\t\t\t\t\tInvalid input.");
            scnr.nextLine();
            Thread.sleep(1000);
            ClearConsole.clearConsole();
            newLine(5);
        }
    }

    private static boolean isTimeConflict(String facility, String reservationDate, LocalTime start, LocalTime end) {

        for (HashMap<String, String> reservations : guestReservations.values()) {
            if (reservations.containsKey(facility)) {
                String details = reservations.get(facility);
                if (isConflict(details, reservationDate, start, end)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isConflict(String details, String reservationDate, LocalTime start, LocalTime end) {
        String[] parts = details.split(",");
        String date = parts[0].split(": ")[1].trim();
        String reservedStartTime = parts[1].split(": ")[1].trim();
        String reservedEndTime = parts[2].split(": ")[1].trim();

        if (date.equals(reservationDate)) {
            LocalTime reservedStart = LocalTime.parse(reservedStartTime);
            LocalTime reservedEnd = LocalTime.parse(reservedEndTime);

            return !(end.isBefore(reservedStart) || start.isAfter(reservedEnd));
        }

        return false;
    }

    private static void newLine(int x) {
        for (int i = 0; i < x; i++) {
            System.out.println("\n");
        }
    }

}

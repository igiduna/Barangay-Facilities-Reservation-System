import java.util.HashMap;
import java.util.Scanner;

public class guestPayment extends guestMenu {

    static Scanner scnr = new Scanner(System.in);
    static HashMap<String, Boolean> paymentStatus = new HashMap<>();

    public static void processPayment(String guestKey, String facility, String reservationDetails) {
        System.out.println("\n--PAYMENT PROCESS--");
        System.out.println("Guest: " + guestKey);
        System.out.println("Facility: " + facility);
        System.out.println("Reservation Details: " + reservationDetails);
        System.out.println("Total Fee: " + guestMenu.totalFee); // this is the total price

        System.out.print("Enter payment amount: ");
        double paymentAmount = scnr.nextDouble();
        scnr.nextLine();

        if (paymentAmount > guestMenu.totalFee) {
            System.out.println("Payment successful! Your reservation is now confirmed.");

            // if the payment is paid
            String reservationKey = guestKey + ":" + facility;
            paymentStatus.put(reservationKey, true);

        } else {
            System.out.println("Invalid payment amount. Please try again.");
            processPayment(guestKey, facility, reservationDetails);
        }
    }

    public static boolean isPaid(String guestKey, String facility) {
        String reservationKey = guestKey + ":" + facility;
        return paymentStatus.getOrDefault(reservationKey, false);
    }

}

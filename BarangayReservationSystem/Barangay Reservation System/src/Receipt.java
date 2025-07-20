import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Receipt {

    public static void receipt() {

        try {

            BufferedWriter bW = new BufferedWriter(new FileWriter("Receipt.txt"));

            bW.write(
                    """
                                                                                                      \t\tRepublic of the Philippines
                                                                                                        \t\t\s\s\s\s\sProvince of Isabela
                                                                                                     \t\tMunicipality of Cauayan City
                                                                                                           \t\t\s\s\s\sBarangay Marabulig 1


                                                                                                    \t\t\s\s\s\sRESERVATION RECEIPT



                            TO WHOM IT MAY CONCERN:\n\n
                                """);
            bW.write("\tThis document is a reservation receipt from " + adminLogin.receiptUserName + " to "
                    + guestLogin.receiptGuestName
                    + " for the amount of PHP " + String.format("%.2f", guestMenu.totalFee) + " for "
                    + guestLogin.receiptGuestName
                    + "'s claim of reservation to the facility " + guestMenu.facility
                    + " of the barangay Marabulig 1, Cauayan City Isabela.");

            bW.write(
                    "\n\n                                                                            \t\t\s\s\s\sRESERVATION DETAILS");
            bW.write("\n\nChair Seats            : " + guestMenu.reservedChair);
            bW.write("\nTables                 : " + guestMenu.reservedTable);
            bW.write("\nSpeakers               : " + guestMenu.reservedSpeaker);
            bW.write("\nReservation Start Date : " + guestMenu.reservationDate);
            bW.write("\nReservation Start Time : " + guestMenu.receiptStartDate);
            bW.write("\nReservation End Time   : " + guestMenu.receiptEndDate);
            bW.write(
                    """
                            \n\n\n\n\n\n\t\t\t      _______________________                                                                                         _______________________
                            \t\t\t         Guest's Signature                                                                                               Admin's Signature
                                    """);

            bW.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

import java.util.*;

public class Guest {

    public String username;
    public String password;
    public String cpNumber;
    public String email;

    protected HashMap<String, guestAccount> guest = new HashMap<>();
    public ArrayList<String> guestInfo = new ArrayList<>();
    public static HashMap<String, Guest> guestAccounts = new HashMap<>();

    public Guest(String username, String password, String cpNumber, String email) {
        this.username = username;
        this.password = password;
        this.cpNumber = cpNumber;
        this.email = email;
    }

    public Guest() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCpNumber() {
        return cpNumber;
    }

    public String getEmail() {
        return email;
    }

    public static void addGuestAccount(String username, String email, String cpNumber, String password) {
        Guest newGuest = new Guest(username, email, cpNumber, password);
        guestAccounts.put(email, newGuest);
    }

    // Check if an account exists
    public static boolean accountExists(String email) {
        return guestAccounts.containsKey(email);
    }

    // Validate login credentials
    public static boolean validateLogin(String email, String password) {
        if (guestAccounts.containsKey(email)) {
            return guestAccounts.get(email).getPassword().equals(password);
        }
        return false;
    }

}

class guestAccount {

    public String email;
    public String username;
    public String cpNumber;

    public guestAccount(String username, String cpNumber, String email) {
        this.username = username;
        this.cpNumber = cpNumber;
        this.email = email;
    }

    public String getUsername(String username) {
        return username;
    }

    public String getEmail(String email) {
        return email;
    }

    public String getCpNumber(String cpNumber) {
        return cpNumber;
    }

    public class ReservationManager {

        // Map to store pending reservations for each guest
        public static HashMap<String, ArrayList<String>> pendingReservations = new HashMap<>();

        // Add a reservation for a specific guest
        public static void addReservation(String guestKey, String reservationDetails) {
            pendingReservations.putIfAbsent(guestKey, new ArrayList<>());
            pendingReservations.get(guestKey).add(reservationDetails);
        }

        // Get reservations for a specific guest
        public static ArrayList<String> getReservations(String guestKey) {
            return pendingReservations.getOrDefault(guestKey, new ArrayList<>());
        }

        // Remove a reservation for a specific guest
        public static void removeReservation(String guestKey, String reservationDetails) {
            if (pendingReservations.containsKey(guestKey)) {
                pendingReservations.get(guestKey).remove(reservationDetails);
                if (pendingReservations.get(guestKey).isEmpty()) {
                    pendingReservations.remove(guestKey);
                }
            }
        }
    }
}

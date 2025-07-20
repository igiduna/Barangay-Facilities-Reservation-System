import java.util.*;

public class Admin {

    public String username, password, email, cpNumber;

    public static HashMap<String, Admin> adminAccounts = new HashMap<>();

    public Admin(String username, String password, String cpNumber, String email) {
        this.username = username;
        this.password = password;
        this.cpNumber = cpNumber;
        this.email = email;
    }

    public Admin() {
    }

    public static String getUsername(String username) {
        return username;
    }

    public String getPassword(String password) {
        return password;
    }

    public String getCpNumber(String cpNumber) {
        return cpNumber;
    }

    public String getEmail(String email) {
        return email;
    }

    public void addAdminAccount(String username, String password, String cpNumber, String email) {
        Admin Account = new Admin(username, password, cpNumber, email);
        adminAccounts.put(email, Account);
    }

    public static Map<String, Admin> getAdminAccounts() {
        return new HashMap<>();
    }

}

class adminAccount {

    public String email;
    public String username;
    public String cpNumber;

    public adminAccount(String username, String cpNumber, String email) {
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

}

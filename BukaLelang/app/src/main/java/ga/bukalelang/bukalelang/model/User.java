package ga.bukalelang.bukalelang.model;

/**
 * Created by arbudt on 5/25/2017.
 */

public class User {
    private static final User ourInstance = new User();

    public static User getInstance() {
        return ourInstance;
    }

    private User() {
    }

    private static String user_id;
    private static String user_name;
    private static String confirmed;
    private static String token;
    private static String email;
    private static String confirmed_phone;
    private static String omnikey;

    public static String getUser_id() {
        return user_id;
    }

    public static void setUser_id(String user_id) {
        User.user_id = user_id;
    }

    public static String getUser_name() {
        return user_name;
    }

    public static void setUser_name(String user_name) {
        User.user_name = user_name;
    }

    public static String getConfirmed() {
        return confirmed;
    }

    public static void setConfirmed(String confirmed) {
        User.confirmed = confirmed;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        User.token = token;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        User.email = email;
    }

    public static String getConfirmed_phone() {
        return confirmed_phone;
    }

    public static void setConfirmed_phone(String confirmed_phone) {
        User.confirmed_phone = confirmed_phone;
    }

    public static String getOmnikey() {
        return omnikey;
    }

    public static void setOmnikey(String omnikey) {
        User.omnikey = omnikey;
    }
}

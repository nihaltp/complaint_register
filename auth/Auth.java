package auth;

public class Auth {
    /**
     * Checks if the given username is an administrator.
     * 
     * @param username the username to check
     * @return true if the username is an administrator, false otherwise
     */
    public static boolean isAdmin(String username) {
        // check if the user is admin
        return username.equals("admin"); // TODO: replace with actual check
    }

    /**
     * Checks if the given username is in the database.
     * 
     * @param username - the username to check
     * @return true if the username is in the database, false otherwise
     */
    public static boolean isUser(String username) {
        // check if the username is in the database
        return true; // TODO: replace with actual check
    }
}

package com.example.codingnatorpoject.DBConnection;

// password(plain) should be 8~32 characters
// with characters: A-Z, a-z, 0-9, !@#$%^*_
public class PasswordManager {

    // header for password
    /* << Why is this needed? >>
     * We can't recognize a String whether it's already encrypted or not
     * So, to make it able, encrypted String has specific header, which cannot be included in normal pw
     */
    public static String header = "**:";
    public static String specials = "!@#$%^*_";

    static boolean isValidPassword(String pw) {

        // check length
        int l = pw.length();
        if (l < 8 || l > 32) return false;

        // check character-validity
        for (int i = 0; i < l; i++) {
            char c = pw.charAt(i);
            if (!(Character.isAlphabetic(c) || Character.isDigit(c) || specials.contains(c+""))) {
                return false;
            }
        }

        return true;
    }

    static String encrypt(String pw) throws InvalidPasswordException{
        //todo : need more secured way...
        if (!isValidPassword(pw)) throw new InvalidPasswordException();
        return header + pw.hashCode();
    }

    static boolean isEncrypted(String pw) {
        return pw.startsWith(header);
    }

    static class InvalidPasswordException extends Exception {}
}

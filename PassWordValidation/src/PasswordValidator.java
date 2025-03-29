public class PasswordValidator {
    public void validatePassword(String password, String confirmPassword)
            throws WeakPasswordException, PasswordMismatchException {

        if (password.length() < 8) {
            throw new WeakPasswordException("Password must be at least 8 characters long.");
        }

        if (password.length() > 20) {
            throw new WeakPasswordException("Password must not exceed 20 characters.");
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        String specialCharacters = "!@#$%^&*()-+";

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUppercase = true;
            else if (Character.isLowerCase(ch)) hasLowercase = true;
            else if (Character.isDigit(ch)) hasDigit = true;
            else if (specialCharacters.indexOf(ch) >= 0) hasSpecialChar = true;
        }

        if (!hasUppercase || !hasLowercase || !hasDigit || !hasSpecialChar) {
            throw new WeakPasswordException("Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
        }

        if (!password.equals(confirmPassword)) {
            throw new PasswordMismatchException("Passwords do not match.");
        }
    }
}



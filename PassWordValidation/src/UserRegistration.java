import java.util.Scanner;

public class UserRegistration {
    public void registerUser(String password, String confirmPassword) {
        try (ValidationLogger logger = new ValidationLogger()) {
            PasswordValidator validator = new PasswordValidator();
            validator.validatePassword(password, confirmPassword);
            // COMPLETE THIS PART: Log success message
        } catch (WeakPasswordException | PasswordMismatchException e) {
            try (ValidationLogger logger = new ValidationLogger()) {
                // COMPLETE THIS PART: Log failure message
            }
        } finally {
            try (ValidationLogger logger = new ValidationLogger()) {
                // COMPLETE THIS PART: Ensure completion message is printed once
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Confirm password: ");
        String confirmPassword = scanner.nextLine();

        UserRegistration registration = new UserRegistration();
        registration.registerUser(password, confirmPassword);
    }
}
import java.util.Scanner;

public class DivisionApp
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        try
        {
            // Prompt the user to enter two numbers
            System.out.print("Enter the numerator: ");
//TODO
            double numerator = scanner.nextInt();


            System.out.print("Enter the denominator: ");
//TODO
            double denominator = scanner.nextInt();

// Perform division
            System.out.println("The answer is " + numerator/denominator);
//TODO
        }
        catch (ArithmeticException e)
        {
            // Handle division by zero
            System.out.println("Error: Division by zero is not allowed.");
        } finally
        {
            // Cleanup operations
            System.out.println("Division operation complete.");
//TODO
        }
    }

}


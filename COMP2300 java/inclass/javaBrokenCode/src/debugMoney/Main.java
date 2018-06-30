package debugMoney;
import java.util.Scanner;
public class Main {

    /* = = = = = = = = = = = = = = = = = = = =
     *  Controller
     * = = = = = = = = = = = = = = = = = = = = */
    public static void main(String[] args)
    {
      /* = = = = = = = = = = = = = = = = = = = =
       * Model
       * = = = = = = = = = = = = = = = = = = = = */
        // Set up the variables
        float exchangeRate = 1.36F;
        float dollarAmount = 0;
        float euroAmount = 0;
        String input = "";

        // Display a prompt
        System.out.println("Click here and type in how many US dollars.");

        // Create a scanner input object from the Scanner class
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        scanner.close();

        // Calculate the result
        // Convert input string to Float
        dollarAmount = Float.parseFloat(input);
        euroAmount  = dollarAmount / exchangeRate;

     /* = = = = = = = = = = = = = = = = = = = =
      * View
      * = = = = = = = = = = = = = = = = = = = = */
        // Display the result using println( )
        System.out.println("The result is:" + euroAmount + "Euros.");

        // Display the result using printf( )
        System.out.printf("%f US Dollars has the value of %f Euros\n", dollarAmount, euroAmount);

        // Display formatted numbers using printf( )
        System.out.printf("%.2f US Dollars has the value of %.4f Euros\n", dollarAmount, euroAmount);
    }
}

package phoneFormatting;

/* Main.java
  Dustin Frandle dafrandle@yahoo.com
  Written:08/30/2016
  Revised:
 */

import java.util.Scanner;

public class Main {
   public static void main(String[] args) { //------------------------------------ start of main
      int[] phoneNumArray = new int[3];
      int[] phonePartsArray = new int[3];
      boolean startsWithParentheses = false;
      boolean middleDashOrDot = false;
      boolean middleDashOrDotWithParentheses = false;
      boolean hasDash = false;
      boolean hasDot = false;
      int testMiddleDash;
      String input = "n";
      //==========================================================================enter====================================================================================

         System.out.println("-------------------------------------------------------- ENTER DATA ------------------------------------------------------------------");
         System.out.println("Please enter your phone number. Please ONLY include you area code and number. (q to quit)"); // tell user what to do
         Scanner scanner = new Scanner(System.in); // get user input
         input = scanner.nextLine();
         scanner.close();
         System.out.println("You entered: " + input);
         if (input.indexOf("-") > -1 && input.indexOf(".") > -1) {
            System.out.println("please use only dashes '-' or dots '.'");
            return;
         } else if (input.indexOf("-") > -1) {
            hasDash = true;
         } else if (input.indexOf(".") > -1) {
            hasDot = true;
         }
      /*System.out.println("Dash/Dot: hasDash: " + hasDash + "   hasDot: " + hasDot);
      System.out.println("phonePartsArray: " + phonePartsArray[0] + " " + phonePartsArray[1] + " " + phonePartsArray[2]);
      System.out.println("phoneNumArray: " + phoneNumArray[0] + " " + phoneNumArray[1] + " " + phoneNumArray[2]);
      System.out.println("--------------------------------------------------------------------------------------------------------------------------");
      //==================================================================================================================================================================
      //=============================================================================Part 1===============================================================================
      System.out.println("----------------------------------------------------------- phonePartsArray[0] ---------------------------------------------------------------");*/
         phonePartsArray[0] = input.indexOf("(");

         if (phonePartsArray[0] == 0) {
            startsWithParentheses = true;
         } else if (phonePartsArray[0] == -1) {
            startsWithParentheses = false;
         } else {
            System.out.println("your phone number formatting is incorrect or incompatible with our system");
            return;
         }
      /*System.out.println("Dash/Dot: hasDash: " + hasDash + "   hasDot: " + hasDot);
      System.out.println("booleans: startsWithParentheses: " + startsWithParentheses);
      System.out.println("phonePartsArray: " + phonePartsArray[0] + " " + phonePartsArray[1] + " " + phonePartsArray[2]);
      System.out.println("phoneNumArray: " + phoneNumArray[0] + " " + phoneNumArray[1] + " " + phoneNumArray[2]);
      System.out.println("--------------------------------------------------------------------------------------------------------------------------");
      //==================================================================================================================================================================
      //=================================================================================part 2===========================================================================
      System.out.println("----------------------------------------------------------- phonePartsArray[1] ---------------------------------------------------------------");*/
          phonePartsArray[1] = input.indexOf(")");

         if (hasDash == true) {
            if (phonePartsArray[1] == -1) {
               phonePartsArray[1] = input.indexOf("-");
               middleDashOrDot = true;
            } else if (phonePartsArray[1] > -1) {
               testMiddleDash = input.indexOf("-");
               if (testMiddleDash == phonePartsArray[1] + 1) {
                  phonePartsArray[1] = input.indexOf("-");
                  middleDashOrDotWithParentheses = true;
               }
            } else {
               System.out.println("error getting second array number, got: " + phonePartsArray[1]);
               return;
            }
         } else if (hasDot == true) {
            if (phonePartsArray[1] == -1) {
               phonePartsArray[1] = input.indexOf(".");
               middleDashOrDot = true;
            } else if (phonePartsArray[1] > -1) {
               testMiddleDash = input.indexOf(".");
               if (testMiddleDash == phonePartsArray[1] + 1) {
                  phonePartsArray[1] = input.indexOf(".");
                  middleDashOrDotWithParentheses = true;
               }
            } else {
               System.out.println("error getting second array number, got: " + phonePartsArray[1]);
               return;
            }
         }
      /*System.out.println("Dash/Dot: hasDash: " + hasDash + "   hasDot: " + hasDot);
      System.out.println("booleans: startsWithParentheses: " + startsWithParentheses + "   middleDashOrDot: " + middleDashOrDot + "   middleDashOrDotWithParentheses: " + middleDashOrDotWithParentheses);
      System.out.println("phonePartsArray: " + phonePartsArray[0] + " " + phonePartsArray[1] + " " + phonePartsArray[2]);
      System.out.println("phoneNumArray: " + phoneNumArray[0] + " " + phoneNumArray[1] + " " + phoneNumArray[2]);
      System.out.println("--------------------------------------------------------------------------------------------------------------------------");
      //==================================================================================================================================================================
      //===================================================================================part 3=========================================================================
      System.out.println("----------------------------------------------------------- phonePartsArray[2] ---------------------------------------------------------------");*/
         if (hasDash == true) {
            if (middleDashOrDot == true) {
               phonePartsArray[2] = input.indexOf("-", phonePartsArray[1] + 1);
            } else if (middleDashOrDotWithParentheses == true) {
               phonePartsArray[2] = input.indexOf("-", phonePartsArray[1] + 2);
            } else {
               phonePartsArray[2] = input.indexOf("-");
            }
         } else if (hasDot == true) {
            if (middleDashOrDot == true) {
               phonePartsArray[2] = input.indexOf(".", phonePartsArray[1] + 1);
            } else if (middleDashOrDotWithParentheses == true) {
               phonePartsArray[2] = input.indexOf(".", phonePartsArray[1] + 2);
            } else {
               phonePartsArray[2] = input.indexOf(".");
            }
         }
      /*System.out.println("Dash/Dot: hasDash: " + hasDash + "   hasDot: " + hasDot);
      System.out.println("booleans: startsWithParentheses: " + startsWithParentheses + "   middleDashOrDot: " + middleDashOrDot + "   middleDashOrDotWithParentheses: " + middleDashOrDotWithParentheses);
      System.out.println("phonePartsArray: " + phonePartsArray[0] + " " + phonePartsArray[1] + " " + phonePartsArray[2]);
      System.out.println("phoneNumArray: " + phoneNumArray[0] + " " + phoneNumArray[1] + " " + phoneNumArray[2]);
      System.out.println("--------------------------------------------------------------------------------------------------------------------------");
      //==================================================================================================================================================================
      //=====================================================================================display======================================================================*/
         System.out.println("----------------------------------------------------------- DISPLAY DATA ---------------------------------------------------------------");
         if (phonePartsArray[2] == -1 || phonePartsArray[1] == -1 || phonePartsArray[0] == phonePartsArray[1] || phonePartsArray[1] == phonePartsArray[2] || phonePartsArray[0] == phonePartsArray[2]) {
            System.out.println("your phone number formatting is incorrect or incompatible with our system");
            return;
         } else if (middleDashOrDot == true && middleDashOrDotWithParentheses == true) {
            System.out.println("Error middleDashOrDot and middleDashOrDotWithParentheses = true");
            return;
         } else if (middleDashOrDotWithParentheses == true) {
            phoneNumArray[0] = Integer.parseInt(input.substring(phonePartsArray[0] + 1, phonePartsArray[1] - 1));
            phoneNumArray[1] = Integer.parseInt(input.substring(phonePartsArray[1] + 1, phonePartsArray[2]));
            phoneNumArray[2] = Integer.parseInt(input.substring(phonePartsArray[2] + 1));
         } else {
            phoneNumArray[0] = Integer.parseInt(input.substring(phonePartsArray[0] + 1, phonePartsArray[1]));
            phoneNumArray[1] = Integer.parseInt(input.substring(phonePartsArray[1] + 1, phonePartsArray[2]));
            phoneNumArray[2] = Integer.parseInt(input.substring(phonePartsArray[2] + 1));
         }
      /*System.out.println("Dash/Dot: hasDash: " + hasDash + "   hasDot: " + hasDot);
      System.out.println("booleans: startsWithParentheses: " + startsWithParentheses + "   middleDashOrDot: " + middleDashOrDot + "   middleDashOrDotWithParentheses: " + middleDashOrDotWithParentheses);
      System.out.println("phonePartsArray: " + phonePartsArray[0] + " " + phonePartsArray[1] + " " + phonePartsArray[2]);
      System.out.println("phoneNumArray: " + phoneNumArray[0] + " " + phoneNumArray[1] + " " + phoneNumArray[2]);
      for(int x=0;x < 3;x++){
         System.out.println(" ");
      }*/
         System.out.printf("%d-%d-%d", phoneNumArray[0], phoneNumArray[1], phoneNumArray[2]);

   } //---------------------------------------------------------------------------- end of main
}
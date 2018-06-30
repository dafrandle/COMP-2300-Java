package registration;
/*
Main.Java
Project Method : create a program for registration
Dustin Frandle dafrandle@yahoo.com
Written:09/22/2016
Revised:09/26/2016
+-fixed over-writing of save data file
Revised:09/27/2016
+-fixed zipcode entry
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    static ArrayList<String> writeList = new ArrayList<String>();
    final static String FILE_NAME = "registrationList.txt";

    //================================================================================================================================================================================
    public static void main(String[] args) throws IOException { //------------------------ start of main -----------------------------------------------------------------------------
        fileCheck(FILE_NAME);
        System.out.println("Show List y/n");// prompt to show list of user in file
        showList(writeList);
        boolean stay = register();
        if(stay) {
            System.out.println("Show summary of registered people y/n");// prompt to show list of user in memory
            showList(writeList);
            writeToFile(writeList,FILE_NAME);
        }
        System.out.println("Goodbye");
    }//------------------------------------------------------------------------------------ end of main ------------------------------------------------------------------------------
    //================================================================================================================================================================================
    public static File fileCheck(String fName) throws IOException{ //------------------- start of fileCheck --------------------------------------------------------------------------

        File list = new File(fName);

        if(list.exists() && !list.isDirectory()){ //file found
            System.out.println("File Found.");
            System.out.println("**********************************************************************");
            FileReader fileRdr = new FileReader(fName);
            BufferedReader bufferRdr = new BufferedReader(fileRdr);
            readFile(writeList, bufferRdr);// read file to memory using readFile
            bufferRdr.close();
        } else {                         // file not found, creates a new one
            System.out.println("No file was found.");
            System.out.println("A new file was created.");
            System.out.println("**********************************************************************");
            list.createNewFile();
            writeList.clear();
        }

        return list;
    }//------------------------------------------------------------------------------- end of fileCheck ------------------------------------------------------------------------------
    //================================================================================================================================================================================
    public static void readFile(ArrayList<String> thisList, BufferedReader buffRead) throws IOException { //----------- start of readFile --------------------------------------------

        String line;

        thisList.clear();

        line = buffRead.readLine();
        while (line != null){
            thisList.add(line);
            line = buffRead.readLine();
        }

    }//-------------------------------------------------------------------------------- end of readFile ------------------------------------------------------------------------------
    //================================================================================================================================================================================
    public static void showList(ArrayList<String> thisList){ //-------------------------- start of showList --------------------------------------------------------------------------

        boolean show = (validInput());
        String entryOut = "";
        int entryCount = 0;


        if(show) { //prints the list (and a lot of asterisks)
            System.out.println("**********************************************************************");
            System.out.println("Registered People:\n**********************************************************************");
            for (String entry : thisList) {
                System.out.println(deCSV(entry));
                entryOut = deCSV(entry);
                entryCount++;
            }
            System.out.println("----------------------------------------------------------------------");
            if(entryOut == "There is no data, or the data is corrupted."){
               System.out.println("There was no readable entries.");
               writeList.clear();
            }else if(entryCount == 1) {
                System.out.println("There was " + entryCount + " entry.");
            }else {
                System.out.println("There was " + entryCount + " entries.");
            }
            System.out.println("**********************************************************************");
        }else {  //prints a bunch of asterisks
            System.out.println("**********************************************************************");
        }
    }//-------------------------------------------------------------------------------- end of showList ------------------------------------------------------------------------------
    //================================================================================================================================================================================
    public static boolean register(){ //------------------------------------------------- start of register --------------------------------------------------------------------------

        String firstName ="";  //variable setup
        String lastName ="";
        int age = -1;
        String zip = "";
        String sex ="";
        String race ="";
        String id = "";
        String input;
        boolean registerAgain = true;
        boolean restart;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Register y/n \nno quits the program."); // do you want to Register prompt
        boolean stay = validInput();


        if(stay) {  // if the user typed N below will not run and returns the boolean "stay" as false to main which has the program stay
            System.out.println("**********************************************************************");
            while (registerAgain) { //allows program to loop until the user wants to stop
                restart = true;
                while (restart) { //allows a restart if you screed something up

                    System.out.println("Please enter your first name."); // first name prompt
                    input = scanner.nextLine();
                    firstName = input;

                    System.out.println("**********************************************************************");

                    System.out.println("Please enter your last name."); // last name prompt
                    input = scanner.nextLine();
                    lastName = input;

                    System.out.println("**********************************************************************");

                    age = getAge(); // ------------ age prompt

                    sex = getGender(); //----------------- user sex prompt (shut your dirty mind off)

                    zip = getZip(); // ----------- postal code prompt

                    race = getRace(); //-------------------------- race type prompt

                    System.out.println("Here is what you entered:\nFirst name: " + firstName + "\nLast name: " + lastName + "\nAge : " + age + "\nSex : " + sex + "\nZip: " + zip + "\nRace: " + race);
                    System.out.println("Is this acceptable? y/n");

                    restart = !validInput();

                    id = buildCode(firstName,lastName,age,zip);

                    System.out.println("Here is your Id, write it down: "+ id);

                    System.out.println("**********************************************************************");
                }

                System.out.println("**********************************************************************");
                writeList.add(firstName + "','" + lastName + "','" + sex + "','" + age + "','" + zip + "','" + race + "','" + id);
                System.out.println("saved to memory.");
                System.out.println("**********************************************************************\nWould you like to register another person? y/n");
                registerAgain = validInput();
            }
        }


        return stay;
    }//-------------------------------------------------------------------------------- end of register ------------------------------------------------------------------------------
    //================================================================================================================================================================================
    public static String buildCode(String first,String last,int age,String zip) {//-------------------- start of buildCode --------------------------------------------------------------
        String divCode = "";
        int number = 0;
        switch (last.length()){ // this switch prevents a crash out if the last name is not at least four letters long
            case  0:
                number = 0;
                break;
            case  1:
                number = 1;
                break;
            case 2:
                number = 2;
                break;
            case 3:
                number = 3;
                break;
            default:
                number = 4;
        }

        divCode = first.substring(0,3);
        divCode += "-";
        divCode += last.substring(last.length()-number,last.length());
        divCode += "-";
        if(age < 10){
            divCode += "0";
            divCode += age;
        }else {
            divCode += age;
        }
        divCode += "-";
        divCode += zip;

        return divCode;
    }//-------------------------------------------------------------------------------- end of buildCode -----------------------------------------------------------------------------
    //================================================================================================================================================================================
    public static boolean validInput(){//------------------------------------------------- start of validInput -----------------------------------------------------------------------
        boolean yesNo = false;
        boolean valid = false;
        String input = "";
        Scanner scanner = new Scanner(System.in);

        while (!valid) {
            input = scanner.nextLine();
            try {
                switch (input.charAt(0)) {
                    case 'y':
                        yesNo = true;
                        valid = true;
                        break;
                    case 'n':
                        yesNo = false;
                        valid = true;
                        break;
                    case 'Y':
                        yesNo = true;
                        valid = true;
                        break;
                    case 'N':
                        yesNo = false;
                        valid = true;
                        break;
                    default:
                        System.out.println("Input not valid please use only the letters Y (yes) or N (no)");
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.print("");
            }
        }

        return yesNo;
    }//-------------------------------------------------------------------------------- end of validInput ----------------------------------------------------------------------------
    //================================================================================================================================================================================
    public static int getAge(){//------------------------------------------------------- start of getAge -----------------------------------------------------------------------------

        boolean valid = false;
        int age = -1;
        String input = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your age.");

        while (!valid) {

            input = scanner.nextLine();

            try {
                if (Integer.parseInt(input) <= -1) {
                    System.out.println("Input not valid please enter a positive number.");
                    valid = false;
                } else {
                    valid = true;
                    age = Integer.parseInt(input);
                }
            } catch (NumberFormatException e) {
                System.out.println("Input not valid please enter a number.");
            }
        }
        System.out.println("**********************************************************************");
        return age;
    }//--------------------------------------------------------------------------------- end of getAge -------------------------------------------------------------------------------
    //================================================================================================================================================================================
    public static String getZip(){//------------------------------------------------------- start of getZip -----------------------------------------------------------------------------

        boolean valid = false;
        int zip = -1;
        String input = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your postal code. (zipcode)");

        while (!valid) {

            input = scanner.nextLine();

            try {
               zip = Integer.parseInt(input);
            } catch (NumberFormatException e) {
               System.out.println("Input not valid please enter a 5 digit zipcode, only with numbers.");
            }
            if (zip < 0){
               System.out.println("Input not valid please enter a 5 digit zipcode, only with numbers.");
            } else {
               if (input.length() == 5){
                  valid = true;
               } else {
                  System.out.println("Input not valid please enter a 5 digit zipcode, only with numbers.");
               }
            }
        }
        System.out.println("**********************************************************************");
        return input;
    }//--------------------------------------------------------------------------------- end of getZip -------------------------------------------------------------------------------
    //================================================================================================================================================================================
    public static String getGender(){//------------------------------------------------------- start of getGender --------------------------------------------------------------------

        boolean valid = false;
        String sex = "";
        String input = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your sex.");
        System.out.println("M - Male");
        System.out.println("F - Female");
        System.out.println("T - Transsexual");
        System.out.println("X - Don't wish to disclose");
        while (!valid) {

            input = scanner.nextLine();

            try {
                switch (input.charAt(0)) {
                    case 'M':
                        valid = true;
                        sex = "Male";
                        break;
                    case 'F':
                        valid = true;
                        sex = "Female";
                        break;
                    case 'T':
                        valid = true;
                        sex = "Transsexual";
                        break;
                    case 'X':
                        valid = true;
                        sex = "Undisclosed Sex";
                        break;
                    case 'm':
                        valid = true;
                        sex = "Male";
                        break;
                    case 'f':
                        valid = true;
                        sex = "Female";
                        break;
                    case 't':
                        valid = true;
                        sex = "Transsexual";
                        break;
                    case 'x':
                        valid = true;
                        sex = "Undisclosed Sex";
                        break;
                    default:
                        System.out.println("Input not valid please only use the letters: \n M - Male \n F - Female \n T - Transsexual \n X - Don't wish to disclose");
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.print("");
            }
        }
        System.out.println("**********************************************************************");
        return sex;
    }//--------------------------------------------------------------------------------- end of getGender ----------------------------------------------------------------------------
    //================================================================================================================================================================================
    public static String getRace(){//----------------------------------------------------- start of getRace --------------------------------------------------------------------------

        boolean valid = false;
        String race = "";
        String input = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("What race would you like to join: \n A: 10 kilometres (6.2 Miles)\n B: Half-marathon (21.097 km or 13.1 mi)\n C: 30 kilometres (19 Miles)\n D: Marathon (42.195 km or 26.2 mi)");
        System.out.println("If you would like to join multiple events please register for each separately");
        while (!valid) {

            input = scanner.nextLine();

            try {
                switch (input.charAt(0)) {
                    case 'A':
                        valid = true;
                        race = "10K";
                        break;
                    case 'a':
                        valid = true;
                        race = "10K";
                        break;
                    case 'B':
                        valid = true;
                        race = "Half-marathon";
                        break;
                    case 'b':
                        valid = true;
                        race = "Half-marathon";
                        break;
                    case 'C':
                        valid = true;
                        race = "30K";
                        break;
                    case 'c':
                        valid = true;
                        race = "30K";
                        break;
                    case 'D':
                        valid = true;
                        race = "Marathon";
                        break;
                    case 'd':
                        valid = true;
                        race = "Marathon";
                        break;
                    default:
                        System.out.println("Input not valid please only use the letters: \n A - 10K \n B - Half-marathon \n C - 30K \n D - Marathon");
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.print("");
            }
        }

        System.out.println("**********************************************************************");
        return race;
    }//----------------------------------------------------------------------------------- end of getRace ----------------------------------------------------------------------------
    //================================================================================================================================================================================
    public static void writeToFile(ArrayList<String> thisList, String fName){//-------------- start of writeToFile -------------------------------------------------------------------

        BufferedWriter fileWrtr;

        try {
            fileWrtr = new BufferedWriter(new FileWriter(fName));
            System.out.println("Data written to file:\n - - - - - - - - - - - - - - - - - - - - - - ");
            for (String line : thisList) {
                fileWrtr.write(line + "\n");
                System.out.println(line + " written to file.");
            }
            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - ");

            fileWrtr.close();
        }
        catch (IOException e){
            System.out.println("Error: " + e);
        }
    }//------------------------------------------------------------------------------------ end of writeToFile -----------------------------------------------------------------------
    //================================================================================================================================================================================
    public static String deCSV(String csv){//---------------------------------------------- start of deCSV ---------------------------------------------------------------------------
        int[] separatorArray;
        separatorArray = new int[6];
        String entry;

        try {
            separatorArray[0] = csv.indexOf("','");
            separatorArray[1] = csv.indexOf("','", separatorArray[0] + 2);
            separatorArray[2] = csv.indexOf("','", separatorArray[1] + 2);
            separatorArray[3] = csv.indexOf("','", separatorArray[2] + 2);
            separatorArray[4] = csv.indexOf("','", separatorArray[3] + 2);
            separatorArray[5] = csv.indexOf("','", separatorArray[4] + 2);

            entry = csv.substring(0, separatorArray[0]);
            entry += " ";
            entry += csv.substring(separatorArray[0] + 3, separatorArray[1]);
            entry += ", ";
            entry += csv.substring(separatorArray[1] + 3, separatorArray[2]);
            entry += ", ";
            entry += csv.substring(separatorArray[2] + 3, separatorArray[3]);
            entry += ", Zip: ";
            entry += csv.substring(separatorArray[3] + 3, separatorArray[4]);
            entry += ", In the ";
            entry += csv.substring(separatorArray[4] + 3, separatorArray[5]);
            entry += " race, ID:";
            entry += csv.substring(separatorArray[5] + 3);
        }
        catch (StringIndexOutOfBoundsException e){
            entry = "There is no data, or the data is corrupted.";
        }
        return entry;
    }//--------------------------------------------------------------------------------------- end of deCSV --------------------------------------------------------------------------
    //================================================================================================================================================================================
}


package inputOutput;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

   static ArrayList<String> userNameList = new ArrayList<String>();
   final static String FILE_NAME = "userName.txt";

   //--------------------------------------------------------------------------------------------
   public static void main(String[] args) throws IOException {
      openFile(FILE_NAME);
      addName();
      displayList(userNameList);
      saveToFile(userNameList, FILE_NAME);
      System.exit(0);
      
   }
   //--------------------------------------------------------------------------------------------
   /**
    * opens a data file
    * @param fName variable for data file
    */
   public static File openFile(String fName) throws IOException {
      File myFile = new File(fName);
      if(myFile.exists() && !myFile.isDirectory()) {
         System.out.println(fName + " exists.\n");
         FileReader fileRdr = new FileReader(fName);
         BufferedReader bufferRdr = new BufferedReader(fileRdr);
         populateList(userNameList, bufferRdr);
         bufferRdr.close();
      }
      else {
         System.out.println(fName + " does NOT exist. ");
         System.out.println("A new file was created.\n");
         myFile.createNewFile();
         userNameList.clear();
      }
      return myFile;
   }
   //--------------------------------------------------------------------------------------------
   /**
    * Puts data read from a file into an ArrayList<String>
    * @param thisList an ArrayList<String> for holding data
    * @param buffRead BufferedReader that holds data read from a data file
    */
   public static void populateList(ArrayList<String> thisList, BufferedReader buffRead) throws IOException {
      int lineCount = 0;
      String line = "";

      thisList.clear();
      line = buffRead.readLine();

      System.out.println("-----------------------File Contents---------------------------------");
      while (line != null){
         System.out.println(line);
         thisList.add(line);
         lineCount++;
         line = buffRead.readLine();
      }
      System.out.println(lineCount + " Lines read from file");
      System.out.println("---------------------------------------------------------------------\n");

   }
   //--------------------------------------------------------------------------------------------
   /**
    * add sample data to an ArrayList<String>
    */
   public static void addName( ) {
      userNameList.add("Potatoman@hotmail.com");
      userNameList.add("FrIeNdlYnEiGhBoRhOoDdInOsAuR@yahoo.com");
      userNameList.add("MeninYOURhouse@gmail.com");
      userNameList.add("Jana@gmail.com");
      userNameList.add("BobieBobBoybobBoberson@hotmail.com");
   }
   //--------------------------------------------------------------------------------------------
   /**
    * displays an ArrayList<String> in the console
    * @param thisList an ArrayList<String>
    */
   public static void displayList(ArrayList<String> thisList ) {
      System.out.println("Array List after additional data:\n***********************************");
      for(String name:thisList){
         System.out.println(name);
      }
      System.out.println("***********************************\n");
   }
   //--------------------------------------------------------------------------------------------
   /**
    * saves the contents of an ArrayList<String> to a data file
    * @param thisList ArrayList<String> to be saved
    * @param fName name of the file that will be modified/created
    */
   public static void saveToFile(ArrayList<String> thisList, String fName) {
      BufferedWriter fileWrtr;
      try {
         fileWrtr = new BufferedWriter(new FileWriter(FILE_NAME));
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

   }
}

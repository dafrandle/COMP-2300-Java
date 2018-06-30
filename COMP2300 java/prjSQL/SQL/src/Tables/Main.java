package Tables;

/*
Main.Java -- main class
Project: SQL
Dustin Frandle dafrandle@yahoo.com
Written: 11/10/2016
*/


import java.sql.*;
import java.util.Scanner;


public class Main {

   public static void main(String[] args) {
      String tableName ="";
      boolean quit = false;

      // get instance of sql driver
      try {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
      }
      catch (Exception e){
         System.out.println("Error in Main:\n" + e);
      }

      while(!quit) {
         tableName = createTable();
         System.out.println("edit table: " + tableName + "? y/n");
         if(validInput()){
            editTable(tableName);
         }
         System.out.println("Show table: " + tableName + "? y/n");
         if(validInput()){
            readData(tableName);
         }
         System.out.println("Quit program? y/n");
         quit = validInput();
      }
      try {
         Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/products", "root", "mysql");
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
         connection.close();
         statement.close();
         resultSet.close();
      }catch (Exception e){
         e.printStackTrace();
         System.exit(1);
      }
   }

   //-----------------------------------------------------------------------------------------------------------------------

   public static void readData(String tableName){ //----------------------------------------------------------------------------------------------------------------------- READ DATA
      Connection connection;
      Statement statement;
      ResultSet resultSet;
      ResultSetMetaData metaData;

      try {
         connection = DriverManager.getConnection("jdbc:mysql://localhost/products", "root", "mysql");
         statement = connection.createStatement();
         resultSet = statement.executeQuery("SELECT * FROM " + tableName);
         metaData = resultSet.getMetaData();
         int numberOfColumns = metaData.getColumnCount();

         String columnName[] = new String[numberOfColumns]; // reads table
         String columnTypes[] = new String[numberOfColumns];

         for (int i = 1; i <= numberOfColumns; i++) {
            columnName[i - 1] = metaData.getColumnLabel(i);
            columnTypes[i - 1] = metaData.getColumnTypeName(i);
         }


         System.out.println("Table Loaded");
         System.out.println("-----------------------------\nDumping data:");
         int count = 1;
         try {
            while (resultSet.next()) {
               System.out.print("Entry " + count + ": ");
               for (int i = 1; i <= numberOfColumns; i++) {
                  System.out.print(columnName[i - 1] + ": ");
                  switch (columnTypes[i - 1]) {
                     case "INT":
                        System.out.print(resultSet.getInt(columnName[i - 1]) + ",  ");
                        break;
                     case "VARCHAR":
                        System.out.print(resultSet.getString(columnName[i - 1]) + ",  ");
                        break;
                     case "DOUBLE":
                        System.out.printf("$%.2f,  ", resultSet.getFloat(columnName[i - 1])); // prints table
                        break;
                     default:
                        System.out.print("couldn't fetch data,  ");
                  }
               }
               System.out.print("\n");
               count++;
            }
         } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
         }
      }
      catch (Exception e){
         System.out.println("Error in readData():\n " + e);
      }
      System.out.println("-----------------------------");
   }

   //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- START OF CREATE TABLE
   //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- START OF CREATE TABLE
   //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- START OF CREATE TABLE

   public static String createTable(){
      String tableName = "";
      boolean newTable;//---------------vars
      boolean view;
      boolean goodName = false;

      Scanner scanner = new Scanner(System.in);

      // ------------------------------------------------------------------Getting name of table-------------------------------------------------------
      System.out.println("would you like to make a new table? y/n");
      newTable = validInput();
      if (newTable){
         while (!goodName) {
            System.out.println("Please enter the table name:");
            tableName = scanner.nextLine();
            try {
               Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/products", "root", "mysql");
               DatabaseMetaData databaseMetaData = connection.getMetaData();
               ResultSet resultSet = databaseMetaData.getTables(null, null, "%", null);
               boolean ran = false;
               while (resultSet.next()) {
                  if (resultSet.getString(3).equals(tableName)) {
                     ran = true;
                     goodName = false;
                     System.out.println("That Table already exists!");
                     break;
                  }
                  ran = true;
                  goodName = true;
               }
               if (!ran){
                  goodName = true;
               }
            } catch (Exception e) {
               e.printStackTrace();
               System.exit(1);
            }
         }
      }else{
         System.out.println("Would you like to view/edit a existing table? y/n");
         view = validInput();
         if (view){
            System.out.println("What is the name of the table:");
            tableName = scanner.nextLine();
         }else{
            System.out.println("Shutting down");
            System.exit(0);
         }
      }//------------------------------------------------------------------------------------------------------- new table ----------------------------------
      //---------------------------------------------------------------------------------------------------------------------------------------------------- number of columns, keys, and their names
      if (newTable){
         System.out.println("How many columns will this table have (you must give a name for each):");
         int numberOfColumns = getNumber();

         System.out.println("How many primary keys will you have?");
         int keyNumber = getNumber();
         while (keyNumber > numberOfColumns){
            System.out.println("you cant have more keys than columns.");
            keyNumber = getNumber();
         }
         String[] columnList;
         columnList = new String[numberOfColumns + keyNumber];
         int count = 0;
         int loop = numberOfColumns;

         System.out.println("Your first column must be a key field, and second column must be a VARCHAR for you to edit this table in the program.");

         while (loop != 0){
            System.out.println("Name for column " + (count + 1));
            columnList[count] = scanner.nextLine();
            count ++;
            loop --;
         }

         //--------------------------------------------------------------------------------------------------------------------------------------------------------- setup columns data types
         System.out.println("Setup columns: keys will auto increment.");
         count = 0;
         int keyCount = numberOfColumns;
         loop = numberOfColumns;
         String typeHolder;
         int keysEntered = 0;

         while (loop != 0){
            System.out.println("input the data type for column " + (count + 1) + ": 1 (integer), 2 (varchar), or 3 (double)");
            typeHolder = getDataType(getNumber());

            if(keysEntered < keyNumber) {
               System.out.println("Will column " + (count + 1) + " be a key? y/n");
               if (validInput()) {
                  if (typeHolder.equals(" INTEGER NOT NULL") || typeHolder.equals(" DOUBLE PRECISION NOT NULL")) {
                     keyCount++;
                     keysEntered++;
                     columnList[keyCount - 1] = "PRIMARY KEY (" + columnList[count] + ")";
                     columnList[count] += typeHolder + " AUTO_INCREMENT";
                  } else {
                     System.out.println("A varchar cannot be a key (here anyway). skipping to next column");
                     columnList[count] += typeHolder;
                  }
               } else {
                  columnList[count] += typeHolder;
               }
            }else{
               columnList[count] += typeHolder;
            }
            count ++;
            loop --;
         }
         //------------------------------------------------------------------------------------------------------------------ build table / sql command
         String sql = "CREATE TABLE " + tableName + " ( ";
         count = 0;
         loop = columnList.length;
         while (loop != 1){
            sql += columnList[count] + ", ";
            count ++;
            loop --;
         }
         sql += columnList[count] + ")";

         try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/products", "root", "mysql");
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql); // sql command
            System.out.println("created a table named: " + tableName);
         }
         catch (Exception e){
            System.out.println("Error in createTable():\n " + e);
            e.printStackTrace();
            System.exit(1);
         }
      }
      return tableName;
   }
   //------------------------------------------------------------------------------------------------------------------------------------------------------ END OF CREATE TABLE
   //------------------------------------------------------------------------------------------------------------------------------------------------------ END OF CREATE TABLE
   //------------------------------------------------------------------------------------------------------------------------------------------------------ END OF CREATE TABLE
   public static void editTable(String tableName){
      Connection connection;
      Statement statement;
      ResultSet resultSet; //vars
      ResultSetMetaData metaData;
      boolean quit = false;
      while (!quit) {
         try {//--------------------------------------------------------------------- get columns from table
            connection = DriverManager.getConnection("jdbc:mysql://localhost/products", "root", "mysql");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();

            String columnName[] = new String[numberOfColumns];
            String columnTypes[] = new String[numberOfColumns];

            for (int i = 1; i <= numberOfColumns; i++) {
               columnName[i - 1] = metaData.getColumnLabel(i);
               columnTypes[i - 1] = metaData.getColumnTypeName(i);
            }

            //--------------------------------------------------------------------------- print out table
            System.out.println("Table Loaded");
            System.out.println("-----------------------------\nDumping data:");
            int count = 1;
            try {
               while (resultSet.next()) {
                  System.out.print("Entry " + count + ": ");
                  for (int i = 1; i <= numberOfColumns; i++) {
                     System.out.print(columnName[i - 1] + ": ");
                     switch (columnTypes[i - 1]) {
                        case "INT":
                           System.out.print(resultSet.getInt(columnName[i - 1]) + ",  ");
                           break;
                        case "VARCHAR":
                           System.out.print(resultSet.getString(columnName[i - 1]) + ",  ");
                           break;
                        case "DOUBLE":
                           System.out.printf("$%.2f,  ", resultSet.getFloat(columnName[i - 1]));
                           break;
                        default:
                           System.out.print("couldn't fetch data,  ");
                     }
                  }
                  System.out.print("\n");
                  count++;
               }
            } catch (Exception e) {
               e.printStackTrace();
               System.exit(1);
            }
            System.out.println("-----------------------------");//---------------------------------------------------------------- Chose what to edit
            System.out.println("Would you like to edit an entry (1) add an entry (2) or delete an entry (3)");
            int userSelect = getNumber();
            while (userSelect > 3) {
               System.out.print("Please select: Edit: 1, Add: 2, Delete: 3");
               userSelect = getNumber();
            }
            boolean done = false;
            int entryCount;
            switch (userSelect) {//---------------------------------------------------------------- Start of switch which have controls for edit
               case 1://-------------------------------------------------------------case 1 edit
                  entryCount = (count - 1);
                  while (!done) {
                     System.out.println("Select an entry number.");
                     int entrySelect = getNumber();
                     while (entrySelect > entryCount) {
                        System.out.println("That entry dose not exist. Try again:");// get the entry number (row number) from user
                        entrySelect = getNumber();
                     }
                     System.out.println("Select a column.");
                     for (int i = 1; i <= numberOfColumns; i++) {
                        System.out.println(i + ": " + columnName[i - 1]);// print column names
                     }
                     int columnSelect = getNumber();
                     while (columnSelect > numberOfColumns) {
                        System.out.println("That column dose not exist. Try again:"); //
                        columnSelect = getNumber();
                     }
                     try {
                        resultSet = statement.executeQuery("SELECT * FROM " + tableName); // get the column that the user wants to edit
                     } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(1);
                     }
                     int where = 0;
                     try {
                        count = 1;
                        while (resultSet.next()) {
                           if (count == entrySelect) {
                              where = resultSet.getInt(columnName[0]); // loop through the rows and get the data in the first column on the appropriate row
                              break;
                           }
                           count++;
                        }
                     } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(1);
                     }
                     Scanner scanner = new Scanner(System.in);
                     System.out.println("Enter new data:");
                     String input = scanner.nextLine();
                     String sql = "UPDATE " + tableName + " SET " + columnName[columnSelect - 1] + "='" + input + "' WHERE " + columnName[0] + "='" + where + "'"; // build sql command
                     try {
                        statement.executeUpdate(sql); // sql command
                     } catch (Exception e) {
                        System.out.println("ERROR: You didn't enter something right, try the edit again. (Exception text: " + e +
                              ") \n The following is a stack trace if you believe you did not enter anything wrong report it to the dev");
                        e.printStackTrace();
                     }
                     System.out.println(columnName[columnSelect - 1] + " was changed to " + input);
                     System.out.println("would you edit to enter another entry? y/n");
                     done = !validInput();
                  }
                  break;
               case 2://---------------------------------------------------------------------------------------------case 2 add
                  while (!done) {
                     String[] insertData = new String[numberOfColumns - 1];
                     System.out.println("Enter data for specified column:");
                     Scanner scanner = new Scanner(System.in);
                     for (int i = 1; i <= (numberOfColumns - 1); i++) {
                        System.out.println("Enter for: " + columnName[i]);   // loop to get input for data added in new row
                        insertData[i - 1] = scanner.nextLine();
                     }

                     String sql = "INSERT INTO " + tableName + " ("; // build sql command
                     for (count = 1; count <= (numberOfColumns - 2); count++) {
                        sql += columnName[count] + ", ";
                     }
                     sql += columnName[count] + ") VALUES ('";

                     for (count = 1; count <= (numberOfColumns - 2); count++) {
                        sql += insertData[count - 1] + "','";
                     }
                     sql += insertData[count - 1] + "')";

                     try {
                        statement.executeUpdate(sql); // sql command
                     } catch (Exception e) {
                        System.out.println("ERROR: You didn't enter something right, try the addition again. (Exception text: " + e +
                              ") \n The following is a stack trace if you believe you did not enter anything wrong report it to the dev");
                        e.printStackTrace();
                     }
                     System.out.println("Add another recoded? y/n");
                     done = !validInput();
                  }
                  break;
               case 3://------------------------------------------------------------------------------------------------------case 3 delete
                  entryCount = (count - 1);
                  while (!done) {
                     System.out.println("Select an entry number.");
                     int entrySelect = getNumber();
                     while (entrySelect > entryCount) {
                        System.out.println("That entry dose not exist. Try again:"); // get the row to be deleted
                        entrySelect = getNumber();
                     }
                     try {
                        resultSet = statement.executeQuery("SELECT * FROM " + tableName);
                     } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(1);
                     }
                     int lot = 0;
                     String name = "";
                     try {
                        count = 1;
                        while (resultSet.next()) {
                           if (count == entrySelect) {
                              lot = resultSet.getInt(columnName[0]);
                              name = resultSet.getString(columnName[1]); // gets the first and second columns data to of the appropriate row, delete row by deleting the one with these two data points
                              break;
                           }
                           count++;
                        }
                     } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(1);
                     }
                     String sql = "DELETE FROM " + tableName + " WHERE " + columnName[0] + "='" + lot + "' AND " + columnName[1] + "='" + name + "'"; // build sql command
                     try {
                        statement.executeUpdate(sql); // sql command
                     } catch (Exception e) {
                        System.out.println("ERROR: You didn't enter something right, try the deletion again. (Exception text: " + e +
                              ") \n The following is a stack trace if you believe you did not enter anything wrong report it to the dev");
                        e.printStackTrace();
                     }
                     System.out.println("Delete another entry? y/n");
                     done = !validInput();
                  }
                  break;
               default://-----------------------------------------------------------------------------------------------------------------default - stops the program
                  System.out.println("The entry entered is not valid \nEntry used: " + userSelect + "\nMain.java line: 231");
                  System.exit(1);
            }
         } catch (Exception e) {
            System.out.println("in editTable():\n" + e);
            e.printStackTrace();
            System.exit(1);
         }
         System.out.println("Edit another thing? y/n");
         quit = !validInput();
      }
   }
   //-------------------------------------------------------------------------------------------------------------------------------------------------------- END OF EDIT TABLE
   //-------------------------------------------------------------------------------------------------------------------------------------------------------- END OF EDIT TABLE
   //-------------------------------------------------------------------------------------------------------------------------------------------------------- END OF EDIT TABLE

   public static  int getNumber(){
      int number = 1;
      boolean valid = false;
      String input;
      Scanner scanner = new Scanner(System.in); // gets a number greater than 0 and returns it

      while (!valid) {
         input = scanner.nextLine();
         try {
            number = Integer.parseInt(input);
            if (number > 0) {
               valid = true;
            }else{
               System.out.println("Please enter a number greater than 0.");
            }
         } catch (java.lang.NumberFormatException e) {
            System.out.println("Please enter a whole number without spaces.");
         }
      }
      return number;
   }

   //-----------------------------------------------------------------------------------------------------------------------

   public static String getDataType(int type){
      String dataType = "";

      switch (type) {
         case 1:
            dataType = " INTEGER NOT NULL";
            break;
         case 2:
            System.out.println("Enter length/value number:"); //- used to build columns in table
            dataType = " VARCHAR(" + getNumber() + ") NOT NULL";
            break;
         case 3:
            dataType = " DOUBLE PRECISION NOT NULL";
            break;
         default:
            System.out.println("Input not valid please use only the numbers: 1 (integer), 2 (varchar), or 3 (double)");
      }
      return dataType;
   }

   //-----------------------------------------------------------------------------------------------------------------------

   public static boolean validInput() {
      boolean yesNo = false;
      boolean valid = false;
      String input;
      Scanner scanner = new Scanner(System.in); // gets a yes or no answer from user

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
   }
}
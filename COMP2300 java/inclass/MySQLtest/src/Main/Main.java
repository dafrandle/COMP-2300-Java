package Main;

/*
Main.Java -- main class
Project: MySQLandGitTest
Dustin Frandle dafrandle@yahoo.com
Written: 11/01/2016
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
/*
1. Download the JDBC driver from here https://dev.mysql.com/downloads/connector/j/
2. Unzip the file and put the .jar folder in a folder that is in the CLASSPATH
   For Mac this is  [yourHardDrive]/Library/Java/Extensions
   Or add the directory where it is located to the CLASSPATH (https://docs.oracle.com/javase/tutorial/essential/environment/paths.html)
3. Using phpMyAdmin and your localhost (WAMP or AMPPS) to create a database and table with sample data
4. Change the database name, userID, and password in the getConnection( ) string.
5. Change the field names in the rs.getString( ) functions
 */
public class Main {

   public static void main(String[] args) {
      Statement stmt = null;
      ResultSet rs = null;

      try {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
         // javaMySQL is database - root is username - mysql is password
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost/javasql", "root", "mysql");
         stmt = con.createStatement();
         rs = stmt.executeQuery("SELECT * FROM employee");
         System.out.println("OUR EMPLOYEES:");
         while (rs.next()) {
            System.out.printf("%d %s %s\t\t%s\n",rs.getInt("id"), rs.getString("fName"), rs.getString("lName"), rs.getString("job"));
         }
         con.close();
      }
      catch(Exception e)
      { System.out.println(e);}
   }
}

package Vehicles;
/*
Person.Java
Project tutorialJavaInheritance
Dustin Frandle dafrandle@yahoo.com
Written: 10/11/2016
*/
import java.time.LocalDate;
public class Person {
   //---------------------------------------------------------------- PROPERTIES
   private String fName = "First Name Not Specified";
   private String lName = "Last Name Not Specified";
   private String ssn = "SSN Not Specified";
   private LocalDate dob = LocalDate.now();
   //---------------------------------------------------------------- CONSTRUCTORS
   public Person(){}
   public Person(String ssn){this.ssn = ssn;};
   public  Person(String ssn, String fName, String lName, LocalDate dob){
      this.ssn = ssn;
      this.fName = fName;
      this.lName = lName;
      this.dob = dob;
   }
   //---------------------------------------------------------------- METHODS
   public String toString() {
      String result = "";
      result = String.format("******* P E R S O N ****************\n");
      result += String.format("Name: %s %s\n", fName, lName);
      result += String.format("SSN: %s\n", ssn);
      result += String.format("Birthday: %s\n", dob.toString( ));
      result += String.format("**************************\n");
      return result;
   }
   //---------------------------------------------------------------- GET
   public String getfName(){return fName;}
   public String getlName(){return lName;}
   public String getSsn(){return ssn;}
   public LocalDate getDob(){return dob;}
   //---------------------------------------------------------------- SET
   public void setfName(String fName){this.fName  = fName;}
   public void setlName(String lName){this.lName  = lName;}
   public void setSsn(String ssn){this.ssn  = ssn;}
   public void setDob(LocalDate dob){this.dob = dob;}

}

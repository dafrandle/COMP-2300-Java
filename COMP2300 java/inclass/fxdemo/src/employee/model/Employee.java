package employee.model;
/*
Employee.Java
Project fxdemo
Dustin Frandle dafrandle@yahoo.com
Written: 11/29/2016
*/

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee {
   //---------------------------------------------------------------- PROPERTIES
   private StringProperty firstName = null;
   private StringProperty lastName = null;
   //---------------------------------------------------------------- CONSTRUCTORS
   public Employee(){}
   public Employee(String firstName, String lastName){
      this.firstName = new SimpleStringProperty(firstName);
      this.lastName = new SimpleStringProperty(lastName);
   }
   //---------------------------------------------------------------- METHODS

   //---------------------------------------------------------------- GET
   public String getFirstName(){return firstName.get();}
   public String getLastName(){return lastName.get();}
   public StringProperty firstNameProperty(){return firstName;}
   public StringProperty lastNameProperty(){return lastName;}
   //---------------------------------------------------------------- SET
   public void setFirstName(String firstName){this.firstName.set(firstName);}
   public void setLastName(String lastName){this.lastName.set(lastName);}
}


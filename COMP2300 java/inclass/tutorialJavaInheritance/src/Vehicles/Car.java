package Vehicles;
/*
Car.Java
Project tutorialJavaInheritance
Dustin Frandle dafrandle@yahoo.com
Written: 10/11/2016
*/

public class Car extends Vehicle {
   //---------------------------------------------------------------- PROPERTIES

   //---------------------------------------------------------------- CONSTRUCTORS
   public Car(){}
   public Car(int mileage, String make){
      super.setMileage(mileage);
      super.setMake(make);
   }
   //---------------------------------------------------------------- METHODS
   public String toString(){
      String result = super.toString();
      result += String.format("***** %s *********************\n",this.getClass().getSimpleName() );
      result += String.format("Make and model are: %s %s %s.\n", super.getYear(), super.getMake(), super.getModel());
      result += String.format("VIN number is: %s\n", super.getVin());
      return result;
   }
   //---------------------------------------------------------------- GET

   //---------------------------------------------------------------- SET
}

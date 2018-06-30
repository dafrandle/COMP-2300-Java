package Vehicles;
/*
Vehicle.Java
Project tutorialJavaInheritance
Dustin Frandle dafrandle@yahoo.com
Written: 10/11/2016
*/

public class Vehicle {
   //---------------------------------------------------------------- PROPERTIES
   private double direction = 0;
   private int mileage = 0;
   private int speed = 0;
   private boolean moving = false;
   private Person driver = new Person();
   private String make = "Not Specified";
   private String model = "Not Specified";
   private String year = "Not Specified";
   private String vin = "Not Specified";
   //---------------------------------------------------------------- CONSTRUCTORS
   public Vehicle(){}
   public Vehicle(int mileage){this.mileage = mileage;}
   //---------------------------------------------------------------- METHODS
   public String toString(){
      String result = "";
      result = String.format("***** %s *********************\n", this.getClass().getSimpleName() );
      result += String.format("Mileage: %s\n", mileage);
      result += String.format("Speed: %s\n", speed);
      result += String.format("Is Moving: %s\n", moving);
      result += String.format("Direction: %.0f degrees.\n", direction);
      result += String.format("Driver is: %s %s.\n", driver.getfName( ), driver.getlName());
      return result;
   }
   //---------------------------------------------------------------- GET
   public double getDirection(){return direction;}
   public int getMileage(){return  mileage;}
   public int getSpeed(){return speed;}
   public boolean isMoving(){return moving;}
   public String getMake(){return  make;}
   public String getModel(){return  model;}
   public String getYear(){return  year;}
   public String getVin(){return  vin;}

   //---------------------------------------------------------------- SET
   public void setDirection(double direction){this.direction = direction;}
   public void setMileage(int mileage){this.mileage = mileage;}
   public void setSpeed(int speed){this.speed = speed;}
   public void setMoving(boolean moving){this.moving = moving;}
   public void setDriver(Person person){driver = person;}
   public void setMake(String make){this.make =  make;}
   public void setModel(String model){this.model = model;}
   public void setYear(String year){this.year = year;}
   public void setVin(String vin){this.vin = vin;}
}

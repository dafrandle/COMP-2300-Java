package Vehicles;
/*
VehicleMain.Java -- main class
Project tutorialJavaInheritance
Dustin Frandle dafrandle@yahoo.com
Written: 10/11/2016
*/
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

public class VehicleMain {
   public static void main(String[] args) {
      // test Vehicle class
      Vehicle myVehicle = new Vehicle(12345);
      System.out.println("toString( ) of myVehicle is:\n" + myVehicle);

      // test out Person class
      // Java 7: Person myPerson = new Person("123-45-6789","Grace", "Hopper", new Date(11,9,1906) );
      Person myPerson = new Person("123-45-6789", "Grace", "Hopper", LocalDate.of(1906, Month.DECEMBER, 9) );
      System.out.println("toString( ) of myPerson is:\n" + myPerson);

      // add a Driver to vehicle and review the vehicle object
      myVehicle.setDriver(myPerson);
      System.out.println("toString( ) of myVehicle with driver is:\n" + myVehicle);
      // test Auto class
      Car myCar = new Car(100000, "Honda");
      myCar.setModel("Accord");
      myCar.setYear("2015");
      myCar.setMileage(12345);
      myCar.setDirection(45.0F);
      myCar.setSpeed(55);
      myCar.setMoving(true);
      myCar.setDriver(new Person("333-44-555", "Marissa",
            "Mayer", LocalDate.of(1975, Month.MAY, 30)));
      myCar.setVin("123-456BC-654-321F");
      System.out.println("toString( ) of myAuto is: \n" + myCar);
   }
}

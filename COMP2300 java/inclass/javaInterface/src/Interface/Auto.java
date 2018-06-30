package Interface;
/*
Auto.Java
Project javaInterface
Dustin Frandle dafrandle@yahoo.com
Written: 10/18/2016
*/
import java.sql.Driver;
import java.util.Scanner;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
public class Auto implements Vehicle {
   //---------------------------------------------------------------- PROPERTIES
   private float direction = 0.0F;
   private int mileage = 0;
   private int speed = 0;
   private boolean moving = false;
   private Person driver = new Person();
   private String make = "";
   private String model ="";
   private String year = "";
   private String vin = "";
   //---------------------------------------------------------------- CONSTRUCTORS
   public Auto(){System.out.println("put object into memory.");}
   public Auto(String vin){
      this.vin = vin;
      direction = 90F;
      mileage = 9999;
      driver = new Person("Minnie", "Driver");
      make = "Ford";
      model = "Mustang";
      year = "2015";
   }
   //---------------------------------------------------------------- METHODS
   @Override
   public void soundHorn(){
      playSound("carHorn2.wav", "sound");
      hitAnyKey();
   }
   @Override
   public void drive(){
      setMoving(true);
      playSound("car.wav", "sound");
      hitAnyKey();
   }
   @Override
   public void playSound(String aSound, String aDescription){
      try {
         InputStream in = new FileInputStream(aSound);
         AudioStream audioStream = new AudioStream(in);
         AudioPlayer.player.start(audioStream);
         System.out.println(aDescription);
      }
      catch (FileNotFoundException e){
         System.out.println("If " + aSound + " existed you would have just heard it.");
         System.out.println("Debug output: " + e);
      }
      catch (IOException e){
         System.out.println(aSound + " is not a valid sound file.");
         System.out.println("Debug output: " + e);
      }
      catch (Exception e){
         System.out.println("There was an error. \n Debug output: " + e);
      }
   }
   @Override
   public String toString(){
      String result = "";
      result = String.format("***** %s ********************\n", this.getClass().getSimpleName());
      result += String.format("Mileage: %s\n", mileage);
      result += String.format("Speed: %s\n", speed);
      result += String.format("Is Moving: %s\n", moving);
      result += String.format("Direction: %.0f degrees.\n", direction);
      result += String.format("VIN is: %s\n", vin);
      result += String.format("Driver is %s %s.\n", driver.getfName(), driver.getlName());
      result += String.format("**************************");
      return result;
   }
   public void hitAnyKey(){
      Scanner keyIn = new Scanner(System.in);
      System.out.println("Clack here and press ENTER");
      keyIn.nextLine();
   }
   //---------------------------------------------------------------- GET
   public float getDirection(){return direction;}
   public int getMileage(){return mileage;}
   public int getSpeed(){return speed;}
   public boolean isMoving() {return moving;}
   public Person getDriver() {return driver;}
   public String getMake() {return make;}
   public String getModel() {return model;}
   public String getYear() {return year;}
   public String getVin() {return vin;}
   //---------------------------------------------------------------- SET
   public void setDirection(float direction){this.direction = direction;}
   public void setMileage(int mileage){this.mileage = mileage;}
   public void setSpeed(int speed){this.speed = speed;}
   public void setMoving(boolean moving){this.moving = moving;}
   public void setDriver(Person driver){this.driver = driver;}
   public void setMake(String make){this.make = make;}
   public void setModel(String model){this.model = model;}
   public void setYear(String year){this.year = year;}
   public void setVin(String vin){this.vin = vin;}

}


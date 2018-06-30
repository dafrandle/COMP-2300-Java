package main;

import java.time.LocalTime;
import java.util.Scanner;
/**
 * Created by Dustin Frandle on 9/27/2016.
 */
public class Thermometer {

   //---------------------------------------------------------------- PROPERTIES
   private String scale = "f";
   private float degree = 0.0F;

   //---------------------------------------------------------------- CONSTRUCTORS
   public Thermometer(){}
   public Thermometer(float degree){
      this.degree = degree;
   }
   public Thermometer(float degree, String scale){
      this.degree = degree;
      this.scale = scale;
   }
   //---------------------------------------------------------------- METHODS
   protected float convert2Celsius(){
      float celsius = 0.0F;
      celsius = (5/9.0F) * (degree-32);
      return celsius;
   }

   protected float convert2Kelvin(){
      return ((degree-32)/1.8F) + 273.15F;
   }

   public void showTemp(){
      switch(scale) {
         case "k":
            System.out.printf("The current temp is %4.2f fahrenheit and %4.2f kelvin.", degree, convert2Kelvin());
            break;

         case "f":
            System.out.printf("The current temp is %4.2f fahrenheit.", degree);
            break;

         case "c":
            System.out.printf("The current temp is %4.2f fahrenheit and %4.2f celsius.", degree, convert2Celsius());
            break;

         default:
            System.out.printf("ERROR: %s is not a valid value for variable scale.", scale);
      }
   }
   protected void getInput()
   {
      String degreeInput = "";
      boolean isValid = false;
      Scanner myScanner = new Scanner(System.in);

      System.out.println("Please click here and type in the temperature as fahrenheit (90-120F)");
      degreeInput = myScanner.nextLine();
      isValid = validateTemp(degreeInput);
      if (isValid)
      {
         System.out.println(degreeInput + " is valid.");

      } else
      {
         System.out.println(degreeInput + " was NOT a valid entry.");
      }

   }

   public boolean validateTemp(String thisInput){
      boolean isValid = false;
      float deg = 0.0F;

      deg = Float.parseFloat(thisInput);
      if (deg >= 90.0F && deg < 121.0F) {
         isValid = true;
         degree = deg;
      }else {
         System.out.println("DEBUG - " + deg + " is NOT in range 90-120.");
         isValid = false;
         // save as an error code
         degree = -1;
      }
      return isValid;
   }
   //---------------------------------------------------------------- SET AND GET
   public String getScale(){
      return scale;
   }
   public float getDegree(){
      return degree;
   }
   public void setDegree(float degree){
      this.degree = degree;
   }
   public void setScale(String scale){
      scale = scale.toLowerCase();
      if(scale.equals("f")||scale.equals("c")||scale.equals("k")){
         System.out.println("DEBUG, valid scale.");
         this.scale = scale;
      }else{
         System.out.println("ERROR: " + " is not a valid indicator. Use K,F,or C");
      }
   }
}

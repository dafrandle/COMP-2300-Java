package main;


public class Main {


   public static void main(String[] args) {
      Thermometer input1 = new Thermometer( );
      input1.setScale("f");
      input1.setDegree(700.0F);
      input1.showTemp( );
      System.out.println("\n***********************");
      input1.setDegree(98.6F);
      input1.showTemp( );
   }

}

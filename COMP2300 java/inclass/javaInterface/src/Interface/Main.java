package Interface;
/*
Main.Java -- Main class
Project javaInterface
Dustin Frandle dafrandle@yahoo.com
Written: 10/18/2016
*/

public class Main {
   public static void main(String[] args) {
      Auto myAuto = new Auto("123-456-ZPD43");
      System.out.println(myAuto.getDriver());
      System.out.println(myAuto);
      myAuto.soundHorn();
   }
}

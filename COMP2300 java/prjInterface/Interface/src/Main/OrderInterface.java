package Main;
/*
Order.Java
Project Interface
Dustin Frandle dafrandle@yahoo.com
Written: 10/27/2016
*/

public interface OrderInterface {
   //---------------------------------------------------------------- CONSTANTS
   public static final double TAX = .06875;
   //---------------------------------------------------------------- ABSTRACT METHODS
   public void findCost(double cost, int quantity, String size);
   public double findTax(double cost);
   public double findTotalCost(double cost, int quantity);
   //---------------------------------------------------------------- INTERFACES

   //---------------------------------------------------------------- CLASSES
}
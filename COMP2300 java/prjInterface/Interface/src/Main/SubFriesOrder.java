package Main;
/*
SubFriesOrder.Java
Project Interface
Dustin Frandle dafrandle@yahoo.com
Written: 10/27/2016
*/

public class SubFriesOrder extends BaseFoodOrder {
   //---------------------------------------------------------------- PROPERTIES

   //---------------------------------------------------------------- CONSTRUCTORS
   public SubFriesOrder(){setCalories(543); setCost(4.99);}//these are properties of this item that are in the super class - and will always start as these
   public SubFriesOrder(String size){
      if(validateSize(size)) {
         setSize(size);
      }else{
         System.out.println("ERROR: The order size " + size + " is not valid!");
         System.exit(1);
      }
      setCalories(223);
      setCost(1.99);
   }
   //---------------------------------------------------------------- METHODS
   @Override
   protected void displayOrderInfo(){ //prints what you might see on a receipt
      double costLine;
      double totalCostLine;
      int caloriesLine;
      costLine = getCost();
      totalCostLine = findTotalCost(costLine, super.quantity);
      caloriesLine = getCalories();
      System.out.println(super.quantity + " Box of Fries");
      System.out.println(" $" + String.format("%1$.2f", costLine));
   }
   @Override
   protected void processOrder(){ //use the quantity and size to modify the amount of calories and the cost of the order
      int calories = findTotalCalories(getCalories(), super.quantity);
      findCost(getCost(), super.quantity, getSize());
      setCalories(calories);
   }
   //---------------------------------------------------------------- GET

   //---------------------------------------------------------------- SET
}
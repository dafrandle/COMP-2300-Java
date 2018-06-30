package Main;
/*
BaseFoodOrder.Java
Project Interface
Dustin Frandle dafrandle@yahoo.com
Written: 10/27/2016
*/


public class BaseFoodOrder implements OrderInterface {
   //---------------------------------------------------------------- PROPERTIES
   private int calories = 0;
   private String size; //size of meal -- if found invalid program terminates
   private double cost = 0;
   protected int quantity = 1; //number of items ordered -- protected so no sets or gets
   //---------------------------------------------------------------- CONSTRUCTORS
   public BaseFoodOrder(){}

   public BaseFoodOrder(String size){ //with size of the order
      if(validateSize(size)) {
         this.size = size;
      }else{
         System.out.println("ERROR: The order size " + size + " is not valid!");
         System.exit(1);
      }
   }

   public BaseFoodOrder(String size, int calories){ //with size and calorie amount of the order
      if(validateSize(size)) {
         this.size = size;
      }else{
         System.out.println("ERROR: The order size " + size + " is not valid!");
         System.exit(1);
      }
      this.calories = calories;
   }

   public BaseFoodOrder(String size, double cost){ //with size and cost of the order
      if(validateSize(size)) {
         this.size = size;
      }else{
         System.out.println("ERROR: The order size " + size + " is not valid!");
         System.exit(1);
      }
      this.cost = cost;
   }

   public BaseFoodOrder(String size,int calories, double cost){ //with size and both cost and calorie amount of the order
      if(validateSize(size)) {
         this.size = size;
      }else{
         System.out.println("ERROR: The order size " + size + " is not valid!");
         System.exit(1);
      }
      this.calories = calories;
      this.cost = cost;
   }


   //---------------------------------------------------------------- METHODS
   @Override//multiplies the cost by the number of items ordered(quantity) and assigns it back to cost
   public void findCost(double cost, int quantity, String size){
      cost = cost * getSizeValue(size);
      cost = cost * quantity;
      this.cost = cost;
   }

   @Override
   public double findTax(double cost){ //use the constant to find the tax on a purchase
      double totalTax = cost * TAX;
      return totalTax;
   }

   @Override
   public double findTotalCost(double cost, int quantity){//adds the tax to the cost (does not save that cost)
      double totalCost = cost;
      totalCost += findTax(totalCost);
      return totalCost;
   }

   protected boolean validateSize(String size){ //if size is not "small" "medium" or "large" false is returned
      boolean valid;
      if(size.equals("small") || size.equals("medium") || size.equals("large")){
         valid = true;
      }else {
         valid = false;
      }
      return valid;
   }

   protected int findTotalCalories(int calories, int quantity){ // multiplies the calorie amount by the quantity ordered
      calories = calories * quantity;
      return calories;
   }

   private double getSizeValue(String size){ //returns a number to be use to increase the cost relative to the size, if the size is unrecognised the program quits
      double sizeValue = 1;

      switch (size) {
         case "small":
            sizeValue = 1;
            break;
         case "medium":
            sizeValue = 1.333333;
            break;
         case "large":
            sizeValue = 1.5;
            break;
         default:
            System.out.println("ERROR: The order size " + size + " is not valid!");
            System.exit(1);
      }

      return sizeValue;
   }

   protected void displayOrderInfo(){ //prints what you might see on a receipt
      double costLine;
      double totalCostLine;
      int caloriesLine;
      costLine = this.cost;
      totalCostLine = findTotalCost(costLine, quantity);
      caloriesLine = calories;
      System.out.println("Total Cost: $" + String.format("%1$.2f", costLine) + "\n Tax: $"
            + String.format("%1$.2f", findTax(costLine)) + "\n   Grand Total: $" + String.format("%1$.2f", totalCostLine)
            + "\n   Total Calories: " + caloriesLine);
   }
   protected void processOrder(){ //use the quantity and size to modify the amount of calories and the cost of the order
      int calories = findTotalCalories(this.calories,quantity);
      findCost(cost,quantity,size);
      this.calories = calories;
   }
   @Override
   public String toString() {
      return "cost less tax: $" + String.format("%1$.2f",cost) + ", size: " + size + ", quantity: " + quantity + ", calories: " + calories;
   }
   //---------------------------------------------------------------- GET
   public int getCalories(){return calories;}
   public String getSize(){return  size;}
   public double getCost(){return cost;}
   //---------------------------------------------------------------- SET
   public void setCalories(int calories){this.calories = calories;}
   public void setSize(String size){this.size = size;}
   public void setCost(double cost){this.cost = cost;}
}

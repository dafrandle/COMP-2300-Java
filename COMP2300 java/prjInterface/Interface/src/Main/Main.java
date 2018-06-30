package Main;

/*
Main.Java -- main class
Project: Interface
Dustin Frandle dafrandle@yahoo.com
Written: 10/26/2016
*/

import  java.util.LinkedList;

public class Main {

   public static void main(String[] args) {
      LinkedList linkedlist = new LinkedList();
      BaseFoodOrder wholeOrder = new BaseFoodOrder("small"); //set up orders
      BaseFoodOrder burgerOrder = new SubBurgerOrder("medium");
      BaseFoodOrder drinkOrder = new SubDrinkOrder("medium");
      BaseFoodOrder friesOrder = new SubFriesOrder("large");


      drinkOrder.quantity=2;


      burgerOrder.processOrder(); //process individual item
      drinkOrder.processOrder();
      friesOrder.processOrder();

      wholeOrder.setCost((drinkOrder.getCost() + burgerOrder.getCost() + friesOrder.getCost())); //pass individual items into wholeOrder
      wholeOrder.setCalories((drinkOrder.getCalories() + burgerOrder.getCalories() + friesOrder.getCalories()));

      //process the whole order. If you thought "won't this take the tax of the individual items" your wrong as the base cost is stored separably
      wholeOrder.processOrder();

      burgerOrder.displayOrderInfo(); //print receipt
      drinkOrder.displayOrderInfo();
      friesOrder.displayOrderInfo();
      wholeOrder.displayOrderInfo();

      System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
      System.out.println("Using a linked list");

      linkedlist.add(wholeOrder);
      System.out.println("added 'wholeOrder' to slot 0 of 'linkedlist'");
      linkedlist.add(drinkOrder);
      System.out.println("added 'drinkOrder' to slot 1 of 'linkedlist'");
      linkedlist.add(friesOrder);
      System.out.println("added 'friesOrder' to slot 2 of 'linkedlist'");
      linkedlist.add(burgerOrder);
      System.out.println("added 'burgerOrder' to slot 3 of 'linkedlist'");

      System.out.println("printing out linked list");
      int size = linkedlist.size();

      for(int x=0;x<size;x++){
         System.out.println(linkedlist.get(x));
      }

      //System.out.println(Arrays.toString(people));

      System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
      System.out.println("1. A Java interface is like recipe because it tells you how you should make the food (class).\n");

      System.out.println("2. An abstract method is like ingredient because it is in your recipe(interface) and thus should be in your food (class).\n");

      System.out.println("3. Polymorphism is like cooking because while cooking the food (object) can take many shapes. \n" +
            "By that I mean an object can be considered as multiple types of things. For example: \n   public class Donut extends Pastry{}" +
            "\nSo an object made from that is both a Donut and a Pastry.\n");

      System.out.println("4. If a class is a recipe that is being cooked than an object is the resulting food.\n");
   }

}

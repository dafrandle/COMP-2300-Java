package main;
/*
Main.Java -- main class
Purpose: make the program run
Project class
Dustin Frandle dafrandle@yahoo.com
Written: 10/07/2016
*/
//String characterName, String characterClass, char sex, int level, int health
public class Main {
   public static void main(String[] args) {
      Character guyMan = new Character("Point-man", "pointman", 'M', 5, 200);
      System.out.println("created character: " + guyMan.getCharacterName() + " sex: " + guyMan.getSex() + " class: "+ guyMan.getCharacterClass() + " level:" + guyMan.getLevel() + " " + guyMan.getHealth() + "/" + guyMan.getMaxHealth() + " health" );
      Character manGuy = new Character("Sniper Man", "sniper");
      System.out.println("created character: " + manGuy.getCharacterName() + " sex: " + manGuy.getSex() + " class: "+ manGuy.getCharacterClass() + " level:" + manGuy.getLevel() + " " + manGuy.getHealth() + "/" + manGuy.getMaxHealth() + " health" );
      Character invalidGuy = new Character("Broken Guy"); // used to show validateSex()
      System.out.println("created character: " + invalidGuy.getCharacterName() + " sex: " + invalidGuy.getSex() + " class: "+ invalidGuy.getCharacterClass() + " level:" + invalidGuy.getLevel() + " " + invalidGuy.getHealth() + "/" + invalidGuy.getMaxHealth() + " health" );
      System.out.println("attempt to set " + invalidGuy.getCharacterName() + " sex to 't'");
      invalidGuy.setSex('t');
      System.out.println("character: " + invalidGuy.getCharacterName() + " sex set to: " + invalidGuy.getSex());
      //---------------------------- below is a hard coded version of something you might see in a fully coded game
      System.out.println("----------------------------");
      System.out.println("this is a fake battle using placeholder variables and print lines");
      System.out.println("a wild boar appears!");
      int boarHealth = 50;
      while (boarHealth > 0){
         System.out.println("----------------------------");
         boarHealth -= guyMan.attack();
         boarHealth -= manGuy.attack();
         guyMan.damaged();
         manGuy.damaged();
         System.out.println("the boar has " + boarHealth + " health left out of 50.");
         if(guyMan.getHealth() < 1 && manGuy.getHealth() < 1){
            System.out.println("the boar has killed all your characters.");
            break;
         }
      }
      if(boarHealth < 1){
         System.out.println("the boar has been killed! Your characters have leveled up");
         guyMan.level ++;
         System.out.println(guyMan.getCharacterName() + " level: " + guyMan.getLevel());
         manGuy.level ++;
         System.out.println(manGuy.getCharacterName() + " level: " + manGuy.getLevel());
      }

   }

}


package main;

import java.util.Random;

/*
Character.Java
Purpose: hold data for characters and give them methods for stuff they can do
Project class
Dustin Frandle dafrandle@yahoo.com
Written: 10/07/2016

TO-DO to make this an actual game:
1. add try-catches for user input
2. add support for user input
3. make attack and damaged methods use more than sample data
 */
public class Character {
   //---------------------------------------------------------------- PROPERTIES
   private String characterName = "null";
   private char sex = 'O';
   private String characterClass = "null";
   protected int level = 1; //cap of 20
   private int health = 100;
   private int maxHealth = health;
   //---------------------------------------------------------------- CONSTRUCTORS
   public Character(){} //basic
   public Character(String characterName){this.characterName = characterName;} //just name
   public Character(char sex){ //---------------------gender
      boolean valid = validateSex(sex);
      if (valid) {
         this.sex = sex;
      }else{
      }
   }
   public Character(int level){this.level = level;} //--------------XP level
   public Character(int level, int health){ //-------------------XP level and health
      this.level = level;
      this.health = health;
      this.maxHealth = health;
   }
   public Character(String characterName, String characterClass){ //----------name and class
      this.characterName = characterName;
      this.characterClass = characterClass;
   }
   public  Character(String characterName, String characterClass, char sex, int level, int health){ // all variables
      this.characterName = characterName;
      this.characterClass = characterClass;
      boolean valid = validateSex(sex);
      if (valid) {
         this.sex = sex;
      }else{
      }
      this.level = level;
      this.health = health;
      this.maxHealth = health;
   }
   //---------------------------------------------------------------- METHODS
   private boolean validateClass(String characterClass){// would have a list of the various things you can be and makes sure you picked one of them
      return true; // I did not fully write this one because just to show you i know this stuff i don't have to come up with a thousand classes that characters can be
   }//----------------------------------------------------end of validateClass
   private boolean validateSex(char sex){ // gives a boolean to tell the things that called it how to respond
      if (sex != 'M' && sex != 'm' && sex != 'F' && sex != 'f' && sex != 'O' && sex != 'o'){// if sex don't = one of these then it's not valid
         System.out.println("input of gender for " + characterName + " is not valid");
         return false;
      }else {
         return true;
      }
   }//----------------------------------------------------end of validateSex
   public int attack (){//do calculations to determine attack strength
      int attackPower;
      Double mathNumber; // need to use Double to use .intValue()
      double healthCalc = health;
      double maxHealthCalc = maxHealth;
      double buff = (healthCalc / maxHealthCalc) * .7;
      Random rng = new Random();
      int random = rng.nextInt(20) + 1;

      // check health, if 0 quit out because your ded.
      // check class, add buff/de-buff to attack power based on class -- store this in buff
      // calculate damage: attackPower = ((Random number * level) * (health / maxHealth)) * buff
      if(isAlive(health)) {
         mathNumber = ((random * level) * buff);
         attackPower = mathNumber.intValue();
         System.out.println(characterName + " did " + attackPower + " damage.");
         return attackPower;
      }else {
         System.out.println(characterName + " cant attack because " + characterName + " is dead");
         return 0;
      }
   }//----------------------------------------------------end of attack
   public void damaged() {//do calculations to determine amount of damage, subtracts from health variable
      int damage;
      int protection = 0;
      Random rng = new Random();
      int random = rng.nextInt(maxHealth) + 1;
      int healthBefore = health; //if rng gives a number that will become negative this prevents the charter from getting healed

      // check health, if 0 quit out because your ded.
      // check class, add value for protection to damage -- store this in protection
      // calculate damage: damage = Random number - protection - level
      if(isAlive(health)) {
         damage = random - protection - level;
         health -= damage;
         if (damage < 0){
            health = healthBefore;
            damage = 0;
         }
         System.out.println(characterName + " took " + damage + " damage and has " + health + " health left.");
      }
   }//----------------------------------------------------end of damaged
   protected boolean isAlive(int health){// checks the health of a character to tell you if they are dead or not
      boolean alive;
      if (health > 0){
         alive = true;
      }else{
         alive = false;
         System.out.println(characterName + " is dead.");
      }
      return alive;
   }
   //---------------------------------------------------------------- GET
   public String getCharacterName(){return characterName;}
   public String getCharacterClass(){return characterClass;}
   public char getSex(){return sex;}
   public int getLevel(){return level;}
   public int getHealth(){return health;}
   public int getMaxHealth(){return  maxHealth;}
   //---------------------------------------------------------------- SET
   public void setCharacterName(String characterName){
      this.characterName = characterName;
   }
   public void setCharacterClass(String characterClass){
      this.characterClass = characterClass;
   }
   public void setSex(char sex){
      boolean valid = validateSex(sex);
      if (valid) {
         this.sex = sex;
      }else{
      }
   }
   public void setLevel(int level){
      this.level = level;
   }
   public void setHealth(int health){ this.health = health;}
   public void setMaxHealth(int maxHealth){ this.maxHealth = maxHealth;}
}


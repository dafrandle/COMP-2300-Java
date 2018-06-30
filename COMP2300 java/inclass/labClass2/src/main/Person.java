package main;
/*
Person.Java
Project labClass2
Dustin Frandle dafrandle@yahoo.com
Written: 10/04/2016
*/

public class Person {
   //---------------------------------------------------------------- PROPERTIES
   private String firstName = "null";
   private String lastName = "null";
   private int age = -1;
   private char sex = 'N';
   //---------------------------------------------------------------- CONSTRUCTORS
   public Person(){}
   public Person(String firstName){
      this.firstName = firstName;
   }



   //---------------------------------------------------------------- METHODS

   //---------------------------------------------------------------- GET
   public String getFirstName(){
      return firstName;
   }
   public String getLastName(){
      return lastName;
   }
   public int getAge(){
      return age;
   }
   public char getSex(){
      return sex;
   }
   //---------------------------------------------------------------- SET
   public void setFirstName(String firstName){
      this.firstName = firstName;
   }
   public void setLastName(String lastName){
      this.lastName = lastName;
   }
   public void setAge(int age){
      this.age = age;
   }
   public void setSex(char sex){
      this.sex = sex;
   }
}

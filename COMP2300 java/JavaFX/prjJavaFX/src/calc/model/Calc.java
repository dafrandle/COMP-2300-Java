package calc.model;
/*
Calc.Java
Project prjJavaFX
Dustin Frandle dafrandle@yahoo.com
Written: 12/01/2016
*/

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Calc {
   //---------------------------------------------------------------- PROPERTIES
   private DoubleProperty valueOne = new SimpleDoubleProperty(0);
   private DoubleProperty valueTwo = new SimpleDoubleProperty(0);
   private DoubleProperty answerValue = new SimpleDoubleProperty(0);

   //---------------------------------------------------------------- CONSTRUCTORS
   public Calc() {}
   public Calc(Double testNum1, Double testNum2){
      this.valueOne = new SimpleDoubleProperty(testNum1);
      this.valueTwo = new SimpleDoubleProperty(testNum2);
      this.answerValue = new SimpleDoubleProperty(testNum1 + testNum2);
   }
   //---------------------------------------------------------------- METHODS

   //---------------------------------------------------------------- GET
   public double getValueOne() {
      return valueOne.get();
   }
   public DoubleProperty valueOneProperty() {
      return valueOne;
   }
   public double getValueTwo() {
      return valueTwo.get();
   }
   public DoubleProperty valueTwoProperty() {
      return valueTwo;
   }
   public double getAnswerValue() {
      return answerValue.get();
   }
   public DoubleProperty answerValueProperty() {
      return answerValue;
   }
   //---------------------------------------------------------------- SET
   public void setValueOne(double number) {valueOne.set(number);}
   public void setValueTwo(double number) {valueTwo.set(number);}
   public void setAnswerValue(double number) {answerValue.set(number);}
}
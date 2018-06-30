package calc.view;
/*
CalcController.Java
Project prjJavaFX
Dustin Frandle dafrandle@yahoo.com
Written: 12/01/2016
*/
import calc.MainApp;
import calc.model.Calc;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CalcController {
   //---------------------------------------------------------------- PROPERTIES
   @FXML
   private TextField valueOne;
   @FXML
   private TextField valueTwo;
   @FXML
   private TextField answer;

   private Boolean focus = true;
   private MainApp mainApp;
   //---------------------------------------------------------------- CONSTRUCTORS
   public CalcController() {}
   //---------------------------------------------------------------- METHODS
   @FXML
   public void initialize(){
      valueOne.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
         if (newPropertyValue) {
            focus = true;
         }
      });
      valueTwo.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
         if (newPropertyValue) {
            focus = false;
         }
      });
   }

   @FXML
   private void calculate(){
      double v1;
      double v2;
      String ans;
      Calc calcAction = new Calc();

      try {
         v1 = Double.parseDouble(valueOne.getText());
         calcAction.setValueOne(v1);
      }catch (Exception e){
         calcAction.setValueOne(0);
         valueOne.setText("0");
      }


      try {
         v2 = Double.parseDouble(valueTwo.getText());
         calcAction.setValueTwo(v2);
      }catch (Exception e){
         calcAction.setValueTwo(0);
         valueTwo.setText("0");
      }

      calcAction.setAnswerValue(calcAction.getValueOne() + calcAction.getValueTwo());
      answer.setText(Double.toString(calcAction.getAnswerValue()));

   }

   @FXML
   private void buttonHandler(Event event){
      String data = event.toString();
      data = data.substring((data.indexOf("]'")+2), (data.indexOf("']")));
      dataHandler(data);
   }

   private void dataHandler(String input){
      boolean empty = checkData();
      if (focus) {
         if (empty){
            valueOne.setText(input);
         }else {
            valueOne.setText((valueOne.getText() + input));
         }
      }else{
         if (empty) {
            valueTwo.setText(input);
         }else {
            valueTwo.setText((valueTwo.getText() + input));
         }

      }
   }

   private boolean checkData(){
      boolean empty;
      if(focus){
         empty = (valueOne.getText() == "" || valueOne.getText() == null);
         if(!empty) {
            try {
               empty = (Double.parseDouble(valueOne.getText()) == 0);
            } catch (Exception e) {
               empty = false;
            }
         }
      }else{
         empty = (valueTwo.getText() == "" || valueTwo.getText() == null);
         if(!empty) {
            try {
               empty = (Double.parseDouble(valueTwo.getText()) == 0);
            } catch (Exception e) {
               empty = false;
            }
         }
      }
      return empty;
   }

   @FXML
   private void clear(){
      if (focus){
         valueOne.setText("");
      }else{
         valueTwo.setText("");
      }
   }

   //---------------------------------------------------------------- GET

   //---------------------------------------------------------------- SET
   public void setMainApp(MainApp mainApp){
      this.mainApp = mainApp;
   }
}
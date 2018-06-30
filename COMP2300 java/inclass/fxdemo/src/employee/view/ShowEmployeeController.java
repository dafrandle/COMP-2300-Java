package employee.view;
/*
ShowEmployeeController.Java
Project fxdemo
Dustin Frandle dafrandle@yahoo.com
Written: 11/29/2016
*/
import employee.MainApp;
import employee.model.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class ShowEmployeeController {
   //---------------------------------------------------------------- PROPERTIES
   @FXML
   private TableView<Employee> employeeTable;
   @FXML
   private TableColumn<Employee, String> firstNameColumn;
   @FXML
   private TableColumn<Employee, String> lastNameColumn;
   private MainApp mainApp;
   //---------------------------------------------------------------- CONSTRUCTORS
   public ShowEmployeeController(){}
   //---------------------------------------------------------------- METHODS
   @FXML
   private void initialize(){
      firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
      lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
   }

   //---------------------------------------------------------------- GET

   //---------------------------------------------------------------- SET
   public void setMainApp(MainApp mainApp){
      this.mainApp = mainApp;
      employeeTable.setItems(mainApp.getEmployeeData());
   }
}


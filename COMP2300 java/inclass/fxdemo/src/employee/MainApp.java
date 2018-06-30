package employee;

import employee.model.Employee;
import employee.view.ShowEmployeeController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class MainApp extends Application {
   private Stage primaryStage;
   private AnchorPane rootLayout;
   private ObservableList<Employee> employeeData = FXCollections.observableArrayList();

   public MainApp() {
      employeeData.add(new Employee("Sue", "Smith"));
      employeeData.add(new Employee("Bob", "Dole"));
      employeeData.add(new Employee("Jim", "Shmity"));
      employeeData.add(new Employee("Carol", "Alice"));
   }
   public ObservableList<Employee> getEmployeeData(){return  employeeData;}

   @Override
   public void start(Stage primaryStage) {
      this.primaryStage = primaryStage;
      this.primaryStage.setTitle("Employee List");
      initRootLayout();
   }
   public void initRootLayout(){
      try {
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(MainApp.class.getResource("view/EmployeeOverview.fxml"));
         rootLayout = (AnchorPane) loader.load();

         ShowEmployeeController controller = loader.getController();

         controller.setMainApp(this);

         Scene scene = new Scene(rootLayout);
         primaryStage.setScene(scene);
         primaryStage.show( );

      }catch (IOException e) {
         e.printStackTrace();
      }
   }
   public Stage getPrimaryStage(){return primaryStage;}


   public static void main(String[] args) {
        launch(args);
    }
}

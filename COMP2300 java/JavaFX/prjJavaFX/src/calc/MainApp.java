package calc;
/*
MainApp.Java
Project prjJavaFX
Dustin Frandle dafrandle@yahoo.com
Written: 12/01/2016
*/
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import calc.model.Calc;
import calc.view.CalcController;

//import java.awt.GraphicsEnvironment;

public class MainApp extends Application {
   private Stage primaryStage;
   private AnchorPane rootLayout;


   @Override
   public void start(Stage primaryStage) throws Exception{
      this.primaryStage = primaryStage;
      this.primaryStage.setTitle("Simple Calc");
      initRootLayout();
   }

   public void initRootLayout(){
      try{
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(MainApp.class.getResource("view/calcLayout.fxml"));
         rootLayout = (AnchorPane) loader.load();

         Scene scene = new Scene(rootLayout);
         primaryStage.setScene(scene);
         primaryStage.show();
      }catch (IOException e){
         e.printStackTrace();
      }
   }


   public Stage getPrimaryStage() {
      return primaryStage;
   }

   public static void main(String[] args) {
      /*String fonts[] =
            GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();  -------used to find fonts for css

      for ( int i = 0; i < fonts.length; i++ )
      {
         System.out.println(fonts[i]);
      }*/
      launch(args);
   }
}
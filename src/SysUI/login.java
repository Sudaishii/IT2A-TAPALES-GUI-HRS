/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Rasheed
 */
public class login extends Application {
    
        public static void main(String[] args) {
               
            launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
       Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
       Scene scene = new Scene(root);
      
       
       String css = this.getClass().getResource("application.css").toExternalForm();
       scene.getStylesheets().add(css);
       
       stage.getIcons().add(new Image("imgs/money.png"));
       stage.setTitle("PayFuse");
       
 
       stage.setResizable(false);
       stage.setScene(scene);
      
       stage.show();
       //d4bdac
    }
    
}

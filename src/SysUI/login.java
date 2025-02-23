/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysUI;

import java.awt.Color;
import java.awt.Paint;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
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

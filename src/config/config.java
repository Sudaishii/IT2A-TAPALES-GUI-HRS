/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Rasheed
 */
public class config {
    
    public void showAlert(Alert.AlertType alertType, String title, String message) {
   
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);


    DialogPane dialogPane = alert.getDialogPane();
    dialogPane.getStylesheets().add(getClass().getResource("/registration/registration.css").toExternalForm());
    dialogPane.getStyleClass().add("alert");


    Stage alertStage = (Stage) dialogPane.getScene().getWindow();


    alertStage.setResizable(false);

   
    alertStage.initStyle(StageStyle.UTILITY);


    alertStage.initModality(Modality.APPLICATION_MODAL);

  

   
    alertStage.setOnShown(event -> {
    
        alertStage.setX(alertStage.getX());
        alertStage.setY(alertStage.getY());
    });

   
    alert.showAndWait();
    
   }
    
     public void switchScene(Class getClass, Event evt, String targetFXML) throws Exception {
        Parent root = FXMLLoader.load(getClass.getResource(targetFXML));
        Stage stage = (Stage)((Node)evt.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

   
    
}

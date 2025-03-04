/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysUI.Registration;

import config.config;
import config.dbConnect;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author Rasheed
 */
public class controller {

    @FXML
    private AnchorPane APaneReg;
    @FXML
    private Label label1;
    @FXML
    private Button LogIn;
    @FXML
    private Pane panereg;
    @FXML
    private Pane RegPane;
    private TextField fname;
    private TextField lname;
    @FXML
    private Button btnSign;
    @FXML
    private Label lblpass1;
    private TextField contactF;
    @FXML
    private TextField emailF;
    @FXML
    private TextField usernameF;
    @FXML
    private PasswordField passwordF;
   
    
    dbConnect db = new dbConnect();
    @FXML
    private Label lblpass11;
    @FXML
    private Label lblpass111;
    private Image imageOn;
    private Image imageOff;
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException{
        
       
    Scene scene = ((Node) event.getSource()).getScene();
    Pane rootPane = (Pane) scene.getRoot();

   
    Parent registrationPane = (Parent) rootPane.getChildren().get(rootPane.getChildren().size() - 1); 

   
    Timeline timeline = new Timeline();

   
    KeyValue keyValue = new KeyValue(registrationPane.translateXProperty(), scene.getWidth(), Interpolator.EASE_OUT);
    KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);

    timeline.getKeyFrames().add(keyFrame);

    
    timeline.play();

   
    timeline.setOnFinished(e -> rootPane.getChildren().remove(registrationPane));
        
    }
    
    config con = new config();

    @FXML
    private void RegisterClick(javafx.scene.input.MouseEvent event) {
        
        String username = usernameF.getText().trim();
        String email = emailF.getText().trim();
        String password = passwordF.getText().trim();
        
        
        if (!validateInputs(email, username, password)) {
            return;
        }

            try {
        // List to collect error messages
        List<String> errors = new ArrayList<>();

       
        if (isDuplicate("user_email", email)) {
            errors.add("This email is already registered.");
        }

     
        if (isDuplicate("user_name", username)) {
            errors.add("This username is already taken.");
        }

  
        if (!errors.isEmpty()) {
            String errorMessage = String.join("\n", errors); // Combine errors into a single message
            con.showAlert(Alert.AlertType.ERROR, "Registration Error", errorMessage);
            return; 
        }

       
        String sql = "INSERT INTO users (user_email, user_name, user_pass, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = db.getConnection().prepareStatement(sql)) {
            pst.setString(1, email);
            pst.setString(2, username);
            pst.setString(3, password);
            pst.setString(4, "Newly Registered");
            pst.executeUpdate();
            con.showAlert(Alert.AlertType.INFORMATION, "Registration Successful", "You have successfully registered!");
            clearFields();
        }
    } catch (SQLException ex) {
        con.showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred: " + ex.getMessage());
    }

    }
        private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    
    
    }
    
    
    private boolean validateInputs(String email, String username, String password) {
        String errorMessage = null;

        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            errorMessage = "All fields are required.";
        } else if (password.length() < 8) {
            errorMessage = "Password must be at least 8 characters long.";
            
        } 
        
        else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) { 
            System.out.println("Email being checked: " + email);
            errorMessage = "Please enter a valid email address.";
        }

        if (errorMessage != null) {
            con.showAlert(Alert.AlertType.ERROR, "Validation Error", errorMessage); 
            return false;
        }

        return true; 
}
    private boolean isDuplicate(String column, String value) throws SQLException {
      
        
        String sql = "SELECT 1 FROM users WHERE " + column + " = ?";
        try (PreparedStatement pst = db.getConnection().prepareStatement(sql)) {
            pst.setString(1, value);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        }
    }
    
 
   

   
   

    private void clearFields() {
     
        emailF.clear();
        passwordF.clear();
        usernameF.clear();
        
    }


 
    
 }

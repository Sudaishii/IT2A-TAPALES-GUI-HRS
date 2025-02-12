/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration;

import config.dbConnect;
import java.awt.event.MouseEvent;
import java.io.IOException;
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
    private Label lblemail;
    @FXML
    private Label lblun;
    @FXML
    private Label lblpass;
    @FXML
    private Label lblcpass;
    @FXML
    private Pane RegPane;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private Button btnSign;
    @FXML
    private Label lblcpass11;
    @FXML
    private Label lblpass1;
    @FXML
    private TextField contactF;
    @FXML
    private TextField emailF;
    @FXML
    private TextField usernameF;
    @FXML
    private PasswordField passwordF;
   
   
 dbConnect db = new dbConnect();
    

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

    @FXML
    private void RegisterClick(javafx.scene.input.MouseEvent event) {
        
         String firstname = fname.getText().trim();
        String lastname = lname.getText().trim();
        String email = emailF.getText().trim();
        String contact = contactF.getText().trim();
        String username = usernameF.getText().trim();
        String password = passwordF.getText().trim();
        
        
        if (!validateInputs(firstname, lastname, contact, email, username, password)) {
            return;
        }

        try {
            if (isDuplicate("user_email", email)) {
                showAlert(Alert.AlertType.ERROR, "Duplicate Email", "This email is already registered.");
                return;
            }

            if (isDuplicate("user_name", username)) {
                showAlert(Alert.AlertType.ERROR, "Duplicate Username", "This username is already taken.");
                return;
            }

            String sql = "INSERT INTO users (user_fname, user_lname, contact, user_email, user_name, user_pass, user_role) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pst = db.getConnection().prepareStatement(sql)) {
                pst.setString(1, firstname);
                pst.setString(2, lastname);
                pst.setString(3, contact);
                pst.setString(4, email);
                pst.setString(5, username);
                pst.setString(6, password);
                pst.setString(7, "Newly Registered");  
                pst.executeUpdate();
                showAlert(Alert.AlertType.INFORMATION, "Registration Successful", "You have successfully registered!");
                clearFields();
            }
        } catch (SQLException ex) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred: " + ex.getMessage());
        }
        
        

        
    }

   

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private boolean validateInputs(String fname, String lname, String contact, String email, String username, String password) {
        if (fname.isEmpty() || lname.isEmpty() || contact.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields except middle name are required.");
            return false;
        }

        if (password.length() < 8) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Password must be at least 8 characters long.");
            return false;
        }

        if (!contact.matches("\\d{10,12}")) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Contact number must be 10 to 12 digits long.");
            return false;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please enter a valid email address.");
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

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        fname.clear();
        lname.clear();
        emailF.clear();
        contactF.clear();
        passwordF.clear();
        usernameF.clear();
        
    }

 
    
 }

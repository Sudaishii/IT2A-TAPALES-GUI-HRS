
package SysUI;

import config.dbConnect;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;




public class controller {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Button btnreg;
    @FXML
    private AnchorPane Apane1;
    @FXML
    private Label welcome;
    @FXML
    private TextField UNField;
    @FXML
    private PasswordField PassField;
    @FXML
    private Label Title;
    @FXML
    private Button btnlog;
    @FXML
    private ImageView image;
    @FXML
    private Rectangle reclog;
  
    
    
    dbConnect db = new dbConnect();
    
   
   

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
   Parent registrationPane = FXMLLoader.load(getClass().getResource("/registration/registration.fxml"));

  
    Scene scene = btnreg.getScene(); 
    Pane rootPane = (Pane) scene.getRoot();

   
    registrationPane.translateXProperty().set(scene.getWidth()); 

       rootPane.getChildren().add(registrationPane);

 
    Timeline timeline = new Timeline();

 
    double registrationPaneWidth = registrationPane.prefWidth(-1); 
    double finalTranslateX = scene.getWidth() - registrationPaneWidth; 

  
    KeyValue keyValue = new KeyValue(registrationPane.translateXProperty(), finalTranslateX, Interpolator.EASE_IN);
    KeyFrame keyFrame = new KeyFrame(Duration.millis(600), keyValue);

    timeline.getKeyFrames().add(keyFrame);

   
    timeline.play();

  
    timeline.setOnFinished(e -> {
      
    });
}
    
    
    @FXML
    private void LogInButton(ActionEvent event) throws IOException, Exception {
        
        String username = UNField.getText().trim();
        String password = PassField.getText().trim();

       

       

               if (username.isEmpty() || password.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Validation Error", "Username and password cannot be empty.");
                    
                }

                try {
                    String role = authenticateUser(username, password);
                    if (role != null) {
                        showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome back!");

                        // Cater first for the admin
                        if (role.equalsIgnoreCase("admin")) {  // Simplified condition
                            switchScene(getClass(), event, "/dashboard/dashboard_reference.fxml");
                        } else { 
//                            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome back!");
//                            switchScene(getClass(), event, "/dashboard/dash_controller.fxml"); // Default to user dashboard
                          //  showAlert(Alert.AlertType.WARNING, "Role Information", "You are logged in as a " + role + ".  This is the default user view.");
                          //  switchScene(getClass(), event, "/fxml/UserDashboard.fxml"); // Redirect to user dashboard
                        }
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
                    }
                } catch (SQLException ex) {
                  
                } 
                   
                
   
    }          
    
            private String authenticateUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_name = ? AND user_pass = ?";
        try (PreparedStatement pst = db.getConnection().prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString("user_role");  // Return user role if authentication is successful
            }
        }
        return null;
    }

    // Utility method to show alert messages
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to switch scenes
    public void switchScene(Class<?> clazz, Event evt, String targetFXML) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(targetFXML));  
        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
        
    }


//    // Method to center the stage on the screen
//    public void setCenterAlignment(Stage stage) {
//        javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//        double centerX = (screenBounds.getWidth() - stage.getWidth()) / 2;
//        double centerY = (screenBounds.getHeight() - stage.getHeight()) / 2;
//        stage.setX(centerX);
//        stage.setY(centerY);
//    }
   
}
    
    


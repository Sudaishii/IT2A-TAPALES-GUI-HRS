
package SysUI.LogIn;

import config.Session;
import config.config;
import config.dbConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
    @FXML
    private Label greetLabel;
  
      Session ses = new Session();
    
    dbConnect db = new dbConnect();
    @FXML
    private Label errorUnLabel;
    @FXML
    private Label errorPassLabel;
    
   
   public void initialize(URL url, ResourceBundle rb) {
        
      
        
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
    Parent registrationPane = FXMLLoader.load(getClass().getResource("/SysUI/Registration/registration.fxml"));

  
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
    config con = new config();
    
    @FXML
    private void LogInButton(ActionEvent event) throws IOException, Exception {
        
        String username = UNField.getText().trim();
        String password = PassField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            con.showAlert(Alert.AlertType.ERROR, "Validation Error", "Username and password cannot be empty.");
            return;
        }

        try {  // The try-catch is BACK!  This is essential.
            String role = authenticateUser(username, password);
            if (role != null) {
            
                

                if (role.equalsIgnoreCase("HR_Admin")) {
                    
                    con.showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome back!");
                    con.showAlert(Alert.AlertType.INFORMATION, "Redirecting...", "You are logged in as a " + role + ".  Redirecting to your dashboard.");
                    con.switchScene(getClass(), event, "/SysUI/AdminDashboard/FXML.fxml");
                    
                } 
               
                else if(role.equalsIgnoreCase("Employee")){
                    con.showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome back!");
                    con.showAlert(Alert.AlertType.INFORMATION, "Redirecting...", "You are logged in as a " + role + ".  Redirecting to your dashboard.");
                    con.switchScene(getClass(), event, "/dashboard/employees/employee_dashboard.fxml");
           
                }
           

            } else {
                con.showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); 
            con.showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred during login. Please try again later.");
        }
            
    }
              
    
     

   public String authenticateUser(String username, String password) throws SQLException {
       String sql = "SELECT u.user_id, u.user_name, r.role_name FROM users u INNER JOIN roles r ON u.role_id = r.role_id WHERE u.user_name = ? AND u.user_pass = ?"; 
        try (PreparedStatement pst = db.getConnection().prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, password);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String Username = rs.getString("user_name");
                    String role = rs.getString("role_name"); // Get user role first
                
                    
                    ses.createSession(userId, Username);

                    return role; 
                }
            }
        }
        return null;
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
    
    


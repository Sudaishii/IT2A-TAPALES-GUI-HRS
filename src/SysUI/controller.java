
package SysUI;

import java.io.IOException;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
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
    private void LogInButton(ActionEvent event) throws IOException {
    
       Parent root = FXMLLoader.load(getClass().getResource("/dashboard/dashboard.fxml"));
       Stage stge = (Stage)((Node)event.getSource()).getScene().getWindow();
       Scene scene = new Scene(root);
       stge.setScene(scene);
       stge.show();
       
    }   
}
    
    


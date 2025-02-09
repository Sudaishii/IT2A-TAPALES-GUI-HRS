
package SysUI;

import java.io.IOException;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    // Get the current scene and root pane
    Scene scene = btnreg.getScene(); 
    Pane rootPane = (Pane) scene.getRoot();

    // Set the initial position of the registration pane (off-screen to the right)
    registrationPane.translateXProperty().set(scene.getWidth()); // Start from the right side

    // Add the registration pane to the root pane
    rootPane.getChildren().add(registrationPane);

    // Create a Timeline for the slide-in animation
    Timeline timeline = new Timeline();

    // Calculate the final position (stop when the registration pane is fully visible)
    double registrationPaneWidth = registrationPane.prefWidth(-1); // Get the width of the registration pane
    double finalTranslateX = scene.getWidth() - registrationPaneWidth; // Stop at this position

    // Define the animation: Move the registration pane from the right to its final position
    KeyValue keyValue = new KeyValue(registrationPane.translateXProperty(), finalTranslateX, Interpolator.EASE_IN);
    KeyFrame keyFrame = new KeyFrame(Duration.millis(600), keyValue); // Animation duration: 1 second

    // Add the keyframe to the timeline
    timeline.getKeyFrames().add(keyFrame);

    // Play the animation
    timeline.play();

    // Optional: Remove the old pane after the animation completes (if needed)
    timeline.setOnFinished(e -> {
        // Example: Remove the old pane (if needed)
        // rootPane.getChildren().remove(0); // Remove the first child (old pane)
    });
}
    
    
    
     
}
    
    


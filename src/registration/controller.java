/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration;

import java.io.IOException;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Button btnSign;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException{
        
        // Get the current scene and root pane
    Scene scene = ((Node) event.getSource()).getScene();
    Pane rootPane = (Pane) scene.getRoot();

    // Find the registration pane that was previously added
    Parent registrationPane = (Parent) rootPane.getChildren().get(rootPane.getChildren().size() - 1); 

    // Create a Timeline for the slide-out animation
    Timeline timeline = new Timeline();

    // Move the registration pane back out to the right
    KeyValue keyValue = new KeyValue(registrationPane.translateXProperty(), scene.getWidth(), Interpolator.EASE_OUT);
    KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);

    timeline.getKeyFrames().add(keyFrame);

    // Play the animation
    timeline.play();

    // Remove the registration pane after the animation completes
    timeline.setOnFinished(e -> rootPane.getChildren().remove(registrationPane));
        
    }
}

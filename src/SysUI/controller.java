
package SysUI;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class controller {

    private Stage stage;
    private Scene scene;
    private Parent roo;
    
    @FXML
    private AnchorPane Apane1;
    @FXML
    private TextField UNField;
    @FXML
    private PasswordField PassField;
    @FXML
    private Button btnlog;
    @FXML
    private Button btnreg;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("registration.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        
    }
    
    
    
    
}

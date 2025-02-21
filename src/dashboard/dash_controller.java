package dashboard;

import config.dbConnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class dash_controller implements Initializable {

    @FXML
    private TableView<users> lamesa;

    @FXML
    private TableColumn<users, Integer> id;

    @FXML
    private TableColumn<users, String> fname;

    @FXML
    private TableColumn<users, String> lname;

    @FXML
    private TableColumn<users, String> contact;

    @FXML
    private TableColumn<users, String> email;

    @FXML
    private TableColumn<users, String> username;

    @FXML
    private TableColumn<users, String> password;

    @FXML
    private TableColumn<users, String> role;

    private Connection connection = null; 

    ObservableList<users> usersList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        id.setCellValueFactory(new PropertyValueFactory<>("Id"));       
        fname.setCellValueFactory(new PropertyValueFactory<>("Fname"));  
        lname.setCellValueFactory(new PropertyValueFactory<>("Lname"));   
        contact.setCellValueFactory(new PropertyValueFactory<>("Contact")); 
        email.setCellValueFactory(new PropertyValueFactory<>("Email"));   
        username.setCellValueFactory(new PropertyValueFactory<>("Username"));
        password.setCellValueFactory(new PropertyValueFactory<>("Password"));
        role.setCellValueFactory(new PropertyValueFactory<>("Role"));    

        loadUserData();
    }

    private void loadUserData() {
    usersList.clear();
    dbConnect dbc = new dbConnect();
    connection = dbc.getConnection(); 

    if (connection == null) {  
        System.err.println("Database connection is null. Check dbConnect.");
        showAlert("Database Error", "Could not connect to the database.");
        return; 
    }

    String query = "SELECT user_id, user_fname, user_lname, contact, user_email, user_name, user_pass, user_role FROM users;";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery()) {

      while (resultSet.next()) {
    users user = new users(
        resultSet.getInt("user_id"),         
        resultSet.getString("user_fname"),    
        resultSet.getString("user_lname"),    
        resultSet.getString("contact"),       
        resultSet.getString("user_email"),    
        resultSet.getString("user_name"), 
        resultSet.getString("user_pass"), 
        resultSet.getString("user_role")      
    );
    usersList.add(user);
}

            
        lamesa.setItems(usersList); 

    } catch (SQLException ex) {
        System.err.println("Database Error: " + ex.getMessage());
        showAlert("Database Error", "Error loading user data: " + ex.getMessage());

    } finally {
        if (connection!= null) {
            try {
                connection.close(); 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
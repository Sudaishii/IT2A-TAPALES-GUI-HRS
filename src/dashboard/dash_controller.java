/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.DriverManager;
import java.sql.Statement;
import config.DBconnector;



    

public class dash_controller implements Initializable {
    
     
     
     

    public class users {
    private int id;
    private String email;
    private String Usern;
    private String pass;

    // Constructor, Getters, and Setters (Important!)
    public users(int id, String email, String Usern, String pass) {
        
        this.id = id;
        this.email = email;
        this.email = email;
        this.pass = pass;
        
        
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String name) { this.email = name; }
    public String getUser() { return Usern; }
    public void setUser(String un) { this.Usern = un; }
    public String getPass() { return pass; }
    public void setPass(String pass) { this.pass = pass; }
    
    
    }
    
    @FXML
    private TableView<users> lamesa;
    
    @FXML
    private TableColumn<users, Integer> id;
    @FXML
    private TableColumn<users, String> User_Email;
    @FXML
    private TableColumn<users, String> Username;
    @FXML
    private TableColumn<users, String> Password;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        id.setCellValueFactory(new PropertyValueFactory("id"));
        User_Email.setCellValueFactory(new PropertyValueFactory("User_Email"));
        Username.setCellValueFactory(new PropertyValueFactory("Username"));
        Password.setCellValueFactory(new PropertyValueFactory("Password"));
        
    }
    
        @FXML
        private void loadUserData() {
            ObservableList<users> userList = lamesa.getItems();
            userList.clear(); // Clear previous data

          
        }
    
}

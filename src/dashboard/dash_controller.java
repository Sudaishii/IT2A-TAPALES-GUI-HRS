package dashboard;

import config.config;
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
import javafx.scene.shape.Rectangle;


public class dash_controller implements Initializable {
    
    @FXML
    private TableView<employees> empTable;
    
   
    
    @FXML
    private Rectangle header;
    @FXML
    private Rectangle KPI_rec1;
    @FXML
    private Rectangle KPI_rec2s;
    @FXML
    private Rectangle KPI_rec3;
    
    @FXML
    private TableColumn<employees, Integer> id;
    
    @FXML
    private TableColumn<employees, String> fname;
    
    @FXML
    private TableColumn<employees, String> lname;
    
    @FXML
    private TableColumn<employees, String> dept;
    
    private dbConnect db = new dbConnect();
    private ObservableList<employees> empList;
   
    
    
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        empList = FXCollections.observableArrayList();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));       
        fname.setCellValueFactory(new PropertyValueFactory<>("Fname"));  
        lname.setCellValueFactory(new PropertyValueFactory<>("Lname"));   
        dept.setCellValueFactory(new PropertyValueFactory<>("Dept")); 
        
   

        loadDataFromDatabase();
    }

     private void loadDataFromDatabase() {
         if (db == null) {
        System.out.println("Database connection is NULL");
        return;
    }
        String query = "SELECT emp_id, emp_fname, emp_lname, emp_dept FROM employee";
        try {
            ResultSet rs = db.getData(query);
            if (rs == null) {
                System.out.println("ResultSet is null");
                return;
            }

            while (rs.next()) {
                int id = rs.getInt("emp_id");
                String fname = rs.getString("emp_fname");
                String lname = rs.getString("emp_lname");
                String dept = rs.getString("emp_dept");
                

                empList.add(new employees(id, fname, lname, dept));
            }

            empTable.setItems(empList);

        } catch (SQLException e) {
            e.printStackTrace();
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
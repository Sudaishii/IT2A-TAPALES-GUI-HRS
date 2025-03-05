/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysUI.AdminDashboard;

import config.Session;
import config.config;
import config.dbConnect;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.application.Platform;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rasheed
 */
public class FXMLController implements Initializable {
    
    private static FXMLController instance;
    
    @FXML 
    private Pane header1;
    
    private TableView <employees> empTable;
    
    private TableColumn<employees, Integer> id;
    
    private TableColumn<employees, String> fname;
    
    private TableColumn<employees, String> lname;
    
    private TableColumn<employees, String> dept;
    @FXML
    private Button employeesButton;
    @FXML
    private BorderPane rootPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button dashboardButton;
    
    
    



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        /*
        
        
        empList = FXCollections.observableArrayList();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));       
        fname.setCellValueFactory(new PropertyValueFactory<>("Fname"));  
        lname.setCellValueFactory(new PropertyValueFactory<>("Lname"));   
        dept.setCellValueFactory(new PropertyValueFactory<>("Dept")); 
        
        
        
        empTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        Platform.runLater(() -> {
            ScrollBar hBar = (ScrollBar) empTable.lookup(".scroll-bar:horizontal");
            if (hBar != null) {
                hBar.setValue(0.5); 
            }
        });
        
        
        Session ses = new Session();
        
        String uname;
        uname = ses.getUname();
  
        
        greetLabel.setText("Welcome, " + uname + "!");
        
       ObservableList<String> months = FXCollections.observableArrayList(
            "    Overall", "    January", "    February", "    March", "    April", "    May", "    June",
            "    July", "    August", "    September", "    October", "    November", "    December"
        );
       
       ObservableList<String> departments = FXCollections.observableArrayList(
               "    Overall", "    IT", "    Finance"
       );
       
        deptcombo.setItems(departments);
        deptcombo.setPromptText("    Select Department");
        
        monthCombo.setItems(months);
        monthCombo.setPromptText("    Select Month");
        
      Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Manila")); 

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

      
        LocalDate firstDay = LocalDate.of(year, month, 1);
        int lastDayOfMonth = YearMonth.of(year, month).lengthOfMonth();
        LocalDate lastDay = LocalDate.of(year, month, lastDayOfMonth);

       
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.US);
        String formattedFirstDay = firstDay.format(formatter);
        String formattedLastDay = lastDay.format(formatter);

     

        
        if (date != null) {
            date.setText(formattedFirstDay + " - " + formattedLastDay);
        }
        updateNetPay();
        CountEmployees();
//         loadPage("/SysUI/AdminDashboard/HRDash.fxml");
*/
        instance = this;
    }
      
     private dbConnect db = new dbConnect();
     private ObservableList<employees> empList;
     
     private void loadDashboard() {
         try{
             Parent root = FXMLLoader.load(getClass().getResource("/SysUI/AdminDashboard/FXML.fxml"));
            rootPane.setCenter(root);
         } catch (Exception e) {
             System.out.println(e);
         }
     }
     
     public static FXMLController getInstance() {
         return instance;
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
    
         /*
         private void CountEmployees() {
        String query = "SELECT COUNT(*) AS total FROM employee";

       
        dbConnect db = new dbConnect();
        Connection conn = db.getConnection();

        if (conn == null) {
            TotalEmp.setText("Database connection failed!");
            return;
        }

        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                int totalUsers = rs.getInt("total");
                TotalEmp.setText(String.valueOf(totalUsers));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            TotalEmp.setText("Error loading users");
        }
     }

        private void updateNetPay() {
          
         dbConnect db = new dbConnect();   
            
        Connection connection = db.getConnection();

        if (connection == null) {
            npay.setText("Database connection failed!");
            return;
        }

        try {
            String query = "SELECT SUM(net_pay) AS total_netpay FROM reports";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                if (resultSet.next()) {
                    npay.setText(String.format("₱%.2f", resultSet.getDouble("total_netpay")));
                     payment.setText(String.format("₱%.2f", resultSet.getDouble("total_netpay")));
                } else {
                    npay.setText("₱0.00");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error calculating netpay sum: " + e.getMessage());
            npay.setText("Error");
        }
        
    }
    */
        
    public void loadPage(String targetFXML) {
         Stage currentStage = (Stage) anchorPane.getScene().getWindow();   
        try{
            Parent root = FXMLLoader.load(getClass().getResource(targetFXML));
            rootPane.setCenter(root);
        } catch (Exception e) {
            System.out.println(e);
        } 
    }

    @FXML
    private void employeesButtonMouseClickHandler(javafx.scene.input.MouseEvent event) {
         loadPage("/SysUI/AdminDashboard/Employees.fxml");
    }

    @FXML
    private void dashboardButtonMouseClickHandler(javafx.scene.input.MouseEvent event) {
        loadPage("/SysUI/AdminDashboard/HRDash.fxml");
    }
} 
      

 


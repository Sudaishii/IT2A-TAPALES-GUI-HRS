package dashboard;

import javafx.collections.ObservableList;

public class employees {

   

    private int id;
    private String fname;
    private String lname;
    private String dept;

    
    public employees (int id, String fname, String lname, String department ) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.dept = department;
       
    }

  public int getid() { return id; }         
      public String getFname() { return fname; } 
      public String getLname() { return lname; } 
      public String getDept() { return dept; } 

}
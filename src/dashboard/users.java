package dashboard;

public class users {

    private int id;
    private String fname;
    private String lname;
    private String contact;
    private String email;
    private String username;
    private String password;
    private String role; // Add role field

    public users(int id, String fname, String lname, String contact, String email, String username, String password, String role) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.contact = contact;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role; 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() { // Getter for role
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
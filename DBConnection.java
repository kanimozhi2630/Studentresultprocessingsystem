import java.sql.*;  

public class DBConnection {

 
    static final String URL = "jdbc:mysql://localhost:3306/student_result_system";

   
    static final String USER = "root";         
    static final String PASS = "root"; 

    
    public static Connection getConnection() {
        try {
         
            Class.forName("com.mysql.cj.jdbc.Driver");

           
            return DriverManager.getConnection(URL, USER, PASS);

        } catch (Exception e) {
            System.out.println("DB Connection Error: " + e.getMessage());
            return null;
        }
    }
}

import java.sql.*;
import java.util.*;

public class UserDAO {
   static final String DB_URL = "jdbc:mysql://mysql.agh.edu.pl:3306/krkrol1";
   static final String USER = "krkrol1";
   static final String PASS = "jX0nfDtzPjC8101B";
   static final String QUERY = "SELECT * FROM Users";
   static ArrayList<User> users = new ArrayList<User>();

   public static void main(String[] args) {
        getData();
   }
   
   public static void getData(){
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(QUERY);) {         
         // Extract data from result set
         while (rs.next()) {
            User user = new User(
               rs.getString("PESEL"),
               rs.getString("Password"),
               rs.getString("FullName")
            );
            users.add(user);            
            // Retrieve by column name
            System.out.println(user.toString()); // TODO: Log that
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}
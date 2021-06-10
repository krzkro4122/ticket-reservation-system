import java.sql.*;
import java.util.*;
import org.apache.logging.log4j.*;

public class UserDAO {

   private static final Logger logger = LogManager.getLogger("UserDAO");

   static final String DB_URL = "jdbc:mysql://mysql.agh.edu.pl:3306/krkrol1";
   static final String USER = "krkrol1";
   static final String PASS = "jX0nfDtzPjC8101B";   
   static ArrayList<User> users = new ArrayList<User>();

   static final String QUERYlistALL = "SELECT * FROM Users";
   static String QUERYadd;
   static String QUERYdelete;
   static String QUERYedit;
   
   public static ArrayList<User> getAll(){
      logger.log(Level.ERROR, "Getting User data from Database...");       
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(QUERYlistALL);) {         
        
        users = new ArrayList<User>();
         // Extract data from result set
         while (rs.next()) {
            User user = new User(
               rs.getString("PESEL"),
               rs.getString("Password"),
               rs.getString("FullName")
            );            
            users.add(user);            
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return users; 
   }
   public static void add(User user){
      logger.log(Level.ERROR, "Adding user: " + user + " to Database...");       
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();) {   
            QUERYadd = "INSERT INTO Users " + 
            "(PESEL, Password, FullName)" +
            " VALUES " +
            "('" + user.GetPesel() + "', " + 
            "'"  + user.GetPassword() + "', " + 
            "'"  + user.GetfullName() + "')";	 
            stmt.execute(QUERYadd);
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
   public static void delete(User user){
      logger.log(Level.ERROR, "Deleting user: " + user + " from Database...");
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();) {   
            QUERYdelete = "DELETE FROM Users WHERE PESEL='" 
            + user.GetPesel() + "';";	 
            stmt.execute(QUERYdelete); 
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
   public static void edit(User userBase, User userDesired){
      logger.log(Level.ERROR, "Updating user from: " + userBase + " to: " + userDesired + " in Database...");
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();) {               
            QUERYdelete = "UPDATE Users SET "             
            + "PESEL = '" + userDesired.GetPesel() + "', "
            + "Password = '" + userDesired.GetPassword() + "', "
            + "FullName = '" + userDesired.GetfullName() + "' "
            + "WHERE PESEL='" 
            + userBase.GetPesel() + "';";	 
            System.out.println(QUERYdelete);
            stmt.execute(QUERYdelete);
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}
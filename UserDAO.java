import java.sql.*;
import java.util.*;

public class UserDAO {
   static final String DB_URL = "jdbc:mysql://mysql.agh.edu.pl:3306/krkrol1";
   static final String USER = "krkrol1";
   static final String PASS = "jX0nfDtzPjC8101B";   
   static ArrayList<User> users = new ArrayList<User>();

   static final String QUERYlistALL = "SELECT * FROM Users";
   static String QUERYadd;
   static String QUERYdelete;
   static String QUERYedit;

   public static void main(String[] args) {
      getAll();
      for (User user : users)
        delete(user);
   }
   public static void getAll(){
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
            // Retrieve by column name
            System.out.println(user); // TODO: Log that
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
   public static void add(User user){
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
            // TODO: Log that         
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
   public static void delete(User user){
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();) {   
            QUERYdelete = "DELETE FROM Users WHERE PESEL='" 
            + user.GetPesel() + "';";	 
            stmt.execute(QUERYdelete);
            // TODO: Log that         
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
   public static void edit(User userBase, User userDesired){
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
            // TODO: Log that         
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}
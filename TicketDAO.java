import java.sql.*;
import java.util.*;

public class TicketDAO {
   static final String DB_URL = "jdbc:mysql://mysql.agh.edu.pl:3306/krkrol1";
   static final String USER = "krkrol1";
   static final String PASS = "jX0nfDtzPjC8101B";   
   static ArrayList<Ticket> tickets = new ArrayList<Ticket>();

   static final String QUERYlistALL = "SELECT * FROM Tickets";
   static String QUERYadd;
   static final String QUERYdelete = "SELECT * FROM Tickets";
   static final String QUERYedit = "SELECT * FROM Tickets";

   public static void main(String[] args) {
      listAll();
      // add(new Ticket("111111111", "111111", "111111", "1", "41487625529", "1111111", "2022-9-16 03:20", "1111111"));
      // listAll();
   }
   public static void listAll(){
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(QUERYlistALL);) {         
         // Extract data from result set
         while (rs.next()) {
            Ticket ticket = new Ticket(
               rs.getString("TicketNr"),
               rs.getString("CarrierNr"),
               rs.getString("ReservationNr"),
               String.valueOf(rs.getInt("Price")),
               rs.getString("PESEL"),
               rs.getString("FlightPath"),
               rs.getString("DepartureTime"),
               rs.getString("OperatorNr")
            );
            tickets.add(ticket);            
            // Retrieve by column name
            System.out.println(ticket); // TODO: Log that
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
   public static void add(Ticket ticket){
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();) {   
            QUERYadd = "INSERT INTO Tickets " + 
            "(TicketNr, CarrierNr, ReservationNr, Price, PESEL, FlightPath, DepartureTime, OperatorNr)" +
            " VALUES " +
            "('" + ticket.getTicketNr() + "', " + 
            "'"  + ticket.getCarrierNr() + "', " + 
            "'"  + ticket.getReservationNr() + "', " + 
            "'"  + ticket.getPrice() + "', " + 
            "'"  + ticket.getPesel() + "', " + 
            "'"  + ticket.getFlightPath() + "',	" + 
            "'"  + ticket.getDepartureTime() + "',	" + 
            "'"  + ticket.getOperatorNr() + "')";	 
            stmt.execute(QUERYadd);
            // TODO: Log that         
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}
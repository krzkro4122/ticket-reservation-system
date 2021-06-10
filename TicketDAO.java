import java.sql.*;
import java.util.*;

public class TicketDAO {
   static final String DB_URL = "jdbc:mysql://mysql.agh.edu.pl:3306/krkrol1";
   static final String USER = "krkrol1";
   static final String PASS = "jX0nfDtzPjC8101B";   
   static ArrayList<Ticket> tickets = new ArrayList<Ticket>();

   static final String QUERYlistALL = "SELECT * FROM Tickets";
   static String QUERYadd;
   static String QUERYdelete;
   static String QUERYedit;

   public static void main(String[] args) {
      getAll();
      for (Ticket ticket : tickets)
         delete(ticket);
   }
   public static ArrayList<Ticket> getAll(){
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(QUERYlistALL);) {   
            
         tickets = new ArrayList<Ticket>();
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
            // System.out.println(ticket); // TODO: Log that
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } 
      return tickets;
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
   public static void delete(Ticket ticket){
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();) {   
            QUERYdelete = "DELETE FROM Tickets WHERE TicketNr='" 
            + ticket.getTicketNr() 
            + "' and CarrierNr='"
            + ticket.getCarrierNr() + "';";	 
            stmt.execute(QUERYdelete);
            // TODO: Log that         
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
   public static void edit(Ticket ticketBase, Ticket ticketDesired){
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();) {               
            QUERYdelete = "UPDATE Tickets SET " 
            + "TicketNr = '" + ticketDesired.getTicketNr() + "', "
            + "CarrierNr = '" + ticketDesired.getCarrierNr() + "', "
            + "ReservationNr = '" + ticketDesired.getReservationNr() + "', "
            + "Price = '" + ticketDesired.getPrice() + "', "
            + "PESEL = '" + ticketDesired.getPesel() + "', "
            + "FlightPath = '" + ticketDesired.getFlightPath() + "', "
            + "DepartureTime = '" + ticketDesired.getDepartureTime() + "', "
            + "OperatorNr = '" + ticketDesired.getOperatorNr() + "' "
            + "WHERE TicketNr='" 
            + ticketBase.getTicketNr() 
            + "' and CarrierNr='"
            + ticketBase.getCarrierNr() + "';";	 
            System.out.println(QUERYdelete);
            stmt.execute(QUERYdelete);
            // TODO: Log that         
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}
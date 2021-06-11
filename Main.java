import org.apache.logging.log4j.*;

public class Main {
    private static final Logger logger = LogManager.getLogger("Main");
    public static void main(String[] args) { 
        logger.log(Level.INFO, "Started the Program.");         
        javax.swing.SwingUtilities.invokeLater(Login::createLoginWindow);                    
        // javax.swing.SwingUtilities.invokeLater(new Runnable() {
        //     @Override
        //     public void run(){
        //         LoginFrame.createTicketReservationFrame("12287952565");
        //     }
        // });
    }
}

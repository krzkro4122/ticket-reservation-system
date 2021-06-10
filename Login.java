import org.apache.logging.log4j.*;

public class Login {

    private static final Logger logger = LogManager.getLogger("Login");

    public static void createLoginWindow() {     
        logger.log(Level.ERROR, "Started the Login.");    
        new LoginFrame().pack();        
    }
}
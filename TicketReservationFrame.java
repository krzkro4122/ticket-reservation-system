import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import org.apache.logging.log4j.*;

public class TicketReservationFrame extends JFrame implements ActionListener {

    private static final Logger logger = LogManager.getLogger("TicketReservationFrame");

    Container container = getContentPane();

    JButton addTicketButton = new JButton("Add Ticket");
    JButton deleteTicketButton = new JButton("Delete");
    JButton refreshButton = new JButton("Refresh");
    myJButton lastClickedButton;

    JPanel buttonsPanel = new JPanel(new GridLayout(2, 1));
    JPanel buttonPanelAddDelete = new JPanel(new GridLayout(1, 2));
    JPanel buttonPanelRefresh = new JPanel(new GridLayout(1, 1));
    public JPanel ticketsPanel = new JPanel(); 

    private String username;
    private User user;

    private ArrayList<Ticket> tickets;

    TicketReservationFrame(String username){
        init(username);
        setLayout();
        addComponentsToContainer(username);
        addActionEvent(); 
        logger.log(Level.ERROR, "Constructed.");    
    }    

    private void init(String username){    

        this.username = username;          
        this.setTitle("Tickets");
        this.setVisible(true); 
        this.setPreferredSize(new Dimension(300, 300)); 

        this.addWindowListener(new WindowAdapter(){
            // Close all children windows
            public void windowClosing(WindowEvent e){
                Window[] windows = Window.getWindows();
                for (Window window : windows){
                    window.setVisible(false);
                }
                // And re-open LoginFrame
                new LoginFrame().setVisible(true);
            }
        });          
        ticketsPanel.setBorder(BorderFactory.createTitledBorder("Tickets of " + username));

        // Current user
        for (User usr : UserDAO.getAll())
            if(usr.GetPesel().equals(username))
                user = usr;
    }

    void setLayout(){
        container.setLayout(new BorderLayout());
        // ticketsPanel.setLayout(new BoxLayout(ticketsPanel, BoxLayout.PAGE_AXIS));                
    }

    public void addComponentsToContainer(String username) { 

        ticketsPanel.removeAll();

        buttonPanelAddDelete.add(addTicketButton);       
        buttonPanelAddDelete.add(deleteTicketButton);     
        buttonPanelRefresh.add(refreshButton);

        tickets = TicketDAO.getAll();

        for (Ticket ticket : tickets) {
            if (ticket.getPesel().equals(username)){
                myJButton button = new myJButton("<html>" + ticket.getTicketNr() + "<br />" + ticket.getCarrierNr() + "</html>");
                button.setButtonsTicket(ticket);
                button.addActionListener(this);
                ticketsPanel.add(button);
            }
        }                

        buttonsPanel.add(buttonPanelAddDelete);
        buttonsPanel.add(buttonPanelRefresh);

        container.add(ticketsPanel);
        container.add(buttonsPanel, BorderLayout.PAGE_END);
    }

    public void addActionEvent() {
        addTicketButton.addActionListener(this);
        deleteTicketButton.addActionListener(this);    
        refreshButton.addActionListener(this);    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Add button pressed
        if(e.getSource() == addTicketButton){  
            logger.log(Level.ERROR, "Add Ticket button pressed.");                   
            // Add form
            new TicketAddPopup(this, user);

        // Delete button pressed
        } else if(e.getSource() == deleteTicketButton){    
            logger.log(Level.ERROR, "Delete Ticket button pressed.");     

            // If any ticket already picked => delete it
            if (lastClickedButton != null){
                TicketDAO.delete(lastClickedButton.getButtonsTicket());
                ticketsPanel.remove(lastClickedButton);                
                lastClickedButton = null;
                deleteTicketButton.setText("Delete");                
            } 
        // Refresh button pressed
        } else if(e.getSource() == refreshButton){   
            logger.log(Level.ERROR, "Refresh button pressed.");               
            addComponentsToContainer(username);
            SwingUtilities.updateComponentTreeUI(this);  
            logger.log(Level.ERROR, "Tickets Refreshed.");       

        // Any ticket's button clicked
        } else {                   
            // Clicked a 2nd time => open up a form with its details
            if (e.getSource() == lastClickedButton){                          
                logger.log(Level.ERROR, lastClickedButton.getButtonsTicket().getCarrierNr() + " button clicked 2nd time.");
                new TicketInfoPopup(lastClickedButton.getButtonsTicket(), user).pack();                         
                lastClickedButton = null;
                deleteTicketButton.setText("Delete");

            // Clicked 1st time => register it and alter DeleteButton text accordingly
            } else {                                 
                lastClickedButton = (myJButton) e.getSource();
                lastClickedButton.setButtonsTicket(((myJButton) e.getSource()).getButtonsTicket());
                deleteTicketButton.setText("Delete " + lastClickedButton.getButtonsTicket().getCarrierNr());
                logger.log(Level.ERROR, lastClickedButton.getButtonsTicket().getCarrierNr() + " button clicked 1st time.");       
            }
        }

        // Update the container after changes
        SwingUtilities.updateComponentTreeUI(ticketsPanel);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import org.apache.logging.log4j.*;

public class TicketInfoPopup extends JFrame implements ActionListener {

    private static final Logger logger = LogManager.getLogger("Login");

    Container container = getContentPane();
    JPanel panelOfPanels = new JPanel(new GridBagLayout());        
    JPanel ticketInformationPanel = new JPanel(new GridLayout(7, 2));        
    JPanel userInformationPanel = new JPanel(new GridLayout(3, 2));
        
    private String ticketLabelNames[] = {"Ticket ID: ", "Carrier ID:", "Reservation ID:", "Price:", "Flight Path:", "Departure Time:", "Operator ID:"};    
    private String userLabelNames[] = {"Full Name:", "Password: ", "PESEL:"};    

    ArrayList<Ticket> tickets;
    ArrayList<User> users;    
    private ArrayList<JLabel> listOfTicketValueLabels = new ArrayList<JLabel>(); // Storage for needed for latter value update
    private ArrayList<JLabel> listOfUserValueLabels = new ArrayList<JLabel>(); // Storage for needed for latter value update

    TicketInfoPopup(Ticket ticket, User user){
        init(ticket, user);
        addComponentsToContainer(ticket, user);
        logger.log(Level.ERROR, "Constructed.");    
    }

    private void init(Ticket ticket, User user){     
        this.setTitle("Ticket Information");
        this.setPreferredSize(new Dimension(350, 350));        
        this.setVisible(true);        

        ticketInformationPanel.setBorder(BorderFactory.createTitledBorder("Information about ticket Nr: " + ticket.getTicketNr()));
        // ticketInformationPanel.setPreferredSize(new Dimension(super.getPreferredSize().width, 300));
        userInformationPanel.setBorder(BorderFactory.createTitledBorder("Associated user: " + user.GetPesel()));        
        // ticketInformationPanel.setPreferredSize(new Dimension(super.getPreferredSize().width, ticketInformationPanel.getPreferredSize().height / 3));
    }

    public void addComponentsToContainer(Ticket ticket, User user){
        // Populate ticket info panel with JLabels
        for (String labelName : ticketLabelNames){
            JLabel leftLabel, rightLabel;

            leftLabel = new JLabel(labelName);
            leftLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
            rightLabel = new JLabel();
            rightLabel.setBorder(BorderFactory.createLineBorder(Color.black));

            int fontSize = 16;
            leftLabel.setFont(new Font("Arial", Font.PLAIN, fontSize));
            rightLabel.setFont(new Font("Arial", Font.PLAIN, fontSize));
            
            listOfTicketValueLabels.add(rightLabel);
            ticketInformationPanel.add(leftLabel);
            ticketInformationPanel.add(rightLabel);
        }
        // Populate user info panel with JLabels
        for (String labelName : userLabelNames){
            JLabel leftLabel, rightLabel;

            leftLabel = new JLabel(labelName);
            leftLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
            rightLabel = new JLabel();
            rightLabel.setBorder(BorderFactory.createLineBorder(Color.black));

            int fontSize = 16;
            leftLabel.setFont(new Font("Arial", Font.PLAIN, fontSize));
            rightLabel.setFont(new Font("Arial", Font.PLAIN, fontSize));

            listOfUserValueLabels.add(rightLabel);
            userInformationPanel.add(leftLabel);
            userInformationPanel.add(rightLabel);
        }
        // Get data from the database and display it in the info form
        updateData(user, ticket);

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 0;

        panelOfPanels.add(ticketInformationPanel, c);
        panelOfPanels.add(userInformationPanel, c);

        container.add(panelOfPanels);
    }

    // Database API used to get ticket's and associated user's info 
    public void updateData(User user, Ticket ticket){

        tickets = TicketDAO.getAll();
        
        listOfTicketValueLabels.get(0).setText(ticket.getTicketNr());
        listOfTicketValueLabels.get(1).setText(ticket.getCarrierNr());
        listOfTicketValueLabels.get(2).setText(ticket.getReservationNr());
        listOfTicketValueLabels.get(3).setText(ticket.getPrice());        
        listOfTicketValueLabels.get(4).setText(ticket.getFlightPath());
        listOfTicketValueLabels.get(5).setText(ticket.getDepartureTime());
        String text;
        if(ticket.getOperatorNr() == null){
            text = "N/A";
        } else text = ticket.getOperatorNr();
        listOfTicketValueLabels.get(6).setText(text);

        listOfUserValueLabels.get(0).setText(user.GetPesel());
        listOfUserValueLabels.get(1).setText(user.GetPassword());
        listOfUserValueLabels.get(2).setText(user.GetfullName());

        SwingUtilities.updateComponentTreeUI(panelOfPanels);        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}

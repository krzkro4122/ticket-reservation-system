import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketInfoPopup extends JFrame implements ActionListener {

    Container container = getContentPane();
    JPanel panelOfPanels = new JPanel(new GridBagLayout());        
    JPanel ticketInformationPanel = new JPanel(new GridLayout(7, 2));        
    JPanel userInformationPanel = new JPanel(new GridLayout(3, 2));
        
    private String ticketLabelNames[] = {"Ticket ID: ", "Carrier ID:", "Reservation ID:", "Price:", "Flight Path:", "Departure Time:", "Operator ID:"};
    // private String ticketLabelValues[] = {"TicketNr", "CarrierNr", "ReservationNr", "Price", "FlightPath", "DepartureTime", "OperatorNr"};
    private String userLabelNames[] = {"Full Name:", "PESEL:"};
    // private String userLabelValues[] = {"FullName", "PESEL"};
    
    private JList<JLabel> listOfTicketValueLabels = new JList<JLabel>(); // Storage for needed for latter value update
    private JList<JLabel> listOfUserValueLabels = new JList<JLabel>(); // Storage for needed for latter value update

    TicketInfoPopup(String ticketNr, String username){
        init(ticketNr, username);
        addComponentsToContainer();
    }

    private void init(String ticketNr, String username){     
        this.setTitle("Ticket Information");
        this.setPreferredSize(new Dimension(350, 350));        
        this.setVisible(true);        

        ticketInformationPanel.setBorder(BorderFactory.createTitledBorder("Information about ticket Nr: " + ticketNr));
        // ticketInformationPanel.setPreferredSize(new Dimension(super.getPreferredSize().width, 300));
        userInformationPanel.setBorder(BorderFactory.createTitledBorder("Associated user: " + username));        
        // ticketInformationPanel.setPreferredSize(new Dimension(super.getPreferredSize().width, ticketInformationPanel.getPreferredSize().height / 3));
    }

    public void addComponentsToContainer(){
        for (String labelName : ticketLabelNames){
            JLabel leftLabel, rightLabel;

            leftLabel = new JLabel(labelName);
            leftLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
            rightLabel = new JLabel("1"); // TODO: Integrate with database read, use ticketLabelValues
            rightLabel.setBorder(BorderFactory.createLineBorder(Color.black));

            int fontSize = 18;
            leftLabel.setFont(new Font("Arial", Font.PLAIN, fontSize));
            rightLabel.setFont(new Font("Arial", Font.PLAIN, fontSize));
            
            listOfTicketValueLabels.add(rightLabel);
            ticketInformationPanel.add(leftLabel);
            ticketInformationPanel.add(rightLabel);
        }
        for (String labelName : userLabelNames){
            JLabel leftLabel, rightLabel;

            leftLabel = new JLabel(labelName);
            leftLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
            rightLabel = new JLabel("1"); // TODO: Integrate with database read, use ticketLabelValues
            rightLabel.setBorder(BorderFactory.createLineBorder(Color.black));

            int fontSize = 18;
            leftLabel.setFont(new Font("Arial", Font.PLAIN, fontSize));
            rightLabel.setFont(new Font("Arial", Font.PLAIN, fontSize));

            listOfUserValueLabels.add(rightLabel);
            userInformationPanel.add(leftLabel);
            userInformationPanel.add(rightLabel);
        }      

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 0;

        panelOfPanels.add(ticketInformationPanel, c);
        panelOfPanels.add(userInformationPanel, c);

        container.add(panelOfPanels);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}

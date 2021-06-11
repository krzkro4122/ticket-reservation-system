import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.regex.Pattern;
import org.apache.logging.log4j.*;

public class TicketAddPopup extends JFrame implements ActionListener {
    private static final Logger logger = LogManager.getLogger("TicketAddPopup");

    Container container = getContentPane();
    JPanel panelOfPanels = new JPanel(new GridBagLayout());        
    JPanel ticketInformationPanel = new JPanel(new GridLayout(7, 2));        
    JPanel userInformationPanel = new JPanel(new GridLayout(3, 2));
    JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

    JButton addTicket = new JButton("Add ticket");
    JButton resetFields = new JButton("Reset");
        
    private String ticketLabelNames[] = {"Ticket ID: ", "Carrier ID:", "Reservation ID:", "Price:", "Flight Path:", "Departure Time:", "Operator ID:"};    
    
    private ArrayList<JTextField> listOfTicketValueLabels = new ArrayList<JTextField>(); // Storage for needed for latter value update

    TicketReservationFrame trf; // Need that reference to alter the parent window (add a button)
    User user;

    TicketAddPopup(TicketReservationFrame trf, User user){        
        init();
        addComponentsToContainer();
        this.trf = trf;
        this.user = user;
        logger.log(Level.INFO, "Opened.");    
    }

    private void init(){   
        this.setTitle("Ticket Addition");
        this.setPreferredSize(new Dimension(375, 300));        
        this.setBounds(0, 0, 375, 300);
        this.setVisible(true);        

        ticketInformationPanel.setBorder(BorderFactory.createTitledBorder("Fill in new ticket info: "));
        ticketInformationPanel.setPreferredSize(new Dimension(super.getPreferredSize().width, 300));     
    }

    public void addComponentsToContainer(){

        for (String labelName : ticketLabelNames){
            JLabel leftLabel;
            JTextField rightField;

            leftLabel = new JLabel(labelName);
            leftLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
            rightField = new JTextField(); // TODO: Integrate with database read, use ticketLabelValues
            // rightField.setBorder(BorderFactory.createLineBorder(Color.black));

            int fontSize = 16;
            leftLabel.setFont(new Font("Arial", Font.PLAIN, fontSize));
            rightField.setFont(new Font("Arial", Font.PLAIN, fontSize));
            
            listOfTicketValueLabels.add(rightField);            
            ticketInformationPanel.add(leftLabel);
            ticketInformationPanel.add(rightField);
        }

        buttonPanel.add(addTicket);
        buttonPanel.add(resetFields);

        addTicket.addActionListener(this);
        resetFields.addActionListener(this);

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 0;

        panelOfPanels.add(ticketInformationPanel, c);
        panelOfPanels.add(buttonPanel, c);

        container.add(panelOfPanels);
    }

    public int checkExpressions(){         
        // Check regex
        if (!Pattern.compile("\\d{9}").matcher(listOfTicketValueLabels.get(0).getText()).matches()) return 1;
        if (!Pattern.compile("[a-zA-Z]{2}\\d{4}").matcher(listOfTicketValueLabels.get(1).getText()).matches()) return 1;
        if (!Pattern.compile("[a-zA-Z]{6}").matcher(listOfTicketValueLabels.get(2).getText()).matches()) return 1;
        if (!Pattern.compile("\\d+").matcher(listOfTicketValueLabels.get(3).getText()).matches()) return 1;
        if (!Pattern.compile("[a-zA-Z]{3}[-][a-zA-Z]{3}").matcher(listOfTicketValueLabels.get(4).getText()).matches()) return 1;
        if (!Pattern.compile("\\d{4}[-]\\d{2}[-]\\d{2}[ ]\\d{2}[:]\\d{2}[:]\\d{2}").matcher(listOfTicketValueLabels.get(5).getText()).matches()) return 1;
        if (!Pattern.compile("([a-zA-Z]{2}\\d{4})??").matcher(listOfTicketValueLabels.get(6).getText()).matches()) return 1;

        // Check if already exists
        ArrayList<Ticket> tickets = TicketDAO.getAll();        

        for (Ticket ticket : tickets) {
            if (ticket.getTicketNr().equals(listOfTicketValueLabels.get(0).getText()) && ticket.getCarrierNr().equals(listOfTicketValueLabels.get(1).getText())) return 2;   
        }

        return 0;        
    }

    public void addTicket(){

        Ticket ticket = new Ticket();
        ticket.setTicketNr(listOfTicketValueLabels.get(0).getText());
        ticket.setCarrierNr(listOfTicketValueLabels.get(1).getText());
        ticket.setReservationNr(listOfTicketValueLabels.get(2).getText());
        ticket.setPrice(listOfTicketValueLabels.get(3).getText());
        ticket.setPesel(user.GetPesel());
        ticket.setFlightPath(listOfTicketValueLabels.get(4).getText());
        ticket.setDepartureTime(listOfTicketValueLabels.get(5).getText());
        ticket.setOperatorNr(listOfTicketValueLabels.get(6).getText());

        myJButton button = new myJButton("<html>" + ticket.getTicketNr() + "<br />" + ticket.getCarrierNr() + "</html>");
        button.setButtonsTicket(ticket);
        button.addActionListener(trf);
        trf.ticketsPanel.add(button);
        
        TicketDAO.add(ticket);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Add Ticket button clicked => check if valid input and add it to parent window
        if(e.getSource() == addTicket){
            logger.log(Level.INFO, "Add Ticket button pressed.");    
            // if valid input, add the Ticket
            if(checkExpressions() == 0) {
                logger.log(Level.INFO, "Input VALID.");    
                addTicket();
                SwingUtilities.updateComponentTreeUI(trf.ticketsPanel);                
                this.setVisible(false);
            // Warn about wrong input
            } else if(checkExpressions() == 1){
                logger.log(Level.INFO, "Input INVALID.");    
                JOptionPane.showMessageDialog(this, "Incorrect values!");
            } else {
                logger.log(Level.INFO, "Input ALREADY EXISTS.");    
                JOptionPane.showMessageDialog(this, "Ticket with TicketNr: " + listOfTicketValueLabels.get(0).getText() + "\nand CarrierNr: " + listOfTicketValueLabels.get(1).getText() + "\nAlready exists!");
            }
        }
        // Reset button clicked => clear all textFields
        if(e.getSource() == resetFields){
        logger.log(Level.INFO, "Reset button pressed.");    
            for (JTextField jtf : listOfTicketValueLabels){
                jtf.setText("");
            }           
        }
    }
}

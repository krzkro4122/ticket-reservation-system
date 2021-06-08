import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketAddPopup extends JFrame implements ActionListener {

    Container container = getContentPane();
    JPanel panelOfPanels = new JPanel(new GridBagLayout());        
    JPanel ticketInformationPanel = new JPanel(new GridLayout(7, 2));        
    JPanel userInformationPanel = new JPanel(new GridLayout(3, 2));
    JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

    JButton addTicket = new JButton("Add ticket");
    JButton resetFields = new JButton("Reset");
        
    private String ticketLabelNames[] = {"Ticket ID: ", "Carrier ID:", "Reservation ID:", "Price:", "Flight Path:", "Departure Time:", "Operator ID:"};
    // private String ticketLabelValues[] = {"TicketNr", "CarrierNr", "ReservationNr", "Price", "FlightPath", "DepartureTime", "OperatorNr"};
    
    private JTextField[] listOfTicketValueLabels = new JTextField[7]; // Storage for needed for latter value update

    TicketReservationFrame trf;

    TicketAddPopup(TicketReservationFrame trf){        
        init();
        addComponentsToContainer();
        this.trf = trf;
    }

    private void init(){   
        this.setTitle("Ticket Addition");
        this.setPreferredSize(new Dimension(350, 300));        
        this.setBounds(0, 0, 350, 300);
        this.setVisible(true);        

        ticketInformationPanel.setBorder(BorderFactory.createTitledBorder("Fill in new ticket info: "));
        ticketInformationPanel.setPreferredSize(new Dimension(super.getPreferredSize().width, 300));     
    }

    public void addComponentsToContainer(){
        int counter = 0;
        for (String labelName : ticketLabelNames){
            JLabel leftLabel;
            JTextField rightField;

            leftLabel = new JLabel(labelName);
            leftLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
            rightField = new JTextField(); // TODO: Integrate with database read, use ticketLabelValues
            // rightField.setBorder(BorderFactory.createLineBorder(Color.black));

            int fontSize = 18;
            leftLabel.setFont(new Font("Arial", Font.PLAIN, fontSize));
            rightField.setFont(new Font("Arial", Font.PLAIN, fontSize));
            
            listOfTicketValueLabels[counter] = rightField;
            counter++;
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

    public boolean checkExpressions(){ // TODO: actually check them
        // int counter = 0;
        // int fieldSizes[] = {9, 6, 6, 7, 19, 7};

        // for (JTextField jtf : listOfTicketValueLabels){
        //     if(counter != 3){
        //         if(!(jtf.getText().length() == fieldSizes[counter])) return false;            
        //     }
        //     counter++;
        // }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addTicket){
            if(checkExpressions() == true) {
                JButton button = new JButton("A ticket");
                button.addActionListener(trf);
                trf.ticketsPanel.add(button);
                
                SwingUtilities.updateComponentTreeUI(trf.ticketsPanel);
                
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect values!");
            }
        }
        if(e.getSource() == resetFields){
            for (JTextField jtf : listOfTicketValueLabels){
                jtf.setText("");
            }           
        }
    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketReservationFrame extends JFrame implements ActionListener {

    Container container = getContentPane();

    JButton addTicketButton = new JButton("Add Ticket");
    JButton deleteTicketButton = new JButton("Delete");
    static int counter = 0;
    JButton lastClickedButton = null;

    JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
    JPanel ticketsPanel = new JPanel();

    TicketReservationFrame(String username){
        init(username);
        setLayout();
        addComponentsToContainer();
        addActionEvent();
    }

    private void init(String username){                
        this.setTitle("Tickets");
        this.setVisible(true); 
        this.setPreferredSize(new Dimension(300, 300));       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ticketsPanel.setBorder(BorderFactory.createTitledBorder("Tickets of " + username));
    }

    void setLayout(){
        container.setLayout(new BorderLayout());
        // ticketsPanel.setLayout(new BoxLayout(ticketsPanel, BoxLayout.PAGE_AXIS));                
    }

    public void addComponentsToContainer() { 
        buttonsPanel.add(addTicketButton);       
        buttonsPanel.add(deleteTicketButton);
        container.add(ticketsPanel);
        container.add(buttonsPanel, BorderLayout.PAGE_END);
    }

    public void addActionEvent() {
        addTicketButton.addActionListener(this);
        deleteTicketButton.addActionListener(this);        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == addTicketButton){                        
            JButton button = new JButton("Ticket#" + counter++);            
            System.out.println("Add " + button.getText());            
            button.addActionListener(this);
            ticketsPanel.add(button);            

        } else if(e.getSource() == deleteTicketButton){            
            if (lastClickedButton != null){
                ticketsPanel.remove(lastClickedButton);
                System.out.println("Delete " + lastClickedButton.getText());
                lastClickedButton = null;
                deleteTicketButton.setText("Delete");
            }                                                 

        } else {
            String buttonID = ((JButton) e.getSource()).getText();
            // After double click
            if (e.getSource() == lastClickedButton){                
                System.out.println("It's me, " + buttonID);      
                new TicketInfoPopup(buttonID).pack();                         
                lastClickedButton = null;
                deleteTicketButton.setText("Delete");
            } else { 
            // Regsiter clicked button
            lastClickedButton = (JButton) e.getSource();
            deleteTicketButton.setText("Delete " + buttonID);
            }
        }

        // Update the container after changes
        SwingUtilities.updateComponentTreeUI(ticketsPanel);
    }
}

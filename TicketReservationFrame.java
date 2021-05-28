import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketReservationFrame extends JFrame implements ActionListener {

    Container container = getContentPane();

    JButton addTicketButton = new JButton("Add Ticket");
    JButton deleteTicketButton = new JButton("Delete Ticket");

    TicketReservationFrame(){
        setLayout();
        addComponentsToContainer();
        addActionEvent();
    }

    void setLayout(){
        container.setLayout(new GridLayout());
    }

    public void addComponentsToContainer() {
        container.add(addTicketButton);
        container.add(deleteTicketButton);
    }

    public void addActionEvent() {
        addTicketButton.addActionListener(this);
        deleteTicketButton.addActionListener(this);        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addTicketButton){
            System.out.println("Add a Ticket.");
        }
        
        if(e.getSource() == deleteTicketButton){
            System.out.println("Delete a Ticket.");
        }
    }
}

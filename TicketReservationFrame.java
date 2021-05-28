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
    }

    void setLayout(){
        container.setLayout(new GridLayout());
    }

    public void addComponentsToContainer() {
        container.add(addTicketButton);
        container.add(deleteTicketButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Nothin for now
    }
}

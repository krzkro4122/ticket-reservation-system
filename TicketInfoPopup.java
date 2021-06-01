import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketInfoPopup extends JFrame implements ActionListener {

    Container container = getContentPane();

    JLabel testLabel;

    TicketInfoPopup(String ticketNr){
        init(ticketNr);
        addComponentsToContainer();
    }

    private void init(String ticketNr){
        this.testLabel = new JLabel("" + ticketNr);
        this.setPreferredSize(new Dimension(200, 100));
        this.setVisible(true);
    }

    public void addComponentsToContainer(){
        container.add(testLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    
}

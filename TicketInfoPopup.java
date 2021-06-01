import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketInfoPopup extends JFrame implements ActionListener {

    Container container = getContentPane();

    JPanel ticketInformationPanel = new JPanel();
    JLabel testLabel;

    TicketInfoPopup(String ticketNr){
        init(ticketNr);
        addComponentsToContainer();
    }

    private void init(String ticketNr){
        this.testLabel = new JLabel("" + ticketNr);
        this.setTitle("Information");
        this.setPreferredSize(new Dimension(400, 200));
        this.setVisible(true);

        ticketInformationPanel.setBorder(BorderFactory.createTitledBorder("Information about ticket Nr: " + ticketNr));
        ticketInformationPanel.setPreferredSize(super.getPreferredSize());
    }

    public void addComponentsToContainer(){
        ticketInformationPanel.add(testLabel);
        container.add(ticketInformationPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}

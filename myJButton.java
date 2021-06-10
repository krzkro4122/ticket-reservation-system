import javax.swing.JButton;

// Class extending JButton with a Ticket object reference
public class myJButton extends JButton{

    myJButton(String text){
        super(text);
    }
    private Ticket buttonsTicket;

    public Ticket getButtonsTicket(){ return buttonsTicket; }
    public void setButtonsTicket(Ticket ticket){ buttonsTicket = ticket; }
}

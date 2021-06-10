import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.regex.Pattern;
import org.apache.logging.log4j.*;

public class UserAddPopup extends JFrame implements ActionListener {

    private static final Logger logger = LogManager.getLogger("UserAddPopup");

    Container container = getContentPane();
    JPanel panelOfPanels = new JPanel(new GridBagLayout());        
    JPanel userInformationPanel = new JPanel(new GridLayout(3, 2));            
    JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

    JButton addUser = new JButton("Add user");
    JButton resetFields = new JButton("Reset");
        
    private String userLabelNames[] = {"PESEL: ", "Password:", "Full Name:"};    
    
    private ArrayList<JTextField> listOfUserValueLabels = new ArrayList<JTextField>(); // Storage for needed for latter value update    
    User user;

    UserAddPopup(){        
        init();
        addComponentsToContainer();
        logger.log(Level.ERROR, "Constructed.");    
    }

    private void init(){   
        this.setTitle("User Addition");
        this.setPreferredSize(new Dimension(300, 200));        
        this.setBounds(0, 0, 300, 200);
        this.setVisible(true);        

        userInformationPanel.setBorder(BorderFactory.createTitledBorder("Fill in new user info: "));
        userInformationPanel.setPreferredSize(new Dimension(super.getPreferredSize().width, 200));     
        panelOfPanels.setPreferredSize(new Dimension(super.getPreferredSize().width, 200));     
    }

    public void addComponentsToContainer(){

        for (String labelName : userLabelNames){
            JLabel leftLabel;
            JTextField rightField;

            leftLabel = new JLabel(labelName);
            leftLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
            rightField = new JTextField();            

            int fontSize = 18;
            leftLabel.setFont(new Font("Arial", Font.PLAIN, fontSize));
            rightField.setFont(new Font("Arial", Font.PLAIN, fontSize));
            
            listOfUserValueLabels.add(rightField);            
            userInformationPanel.add(leftLabel);
            userInformationPanel.add(rightField);
        }

        buttonPanel.add(addUser);
        buttonPanel.add(resetFields);

        addUser.addActionListener(this);
        resetFields.addActionListener(this);

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 0;

        panelOfPanels.add(userInformationPanel, c);
        panelOfPanels.add(buttonPanel, c);

        container.add(panelOfPanels);
    }

    public int checkExpressions(){         
        // Check regex
        if (!Pattern.compile("\\d{11}").matcher(listOfUserValueLabels.get(0).getText()).matches()) return 1;
        if (!Pattern.compile("[^ ]+").matcher(listOfUserValueLabels.get(1).getText()).matches()) return 1;
        if (!Pattern.compile("[a-zA-Z]+[ ][a-zA-Z]+").matcher(listOfUserValueLabels.get(2).getText()).matches()) return 1;        
        
        // Check if already exists
        ArrayList<User> users = UserDAO.getAll();

        for (User usr : users) {
            if (usr.GetPesel().equals(listOfUserValueLabels.get(0).getText())) return 2;   
        }

        return 0;        
    }

    public void addTicket(){

        user = new User();
        user.SetPesel(listOfUserValueLabels.get(0).getText());
        user.SetPassword(listOfUserValueLabels.get(1).getText());
        user.SetFullName(listOfUserValueLabels.get(2).getText());                
        
        UserDAO.add(user);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Add User button clicked => check if valid input and add it to parent window
        if(e.getSource() == addUser){
            logger.log(Level.ERROR, "Add User button pressed.");    
            // if valid input, add the Ticket
            if(checkExpressions() == 0) {
                logger.log(Level.ERROR, "Values OK.");    
                addTicket();                
                this.setVisible(false);
            // Warn about wrong input
            } else if (checkExpressions() == 1){
                logger.log(Level.ERROR, "Values INCORRECT.");    
                JOptionPane.showMessageDialog(this, "Incorrect values!");
            } else {
                logger.log(Level.ERROR, "Values ALREADY EXIST.");    
                JOptionPane.showMessageDialog(this, "User with PESEL: " + listOfUserValueLabels.get(0).getText() + " alerady exists!");
            }
        }
        // Reset button clicked => clear all textFields
        logger.log(Level.ERROR, "Reset button pressed.");    
        if(e.getSource() == resetFields){
            for (JTextField jtf : listOfUserValueLabels){
                jtf.setText("");
            }           
        }
    }
}

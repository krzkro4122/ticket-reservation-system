import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    Container container = getContentPane();

    JLabel userLabel = new JLabel("USERNAME", SwingConstants.CENTER);
    JLabel passwordLabel = new JLabel("PASSWORD", SwingConstants.CENTER);
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");    
    static Map<String, String> credentialMap;

    // Constructor
    LoginFrame() {
        setFonts();
        setColors();
        setLayoutManager();
        addComponentsToContainer();
        addActionEvent();        
        credentialMap = createDataMap();
    }

    public void setFonts(){
        int fontSize = 18;
        userLabel.setFont(new Font("Arial", Font.PLAIN, fontSize));
        userTextField.setFont(new Font("Arial", Font.PLAIN, fontSize));
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, fontSize));        
        passwordField.setFont(new Font("Arial", Font.PLAIN, fontSize));
        showPassword.setFont(new Font("Arial", Font.PLAIN, fontSize)); 
        loginButton.setFont(new Font("Arial", Font.PLAIN, fontSize));
        resetButton.setFont(new Font("Arial", Font.PLAIN, fontSize));
    }

    public void setColors() {
        // Background Color
        Color backgroundColor = Color.white;
        container.setBackground(backgroundColor);
        showPassword.setBackground(backgroundColor);

        // Button Colors
        Color buttonTextColor = Color.white;
        loginButton.setForeground(buttonTextColor);
        resetButton.setForeground(buttonTextColor);
        Color buttonColor = Color.darkGray;
        loginButton.setBackground(buttonColor);
        resetButton.setBackground(buttonColor);
    }

    public void setLayoutManager() {
        container.setLayout(new GridLayout(6, 2));
    }

    public void addComponentsToContainer() {
        container.add(new Label()); // 👻
        container.add(new Label()); // 👻
        container.add(userLabel);
        container.add(userTextField);
        container.add(passwordLabel);        
        container.add(passwordField);
        container.add(new Label()); // 👻
        container.add(showPassword);
        container.add(new Label()); // 👻
        container.add(new Label()); // 👻
        container.add(loginButton);
        container.add(resetButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        passwordField.addActionListener(this);
    }

    // Retrieve usernames with respective passwords and store them in a map
    public Map<String, String> createDataMap(){
        Map<String, String> credentialMap = new HashMap<String, String>();                

        // int numberOfUsers = 2;
        // for (int i = 0; i < numberOfUsers; i++) {            
            credentialMap.put("mehtab", "12345");
            credentialMap.put("99111104190", "krkrol");
        // }

        return credentialMap;
    }

    public boolean checkCredentials(String username, String password){
        // Check whether username already exists
        if(credentialMap.containsKey(username)){
            // Check whether password mathes the given username
            if(credentialMap.get(username).equals(password)) return true;
        }        
        return false;
    }

    public static void createTicketReservationFrame(String username){
        TicketReservationFrame ticketFrame = new TicketReservationFrame(username);                
        ticketFrame.setTitle("Tickets");
        ticketFrame.setVisible(true); 
        ticketFrame.setPreferredSize(new Dimension(300, 300));       
        ticketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ticketFrame.pack(); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Button "Login" or enter key press
        if (e.getSource() == loginButton || e.getSource() == passwordField) {
            String userText;
            String pwdText;

            userText = userTextField.getText();
            pwdText = String.valueOf(passwordField.getPassword());

            if (checkCredentials(userText, pwdText)) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                // Open the Ticket Reservation Window    
                createTicketReservationFrame(userText);
                // And close the Login window
                this.setVisible(false);
            }
            else JOptionPane.showMessageDialog(this, "Invalid Username or Password");            
        }
        // Button "Reset"
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        // RadioButton "Show Password"
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) passwordField.setEchoChar((char) 0);
            else passwordField.setEchoChar('*');
        }

    }

}
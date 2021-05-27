import javax.swing.*;
import java.awt.*;
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

    LoginFrame() {
        setFonts();
        setColors();
        setLayoutManager();
        addComponentsToContainer();
        addActionEvent();
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            if (userText.equalsIgnoreCase("mehtab") && pwdText.equalsIgnoreCase("12345")) {
                JOptionPane.showMessageDialog(this, "Login Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        }

        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }

    }

}
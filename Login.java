import javax.swing.*;

public class Login {
    public static void createLoginWindow() {
        LoginFrame Loginframe = new LoginFrame();
        Loginframe.setTitle("Login");
        Loginframe.setVisible(true);
        Loginframe.setBounds(10, 10, 370, 201);
        Loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Loginframe.pack();
    }
}
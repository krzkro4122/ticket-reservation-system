import javax.swing.*;

public class Login {
    public static void createLoginWindow() {
        LoginFrame frame = new LoginFrame();
        frame.setTitle("Login Window");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 201);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
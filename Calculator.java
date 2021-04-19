import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator {

    public static void print(String str, int arg1, int arg2, char operator){
        System.out.println(str + " (Current arg1: " + arg1 + ", arg2: " + arg2 + ", operator: " + operator + ")");
    }

    public static int calculate(int input1, int input2, char operator){

        return switch (operator) {
            default -> input2 + input1; // <- addition as default operation
            case '-' -> input2 - input1;
            case '*' -> input2 * input1;
            case '/' -> input2 / input1;
        };
    }

    public static void createAndShowGUI() {

        JFrame jf = new JFrame("Calculator");

        JTextField jtf = new JTextField("0");
        jtf.setHorizontalAlignment(JTextField.RIGHT);
        jtf.setPreferredSize(new Dimension(200, 30));
        Font font1 = new Font("SansSerif", Font.BOLD, 20);
        jtf.setFont(font1);

        jf.getContentPane().add(jtf, BorderLayout.NORTH);

        JPanel jp = new JPanel();
        jf.getContentPane().add(jp, BorderLayout.CENTER);
        jp.setLayout(new GridLayout(4, 4));

        String[] buttons = {"1", "2", "3", "+",
                            "4", "5", "6", "-", 
                            "7", "8", "9", "*", 
                            "C", "0", "=", "/"};

        ActionListener myActionListener = new ActionListener() {

            // Fields initialization:
            char operator;
            boolean operatorPresent = false;
            boolean afterEquals = false;
            int arg1 = 0, arg2 = 0;

            // Reset all flags and fields
            public void clear(){
                operator = 0;
                arg1 = arg2 = 0;
                jtf.setText("");
                operatorPresent = false;
                afterEquals = false;
            }

            // Perform actions as if '=' was clicked:
            public int handleEquals(int outcome){
                jtf.setText("" + outcome);
                print("" + outcome, arg1, arg2, operator);
                arg2 = outcome;
                operatorPresent = false;
                afterEquals = true;
                return outcome;
            }

            public void actionPerformed(ActionEvent e) {

                char input = e.getActionCommand().charAt(0);

                if (input == 'C') clear(); // If it's 'C':
                else { // if it's anything but 'C':
                    print("" + input, arg1, arg2, operator);
                    try{
                        Integer.parseInt(String.valueOf(input)); // <- Just doesn't print operators
                        if (jtf.getText().equals("0")) jtf.setText("" + input); // Erase the initial "0"
                        else jtf.setText(jtf.getText() + input); // Display numbers
                    } catch (NumberFormatException ignored){}
                }

                // Handle input
                try {
                    // If it's a number:
                    arg1 = Integer.parseInt(arg1 + Integer.toString(Integer.parseInt(String.valueOf(input)))); // 👻
                    if (operator != 0) {
                        operatorPresent = true;
                        arg2 = arg1;
                    }
                } catch (NumberFormatException nfe) {
                    // If it's '=':
                    if (input == '=') {
                        arg1 = handleEquals(calculate(arg1, arg2, operator));
                    } else {
                    // if it's '+', '-', '*' or '/':
                        operator = input;
                        jtf.setText("");
                        // if 2nd operator without '=':
                        if (operatorPresent){
                            arg1 = handleEquals(calculate(arg2, arg2, operator));
                            operatorPresent = false;
                        }
                    arg2 = arg1;
                    arg1 = 0;
                    }
                }
            }
        };

        for (String button : buttons) {
            JButton jb = new JButton(button);
            jp.add(jb);
            jb.addActionListener(myActionListener);
        }

        jf.setSize(300, 300);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

        public static void main(String[] args) {
            javax.swing.SwingUtilities.invokeLater(Calculator::createAndShowGUI);
        }
}
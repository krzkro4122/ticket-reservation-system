import java.awt.*;
import java.awt.event.*;
//import java.io.OutputStream;
import javax.swing.*;

public class Calculator {

    private static final JTextField jtf = new JTextField("0");

    public static int calculate(int input1, int input2, char operator) { 

        return switch (operator) {
            default -> input1 + input2; // <- addition as default operation
            case '-' -> input1 - input2;
            case '*' -> input1 * input2;
            case '/' -> input1 / input2;
        };
    }

    public static void createAndShowGUI() {

        JFrame jf = new JFrame("Calculator");

        jtf.setHorizontalAlignment(JTextField.RIGHT);
        jtf.setPreferredSize(new Dimension(200, 30));
        jtf.setBackground(Color.BLACK);
        jtf.setForeground(Color.WHITE);            
        jtf.setFont(new Font("SansSerif", Font.BOLD, 20));
        jtf.setEditable(false);

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
            boolean previousWasDigit = false;
            boolean afterEquals = false;
            boolean multiOperation = false;
            int arg1 = 0, arg2 = 0, temp = 0;

            // Reset all flags and fields
            public void clear(){
                operator = 0;
                arg1 = arg2 = 0;
                jtf.setText("0");
                operatorPresent = false;
                previousWasDigit = false;
                afterEquals = false;
                multiOperation = false;
            }

            // Perform actions as if '=' was clicked:
            public int handleEquals(int outcome) {
                jtf.setText("" + outcome);
                System.out.println(outcome);
                operatorPresent = false;
                previousWasDigit = false;
                afterEquals = true;
                return outcome;
            }

            public void actionPerformed(ActionEvent e) {

                char input = e.getActionCommand().charAt(0);

                if (input == 'C') clear(); // If it's 'C':
                else { // if it's anything but 'C':
                    System.out.println(input);
                    try {
                        try {
                            // trigger NumberFormatException when input's not a digit
                            Integer.parseInt(String.valueOf(input));
                            // If number after '=', clear is needed:
                            if (afterEquals && !previousWasDigit) clear();
                                // Case with multiple operators and no '='
                            else if (afterEquals) {
                                arg1 = 0;
                                multiOperation = true;
                                afterEquals = false;
                            }
                            // If it's a number:
                            String digit = Integer.toString(Integer.parseInt(String.valueOf(input)));
                            if (!operatorPresent) {
                                arg1 = Integer.parseInt(arg1 + digit); // 👻
                                if (multiOperation) temp = arg1;
                                jtf.setText("" + arg1); // Print arg1 in textField
                            } else {
                                arg2 = Integer.parseInt(arg2 + digit); // 👻
                                jtf.setText("" + arg2); // Print arg2 in textField
                            }
                            previousWasDigit = true;
                        } catch (NumberFormatException nfe) {
                            // Block all except digits and 'C' if Division by Zero occurs
                            if (jtf.getText().equals("Division by zero!")) return;
                            // If it's '=':
                            if (input == '=') {
                                // '=' following a digit
                                if (previousWasDigit) {
                                    // when multiple operators -> need to calculate with inverted arguments
                                    if (multiOperation) {
                                        arg1 = handleEquals(calculate(arg2, arg1, operator));
                                        arg2 = temp;
                                    }
                                    else arg1 = handleEquals(calculate(arg1, arg2, operator));
                                } else { // '=' following an operator -> take both arguments as arg1
                                    if (arg2 == 0) arg2 = arg1;
                                    arg1 = handleEquals(calculate(arg1, arg2, operator));
                                }
                                multiOperation = false;
                            } else {
                                // if it's '+', '-', '*' or '/':
                                // if 2nd operator before '=':
                                if (operatorPresent) {
                                    // 2nd operator after digit -> calculate for first operator and calculate result with second operator and new argument
                                    if (previousWasDigit) {
                                        arg2 = handleEquals(calculate(arg1, arg2, operator));
                                        // arg1 = 0;
                                        operator = input;
                                        operatorPresent = false;
                                        previousWasDigit = true;
                                    } else { // 2nd operator after operator -> override with new operator
                                        operator = input;
                                        operatorPresent = true;
                                        previousWasDigit = false;
                                    }
                                } else { // if 1st operator before '=':
                                    operator = input;
                                    // case where we want to operate on output of previous calculation -> just after the output of a calculation
                                    if (afterEquals && !previousWasDigit) {
                                        arg2 = 0;
                                        afterEquals = false;
                                    }
                                    operatorPresent = true;
                                    previousWasDigit = false;
                                }
                            }
                        }
                    } catch (ArithmeticException ae) {
                        clear();
                        jtf.setText("Division by zero!");
                    }
                }
            }
        };

        for (String button : buttons) {
            JButton jb = new JButton(button);
            jb.setBackground(Color.BLACK);
            jb.setForeground(Color.WHITE);
            jb.setFont(new Font("SansSerif", Font.BOLD, 20));
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
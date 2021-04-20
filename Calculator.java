import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator {

    public static void print(String str, int arg1, int arg2, char operator){
        System.out.println(str + " (Current arg1: " + arg1 + ", arg2: " + arg2 + ", operator: " + operator + ")");
    }

    public static int calculate(int input1, int input2, char operator){

        return switch (operator) {
            default -> input1 + input2; // <- addition as default operation
            case '-' -> input1 - input2;
            case '*' -> input1 * input2;
            case '/' -> input1 / input2;
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
            boolean previousWasDigit = false;
            boolean afterEquals = false;
            int arg1 = 0, arg2 = 0;

            // Reset all flags and fields
            public void clear(){
                operator = 0;
                arg1 = arg2 = 0;
                jtf.setText("");
                operatorPresent = false;
                previousWasDigit = false;
                afterEquals = false;
            }

            // Perform actions as if '=' was clicked:
            public int handleEquals(int outcome){
                jtf.setText("" + outcome);
                print("" + outcome, arg1, arg2, operator);
                operatorPresent = false;
                previousWasDigit = false;
                afterEquals = true;
                return outcome;
            }

            public void actionPerformed(ActionEvent e) {

                char input = e.getActionCommand().charAt(0);

                if (input == 'C') clear(); // If it's 'C':
                else { // if it's anything but 'C':
                    print("" + input, arg1, arg2, operator);

                    // Handle input
                    try {
                        // trigger NumerFormatException when input's not a digit
                        Integer.parseInt(String.valueOf(input)); 
                        // If number after '=', clear is needed:     
                        if(afterEquals) clear();
                        // If it's a number:                        
                        if (!operatorPresent) {
                            arg1 = Integer.parseInt(arg1 + Integer.toString(Integer.parseInt(String.valueOf(input)))); // 👻
                            jtf.setText("" + arg1); // Print arg1 in textfield                            
                        }
                        else {
                            arg2 = Integer.parseInt(arg2 + Integer.toString(Integer.parseInt(String.valueOf(input)))); // 👻
                            jtf.setText("" + arg2); // Print arg2 in textfield                            
                        }
                        previousWasDigit = true;
                    } catch (NumberFormatException nfe) {
                        // If it's '=':
                        if (input == '=') arg1 = handleEquals(calculate(arg1, arg2, operator));
                        else {
                        // if it's '+', '-', '*' or '/':                                                               
                            // if 2nd operator without '=':
                            if (operatorPresent){
                                // operator after digit
                                if(previousWasDigit){                                                                    
                                    arg1 = handleEquals(calculate(arg1, arg2, operator));
                                    operator = input;
                                    arg2 = 0;
                                    operatorPresent = false;
                                    previousWasDigit = true;
                                } else{ // operator after operator
                                    operator = input;
                                    operatorPresent = true;
                                    System.out.println("Debug =v");
                                    previousWasDigit = false;
                                }                                
                            }
                            // if 1st operator without '=':
                            else {
                                operator = input;
                                operatorPresent = true;
                                previousWasDigit = false;
                            }                            
                        }
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
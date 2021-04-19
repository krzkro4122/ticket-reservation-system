import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator {

    public static int calculate(int input1, int input2, char operator){
        int output = 0;

        switch (operator) {
            default:
            case '+':
                output = input2 + input1;
                break;
            case '-':
                output = input2 - input1;
                break;
            case '*':
                output = input2 * input1;
                break;
            case '/':
                output = input2 / input1;
                break;
        }

        return output;
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

        String buttons[] = {"1", "2", "3", "+",
                            "4", "5", "6", "-", 
                            "7", "8", "9", "*", 
                            "C", "0", "=", "/"};

        ActionListener myActionListener = new ActionListener(){
    
            char operator;
            int in1, in2;
            boolean operatorPresent = false;
            boolean previousWasNumber = true;
            boolean previousWasEquals = false;
        
            public void actionPerformed(ActionEvent e) {
                char input = e.getActionCommand().charAt(0);
                if (input != 'C')
                    System.out.println(input);
        
                if (input != '+' && input != '-' && input != '*' && input != '/' && input != '='){
        
                    if(!previousWasNumber || previousWasEquals){
                        jtf.setText("" + input);
                        previousWasEquals = false;
                    }
        
                    else{
                        if (jtf.getText().equals("0"))
                            jtf.setText("" + input);
                        else
                            jtf.setText(jtf.getText() + input);
                    }
                }
        
                switch(input){
                case '+':
                    if(!operatorPresent){
                        operatorPresent = true;
                        previousWasNumber = false;
                    }
                    else{
                        if(!previousWasNumber){
                            operator = input;
                            // jtf.setText(jtf.getText().substring(0, jtf.getText().length() - 2));
                            // jtf.setText(jtf.getText() + input);
                        }
                        else{
                            in2 = 0;
                        }
                    }
                    operator = input;
                    break;
                case '-':
                    if(!operatorPresent){
                        operatorPresent = true;
                        previousWasNumber = false;
                    }
                    else{
                        if(!previousWasNumber){
                            operator = input;
                            // jtf.setText(jtf.getText().substring(0, jtf.getText().length() - 2));
                            // jtf.setText(jtf.getText() + input);
                        }
                        else{
                            in2 = 0;
                        }
                    }
                    operator = input;
                    break;
                case '*':
                    if(!operatorPresent){
                        operatorPresent = true;
                        previousWasNumber = false;
                    }
                    else{
                        if(!previousWasNumber){
                            operator = input;
                            // jtf.setText(jtf.getText().substring(0, jtf.getText().length() - 2));
                            // jtf.setText(jtf.getText() + input);
                        }
                        else{
                            in2 = 0;
                        }
                    }
                    operator = input;
                    break;
                case '/':
                    if(!operatorPresent){
                        operatorPresent = true;
                        previousWasNumber = false;
                    }
                    else{
                        if(!previousWasNumber){
                            operator = input;
                            // jtf.setText(jtf.getText().substring(0, jtf.getText().length() - 2));
                            // jtf.setText(jtf.getText() + input);
                        }
                        else{
                            in2 = 0;
                        }
                    }
                    operator = input;
                    break;
                case 'C':
                    in1 = 0;
                    in2 = 0;
                    operatorPresent = false;
                    previousWasNumber = true;
                    operator = 0;
                    jtf.setText("0");
                    break;
                case '=':
                    String output = "";
                    int temp = 0;        
                    previousWasEquals = true;
                    switch(operator){
                        case '+':
                            temp = in1 + in2;
                            output = ("" + temp);
                            break;
                        case '-':
                            temp = in1 - in2;
                            output = ("" + temp);  
                            break;
                        case '*':
                            temp = in1 * in2;
                            output = ("" + temp);
                            break;
                        case '/':
                            try{
                                temp = in1 / in2;
                                output = ("" + temp);
                            } catch (ArithmeticException exc){
                                System.err.println("Division by zero detected!");
                                in1 = 0;
                                in2 = 0;
                                operatorPresent = false;
                                previousWasNumber = true;
                                operator = 0;
                                output = "Division by zero detected!";
                                jtf.setText("Division by zero detected!");
                            }                        
                            break;                
                    }
                    in1 = temp;
                    System.out.println(output);
                    jtf.setText("" + output);                    
                    break;                    
                default:
                    previousWasNumber = true;
                    if(!operatorPresent){
                        in1 = Integer.valueOf("" + in1 + (input - 48));
                    } else
                        in2 = Integer.valueOf("" + in2 + (input - 48));                        
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
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() { createAndShowGUI(); }
            });        
        }
}
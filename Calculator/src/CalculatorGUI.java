import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CalculatorGUI implements ActionListener {
    private JFrame frame;
    private JTextField display;
    private Calculator calculator;
    private String currentInput = "";

    // Constructor for the calculator interface
    public CalculatorGUI() {
        calculator = new Calculator();
        frame = new JFrame();

        // Display field
        display = new JTextField();
        display.setEditable(false);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Main panel for buttons
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        // Add buttons for calculator digits and basic operators
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(this);
            panel.add(button);
        }

        frame.add(display, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Calculator");
        frame.setSize(300, 400);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("0123456789".contains(command)) {
            currentInput += command;
            display.setText(currentInput);
        } else if (command.equals("C")) {
            currentInput = "";
            calculator.reset();
            display.setText("");
        } else if (command.equals("=")) {
            try {
                calculator.calculate(Double.parseDouble(currentInput));
                currentInput = String.valueOf(calculator.getResult());
                display.setText(currentInput);
            } catch (ArithmeticException ex) {
                display.setText("Error: " + ex.getMessage()); // Display custom error message
                calculator.reset(); // Reset calculator state
                currentInput = "";
            } catch (NumberFormatException ex) {
                display.setText("Invalid input");
                currentInput = "";
            }
        } else { // if an operator is pressed
            if (!currentInput.isEmpty()) {
                try {
                    calculator.calculate(Double.parseDouble(currentInput));
                    calculator.setOperator(command);
                    currentInput = ""; // Reset currentInput for the next number
                } catch (ArithmeticException ex) {
                    display.setText("Error: " + ex.getMessage()); // Display custom error message
                    calculator.reset(); // Reset calculator state
                    currentInput = "";
                }
            }
        }
    }

    public static void main(String[] args) {
        new CalculatorGUI(); // Start the calculator application
    }
}
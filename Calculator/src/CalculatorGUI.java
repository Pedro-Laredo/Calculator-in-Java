import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CalculatorGUI implements ActionListener {
    private JFrame frame;
    private JTextField display;
    private Calculator calculator;
    private String currentInput = "";

    // Construtor da interface
    public CalculatorGUI() {
        calculator = new Calculator();
        frame = new JFrame();

        // Campo de exibição
        display = new JTextField();
        display.setEditable(false);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel principal para botões
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        // Botões da calculadora
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setBackground(Color.orange);
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

        if ("0123456789".contains(command)) { // Número digitado
            currentInput += command;
            display.setText(currentInput);
        } else if (command.equals("C")) { // Botão de reset
            currentInput = "";
            calculator.reset();
            display.setText("");
        } else if (command.equals("=")) { // Calcula e mostra o resultado
            try {
                if (!currentInput.isEmpty()) { // Evita erro se "=" for pressionado sem entrada
                    calculator.calculate(Double.parseDouble(currentInput));
                }
                display.setText(String.valueOf(calculator.getResult()));
                currentInput = ""; // Limpa a entrada para começar novas operações
            } catch (ArithmeticException ex) {
                display.setText("Error: " + ex.getMessage());
                calculator.reset();
                currentInput = "";
            } catch (NumberFormatException ex) {
                display.setText("Invalid input");
                currentInput = "";
            }
        } else { // Operador pressionado
            try {
                if (!currentInput.isEmpty()) {
                    calculator.calculate(Double.parseDouble(currentInput));
                }
                calculator.setOperator(command); // Define o novo operador
                display.setText(String.valueOf(calculator.getResult())); // Mostra o resultado parcial
                currentInput = ""; // Reseta o número atual
            } catch (ArithmeticException ex) {
                display.setText("Error: " + ex.getMessage());
                calculator.reset();
                currentInput = "";
            }
        }
    }

    public static void main(String[] args) {
        new CalculatorGUI(); // Inicia a calculadora
    }
}
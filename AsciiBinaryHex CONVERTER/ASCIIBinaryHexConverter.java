import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;

public class ASCIIBinaryHexConverter extends JFrame {

    private JTextArea inputTextArea, outputTextArea;
    private JButton asciiToBinaryButton, binaryToAsciiButton, asciiToHexButton, hexToAsciiButton, binaryToHexButton, hexToBinaryButton, copyButton;

    public ASCIIBinaryHexConverter() {
        setTitle("ASCII-Binary-Hex Converter");
        setSize(1280, 960);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));

        inputTextArea = new JTextArea();
        inputTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);

        outputTextArea = new JTextArea();
        outputTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        outputTextArea.setEditable(false);
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);

        asciiToBinaryButton = new JButton("ASCII to Binary");
        binaryToAsciiButton = new JButton("Binary to ASCII");
        asciiToHexButton = new JButton("ASCII to Hex");
        hexToAsciiButton = new JButton("Hex to ASCII");
        binaryToHexButton = new JButton("Binary to Hex");
        hexToBinaryButton = new JButton("Hex to Binary");
        copyButton = new JButton("Copy");

        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Color buttonColor = new Color(0, 153, 204);
        asciiToBinaryButton.setFont(buttonFont);
        binaryToAsciiButton.setFont(buttonFont);
        asciiToHexButton.setFont(buttonFont);
        hexToAsciiButton.setFont(buttonFont);
        binaryToHexButton.setFont(buttonFont);
        hexToBinaryButton.setFont(buttonFont);
        copyButton.setFont(buttonFont);
        asciiToBinaryButton.setBackground(buttonColor);
        binaryToAsciiButton.setBackground(buttonColor);
        asciiToHexButton.setBackground(buttonColor);
        hexToAsciiButton.setBackground(buttonColor);
        binaryToHexButton.setBackground(buttonColor);
        hexToBinaryButton.setBackground(buttonColor);
        copyButton.setBackground(buttonColor);
        asciiToBinaryButton.setForeground(Color.WHITE);
        binaryToAsciiButton.setForeground(Color.WHITE);
        asciiToHexButton.setForeground(Color.WHITE);
        hexToAsciiButton.setForeground(Color.WHITE);
        binaryToHexButton.setForeground(Color.WHITE);
        hexToBinaryButton.setForeground(Color.WHITE);
        copyButton.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(asciiToBinaryButton);
        buttonPanel.add(binaryToAsciiButton);
        buttonPanel.add(asciiToHexButton);
        buttonPanel.add(hexToAsciiButton);
        buttonPanel.add(binaryToHexButton);
        buttonPanel.add(hexToBinaryButton);

        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBackground(new Color(240, 240, 240));
        JLabel outputLabel = new JLabel("Output:");
        outputLabel.setFont(new Font("Arial", Font.BOLD, 18));
        outputPanel.add(outputLabel, BorderLayout.NORTH);
        outputPanel.add(outputScrollPane, BorderLayout.CENTER);
        outputPanel.add(copyButton, BorderLayout.EAST);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(new Color(240, 240, 240));
        JLabel inputLabel = new JLabel("Input:");
        inputLabel.setFont(new Font("Arial", Font.BOLD, 18));
        inputPanel.add(inputLabel, BorderLayout.NORTH);
        inputPanel.add(inputScrollPane, BorderLayout.CENTER);

        add(inputPanel, BorderLayout.NORTH);
        add(outputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        asciiToBinaryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = inputTextArea.getText();
                String binaryOutput = asciiToBinary(input);
                outputTextArea.setText(binaryOutput);
            }
        });

        binaryToAsciiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = inputTextArea.getText();
                String asciiOutput = binaryToAscii(input);
                outputTextArea.setText(asciiOutput);
            }
        });

        asciiToHexButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = inputTextArea.getText();
                String hexOutput = asciiToHex(input);
                outputTextArea.setText(hexOutput);
            }
        });

        hexToAsciiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = inputTextArea.getText();
                String asciiOutput = hexToAscii(input);
                outputTextArea.setText(asciiOutput);
            }
        });

        binaryToHexButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = inputTextArea.getText();
                String hexOutput = binaryToHex(input);
                outputTextArea.setText(hexOutput);
            }
        });

        hexToBinaryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = inputTextArea.getText();
                String binaryOutput = hexToBinary(input);
                outputTextArea.setText(binaryOutput);
            }
        });

        copyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String outputText = outputTextArea.getText();
                StringSelection stringSelection = new StringSelection(outputText);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        });
    }

    private String asciiToBinary(String ascii) {
        StringBuilder binary = new StringBuilder();
        for (int i = 0; i < ascii.length(); i++) {
            String binaryChar = Integer.toBinaryString(ascii.charAt(i));
            while (binaryChar.length() < 8) {
                binaryChar = "0" + binaryChar;
            }
            binary.append(binaryChar).append(" ");
        }
        return binary.toString();
    }

    private String binaryToAscii(String binary) {
        StringBuilder ascii = new StringBuilder();
        String[] binaryArray = binary.split(" ");
        for (String binaryChar : binaryArray) {
            int asciiValue = Integer.parseInt(binaryChar, 2);
            ascii.append((char) asciiValue);
        }
        return ascii.toString();
    }

    private String asciiToHex(String ascii) {
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < ascii.length(); i++) {
            String hexChar = Integer.toHexString(ascii.charAt(i));
            hex.append(hexChar).append(" ");
        }
        return hex.toString();
    }

    private String hexToAscii(String hex) {
        StringBuilder ascii = new StringBuilder();
        String[] hexArray = hex.split(" ");
        for (String hexChar : hexArray) {
            int asciiValue = Integer.parseInt(hexChar, 16);
            ascii.append((char) asciiValue);
        }
        return ascii.toString();
    }

    private String binaryToHex(String binary) {
        StringBuilder hex = new StringBuilder();
        String[] binaryArray = binary.split(" ");
        for (String binaryChar : binaryArray) {
            int decimal = Integer.parseInt(binaryChar, 2);
            String hexChar = Integer.toHexString(decimal);
            hex.append(hexChar).append(" ");
        }
        return hex.toString();
    }

    private String hexToBinary(String hex) {
        StringBuilder binary = new StringBuilder();
        String[] hexArray = hex.split(" ");
        for (String hexChar : hexArray) {
            int decimal = Integer.parseInt(hexChar, 16);
            String binaryChar = Integer.toBinaryString(decimal);
            while (binaryChar.length() < 8) {
                binaryChar = "0" + binaryChar;
            }
            binary.append(binaryChar).append(" ");
        }
        return binary.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ASCIIBinaryHexConverter converter = new ASCIIBinaryHexConverter();
                converter.setVisible(true);
            }
        });
    }
}

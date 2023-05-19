package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

// Defines the input area
public class InputArea extends JPanel {
    private final String PLACEHOLDER_TEXT = "Write here...";
    private final Font INPUT_FONT = new Font("TimesNewRoman", Font.PLAIN, 20);
    private final Gui GUI;
    JTextArea inputArea;
    JButton submitButton;

    // Constructor that takes in a Gui object and sets up the input area
    public InputArea(Gui gui) {
        this.GUI = gui;
        createInputArea();
    }

    // Method that creates and sets up the input area and submit button
    private void createInputArea() {
        // Set layout for the panel
        this.setLayout(new FlowLayout());

        // Set up the input text area
        inputArea = new JTextArea();
        inputArea.setPreferredSize(new Dimension(600, 70));
        inputArea.setBackground(Color.white);
        inputArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputArea.getText().equals(PLACEHOLDER_TEXT)) {
                    inputArea.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inputArea.getText().equals("")) {
                    inputArea.setText(PLACEHOLDER_TEXT);
                }
            }
        });
        inputArea.setFont(INPUT_FONT);
        inputArea.setText(PLACEHOLDER_TEXT);
        this.add(inputArea);

        // Set up the submit button
        submitButton = new JButton("Send");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage(inputArea.getText());
            }
        });
        submitButton.setPreferredSize(new Dimension(200, 70));
        this.add(submitButton);
    }

    // Method that sends a message to the server via the Gui object
    private void sendMessage(String message) {
        if (message.trim().isEmpty() || message.equals(PLACEHOLDER_TEXT)) {
            return;
        }
        GUI.sendMessage(message);
        inputArea.setText(PLACEHOLDER_TEXT);
    }
}
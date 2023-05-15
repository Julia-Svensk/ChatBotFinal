//Defines the gui class in charge of opening the chat window.
package UI;

import Main.User;
import Messages.DeserializedMessages;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Defines the gui class in charge of opening the chat window.
public class Gui {
    private final User user;
    private JFrame window;
    private JPanel content;
    private SentMessages messageDisplay;
    private InputArea inputArea;

    public Gui(User user) {
        this.user = user;
        initializeUI();
    }

    // Initializes the user interface for the chat window.
    private void initializeUI() {
        createUi();

        // Adds a window listener to the chat window to detect when it is closed.
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                user.shutdown();
            }
        });
    }

    // Creates the user interface for the chat window.
    private void createUi() {
        window = new JFrame("Your chat");
        window.setSize(1000, 500);

        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        messageDisplay = new SentMessages();
        content.add(messageDisplay);

        inputArea = new InputArea(this);
        content.add(inputArea);

        window.add(content);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    // Adds a message to the chat window message display.
    public void addMessage(DeserializedMessages deserializedMessage) {
        messageDisplay.addMessage(deserializedMessage);
    }

     // Sends a message to the chat user.
    protected void sendMessage(String message) {
        user.sendMessage(message);
    }

     // Closes the chat window.
    public void closeGui() {
        window.dispose();
        System.exit(0);
    }
}
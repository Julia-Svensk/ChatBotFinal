package UI;

import Messages.DeserializedMessages;

import javax.swing.*;
import java.awt.*;

//The MessageInfo class is responsible for displaying the content and other information related to a message.
public class MessageInfo extends JPanel {

    private final String senderName;
    private final String message;
    private final boolean isServerMessage;
    private final Font senderFont = new Font("TimesNewRoman", Font.BOLD, 24);
    private final Font messageFont = new Font("TimesNewRoman", Font.PLAIN, 20);
    private final Color serverMessageColor = Color.BLUE;

    private JPanel messageContainer;
    private JLabel senderLabel;
    private JLabel messageLabel;

    // Constructor for MessageInfo.
    public MessageInfo(DeserializedMessages deserializedMessage) {
        // Set member variables from deserializedMessage
        this.senderName = deserializedMessage.sender;
        this.message = deserializedMessage.content;
        this.isServerMessage = deserializedMessage.fromServer;

        // Create the message UI
        createMessage();
    }

    // Sets the size of the component to an absolute value.
    private void setAbsoluteSize(JComponent component, Dimension newSize) {
        component.setPreferredSize(newSize);
        component.setMinimumSize(newSize);
        component.setMaximumSize(newSize);
    }

    // Creates the message UI.
     private void createMessage() {
        // Create the main container for the message
        messageContainer = new JPanel();

        // Set the size and appearance of the container
        setAbsoluteSize(messageContainer, new Dimension(1350, 75));
        messageContainer.setBackground(Color.LIGHT_GRAY);
        messageContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Create the sender label and set its font
        senderLabel = new JLabel("'" + this.senderName + "':");
        senderLabel.setFont(senderFont);

        // Set the color of the sender label to blue if the message is from the server
        if (this.isServerMessage) {
            senderLabel.setForeground(serverMessageColor);
        }

        // Set the size of the sender label based on its content
        FontMetrics senderFontInfo = senderLabel.getFontMetrics(senderFont);
        int senderLabelWidth = 25 + senderFontInfo.stringWidth(senderLabel.getText());
        setAbsoluteSize(senderLabel, new Dimension(senderLabelWidth, 75));

        // Create the message label and set its font and size
        messageLabel = new JLabel(this.message);
        messageLabel.setFont(messageFont);
        setAbsoluteSize(messageLabel, new Dimension(1200 - senderLabelWidth, 75));

        // Add the sender and message labels to the message container
        messageContainer.add(senderLabel);
        messageContainer.add(messageLabel);

        // Add the message container to the main panel and set its background color
        this.add(messageContainer);
        this.setBackground(Color.DARK_GRAY);

        // Set the layout of the main panel and make it visible
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setVisible(true);
    }
}
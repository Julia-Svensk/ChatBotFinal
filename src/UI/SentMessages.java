package UI;

import Messages.DeserializedMessages;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// JPanel to display the list of sent messages
public class SentMessages extends JPanel {
    private final ArrayList<MessageInfo> recentMessages = new ArrayList<>();
    private JScrollPane scrollableArea;
    private JPanel messageArea;

    public SentMessages() {
        createMessageList();
    }

    // Creates the message area with scrollable functionality
    private void createMessageList() {
        messageArea = new JPanel();
        messageArea.setLayout(new BoxLayout(messageArea, BoxLayout.PAGE_AXIS));
        messageArea.setPreferredSize(new Dimension(1350, 650));
        messageArea.setBackground(Color.darkGray);

        // Add scrollable functionality to the message area
        scrollableArea = new JScrollPane(messageArea);
        scrollableArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollableArea.getVerticalScrollBar().setUnitIncrement(16);
        scrollableArea.setPreferredSize(new Dimension(1350, 650));
        this.add(scrollableArea);
    }

    // Adds a message to the list of recent messages
    public void addMessage(DeserializedMessages deserializedMessage) {
        // Check if the message content is empty
        if (deserializedMessage.content.isEmpty()) {
            return;
        }

        // Create a new message info panel with the given message
        MessageInfo message = new MessageInfo(deserializedMessage);

        // Calculate the new height of the message area after adding the new message
        int messageAreaSizeY = 650 + ((recentMessages.size()) * 75);

        // Set the new preferred size of the message area
        messageArea.setPreferredSize(new Dimension(1350, messageAreaSizeY));

        // Add the new message to the message area at the top
        messageArea.add(message, 0);

        // Revalidate the scrollable area to update the scrollbar
        scrollableArea.revalidate();

        // Add the new message to the list of recent messages
        recentMessages.add(message);
    }
}
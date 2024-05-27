package email;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TrashBinPage extends JPanel {
    private List<Message> deletedMessages;
    private JTextArea messagesTextArea;

    public TrashBinPage() {
        deletedMessages = new ArrayList<>();
        setLayout(new BorderLayout());

        messagesTextArea = new JTextArea();
        messagesTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(messagesTextArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addMessage(String sender, String message) {
        deletedMessages.add(new Message(sender, message));
        refreshTrashBinText();
    }

    private void refreshTrashBinText() {
        messagesTextArea.setText("");
        for (Message message : deletedMessages) {
            messagesTextArea.append("From: " + message.getSender() + "\n");
            messagesTextArea.append("Message: " + message.getMessage() + "\n\n");
        }
    }

    private static class Message {
        private String sender;
        private String message;

        public Message(String sender, String message) {
            this.sender = sender;
            this.message = message;
        }

        public String getSender() {
            return sender;
        }

        public String getMessage() {
            return message;
        }
    }
}

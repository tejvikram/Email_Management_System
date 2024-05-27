package email;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InboxPage extends JPanel {
    private String userEmail;
    private List<Message> messages;
    private DefaultListModel<String> messageListModel;
    private JList<String> messageList;
    private JTextArea messageTextArea;
    private TrashBinPage trashBinPage;
    private ImportantPage importantPage; // Reference to ImportantPage

    public InboxPage(String userEmail, TrashBinPage trashBinPage, ImportantPage importantPage) {
        this.userEmail = userEmail;
        this.messages = new ArrayList<>();
        this.messageListModel = new DefaultListModel<>();
        this.messageList = new JList<>(messageListModel);
        this.trashBinPage = trashBinPage;
        this.importantPage = importantPage; // Initialize the reference

        setLayout(new BorderLayout());

        JScrollPane messageScrollPane = new JScrollPane(messageList);
        add(messageScrollPane, BorderLayout.WEST);

        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setPreferredSize(new Dimension(500, 500));
        add(messagePanel, BorderLayout.CENTER);

        messageTextArea = new JTextArea();
        messageTextArea.setEditable(false);
        JScrollPane textAreaScrollPane = new JScrollPane(messageTextArea);
        messagePanel.add(textAreaScrollPane, BorderLayout.CENTER);

        JButton deleteButton = new JButton("Delete Selected");
        deleteButton.addActionListener(e -> removeSelectedMessage());
        add(deleteButton, BorderLayout.SOUTH);

        messageList.addListSelectionListener(e -> showSelectedMessageDetails());
    }

    public void addMessage(String sender, String message) {
        messages.add(new Message(sender, message));
        messageListModel.addElement("From: " + sender);
    }

    private void showSelectedMessageDetails() {
        int selectedIndex = messageList.getSelectedIndex();
        if (selectedIndex != -1) {
            Message selectedMessage = messages.get(selectedIndex);
            messageTextArea.setText("From: " + selectedMessage.getSender() + "\n\n" +
                    "Message: " + selectedMessage.getMessage());
        }
    }

    private void removeSelectedMessage() {
        int selectedIndex = messageList.getSelectedIndex();
        if (selectedIndex != -1) {
            Message selectedMessage = messages.remove(selectedIndex);
            messageListModel.remove(selectedIndex);
            trashBinPage.addMessage(selectedMessage.getSender(), selectedMessage.getMessage());
            messageTextArea.setText(""); // Clear the text area after deletion
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

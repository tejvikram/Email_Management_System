package email;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailComposerFrame extends JFrame {
    private String senderEmail;
    private DashboardFrame dashboardFrame;
    private JTextField recipientField;
    private JTextArea messageTextArea;
    private JComboBox<String> categoryComboBox;
    private JLabel verificationStatusLabel;

    public MailComposerFrame(String senderEmail, DashboardFrame dashboardFrame) {
        this.senderEmail = senderEmail;
        this.dashboardFrame = dashboardFrame;

        setTitle("Compose Mail");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        recipientField = new JTextField();
        messageTextArea = new JTextArea();
        categoryComboBox = new JComboBox<>(new String[]{"Inbox", "Promotional", "Important", "Scheduled", "Spam"});
        JButton sendButton = new JButton("Send");
        verificationStatusLabel = new JLabel("");

        // Add action listener to the send button
        sendButton.addActionListener(e -> sendMessage());

        // Set layout
        setLayout(new BorderLayout());

        // Add components to the content pane
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(4, 2));
        topPanel.add(new JLabel("Recipient: "));
        topPanel.add(recipientField);
        topPanel.add(new JLabel("Category: "));
        topPanel.add(categoryComboBox);
        topPanel.add(new JLabel(" "));
        topPanel.add(verificationStatusLabel);

        add(topPanel, BorderLayout.NORTH);
        add(new JLabel("Message: "), BorderLayout.WEST);
        add(new JScrollPane(messageTextArea), BorderLayout.CENTER);
        add(sendButton, BorderLayout.SOUTH);
    }

    private void sendMessage() {
        String recipient = recipientField.getText();
        String message = messageTextArea.getText();
        String selectedCategory = categoryComboBox.getSelectedItem().toString();
        boolean isValidEmail = isValidEmail(recipient);

        if (isValidEmail && !message.isEmpty()) {
            switch (selectedCategory) {
                case "Inbox":
                    dashboardFrame.getInboxPage().addMessage(recipient, message);
                    break;
                case "Promotional":
                    dashboardFrame.getPromotionalPage().addMessage(recipient, message);
                    break;
                case "Important":
                    dashboardFrame.getImportantPage().addMessage(recipient, message);
                    break;
                case "Scheduled":
                    // Handle scheduled messages (if needed)
                    break;
                case "Spam":
                    dashboardFrame.getSpamPage().addMessage(recipient, message);
                    break;
                default:
                    break;
            }
            JOptionPane.showMessageDialog(this, "Message sent!");
            dispose();  // Close the composer frame after sending the message
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email address or message is empty. Please enter a valid email address and message.");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

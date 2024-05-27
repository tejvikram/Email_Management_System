
package email;


import javax.swing.*;
import java.awt.*;

public class ScheduledPage extends JPanel {
    private String userEmail;
    private JTextArea messagesTextArea;

    public ScheduledPage(String userEmail) {
        this.userEmail = userEmail;

        setLayout(new BorderLayout());

        messagesTextArea = new JTextArea();
        messagesTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(messagesTextArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addMessage(String sender, String message) {
        messagesTextArea.append("From: " + sender + "\n" + message + "\n\n");
    }
}


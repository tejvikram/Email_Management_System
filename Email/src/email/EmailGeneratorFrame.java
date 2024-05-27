/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package email;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmailGeneratorFrame extends JFrame {
    private JComboBox<String> emailTypeComboBox;
    private JTextArea emailTextArea;
    private JButton generateButton;
    private JPanel panel;

    public EmailGeneratorFrame() {
        setTitle("Email Generator");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null); // Use null layout to set bounds manually

        JLabel emailTypeLabel = new JLabel("Select Email Type:");
        emailTypeLabel.setBounds(10, 10, 150, 30);
        panel.add(emailTypeLabel);

        String[] emailTypes = {"Sick Leave", "Newsletter", "Welcome", "Product Announcement", "Confirmation", "Custom"};
        emailTypeComboBox = new JComboBox<>(emailTypes);
        emailTypeComboBox.setBounds(170, 10, 200, 30);
        panel.add(emailTypeComboBox);

        emailTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(emailTextArea);
        scrollPane.setBounds(10, 50, 580, 250);
        panel.add(scrollPane);

        generateButton = new JButton("Generate Email");
        generateButton.setBounds(200, 310, 150, 30);
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateEmail();
            }
        });
        panel.add(generateButton);

        add(panel);
    }

    private void generateEmail() {
        String emailType = emailTypeComboBox.getSelectedItem().toString();
        switch (emailType) {
            case "Sick Leave":
                emailTextArea.setText("Dear Manager,\n\nI am feeling unwell and unable to come to work today.\n\nRegards,\n[Your Name]");
                break;
            case "Newsletter":
                emailTextArea.setText("Hello,\n\nHere is the latest newsletter from our company.\n\n[Newsletter Content]\n\nRegards,\n[Your Name]");
                break;
            case "Welcome":
                emailTextArea.setText("Dear [Recipient Name],\n\nWelcome to our platform! We are thrilled to have you on board.\n\nRegards,\n[Your Name]");
                break;
            case "Product Announcement":
                emailTextArea.setText("Hello,\n\nWe are excited to announce our new product: [Product Name].\n\n[Product Description]\n\nRegards,\n[Your Name]");
                break;
            case "Confirmation":
                emailTextArea.setText("Hello,\n\nThank you for your purchase. Your order has been confirmed.\n\nRegards,\n[Your Name]");
                break;
            case "Custom":
                emailTextArea.setText("");
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EmailGeneratorFrame().setVisible(true);
        });
    }
}


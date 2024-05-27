package email;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DashboardFrame extends JFrame {
    private String userEmail;
    private InboxPage inboxPage;
    private PromotionalPage promotionalPage;
    private ImportantPage importantPage;
    private ScheduledPage scheduledPage;
    private SpamPage spamPage;
    private TrashBinPage trashBinPage;

    public DashboardFrame(String userEmail) {
        this.userEmail = userEmail;
        setTitle("Dashboard - " + userEmail);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Using setBounds, so no layout manager
        setLocationRelativeTo(null);

        // Initialize pages
        trashBinPage = new TrashBinPage();
        inboxPage = new InboxPage(userEmail, trashBinPage, importantPage);
        promotionalPage = new PromotionalPage(userEmail);
        importantPage = new ImportantPage(userEmail);
        scheduledPage = new ScheduledPage(userEmail);
        spamPage = new SpamPage(userEmail);

        // Create buttons
        JButton composeButton = createStyledButton("Compose", Color.RED, 150, 30, 5, 5);
        JButton inboxButton = createStyledButton("Inbox", Color.BLUE, 150, 30, 5, 45);
        JButton promotionalButton = createStyledButton("Promotional", Color.GREEN, 150, 30, 5, 85);
        JButton importantButton = createStyledButton("Important", Color.ORANGE, 150, 30, 5, 125);
        JButton scheduledButton = createStyledButton("Scheduled", Color.MAGENTA, 150, 30, 5, 165);
        JButton spamButton = createStyledButton("Spam", Color.YELLOW, 150, 30, 5, 205);
        JButton emailGeneratorButton = createStyledButton("Email Generator", Color.CYAN, 150, 30, 5, 245);
        JButton generateOTPButton = createStyledButton("Generate OTP", Color.PINK, 150, 30, 5, 285);
        JButton trashBinButton = createStyledButton("Trash Bin", Color.GRAY, 150, 30, 5, 325);

        // Add action listeners
        composeButton.addActionListener(e -> openMailComposer());
        inboxButton.addActionListener(e -> showPage(inboxPage));
        promotionalButton.addActionListener(e -> showPage(promotionalPage));
        importantButton.addActionListener(e -> showPage(importantPage));
        scheduledButton.addActionListener(e -> showPage(scheduledPage));
        spamButton.addActionListener(e -> showPage(spamPage));
        emailGeneratorButton.addActionListener(e -> openEmailGenerator());
        generateOTPButton.addActionListener(e -> generateOTP());
        trashBinButton.addActionListener(e -> showPage(trashBinPage));

        // Add buttons to frame
        add(composeButton);
        add(inboxButton);
        add(promotionalButton);
        add(importantButton);
        add(scheduledButton);
        add(spamButton);
        add(emailGeneratorButton);
        add(generateOTPButton);
        add(trashBinButton);

        // Add pages to frame
        add(inboxPage);
        add(promotionalPage);
        add(importantPage);
        add(scheduledPage);
        add(spamPage);
        add(trashBinPage);

        // Set positions and visibility
        inboxPage.setBounds(160, 5, 620, 590);
        inboxPage.setVisible(false);

        promotionalPage.setBounds(160, 5, 620, 590);
        promotionalPage.setVisible(false);

        importantPage.setBounds(160, 5, 620, 590);
        importantPage.setVisible(false);

        scheduledPage.setBounds(160, 5, 620, 590);
        scheduledPage.setVisible(false);

        spamPage.setBounds(160, 5, 620, 590);
        spamPage.setVisible(false);

        trashBinPage.setBounds(160, 5, 620, 590);
        trashBinPage.setVisible(false);
    }

    private JButton createStyledButton(String text, Color bgColor, int width, int height, int x, int y) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setBackground(bgColor);
        button.setBounds(x, y, width, height);
        return button;
    }

    private void showPage(JPanel page) {
        inboxPage.setVisible(false);
        promotionalPage.setVisible(false);
        importantPage.setVisible(false);
        scheduledPage.setVisible(false);
        spamPage.setVisible(false);
        trashBinPage.setVisible(false);

        page.setVisible(true);

        revalidate();
        repaint();
    }

    private void openMailComposer() {
        new MailComposerFrame(userEmail, this).setVisible(true);
    }

    private void openEmailGenerator() {
        new EmailGeneratorFrame().setVisible(true);
    }

    private void generateOTP() {
        String otp = String.format("%06d", new Random().nextInt(1000000));
        String subject = "One-Time Password (OTP) for XYZ Website";
        String message = "Your OTP for XYZ Website is: " + otp + "\n\nThis OTP is valid for 5 minutes.";
        String from = "no-reply@xyzwebsite.com";

        if (sendEmail(from, userEmail, subject, message)) {
            inboxPage.addMessage(from, message);
            JOptionPane.showMessageDialog(this, "OTP has been sent to your email address.", "OTP Sent", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to send OTP. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean sendEmail(String from, String to, String subject, String message) {
        // Placeholder method for sending email
        return true;
    }

    
      public InboxPage getInboxPage() {
        return inboxPage;
    }

    public PromotionalPage getPromotionalPage() {
        return promotionalPage;
    }

    public ImportantPage getImportantPage() {
        return importantPage;
    }

    public ScheduledPage getScheduledPage() {
        return scheduledPage;
    }

    public SpamPage getSpamPage() {
        return spamPage;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DashboardFrame("sample@example.com").setVisible(true);
        });
    }
}

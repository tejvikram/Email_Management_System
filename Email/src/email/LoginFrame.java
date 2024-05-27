
package email;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(40, 154, 155));

        JLabel emailLabel = new JLabel("Email ID:");
        emailLabel.setBounds(50, 50, 100, 30);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(160, 50, 200, 30);
        panel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 30);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(160, 100, 200, 30);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(160, 150, 100, 30);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 16));
        Border roundedBorder = new LineBorder(Color.BLACK, 2, true);
        loginButton.setBorder(roundedBorder);
        loginButton.setBackground(new Color(200, 150, 12));
        loginButton.setForeground(Color.white);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                if (authenticateUser(email, password)) {
                    openDashboard(email);
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid email or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(loginButton);
        add(panel);
    }

    private boolean authenticateUser(String email, String password) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/email_system", "root", "root123");

            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet.next(); // Returns true if a matching user is found
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void openDashboard(String userEmail) {
        // You can create and open the DashboardFrame here
        DashboardFrame dashboardFrame = new DashboardFrame(userEmail);
        dashboardFrame.setVisible(true);

        // Close the current login frame
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}


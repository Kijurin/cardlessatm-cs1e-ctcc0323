package com.totallyrealbanking;

import javax.swing.*;
import java.awt.*;

public class SignupScreen extends JFrame {
    private JTextField emailField;
    private JTextField phoneField;
    private JPasswordField passwordField;

    public SignupScreen() {
        setTitle("Sign Up - TotallyRealBanking");
        setSize(1360, 768); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240)); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); 

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel titleLabel = new JLabel("Sign Up");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        phoneField = new JTextField(20);
        phoneField.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton signupButton = new JButton("Sign Up");
        signupButton.setFont(new Font("Arial", Font.BOLD, 24));
        signupButton.setBackground(new Color(30, 144, 255)); 
        signupButton.setForeground(Color.WHITE);
        signupButton.addActionListener(e -> signup());
        panel.add(signupButton, gbc);

        gbc.gridy = 5;
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 24));
        backButton.setBackground(new Color(30, 144, 255)); 
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            new SplashScreen();
            dispose();
        });
        panel.add(backButton, gbc);

        gbc.gridy = 6;
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 24));
        exitButton.setBackground(new Color(220, 20, 60)); 
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(e -> System.exit(0));


        add(panel);
        setVisible(true);
    }

    private void signup() {
        String email = emailField.getText();
        String phone = phoneField.getText();
        String password = new String(passwordField.getPassword());

        if (isValidEmail(email) && isValidPhoneNumber(phone) && isValidPassword(password)) {
            UserDatabase.addUser(email, phone, password);
            JOptionPane.showMessageDialog(this, "Account created successfully!");
            new LoginScreen();
            dispose();
        } else {
            StringBuilder errorMessage = new StringBuilder("Invalid input:");
            if (!isValidEmail(email)) {
                errorMessage.append("\n- Email must be a valid Gmail address (e.g., example@gmail.com)");
            }
            if (!isValidPhoneNumber(phone)) {
                errorMessage.append("\n- Phone number must be exactly 11 digits and contain only numbers (e.g., 09150026334)");
            }
            if (!isValidPassword(password)) {
                errorMessage.append("\n- Password must be at least 8 characters long, contain at least one digit, and one uppercase letter");
            }
            JOptionPane.showMessageDialog(this, errorMessage.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidEmail(String email) {
        
        return email.endsWith("@gmail.com");
    }

    private boolean isValidPhoneNumber(String phone) {
        
        return phone.matches("\\d{11}");
    }

    private boolean isValidPassword(String password) {
        
        return password.length() >= 8 && password.matches(".*\\d.*") && password.matches(".*[A-Z].*");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SignupScreen());
    }
}
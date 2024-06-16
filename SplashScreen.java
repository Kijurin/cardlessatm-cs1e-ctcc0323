package com.totallyrealbanking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashScreen extends JFrame {
    public SplashScreen() {
        setTitle("TotallyRealBanking");
        setSize(1360, 768); // Set size to full screen resolution
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240)); // Light Gray background

        JLabel label = new JLabel("Welcome to TotallyRealBanking", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 36));
        panel.add(label, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 240, 240)); // Light Gray background

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 24));
        loginButton.setBackground(new Color(30, 144, 255)); // DodgerBlue background
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(e -> {
            new LoginScreen();
            dispose();
        });
        buttonPanel.add(loginButton);

        JButton signupButton = new JButton("Sign Up");
        signupButton.setFont(new Font("Arial", Font.BOLD, 24));
        signupButton.setBackground(new Color(30, 144, 255)); // DodgerBlue background
        signupButton.setForeground(Color.WHITE);
        signupButton.addActionListener(e -> {
            new SignupScreen();
            dispose();
        });
        buttonPanel.add(signupButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}
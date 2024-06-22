package com.totallyrealbanking;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JFrame {
    public SplashScreen() {
        setTitle("TotallyRealATM");
        setSize(1360, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240)); 
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 200, 0));

        JLabel label = new JLabel("Welcome to TotallyRealATM", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 40));
        panel.add(label, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 240, 240)); 

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 28));
        loginButton.setPreferredSize(new Dimension(200, 60)); 
        loginButton.setBackground(new Color(30, 144, 255)); 
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(e -> {
            new LoginScreen();
            dispose();
        });
        buttonPanel.add(loginButton);

        JButton signupButton = new JButton("Sign Up");
        signupButton.setFont(new Font("Arial", Font.BOLD, 28));
        signupButton.setPreferredSize(new Dimension(200, 60)); 
        signupButton.setBackground(new Color(30, 144, 255)); 
        signupButton.setForeground(Color.WHITE);
        signupButton.addActionListener(e -> {
            new SignupScreen();
            dispose();
        });
        buttonPanel.add(signupButton);
        panel.add(Box.createVerticalStrut(1000), BorderLayout.SOUTH); 
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}
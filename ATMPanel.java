package com.totallyrealbanking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMPanel extends JFrame {
    private String username;

    public ATMPanel(String username) {
        this.username = username;

        setTitle("ATM - TotallyRealBanking");
        setSize(1360, 768); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Logo panel
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(245, 245, 245)); 
        JLabel logoLabel = new JLabel("TotallyRealATM", SwingConstants.CENTER);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 48));
        logoLabel.setForeground(new Color(30, 144, 255)); 
        logoPanel.add(logoLabel);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(245, 245, 245)); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 50, 20, 50); 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JButton balanceButton = new JButton("Show Balance");
        balanceButton.setFont(new Font("Arial", Font.PLAIN, 24));
        balanceButton.setBackground(new Color(30, 144, 255)); 
        balanceButton.setForeground(Color.WHITE);
        balanceButton.addActionListener(e -> showBalance());
        buttonPanel.add(balanceButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Arial", Font.PLAIN, 24));
        withdrawButton.setBackground(new Color(30, 144, 255));
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.addActionListener(e -> withdraw());
        buttonPanel.add(withdrawButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JButton depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Arial", Font.PLAIN, 24));
        depositButton.setBackground(new Color(30, 144, 255));
        depositButton.setForeground(Color.WHITE);
        depositButton.addActionListener(e -> deposit());
        buttonPanel.add(depositButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JButton backButton = new JButton("Logout");
        backButton.setFont(new Font("Arial", Font.PLAIN, 24));
        backButton.setBackground(new Color(220, 20, 60));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> backToMenu());
        buttonPanel.add(backButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        

        add(logoPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void showBalance() {
        double balance = UserDatabase.getBalance(username);
        JOptionPane.showMessageDialog(this, "Your balance is: ₱" + balance, "Balance", JOptionPane.INFORMATION_MESSAGE);
    }

    private void withdraw() {
        String amountStr = JOptionPane.showInputDialog(this, "Enter amount to withdraw (max ₱100,000):");
        if (amountStr != null && !amountStr.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountStr);
                double balance = UserDatabase.getBalance(username);
                if (amount > 0 && amount <= 100000) {
                    if (balance - amount >= 1000) {
                        if (UserDatabase.withdraw(username, amount)) {
                            JOptionPane.showMessageDialog(this, "Withdrawal successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "Insufficient balance", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Cannot withdraw. Maintaining balance of ₱1,000 required.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid amount. Withdrawal amount must be between ₱1 and ₱100,000", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deposit() {
        String amountStr = JOptionPane.showInputDialog(this, "Enter amount to deposit (max ₱100,000):");
        if (amountStr != null && !amountStr.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountStr);
                if (amount > 0 && amount <= 100000) {
                    UserDatabase.deposit(username, amount);
                    JOptionPane.showMessageDialog(this, "Deposit successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid amount. Deposit amount must be between ₱1 and ₱100,000", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void backToMenu() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            new SplashScreen();
            dispose();
        }
    }
}
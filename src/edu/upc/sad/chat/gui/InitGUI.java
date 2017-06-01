/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.sad.chat.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InitGUI extends JFrame implements ActionListener{

    private JButton submitButton;
    private JTextField usernameText;

    public InitGUI() {
        createAndShowGUI();
        this.getRootPane().setDefaultButton(submitButton);
        usernameText.addActionListener(this);
        submitButton.addActionListener(this);
    }

    private void createAndShowGUI() {
        java.awt.GridBagConstraints gridBagConstraints;

        JPanel panel = new JPanel();
        usernameText = new JTextField();
        submitButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel.setBorder(BorderFactory.createTitledBorder("Set your username:"));
        panel.setMinimumSize(new java.awt.Dimension(10, 10));
        panel.setPreferredSize(getMinimumSize());
        panel.setLayout(new java.awt.GridBagLayout());

        usernameText.setColumns(10);
        usernameText.setHorizontalAlignment(JTextField.CENTER);
        usernameText.setAlignmentY(-4.0F);
        usernameText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                usernameTextActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panel.add(usernameText, gridBagConstraints);

        submitButton.setText("Enter");
        submitButton.setAlignmentY(0.0F);
        submitButton.setMaximumSize(new java.awt.Dimension(29, 29));
        submitButton.setMinimumSize(new java.awt.Dimension(29, 29));
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panel.add(submitButton, gridBagConstraints);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void usernameTextActionPerformed(ActionEvent evt) { }

    private void submitButtonActionPerformed(ActionEvent evt) { }
    
    
    public void actionPerformed(ActionEvent evt) {
        new ClientGUI(usernameText.getText()).setVisible(true);
        this.dispose();
    }

    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InitGUI().setVisible(true);
            }
        });
    }
}

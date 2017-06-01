package edu.upc.sad.chat.gui;

import edu.upc.sad.chat.client.Client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;


public class ClientGUI extends JFrame implements ActionListener {

    private String usuari;
    private Client client;
    private DefaultListModel model;
    private JButton sendButton;
    private JList<String> userList;
    private JTextArea chatTextArea;
    private JTextField messageTextField;
    
    public ClientGUI(String usuari) {
        initComponents();
        messageTextField.requestFocus();
        sendButton.setDefaultCapable(true);

        SwingUtilities.getRootPane(sendButton).setDefaultButton(sendButton);

        this.usuari = usuari;
        model = new DefaultListModel<String>();
        userList.setModel(model);
        client = new Client("localhost", 2500, this.usuari, this);

        messageTextField.addActionListener(this);
        sendButton.addActionListener(this);
        
        
        super.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                client.closeSocket();
                System.exit(0);
            }
        });


    }

    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        JPanel panel = new JPanel();
        JScrollPane scrollPane1 = new JScrollPane();
        chatTextArea = new JTextArea();
        messageTextField = new JTextField();
        sendButton = new JButton();
        JScrollPane scrollPane2 = new JScrollPane();
        userList = new JList<>();

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        panel.setAutoscrolls(true);
        panel.setMinimumSize(new java.awt.Dimension(1, 1));
        panel.setLayout(new java.awt.GridBagLayout());

        scrollPane1.setAutoscrolls(true);

        chatTextArea.setEditable(false);
        chatTextArea.setColumns(32);
        chatTextArea.setRows(20);
        chatTextArea.setFocusable(false);
        chatTextArea.setMinimumSize(new java.awt.Dimension(0, 0));
        scrollPane1.setViewportView(chatTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.75;
        gridBagConstraints.weighty = 0.9;
        panel.add(scrollPane1, gridBagConstraints);

        messageTextField.setColumns(32);
        messageTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                messageTextFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.75;
        gridBagConstraints.weighty = 0.1;
        panel.add(messageTextField, gridBagConstraints);

        sendButton.setText("Send");
        sendButton.setAlignmentY(0.0F);
        sendButton.setMaximumSize(new java.awt.Dimension(29, 29));
        sendButton.setMinimumSize(new java.awt.Dimension(50, 29));
        sendButton.setPreferredSize(new java.awt.Dimension(120, 29));
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.weighty = 0.1;
        panel.add(sendButton, gridBagConstraints);

        scrollPane2.setPreferredSize(new java.awt.Dimension(150, 320));

        userList.setToolTipText("");
        userList.setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        userList.setEnabled(false);
        userList.setFocusTraversalKeysEnabled(false);
        userList.setFocusable(false);
        userList.setVisibleRowCount(0);
        scrollPane2.setViewportView(userList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.weighty = 0.9;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        panel.add(scrollPane2, gridBagConstraints);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(557, 387));
        setLocationRelativeTo(null);
    }

    private void sendButtonActionPerformed(ActionEvent evt) { }

    private void messageTextFieldActionPerformed(ActionEvent evt) { }

    public void actionPerformed(ActionEvent evt) {
        String str = messageTextField.getText();
        if (!(str.equals(""))) {
            chatTextArea.append("Me: "+str + "\n");
            client.sendText(str);
            messageTextField.setText("");
        }
    }

    public void receiveText(String str) {
        chatTextArea.append(str + "\n");
    }

    public void addUser(String user) {
        model.addElement(user);
    }

    public void removeUser(String user) {
        model.removeElement(user);
    }
}

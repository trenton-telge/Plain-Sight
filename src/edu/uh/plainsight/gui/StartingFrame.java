package edu.uh.plainsight.gui;

import javax.swing.*;
import java.awt.*;

public class StartingFrame extends JFrame {
    public StartingFrame(){
        this.setTitle("Plain Sight");
        this.setSize(new Dimension(600, 200));
        JPanel mainPanel = new JPanel();
        mainPanel.setSize(new Dimension(600, 200));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel prompt = new JLabel("Choose an operation:");
        JButton encryptButton = new JButton("Encrypt");
        JButton decryptButton = new JButton("Decrypt");
        this.setContentPane(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        prompt.setPreferredSize(new Dimension(600, 75));
        prompt.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(prompt, BorderLayout.NORTH);
        Dimension buttonDimension = new Dimension(300, 125);
        encryptButton.setPreferredSize(buttonDimension);
        decryptButton.setPreferredSize(buttonDimension);
        mainPanel.add(encryptButton, BorderLayout.WEST);
        mainPanel.add(decryptButton, BorderLayout.EAST);
        encryptButton.addActionListener(e->{
            //TODO make a JFileChooser to choose starting image
            //TODO make a JFileChooser to choose file to encrypt in image
            //TODO start new thread for encryption
            //TODO make progress bar frame
            //TODO make output frame on thread close
        });
        decryptButton.addActionListener(e->{
            //TODO make a frame with buttons to choose starting image, encrypted image, and destination
            //TODO make new thread for decryption
            //TODO make progress bar frame
            //TODO make output frame on thread close
        });
        mainPanel.setVisible(true);
        this.setVisible(true);
    }
}

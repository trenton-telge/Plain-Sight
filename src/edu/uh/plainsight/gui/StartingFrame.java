package edu.uh.plainsight.gui;

import edu.uh.plainsight.PlainSight;
import edu.uh.plainsight.util.EncryptThread;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

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
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "PNG Images", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(new JFrame("Choose an base image."));
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                PlainSight.inputFile = chooser.getSelectedFile();
            }
            //TODO calculate max file size
            chooser = new JFileChooser();
            returnVal = chooser.showOpenDialog(new JFrame("Choose a file to encrypt."));
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                PlainSight.dataFile = chooser.getSelectedFile();
            }

            PlainSight.outputFile = new File(PlainSight.inputFile.toString().concat("encrypted.png"));

            new EncryptThread(PlainSight.inputFile, PlainSight.dataFile, PlainSight.outputFile).start();
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

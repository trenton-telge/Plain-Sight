package edu.uh.plainsight.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static edu.uh.plainsight.util.ImageUtil.colorSub;

public class DecryptThread extends Thread {
    private File encryptedFile, startingFile, outputFile;

    public DecryptThread (File encryptedFile, File startingFile, File outputFile){
        this.encryptedFile = encryptedFile;
        this.startingFile = startingFile;
        this.outputFile = outputFile;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void run() {
        try {
            System.out.println("Reading color data from " + startingFile.toString());
            BufferedImage image = ImageIO.read(startingFile);
            int height = image.getHeight();
            int width = image.getWidth();
            int h, w;
            Color[][] baseRGB = new Color[height][width];
            System.out.println("Reading RGB values...");
            for (h = 0; h < height; h++){
                for (w = 0; w < width; w++){
                    baseRGB[h][w] = new Color(image.getRGB(w, h));
                }
            }
            System.out.println("Values read.");
            System.out.println("Reading color data from " + encryptedFile.toString());
            image = ImageIO.read(startingFile);
            height = image.getHeight();
            width = image.getWidth();
            Color[][] encryptedRGB = new Color[height][width];
            System.out.println("Reading RGB values...");
            for (h = 0; h < height; h++){
                for (w = 0; w < width; w++){
                    encryptedRGB[h][w] = new Color(image.getRGB(w, h));
                }
            }
            System.out.println("Values read.");
            System.out.println("Desaturating base...");
            for (h = 0; h < height; h++){
                for (w = 0; w < width; w++){
                    baseRGB[h][w] = new Color(colorSub(baseRGB[h][w].getRed(), 16), colorSub(baseRGB[h][w].getGreen(), 16), colorSub(baseRGB[h][w].getBlue(), 16));
                }
            }
            System.out.println("Desaturated.");
            System.out.println("Reading metadata...");
        } catch (IOException e){
            e.printStackTrace();
        }
        super.run();
    }
}

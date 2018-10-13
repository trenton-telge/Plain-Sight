package edu.uh.plainsight.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EncryptThread extends Thread {
    private File inputFile, dataFile, outputFile;

    public EncryptThread(File inputFile, File dataFile, File outputFile){
        this.inputFile = inputFile;
        this.dataFile = dataFile;
        this.outputFile = outputFile;
    }

    @Override
    public void run() {
        try {
            System.out.println("Reading color data from " + inputFile.toString());
            BufferedImage image = ImageIO.read(inputFile);
            int height = image.getHeight();
            int width = image.getWidth();
            Color[][] RGB = new Color[height][width];
            System.out.println("Reading RGB values...");
            for (int h = 0; h < height; h++){
                for (int w = 0; w < width; w++){
                    RGB[h][w] = new Color(image.getRGB(w, h));
                }
            }
            System.out.println("Values read.");
            System.out.println("Desaturating...");
            for (int h = 0; h < height; h++){
                for (int w = 0; w < width; w++){
                    RGB[h][w] = new Color(colorSub(RGB[h][w].getRed(), 16), colorSub(RGB[h][w].getGreen(), 16), colorSub(RGB[h][w].getBlue(), 16));
                }
            }
            System.out.println("Desaturated.");
            //TODO add in values from binary data
            System.out.println("Generating new bitmap...");
            for (int h = 0; h < height; h++){
                for (int w = 0; w < width; w++){
                    image.setRGB(w, h, RGB[h][w].getRGB());
                }
            }
            System.out.println("Bitmap generated.");
            System.out.println("Writing to file...");
            ImageIO.write(image, "png", outputFile);
            System.out.println("File written.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.run();
    }

    int colorSub(int val, int offset){
        if (val - offset >= 0){return val - offset;}
        else {return 0;}
    }
    int colorAdd(int val, int offset){
        if (val + offset <= 255){return val + offset;}
        else {return 255;}
    }
}


/*
 * This debug line will print the argb values of each pixel
 *
 * System.out.println(w + "x" + h + " : " + RGB[h][w].getRed() + " " + RGB[h][w].getGreen() + " " + RGB[h][w].getBlue() + " " + RGB[h][w].getAlpha());
 *
 */
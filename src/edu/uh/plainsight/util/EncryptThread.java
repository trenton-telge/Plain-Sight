package edu.uh.plainsight.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class EncryptThread extends Thread {
    private File inputFile, dataFile, outputFile;

    public EncryptThread(File inputFile, File dataFile, File outputFile){
        this.inputFile = inputFile;
        this.dataFile = dataFile;
        this.outputFile = outputFile;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void run() {
        try {
            System.out.println("Reading color data from " + inputFile.toString());
            BufferedImage image = ImageIO.read(inputFile);
            int height = image.getHeight();
            int width = image.getWidth();
            int h, w; //just declaring here to reduce redundancy.
            Color[][] RGB = new Color[height][width];
            System.out.println("Reading RGB values...");
            for (h = 0; h < height; h++){
                for (w = 0; w < width; w++){
                    RGB[h][w] = new Color(image.getRGB(w, h));
                }
            }
            System.out.println("Values read.");
            System.out.println("Desaturating...");
            for (h = 0; h < height; h++){
                for (w = 0; w < width; w++){
                    RGB[h][w] = new Color(colorSub(RGB[h][w].getRed(), 16), colorSub(RGB[h][w].getGreen(), 16), colorSub(RGB[h][w].getBlue(), 16));
                }
            }
            System.out.println("Desaturated.");
            System.out.println("Reading file to binary...");
            byte[] binary = Files.readAllBytes(dataFile.toPath());
            System.out.println("File read complete. " + binary.length + " bytes read.");
            h = 0;
            w = 0;
            System.out.println("Encoding...");
            while (binary.length > 0) {
                System.out.print("Working on pixels: " + w + " x " + h + " | ");
                RGB[h][w] = new Color(colorAdd(RGB[h][w].getRed(), (binary[0] >> 4) & ((1 << 4) - 1)),
                        colorAdd(RGB[h][w].getGreen(), (binary[0]) & ((1 << 4) - 1)),
                        binary.length > 1 ? colorAdd(RGB[h][w].getBlue(), (binary[1] >> 4) & ((1 << 4) - 1)) : RGB[h][w].getBlue());
                if (w < width-2){
                    w++;
                }
                else {
                    if (h < height-2){
                        w = 0;
                        h++;
                    } else {
                        throw new OutOfEncryptionSpaceException();
                    }
                }
                System.out.println(" " + w + " x " + h + " | " + binary.length/1000 + " KB of data remaining.");
                RGB[h][w] = new Color(binary.length > 1 ? colorAdd(RGB[h][w].getRed(), (binary[1]) & ((1 << 4) - 1)) : RGB[h][w].getRed(),
                        binary.length > 2 ? colorAdd(RGB[h][w].getGreen(), (binary[2] >> 4) & ((1 << 4) - 1)) : RGB[h][w].getGreen(),
                        binary.length > 2 ? colorAdd(RGB[h][w].getBlue(), (binary[2]) & ((1 << 4) - 1)) : RGB[h][w].getBlue());
                if (w < width-2){
                    w++;
                }
                else {
                    if (h < height-2){
                        w = 0;
                        h++;
                    } else {
                        throw new OutOfEncryptionSpaceException();
                    }
                }
                binary = Arrays.copyOfRange(binary, 1, binary.length);
            }
            System.out.println("Encoded.");
            System.out.println("Generating new bitmap...");
            for (h = 0; h < height; h++){
                for (w = 0; w < width; w++){
                    image.setRGB(w, h, RGB[h][w].getRGB());
                }
            }
            System.out.println("Bitmap generated.");
            System.out.println("Writing to file...");
            ImageIO.write(image, "png", outputFile);
            System.out.println("File written.");
        } catch (IOException | OutOfEncryptionSpaceException e) {
            e.printStackTrace();
        }
        super.run();
    }

    private int colorSub(int val, int offset){
        if (val - offset >= 0){return val - offset;}
        else {return 0;}
    }
    private int colorAdd(int val, int offset){
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
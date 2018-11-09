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
            long fileSize = 0;
            fileSize += (encryptedRGB[height-1][width-4].getRed() - baseRGB[height-1][width-4].getRed())+2 * Math.pow(10, 11);
            fileSize += (encryptedRGB[height-1][width-4].getGreen() - baseRGB[height-1][width-4].getGreen())+2 * Math.pow(10, 10);
            fileSize += (encryptedRGB[height-1][width-4].getBlue() - baseRGB[height-1][width-4].getBlue())+2 * Math.pow(10, 9);
            fileSize += (encryptedRGB[height-1][width-3].getRed() - baseRGB[height-1][width-3].getRed())+2 * Math.pow(10, 8);
            fileSize += (encryptedRGB[height-1][width-3].getGreen() - baseRGB[height-1][width-3].getGreen())+2 * Math.pow(10, 7);
            fileSize += (encryptedRGB[height-1][width-3].getBlue() - baseRGB[height-1][width-3].getBlue())+2 * Math.pow(10, 6);
            fileSize += (encryptedRGB[height-1][width-2].getRed() - baseRGB[height-1][width-2].getRed())+2 * Math.pow(10, 5);
            fileSize += (encryptedRGB[height-1][width-2].getGreen() - baseRGB[height-1][width-2].getGreen())+2 * Math.pow(10, 4);
            fileSize += (encryptedRGB[height-1][width-2].getBlue() - baseRGB[height-1][width-2].getBlue())+2 * Math.pow(10, 3);
            fileSize += (encryptedRGB[height-1][width-1].getRed() - baseRGB[height-1][width-1].getRed())+2 * Math.pow(10, 2);
            fileSize += (encryptedRGB[height-1][width-1].getGreen() - baseRGB[height-1][width-1].getGreen())+2 * Math.pow(10, 1);
            fileSize += (encryptedRGB[height-1][width-1].getBlue() - baseRGB[height-1][width-1].getBlue())+2 * Math.pow(10, 0);
            System.out.println(" Encrypted data size is " + fileSize + " bytes.");
            String ext = ImageUtil.concatHalfBytesToASCII((encryptedRGB[height-1][width-8].getGreen() - baseRGB[height-1][width-8].getGreen()), (encryptedRGB[height-1][width-8].getBlue() - baseRGB[height-1][width-8].getBlue())) == (char)0? "" : Character.toString(ImageUtil.concatHalfBytesToASCII((encryptedRGB[height-1][width-8].getGreen() - baseRGB[height-1][width-8].getGreen()), (encryptedRGB[height-1][width-8].getBlue() - baseRGB[height-1][width-8].getBlue())));
            ext = ext.concat(ImageUtil.concatHalfBytesToASCII((encryptedRGB[height-1][width-7].getGreen() - baseRGB[height-1][width-7].getGreen()), (encryptedRGB[height-1][width-7].getBlue() - baseRGB[height-1][width-7].getBlue())) == (char)0? "" : Character.toString(ImageUtil.concatHalfBytesToASCII((encryptedRGB[height-1][width-7].getGreen() - baseRGB[height-1][width-7].getGreen()), (encryptedRGB[height-1][width-7].getBlue() - baseRGB[height-1][width-7].getBlue()))));
            ext = ext.concat(ImageUtil.concatHalfBytesToASCII((encryptedRGB[height-1][width-6].getGreen() - baseRGB[height-1][width-6].getGreen()), (encryptedRGB[height-1][width-6].getBlue() - baseRGB[height-1][width-6].getBlue())) == (char)0? "" : Character.toString(ImageUtil.concatHalfBytesToASCII((encryptedRGB[height-1][width-6].getGreen() - baseRGB[height-1][width-6].getGreen()), (encryptedRGB[height-1][width-6].getBlue() - baseRGB[height-1][width-6].getBlue()))));
            ext = ext.concat(ImageUtil.concatHalfBytesToASCII((encryptedRGB[height-1][width-5].getGreen() - baseRGB[height-1][width-5].getGreen()), (encryptedRGB[height-1][width-5].getBlue() - baseRGB[height-1][width-5].getBlue())) == (char)0? "" : Character.toString(ImageUtil.concatHalfBytesToASCII((encryptedRGB[height-1][width-5].getGreen() - baseRGB[height-1][width-5].getGreen()), (encryptedRGB[height-1][width-5].getBlue() - baseRGB[height-1][width-5].getBlue()))));
            System.out.println(" File type is " + ext.toUpperCase() + ".");
            System.out.println("Metadata decrypted.");
        } catch (IOException e){
            e.printStackTrace();
        }
        super.run();
    }
}

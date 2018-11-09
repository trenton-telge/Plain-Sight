package edu.uh.plainsight.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class ImageUtil {
    public static long calculateEncryptionCapacity(File image, int colorOffset){
        BufferedImage i = null;
        try {
            i = ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (i != null) {
            return roundLog(colorOffset)*3*i.getWidth()*i.getHeight()/8/1000; //in kilobytes
        } else {return 0;}
    }
    private static int roundLog(long num){
        int r = 0;
        while (num/ (long) 2 >= 1){
            num = num/ (long) 2;
            r++;
        }
        return r;
    }
    static int colorSub(int val, int offset){
        if (val - offset >= 0){return val - offset;}
        else {return 0;}
    }
    static int colorAdd(int val, int offset){
        if (val + offset <= 255){return val + offset;}
        else {return 255;}
    }
}

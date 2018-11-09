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
    static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndex = name.lastIndexOf('.');
        if (lastIndex == -1) {
            return "";
        }
        return name.substring(lastIndex);
    }
    static short firstFourBitASCIIMask(char c){
        short t = 0;
        if ((int) c >= 128){
            t+=8;
            c-=128;
        }
        if ((int) c >= 64){
            t+=4;
            c-=64;
        }
        if ((int) c >= 32){
            t+=2;
            c-=32;
        }
        if ((int) c >= 16){
            t+=1;
        }
        return t;
    }
    static short lastFourBitASCIIMask(char c){
        short t = 0;
        if ((int) c >= 128){
            c-=128;
        }
        if ((int) c >= 64){
            c-=64;
        }
        if ((int) c >= 32){
            c-=32;
        }
        if ((int) c >= 16){
            c-=16;
        }
        if ((int) c >= 8){
            t+=8;
            c-=8;
        }
        if ((int) c >= 4){
            t+=4;
            c-=4;
        }
        if ((int) c >= 2){
            t+=2;
            c-=2;
        }
        if ((int) c >= 1){
            t+=1;
        }
        return t;
    }
}

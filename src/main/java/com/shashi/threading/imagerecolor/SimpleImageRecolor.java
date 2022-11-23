package com.shashi.threading.imagerecolor;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class SimpleImageRecolor {

    public static void changePixelColor(BufferedImage srcImg, BufferedImage destImg, int w, int h,
            int x, int y) throws IOException {

        for (int i = x; i < w + x; i++) {
            for (int j = y; j < h + y; j++) {
                int rgb = srcImg.getRGB(i, j);
                if (isShadeOfGray(rgb)) {
                    setPixelRgb(destImg, i, j, 0xFF0000FF);
                } else {
                    setPixelRgb(destImg, i, j, rgb);
                }
            }
        }
    }

    public static void createNewImage(BufferedImage srcImg, BufferedImage destImg, int w, int h)
            throws IOException {
        changePixelColor(srcImg, destImg, w, h, 0, 0);
    }

    public static void setPixelRgb(BufferedImage destImg, int i, int j, int rgb) {
        destImg.getRaster().setDataElements(i, j,
                destImg.getColorModel().getDataElements(rgb, null));
    }

    public static boolean isShadeOfGray(int rgb) {
        int b = getColorBlue(rgb);
        int g = getColorGrean(rgb);
        int r = getColorRed(rgb);

        return Math.abs(r - b) < 30 && Math.abs(r - g) < 30 && Math.abs(g - b) < 30;
    }

    public static int getColorBlue(int rgb) {
        return rgb & 0x000000FF;
    }

    public static int getColorGrean(int rgb) {
        return (rgb & 0x0000FF00) >> 8;
    }

    public static int getColorRed(int rgb) {
        return (rgb & 0x00FF0000) >> 16;
    }

    public static int getRGB(int blue, int green, int red) {
        int rgb = 0;

        rgb |= blue;
        rgb |= green << 8;
        rgb |= red << 16;
        rgb |= 0xFF000000; // assign the ful transparency value
        return rgb;
    }

}

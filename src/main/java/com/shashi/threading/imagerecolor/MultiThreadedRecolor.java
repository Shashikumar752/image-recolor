package com.shashi.threading.imagerecolor;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MultiThreadedRecolor {

    public static void multiThreadedImage(BufferedImage srcImg, BufferedImage destImg, int w, int h,
            int x, int y, int t) {
        List<Thread> tList = new ArrayList<>();
        int nh = h / t;
        for (int i = 0; i < t; i++) {
            int m = nh * i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        SimpleImageRecolor.changePixelColor(srcImg, destImg, w, nh, 0, m);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
            tList.add(thread);
        }
        for (Thread th : tList) {
            th.start();
        }
        for (Thread th : tList) {
            try {
                th.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}

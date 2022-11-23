package com.shashi.threading.imagerecolor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageRecolorApplication {

	public static final String SOURCE_FILE =
			"/C:/Users/shash/code/corejava/threading/image-recolor-latency/src/main/resources/flower.jpg";

	public static final String DEST_FILE_SINGLE_THREAD =
			"/C:/Users/shash/code/corejava/threading/image-recolor-latency/src/main/resources/changed-color-flower.jpg";
	public static final String DEST_FILE_MULTI_THREAD =
			"/C:/Users/shash/code/corejava/threading/image-recolor-latency/src/main/resources/changed-color-flower-multi.jpg";

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ImageRecolorApplication.class, args);

		File file = new File(SOURCE_FILE);
		BufferedImage srcImg = ImageIO.read(file);

		int w = srcImg.getWidth();
		int h = srcImg.getHeight();
		BufferedImage destImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

		long start2 = System.currentTimeMillis();
		MultiThreadedRecolor.multiThreadedImage(srcImg, destImg, w, h, 0, 0, 8);
		long end2 = System.currentTimeMillis();
		System.out.println("multi thread processing time: " + (end2 - start2));
		ImageIO.write(destImg, "jpg", new File(DEST_FILE_MULTI_THREAD));
		long start1 = System.currentTimeMillis();
		SimpleImageRecolor.changePixelColor(srcImg, destImg, w, h, 0, 0);
		long end1 = System.currentTimeMillis();
		System.out.println("single thread processing time: " + (end1 - start1));
		ImageIO.write(destImg, "jpg", new File(DEST_FILE_SINGLE_THREAD));
	}

}

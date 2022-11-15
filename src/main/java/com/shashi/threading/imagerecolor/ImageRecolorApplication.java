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
			"/C:/Users/shash/code/corejava/image-recolor/src/main/resources/flower.jpg";

	public static final String DEST_FILE =
			"/C:/Users/shash/code/corejava/image-recolor/src/main/resources/changed-color-flower.jpg";

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ImageRecolorApplication.class, args);

		File file = new File(SOURCE_FILE);
		BufferedImage srcImg = ImageIO.read(file);

		int w = srcImg.getWidth();
		int h = srcImg.getHeight();
		BufferedImage destImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

		SimpleImageRecolor.changePixelColor(srcImg, destImg, w, h);

		ImageIO.write(destImg, "jpg", new File(DEST_FILE));
	}

}

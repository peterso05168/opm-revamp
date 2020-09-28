package com.opm.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;

public class ImageUtil {
	public static byte[] resize(byte[] inputFileByteArray)
            throws IOException {
		double width = 300;
		double height = 300;
		
		 // reads input image
        File inputFile = new File("temp1");
        OutputStream os = new FileOutputStream(inputFile);
        os.write(inputFileByteArray);
        os.close();
        
        BufferedImage image = ImageIO.read(inputFile);
        double ratioW = image.getWidth() / width;
        double ratioH = image.getHeight() / height;
        double newWidth = width, newHeight = height;
        int fitW = 0, fitH = 0;
        BufferedImage resultImage;
        Image resize;

        //padding width
        if (ratioW < ratioH) {
            newWidth = image.getWidth() / ratioH;
            newHeight = image.getHeight() / ratioH;
            fitW = (int) ((width - newWidth) / 2.0);

        }//padding height
        else if (ratioH < ratioW) {
            newWidth = image.getWidth() / ratioW;
            newHeight = image.getHeight() / ratioW;
            fitH = (int) ((height - newHeight) / 2.0);
        }

        resize = image.getScaledInstance((int) newWidth, (int) newHeight, Image.SCALE_SMOOTH);
        resultImage = new BufferedImage((int) width, (int) height, image.getType());
        Graphics g = resultImage.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, (int) width, (int) height);
        g.drawImage(resize, fitW, fitH, null);
        g.dispose();
        
        File outputFile = new File("temp2");
        ImageIO.write(resultImage, "jpg", outputFile);
	    return Files.readAllBytes(outputFile.toPath());
    }
}
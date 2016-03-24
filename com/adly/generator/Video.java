package com.adly.generator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;

public class Video {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String fileSource="/Users/xiangao/Desktop/QR-code/Videos/demo.mp4";
		try {
			for(int i=10;i<101;i+=10){
				getSingleFrame(fileSource,i);
			}
			
		} catch (IOException | JCodecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	public static void getSingleFrame(String fileSource,int frameNumber) throws IOException, JCodecException{
		
		BufferedImage frame = FrameGrab.getFrame(new File("/Users/xiangao/Desktop/QR-code/Videos/demo.mp4"), frameNumber);
		ImageIO.write(frame, "png", new File("/Users/xiangao/Desktop/QR-code/FromVideo/"+frameNumber+".png"));
		
	}

}

package com.adly.generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.detector.Detector;

public class ImageDetector {
	static final String fileAddress="/Users/xiangao/Desktop/QR-code/Photos/QR3.jpg";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ImageDetector imageDetector=new ImageDetector();
		
			
			String[] filetypes = {"png","jpg","JPEG","jpeg","JPG","PNG"};
			Iterator<File> it=FileUtils.iterateFiles(new File("/Users/xiangao/Desktop/QR-code/Photos/"), filetypes, true);
			while(it.hasNext()){
				File f= it.next();
				String name=f.getName();
				String path=f.getPath();
				it.remove();
				System.out.println(name);
				
				String result;
				
					try {
						result = imageDetector.detectImage(path);
						System.out.println(result);
						TxtWriter.writeTxt(result,name+".txt");
					} catch (NotFoundException | FormatException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println();
					}

				
			}
		
			
			
			
			
			
			
			
			
			
	
		

	}
	public String detectImage(String filePath) throws FileNotFoundException, IOException, NotFoundException, FormatException{
		String result="";
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
		new BufferedImageLuminanceSource(
		ImageIO.read(new FileInputStream(filePath)))));
		BitMatrix bitMatrixAfterDetection = getResultAfterDectection_597(binaryBitmap);
        result= bitMatrixAfterDetection.toString();

        return result;

		
	}
	  public BitMatrix getResultAfterDectection_597(BinaryBitmap image) throws NotFoundException, FormatException {
	        DetectorResult result = (new Detector(image.getBlackMatrix())).detect(null);
	        BitMatrix bitMatrix=result.getBits();
	        return  bitMatrix;
	        

	    }

}

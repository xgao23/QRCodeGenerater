package com.adly.generator;
 
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
 
public class GenerateQRCode {
 
    /**
     * @param args
     * @throws WriterException
     * @throws IOException
     */
    public static void main(String[] args) throws WriterException, IOException {
        String qrCodeText = "Lorem Ipsum is simply dummy text of the printing and typesetting"
        		+ " industry. Lorem Ipsum has been the industry's stand dummy text of"
        		+ " dummy text of the printing and typesetting dummy text of the printing and typesetting"
        		+ " industry. Lorem Ipsum has been the industry's stand the printing and typesetting"
        		+ " industry. Lorem Ipsum has been the industry's stand";
        String filePath = "/Users/xiangao/Desktop/QR3.png";
        int size =0;
        String fileType = "png";
        File qrFile = new File(filePath);
        createQRImage(qrFile, qrCodeText, size, fileType);
        System.out.println("DONE");
    }
 
    private static void createQRImage(File qrFile, String qrCodeText, int size,
            String fileType) throws WriterException, IOException {
        // Create the ByteMatrix for the QR-Code that encodes the given String
        Hashtable hintMap = new Hashtable();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText,
                BarcodeFormat.QR_CODE, size, size, hintMap);
        
        
        
        String byteString =byteMatrix.toString();
        System.out.println(byteString);
        generateQRCodeStringMatrix("QR_TEXT",byteString);
        
        
        
        
        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth,
                BufferedImage.TYPE_INT_RGB);
        image.createGraphics();
 
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // Paint and save the image using the ByteMatrix
        graphics.setColor(Color.BLACK);
 
        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        ImageIO.write(image, fileType, qrFile);
    }
    
    
	public static void generateQRCodeStringMatrix(String sFileName, String sBody){
		try
		{
			File root = new File("/Users/xiangao/Desktop/","QR_Template");
			if (!root.exists()) {
				root.mkdirs();
			}
			File gpxfile = new File(root, sFileName);
			FileWriter writer = new FileWriter(gpxfile);
			writer.append(sBody);
			writer.flush();
			writer.close();
			
		}
		catch(IOException e)
		{
			e.printStackTrace();

		}
		
	}
    
    
 
}
package com.adly.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

public class TxtWriter {
	public static void writeTxt(String str,String fileName){
        try {

            File newTextFile = new File("/Users/xiangao/Desktop/QR-code/FromPhoto/"+fileName);

            FileWriter fw = new FileWriter(newTextFile);
            fw.write(str);
            fw.close();

        } catch (IOException iox) {
            //do stuff with exception
            iox.printStackTrace();
        }
	}

}
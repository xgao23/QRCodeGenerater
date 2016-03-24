package com.adly.generator;
import java.io.File;  
import java.io.IOException;
import java.io.InputStreamReader;  
import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.FileInputStream;  
import java.io.FileWriter;  
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
  
public class QRCodeComparer {  
	
	public static void main(String args[]) {  
        try {
			char matrix[][]=get2DMartrix("/Users/xiangao/Desktop/QR_Template/QR1.txt");			
			String[] filetypes = {"txt"};
			Iterator<File> it=FileUtils.iterateFiles(new File("/Users/xiangao/Desktop/QR-code/"), filetypes, true);
			while(it.hasNext()){
				File f= it.next();
				String name=f.getName();
				String path=f.getPath();
				it.remove();
				System.out.println(name);
				char matrix2[][]=get2DMartrix(path);
				compareMatrix(matrix, matrix2);
				System.out.println("--------------------------------------------");			
			}
			
			
			
			
			
			
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
        
       
        
    }
	private static char[][] get2DMartrix(String pathname) throws IOException {
 
//            String pathname = "/Users/xiangao/Desktop/QR-code/Template.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径  
            File filename = new File(pathname); // 要读取以上路径的input。txt文件  
            InputStreamReader reader = new InputStreamReader(  
                    new FileInputStream(filename)); // 建立一个输入流对象reader  
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
            String line = "";  
            line = br.readLine();  
            int length=line.length()/2;      
            char matrix[][]=new char[length][length];

            int row=0;
            while (row<length){  
                
                for(int column=0;column<length;column++){
                	matrix[row][column]=line.charAt(2*column);
                }
                
                line = br.readLine(); // 一次读入一行数据  
                if(line == null)
                	break;

                row++;
            } 

      
		return matrix;

	}
	public static void print2DMatrix(char[][] arr ){
		int length=arr[0].length;
		for(int i=0;i<length;i++)
			{
				for(int j=0;j<length;j++){
					System.out.print(arr[i][j]);
				}
				System.out.println();
			}	
	}
	
	public static void compareMatrix(char[][] a, char[][] b){
		float blackToWhite=0;
		float whiteToBlack=0;
		int length=a.length;
		float dimension=(length+1)*(length+1);
		int blength=b.length;
		if(length!=blength)
			System.out.println("Two matrix are not in the same dimension");
		System.out.println();
		for(int i=0;i<length;i++)
		{
			for(int j=0;j<length;j++){
				
				if(a[i][j]=='X'&&b[i][j]==' '){
					blackToWhite++;
					System.out.println("point("+(i+1)+","+(j+1)+") B/W");
				}else if(a[i][j]==' '&&b[i][j]=='X'){
					whiteToBlack++;
					System.out.println("point("+(i+1)+","+(j+1)+") W/B");
					
				}			
			}
		}
		
		System.out.println("Black to White Num "+blackToWhite+ ", Rate "+(blackToWhite/dimension)*100+"%" );
		System.out.println("White to Black Num "+whiteToBlack+ ", Rate " +(whiteToBlack/dimension)*100+"%" );
		System.out.println("Total Mutation Rate "+((whiteToBlack+blackToWhite)/dimension)*100+"%");
	
	}
	
	
}
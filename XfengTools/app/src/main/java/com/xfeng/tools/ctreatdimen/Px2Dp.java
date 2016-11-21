package com.xfeng.tools.ctreatdimen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;

/**
 * @User:        xuyuqiang
 * @Version:     1.0.0
 * @ClassName:   Px2Dp
 * @CreateTime:  2016/11/16 下午2:12
 * @Description: 生成dimen文件 格式为:<dimen name="dimen_1px">1.5dp</dimen>
 */
public class Px2Dp {
	
	public static final int BASE_DPI = 160 ;

	public static void main(String[] args) {
		 float density_xxhdpi = 480.0f;  
	     float density_xhdpi = 320.0f;  
	     float density_hdpi = 240.0f;  
	     createDimen(density_xxhdpi,"xxhdpi_dimens.xml");  
	     createDimen(density_xhdpi,"xhdpi_dimens.xml");  
	     createDimen(density_hdpi,"hdpi_dimens.xml");  
	}
	
	
	public static void createDimen(float desity , String fileName){
		
		File file = new File(fileName) ;
		FileWriter fileWriter = null ;
		BufferedWriter buffWritter = null ;
		
		try{
			
			if(!file.exists()){
				file.createNewFile() ;
				fileWriter = new FileWriter(file) ;
				buffWritter = new BufferedWriter(fileWriter) ;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for(int px = 1 ; px < 2000 ; px ++){
			float dp = px / (desity / BASE_DPI) ;
			BigDecimal bd = new BigDecimal(Double.parseDouble(String.valueOf(dp))) ;
			String value = "<dimen name=\"" + px + "px" + "\">" + bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()  
                    + "dp</dimen>" + "\n" ;
			try{
				buffWritter.write(value);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		try{
			buffWritter.flush();
			buffWritter.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

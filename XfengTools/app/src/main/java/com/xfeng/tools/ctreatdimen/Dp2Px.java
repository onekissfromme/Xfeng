package com.xfeng.tools.ctreatdimen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;

/**
 * @User:        xuyuqiang
 * @Version:     1.0.0
 * @ClassName:   Dp2Px
 * @CreateTime:  2016/11/16 下午2:10
 * @Description: 生成dimen 文件 格式为: <dimen name="dimen_1dp">1.5px</dimen>
 */
public class Dp2Px {

	private static final int BASE_DPI = 160 ; //标准密度
	private static final int BASE_WIDTH = 1920 ; //设计的标准宽度
	
	private static final float DPWIDTH3 = 1920f ; //三代板的DP宽度
	private static final float DPWIDTH2 = 1280f ; //二代板的DP宽度
	
	private static final float DENSITY_MDPI = 160F ;    //mdpi
	private static final float density_hdpi = 240.0f ;  //三代板密度
	private static final float density_216 = 216.0f ;	//二代板谜底
	private static final float DENSITY_XHDPI = 320.0f ; //
	
	public static void main(String[] args) {

		createDimen(density_216, "dimens_216.xml", DPWIDTH2);
		createDimen(density_hdpi, "dimens_hdpi.xml", DPWIDTH3);
		createDimen(DENSITY_MDPI, "dimens_mdpi.xml", DPWIDTH3);
	}

	private static void createDimen(float desity , String fileName , float dpWidth){
		
		File file = new File(fileName) ;
		FileWriter fileWriter = null ;
		BufferedWriter buffWriter = null;
		
		try{
			if(!file.exists()){
				file.createNewFile() ;
				fileWriter = new FileWriter(file) ;
				buffWriter = new BufferedWriter(fileWriter) ;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for (int dp = 1 ; dp < 2000 ; dp ++){
			float px = dp*(desity / BASE_DPI) * (dpWidth / BASE_WIDTH) ;
			BigDecimal bd = new BigDecimal(Double.parseDouble(String.valueOf(px))) ;
			String value =  "\t" + "<dimen name=\"" + "dimen_" + dp + "dp" + "\">" + bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()  
                    + "px</dimen>" + "\n" ;
			try{
				if(dp == 1){
					buffWriter.write("<resources>" + "\n") ;
				}
				buffWriter.write(value);
				if(dp == 1999){
					buffWriter.write("</resources>") ;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		try{
			buffWriter.flush();
			buffWriter.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

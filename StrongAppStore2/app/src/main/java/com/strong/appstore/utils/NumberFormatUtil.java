package com.strong.appstore.utils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * 描述:
 *
 * @author  Administrator
 * @created 2014-10-14 下午8:28:02
 * @since   v1.0.0
 */
public class NumberFormatUtil {

	/**
	 *	
	 * 描述:如果数字为17.0则返回17，如果数字为17.2则返回17.2
	 *
	 * @author  Administrator
	 * @created 2014-10-14 下午8:33:34
	 * @since   v1.0.0 
	 * @param point f
	 * @return
	 * @return  String
	 */
	public static String getScorePoint(float point) {
		String score = point + "";
		Pattern pattern = Pattern.compile("^-?[1-9]\\d*$");
		Matcher matcher = pattern.matcher(score);
		if (matcher.find()) {
			score = String.valueOf(((int) point));
		} else {
			score = new DecimalFormat("###.#").format(point);
		}
		return score;
	}

	public static String getScorePoint(double point) {
		return getScorePoint((float) point);
	}
	

    public static int buildRandom(int length) {
         int num = 1;
         double random = Math.random();
         if (random < 0.1) {
              random = random + 0.1;
         } 
         for (int i = 0; i < length; i++) {
              num = num * 10;
         }
         return (int) ((random * num));
  }
}

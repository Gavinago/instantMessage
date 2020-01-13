package com.instant.utils.wrapper;

/**
   * 特殊字符过滤
 * @author Administrator
 *
 */
public class SpecharsWrapper  {
	
		/**
		 * 	转义字符转为空值
		 * @author grl
		 * @param str
		 * @return
		 */
	   public static String conventspechars(String str) {
	    	str = str.replaceAll("&amp;", "")
			.replaceAll("&lt;", "")
			.replaceAll("&gt;", "")
			.replaceAll("&quot;", "")
			.replaceAll("&#39;", "")
			.replaceAll("&#37;", "")
			.replaceAll("&#45;", "")
			.replaceAll("&#95;", "");
	    	return str;
	    }
	   /**
	    * 	反转义字符
	    * @author grl
	    * @param str
	    * @return
	    */
	   public static String reconventspechars(String str) {
		   str = str
			.replaceAll("&amp;", "&")
			.replaceAll("&lt;", "<")
			.replaceAll("&gt;", ">")
			.replaceAll("&quot;", "\"")
		//	.replaceAll("&#39;", "'")
			.replaceAll("&#37;", "%")
			.replaceAll("&#45;", "-")
			.replaceAll("&#95;", "_")
			.replaceAll("\n", "&lt;br/&gt;")
			.replaceAll("\r", "&lt;br/&gt;");
	        return str;
	   }
}

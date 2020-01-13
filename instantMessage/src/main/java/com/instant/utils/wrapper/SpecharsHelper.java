package com.instant.utils.wrapper;

public class SpecharsHelper {

	/**
	 * 1.保存
	 * @param str 输入字符串参数
	 * @return 转换后字符串
	 */
	public String encryptionConvert(String str) {
		StringBuffer strbuff = new StringBuffer();
		int strlen = str.length();
		for(int i=0;i<strlen;i++) {
			char charAtStr = str.charAt(i);
			switch (charAtStr) {
				case '&':  strbuff.append("&amp;");   break;
				case '<':  strbuff.append("&lt;");    break;
				case '>':  strbuff.append("&gt;");    break;
				case '\"': strbuff.append("&quot;");  break;
				case '\'': strbuff.append("&#39;");   break;
				case '%':  strbuff.append("&#37;");   break;
				case '-':  strbuff.append("&#45;");   break;
				case '_':  strbuff.append("&#95;");   break;
				default:   strbuff.append(charAtStr); break;
			}
		}
		return strbuff.toString();
	}
	/**
	 * 1.获取
	 * @param str 输入字符串参数
	 * @return 转换后字符串
	 */
	// 获取数据转码输出
	public String decodeConvert(String str) {
		String newStr = "";
		newStr = str.replaceAll("&amp;","&"); 
		newStr = newStr.replaceAll("&lt;","<");
		newStr = newStr.replaceAll("&gt;",">");
		newStr = newStr.replaceAll("&quot;","\""); 
		newStr = newStr.replaceAll("&#39;","\'");
		newStr = newStr.replaceAll("&#37;","%"); 
		newStr = newStr.replaceAll("&#45;","-"); 
		newStr = newStr.replaceAll("&#95;","_");
		return newStr;
	}
}

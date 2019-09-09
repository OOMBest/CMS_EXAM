package cn.com.zhangdake.utils;

/**
 * Author: ZhangDaKe
 * Date: 2019年9月6日
 * Describe: 
 * Version: 
 * 
 * 1.0
 * isEmpty(CharSequence sequence) 判断字符序列是否为空 
 * isEmptyOrSpaces(String text) 判断String是否为空 或 全是空格
 */
public final class TextUtils {

	private TextUtils() {
		throw new IllegalStateException("this class can`t be created by constructor");
	}
	
	/**
	 * 判断字符序列是否为空 
	 * 
	 * @param sequence 字符序列
	 * @return true空  false非空
	 */
	public static boolean isEmpty(CharSequence sequence) {
		if (sequence == null || sequence.length() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断String是否为空 或 全是空格
	 * 
	 * @param sequence
	 * @return
	 */
	public static boolean isEmptyOrSpaces(String text) {
		return isEmpty(text) ? true : text.trim().length() == 0;
	}
	
}



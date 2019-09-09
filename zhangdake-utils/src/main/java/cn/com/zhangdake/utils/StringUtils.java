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
public final class StringUtils {

	private static final String PHONE_PATTERN = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17([0,1,6,7,]))|(18[0-2,5-9]))\\d{8}$";
	private static final String EMAIL_PATTERN = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	
	private StringUtils() {
		throw new IllegalStateException("this class can`t be created by constructor");
	}

	/**
	 * 判断字符序列是否为空
	 * 
	 * @param sequence 字符序列
	 * @return true空 false非空
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

	/**
	 * 将字符串转换成html文本，如果遇到“\n”换行换符，则要将这一段文本使用
	 * <p>
	 * </p>
	 * 标签包起来。如果遇到“\n\r”两个在一起按上面\n处理。如果只遇到一个“\r”则替换成<br/>
	 * 标签。
	 * 
	 * @param text 要替换的文本
	 * @return 替换后的文本
	 */
	public static String toHtml(String text) {
		
		if (isEmpty(text)) {
			return "empty text";
		}
		
		System.out.println("before replace : " + text);
		StringBuilder sb = new StringBuilder(text.length());

		//替换\r\n
		String[] arr = text.split("\r\n");
		for (int i = 0; i < arr.length; i++) {
			if (!isEmptyOrSpaces(arr[i])) {
				sb.append("<p>").append(arr[i]).append("</p>");
			}
		}

		//替换\n
		arr = sb.toString().split("\n");
		if (arr.length > 1) {
			clear(sb);
			for (int i = 0; i < arr.length; i++) {
				sb.append("<p>").append(arr[i]).append("</p>");
			}
		}

		//替换\r
		arr = sb.toString().split("\r");
		if (arr.length > 1) {
			clear(sb);
			for (int i = 0; i < arr.length; i++) {
				sb.append(arr[i]).append("<br/>");
			}
		}
		
		System.out.println("after replace : " + sb.toString());
		return sb.toString();
	}

	/**
	 * 判断是否为中国地区手机号码、
	 * 
	 * @param phoneNum
	 * @return
	 */
	public static boolean isPhone(String phoneNum) {
		if (isEmptyOrSpaces(phoneNum)) {
			return false;
		}
		return phoneNum.matches(PHONE_PATTERN);
	}
	
	/**
	 * 是否为邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (isEmptyOrSpaces(email)) {
			return false;
		}
		return email.matches(EMAIL_PATTERN);
	}
	
	/**
	 * 清空StringBuilder中的数据
	 * 
	 * @param builder
	 */
	private static void clear(StringBuilder builder) {
		builder.delete(0, builder.length());
	}
	
}

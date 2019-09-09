package cn.com.zhangdake.utils;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @Author: ZhangDaKe
 * @Date: 2019年9月5日
 * @Describe:
 * @Version: 1.0
 */
public class TestClass {

	// 方法1：判断源字符串是否有值，空引号(空白字符串)也算没值(5分)
	public static boolean hasLength(String src) {
		return !isEmpty(src);
	}

	// 方法2：判断源字符串是否有值，空引号(空白字符串)和空格也算没值(5分)
	public static boolean hasText(String src) {
		return isEmpty(src) ? src.trim().length() > 0 : false;
	}

	private static boolean isEmpty(CharSequence sequence) {
		if (sequence == null || sequence.length() == 0) {
			return true;
		}
		return false;
	}

	// 方法3：返回参数length个中文汉字字符串，字符集必须在GB2312(相当于中文简体)范围内，例如“中呀被”(5分)
	public static String randomChineseString(int length) {
		// Unicode中汉字所占区域\u4e00-\u9fa5,将4e00和9fa5转为10进制
		int start = Integer.parseInt("4e00", 16);
		int end = Integer.parseInt("9fa5", 16);

		Random random = new Random();

		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			sb.append((char) (random.nextInt(end - start + 1) + start));
		}

		return sb.toString();
	}

	// 方法4：返回中文姓名，必须以真实姓开头，百家姓在“六、附百家姓”里，名使用1-2个随机汉字(8分)，内部调用randomChineseString()方法(3分)。返回示例：“刘呀被”、“欧阳及为”
	public static String generateChineseName() {
		return null;
	}

	public static String getChinese() {
		String str = null;
		int highPos, lowPos;
		Random random = new Random();
		highPos = (176 + Math.abs(random.nextInt(40)));// 区码，0xA0打头，从第16区开始，即0xB0=11*16=176,16~55一级汉字，56~87二级汉字
		random = new Random();
		lowPos = 161 + Math.abs(random.nextInt(94));// 位码，0xA0打头，范围第1~94列

		byte[] bArr = new byte[2];
		bArr[0] = (new Integer(highPos)).byteValue();
		bArr[1] = (new Integer(lowPos)).byteValue();
		try {
			str = new String(bArr, "GB2312"); // 区位码组合成汉字
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

}

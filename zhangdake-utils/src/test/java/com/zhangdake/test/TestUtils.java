package com.zhangdake.test;

import org.junit.Test;

import cn.com.zhangdake.utils.StringUtils;

/**
 * Author: ZhangDaKe
 * Date: 2019年9月9日
 * Describe: 
 * Version: 1.0
 */
public class TestUtils {

	/**
	 * 测试toHtml方法
	 */
	@Test
	public void testToHtml() {
		String text = "9月5日一大早，又有了新情况。\r\n" + 
				"上午9点多，传出中美经贸高级别磋商牵头人通话的消息。\r\n" + 
				"9月5日上午，中共中央政治局委员、国务院副总理、中美全面经济对话中方牵头人刘鹤应约与美国贸\r\n" + 
				"\r\n" + 
				"易代表莱特希泽、财政部长姆努钦通话。";
		
		StringUtils.toHtml(text);
	}
	
	/**
	 * 测试isPhone方法
	 */
	@Test
	public void testIsPhone() {
		String testPhone = "15164624901";
		boolean f = StringUtils.isPhone(testPhone);
		System.out.println(f ? "手机号正确 " : "手机号错误");
	}
	
	/**
	 * 测试isEmail方法
	 */
	@Test
	public void textIsEmail() {
		String testEmail = "273092218@qq.com";
		boolean f = StringUtils.isEmail(testEmail);
		System.out.println(f ? "邮箱正确 " : "邮箱错误");
	}
	
}



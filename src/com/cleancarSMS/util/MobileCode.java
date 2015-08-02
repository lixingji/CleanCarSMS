package com.cleancarSMS.util;

import java.util.Random;

public class MobileCode {

	/**
	 * 产生随机字符串
	 */
	private static Random randGen = null;
	private static char[] numbersAndLetters = null;

	public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			randGen = new Random();
			numbersAndLetters = ("123456789" + "0123456789" + "0123456789")
					.toCharArray();
			// numbersAndLetters =
			// ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(12)];
		}
		return new String(randBuffer);//"您的验证码是："+randBuffer+"。请不要把验证码泄露给其他人。"
	}

	// 测试
	public static void main(String[] args) {
		System.out.println(randomString(6));
	}
}

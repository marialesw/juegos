package models;

import java.util.Random;

public class MyNumber {

	private int number;

	public MyNumber() {

	}

	public MyNumber(int number) {
		this.number = number;
	}

	/**
	 * generate random N dijits whitout duplicates
	 * @param digits lenght
	 * @return number...
	 */

	public int generate(int digits) {
		Random random = new Random();
		int number = 0;
		do {
			number = (int)Math.pow(10, digits-1) +
					random.nextInt((int)Math.pow(10, digits -1));
		}while(isDijitDuplicate(number, digits));
		this.number = number;
		return number;
	}

	public boolean isDijitDuplicate(int number, int digits) {
		String num = String.valueOf(number);
		if (num.length() < digits) {
			num = "0" + num;
		}
		for (int i = 0; i < num.length() - 1; i++) {
			for (int j = i+1; j < num.length(); j++) {
				if (num.charAt(i) == num.charAt(j) ) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isNumberOfDigit(int number, int digit) {
		int count = 0;
		while(number > 0) {
			count  ++;
			number = number / 10;
		}
		if (count == digit) {
			return true;
		}
		return false;
	}

	public int pikes(int number) {
		int count = 0;
		int number1 = this.number;
		int number2 = number;
		while(number1 > 0) {
			while(number2 >0) {
				count += ((number1 % 10) == number2 % 10)?1:0;
				number2 = number2 / 10;
			}
			number1 = number1 / 10;
			number2 = number;
		}
		return count;
	}

	public int fixes(int number) {
		int count = 0;
		int number1 = this.number;
		while(number1 > 0) {
			count += ((number1 % 10) == number % 10)?1 :0;
			number1 = number1 / 10;
			number = number / 10;
		}
		return count;
	}

	public int getNumber() {
		return number;
	}
	
	public int process(int number) {
		String num = String.valueOf(number);
		return num.length();
	}
}

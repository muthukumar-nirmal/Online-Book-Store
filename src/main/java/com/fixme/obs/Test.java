/**
 * 
 */
package com.fixme.obs;

import java.util.Scanner;

public class Test {
	static Float a, b; // This is class / instance variable

	public static void main(String arg[]) {
		Float c; // This is method variable 
		Scanner obj = new Scanner(System.in);
		System.out.println("Enter the value for A");
		a = obj.nextFloat();
		System.out.println("Enter the value for B");
		b = obj.nextFloat();
		c = a + b;
		// System.out.println("The answer for adding two number" +a);
		// System.out.println("The answer for adding two number" +b);
		System.out.println("The answer for adding two number(" + a + "+" + b
				+ ")" + "=" + c);
	}
}

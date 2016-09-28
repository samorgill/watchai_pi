package uk.ac.mmu.watchai.Controller;

import java.util.Scanner;

/**
 * @author Samuel Orgill 15118305
 * NW5 Smartwatch Control of Environment
 * September 2016
 * 
 * A Scanner class to make console logging & unit testing easier.
 */

public class EasyScanner {


	public static String nextString(){
		Scanner kb = new Scanner(System.in);
		String s = kb.nextLine();
		return s;
	}
	
	public static int nextInt(){
		Scanner kb = new Scanner(System.in);
		int i = kb.nextInt();
		return i;
	}
	
	public static double nextDouble(){
		Scanner kb = new Scanner(System.in);
		double d = kb.nextDouble();
		return d;
	}
	
	public static char nextChar()
	{
		Scanner sc = new Scanner(System.in);
		char c = sc.next().charAt(0);
		return c;
	}
	
}

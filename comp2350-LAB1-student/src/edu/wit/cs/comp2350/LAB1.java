package edu.wit.cs.comp2350;

/* Sorts integers from command line using various algorithms 
 * 
 * Wentworth Institute of Technology
 * COMP 2350
 * Programming Assignment 0
 * 
 */

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class LAB1 {

	public final static int MAX_INPUT = 524287;
	public final static int MIN_INPUT = 0;

	// TODO: document this method
	public static int[] countingSort(int[] a) {
		int greatest=0;
		
		//Finds the highest number in the array to make the size of the count array.
		for(int i=0; i<a.length; i++){
			if (a[i]>greatest){
				greatest=a[i];
			}
		}
		int[] count= new int[greatest+1];
		
		//Builds the count array incrementing at the index at the value of a[j] 
		for(int i=0; i<a.length; i++){
			count[a[i]]++;
		}
		int i=0;
		
		//Builds the output array based on the data in the count array
		for(int j=0; j<count.length; j++){
			while(count[j]>0){
				a[i]=j;
				count[j]--;
				i++;
				
			}
		}
		return a;
	}

	
	public static int[] radixSort(int[] a) {
		int mostDigits=0;
		int curDigits=0;
		
		//Finds the max number of digits in the array
		for(int i=0; i<a.length; i++){
			curDigits=(int)(Math.log10(a[i])+1);
			if (curDigits>mostDigits){
				mostDigits= curDigits;
			}
		}
		int [] count = new int[10];
		int [] pos = new int[10];
		int [] output = new int[a.length];
		int outplace=0;
		
		//Loops over for all digits in the array
		for(int i=0; i<mostDigits; i++){
			
		//Sets and resets the pos and count arrays
			for(int j=0; j<=9; j++){
				pos[j]=0;
				count[j]=0;
			}
			
			//Builds the count array, for the "a" array
			for(int j=0; j< a.length; j++){
				if((double)a[j]/(Math.pow(10.0, (double)i))<0){
					count[0]++;
				} else{
					count[a[j]%(int)(Math.pow(10.0, (double)i+1.0))/(int)Math.pow(10.0, (double)i)]++;
				}
			}
			
			//Creates the pos array based off the count array.
			for(int j=0; j<=9; j++){
				if(j>0){
					pos[j]=count[j-1]+pos[j-1];
				}
			}
			for(int j=0; j<a.length;j++){
				
				//Sets the index to be a which is the position held in the output array
				outplace=pos[a[j]%(int)(Math.pow(10.0, (double)i+1.0))/(int)Math.pow(10.0, (double)i)];
				
				//Adds the entry to the output array
				output[outplace]=a[j];
				
				//increments the pos array after the output array has been added to
				pos[a[j]%(int)(Math.pow(10.0, (double)i+1.0))/(int)Math.pow(10.0, (double)i)]++;
				
			}
			
			//a becomes the output array to be used in the next iteration
			for(int j=0; j<a.length; j++){
				a[j]=output[j];
			}
			
		}
		return a;
	}

	/********************************************
	 * 
	 * You shouldn't modify anything past here
	 * 
	********************************************/
	
	// example sorting algorithm
	public static int[] insertionSort(int[] a) {

		for (int i = 1; i < a.length; i++) {
			int tmp = a[i];
			int j;
			for (j = i-1; j >= 0 && tmp < a[j]; j--)
				a[j+1] = a[j];
			a[j+1] = tmp;
		}
		
		return a;
	}

	/* Implementation note: The sorting algorithm is a Dual-Pivot Quicksort by Vladimir Yaroslavskiy,
	 *  Jon Bentley, and Joshua Bloch. This algorithm offers O(n log(n)) performance on many data 
	 *  sets that cause other quicksorts to degrade to quadratic performance, and is typically 
	 *  faster than traditional (one-pivot) Quicksort implementations. */
	public static int[] systemSort(int[] a) {
		Arrays.sort(a);
		return a;
	}

	// read ints from a Scanner
	// returns an array of the ints read
	private static int[] getInts(Scanner s) {
		ArrayList<Integer> a = new ArrayList<Integer>();

		while (s.hasNextInt()) {
			int i = s.nextInt();
			if ((i <= MAX_INPUT) && (i >= MIN_INPUT))
				a.add(i);
		}

		return toIntArray(a);
	}

	// copies an ArrayList to an array
	private static int[] toIntArray(ArrayList<Integer> a) {
		int[] ret = new int[a.size()];
		for(int i = 0; i < ret.length; i++)
			ret[i] = a.get(i);
		return ret;
	}

	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.printf("Enter the sorting algorithm to use ([c]ounting, [r]adix, [i]nsertion, or [s]ystem): ");
		char algo = s.next().charAt(0);
		
		System.out.printf("Enter the integers that you would like sorted: ");
		int[] unsorted_values = getInts(s);
		int[] sorted_values = {};

		s.close();

		switch (algo) {
		case 'c':
			sorted_values = countingSort(unsorted_values);
			break;
		case 'r':
			sorted_values = radixSort(unsorted_values);
			break;
		case 'i':
			sorted_values = insertionSort(unsorted_values);
			break;
		case 's':
			sorted_values = systemSort(unsorted_values);
			break;
		default:
			System.out.println("Invalid sorting algorithm");
			System.exit(0);
			break;
		}
		
		System.out.println(Arrays.toString(sorted_values));
		
	}

}

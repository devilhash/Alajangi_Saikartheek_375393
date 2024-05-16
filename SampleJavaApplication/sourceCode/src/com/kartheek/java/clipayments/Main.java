package com.kartheek.java.clipayments;

public class Main {

	public static void main(String[] args) {
		 int arr[] =  {3, 2, 4, 1, 10, 30, 40, 20};
	      int n = arr.length;
//	      System.out.print(secSmallest(arr, n)); 
//	      System.out.print(secHighest(arr, n)); 
//	      reverse(arr,0,arr.length-1);
	      reversehalf(arr);
	      
	      for(int i : arr) {
	    	  System.out.println(i);
	      }


	}

	private static  int secSmallest(int[] arr, int n) {
		 int smallest = arr[0];
		 for(int i = 0 ; i < arr.length ; i++) {
			 if(arr[i] < smallest) {
				 smallest = arr[i];
			 }
		 }
		 int secSmallest = Integer.MAX_VALUE;
		 for(int i = 0 ; i < arr.length ; i++) {
			 if(arr[i]!=smallest && arr[i]<secSmallest) {
				 secSmallest = arr[i];
			 }
		 }
		 return secSmallest;
	}
	private static int secHighest(int[] arr , int n) {
		int highest = Integer.MIN_VALUE;
		int second = Integer.MIN_VALUE;
		for(int i = 0 ; i < arr.length ; i ++) {
			if(arr[i]>highest) {
				second = highest;
				highest = arr[i];
			}
			else if(arr[i]>second) {
				second = arr[i];
			 
			}
		}
		return second;
	}
	private static void reverse(int[]arr,int start,int end) {
		if(start>=end)
			return;
		int temp = arr[start]+arr[end];
		arr[end] = temp-arr[end];
		arr[start] = temp-arr[start];
		
		reverse(arr,start+1,end-1);
	}
	private static void reversehalf(int[]arr) {
		for(int i = 0 ; i < arr.length /2-1; i++) {
			 for(int j=i+1; j<arr.length;j++) {
				 if(arr[i]>arr[j]) {
					 int temp = arr[i]+arr[j];
					 arr[i]  = temp-arr[i];
					 arr[j] = temp -arr[j];
				 }
			 }
		}
		for(int i = arr.length/2;i<arr.length-1;i++) {
			for(int j = i+1;j<arr.length;j++) {
				if(arr[i]<arr[j]) {
					int temp = arr[i]+arr[j];
					arr[i] = temp - arr[i];
					arr[j] = temp - arr[j];
				}
			}
		}
	}
	 

}

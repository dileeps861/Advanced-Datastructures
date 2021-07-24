// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class GFG
{
  public static void main(String args[])throws IOException
  {
    BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(read.readLine());
    while(t-- > 0)
    {
      String input_line1[] = read.readLine().trim().split("\\s+");
      int k = Integer.parseInt(input_line1[0]);
      int n = Integer.parseInt(input_line1[1]);
      String input_line[] = read.readLine().trim().split("\\s+");
      int arr[]= new int[n];
      for(int i = 0; i < n; i++)
        arr[i] = Integer.parseInt(input_line[i]);

      Solution ob = new Solution();
      System.out.println(ob.minTime(arr,n,k));
    }
  }
}

// } Driver Code Ends


//User function Template for Java

/**
3 5
5 10 30 20 15
**/

class Solution{
  static long minTime(int[] arr,int n,int k){
    //code here
    long[][] C = new long[n][n];
    for(long[] arrLoc: C){
      Arrays.fill(arrLoc, -1);
    }
    return minTime(arr,0, n, k, C);
  }
  private static long minTime(int[] arr,int i, int n,int k, long[][] C){
    //code here
    long max = Integer.MAX_VALUE;
    System.out.println(max+ " ß== "+ k);
    for(int j = i+1; j<n;j++){
      long sum = 0;
      if(k ==1) j = n-1;
      for(int l=i;l<j;l++){

        sum+= arr[l];
      }
      if(k>1) sum += minTime(arr,j+1, n, k-1, C);
      System.out.println(sum+ " == "+ k);
      if(sum < max){
        max = sum;
      }
      // if(C[i][j] == -1){
      //     long minLoc = minTime(arr,j+1, n, k-1, C);
      //     if (minLoc < min) {
      //         min = minLoc;
      //     }
      // }
      // else{
      //     if (C[i][j] < min) {
      //         min = C[i][j];
      //     }
      // }
    }
    return max;
  }
}



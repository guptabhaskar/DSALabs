/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{   
     public static int func(int[][] data,int n)
    {
        for(int i=1;i<n;i++)
            for(int j=0;j<=i;j++)
            {
                if(j==0)
                    data[i][j]+=data[i-1][j];
                else if(i==j)
                    data[i][j]+=data[i][j-1];
                else
                    data[i][j]+=Math.max(data[i][j-1],data[i-1][j]);
            }
        return data[n-1][n-1];
    }
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[][] data1 = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                data1[i][j] = input.nextInt();
        int[][] data2 =new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                data2[i][j] = data1[n-i-1][n-j-1];
        System.out.println(func(data1, n)+func(data2, n));
	}
}
/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{   
    public static int func(int[][] data,int sx,int sy,int ex,int ey)
    {
        int count=0;
        if(sx==ex && sy==ey)
            count=1;
        else if(data[sx][sy]==0)
            count=0;
        else if(sx<ex && sy<ey)
            count+=func(data,sx+1,sy,ex,ey)+func(data,sx,sy+1,ex,ey);
        else if(sx<ex && sy==ey)
            count+=func(data,sx+1,sy,ex,ey);
        else if(sx==ex && sy<ey)
            count+=func(data,sx,sy+1,ex,ey);
        else
            count=0;
        return count;
    }
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner input=new Scanner(System.in);
		int m=input.nextInt();
		int n=input.nextInt();
		int x=input.nextInt();
		int y=input.nextInt();
		int[][] data=new int[m][n]; 
		for(int i=0;i<m;i++)
		    for(int j=0;j<n;j++)
		        data[i][j]=input.nextInt();
		int result;
		if(data[0][0]==0 || data[x-1][y-1]==0 || data[m-1][n-1]==0)
		    result=0;
		else
		    result=func(data,0,0,x-1,y-1)*func(data,x-1,y-1,m-1,n-1);
		System.out.println(result);
	}
}
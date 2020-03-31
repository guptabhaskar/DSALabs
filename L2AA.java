
import java.util.Scanner;

public class Main {
    public static int func(int[] arr,int v,int n,int l,int r)
    {

        if(l==r)
        {
            if(arr[l]==v)
                return 1;
            else
                return 0;
        }
        else if(arr[l]==v && arr[r]==v)
            return r-l+1;

        else if(r-l==1)
        {
            if(arr[r]==v && arr[l]==v)
                return 2;
            else if(arr[r]==v || arr[l]==v)
                return 1;
            else
                return 0;
        }

        else
        {
            int mid=(l+r)/2;
            if(arr[mid]>v)
            {
                return func(arr,v,n,l,mid-1);
            }
            else if(arr[mid]<v)
            {
                return func(arr,v,n,mid+1,r);
            }
            else
            {
                return (func(arr,v,n,l,mid-1)+func(arr,v,n,mid+1,r)+1);
            }
        }

    }

    public static void main(String[] args) {
    // write your code here
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++)
            arr[i]=input.nextInt();
        int t=input.nextInt();
        for(int j=0;j<t;j++)
            {
                int v=input.nextInt();
                int ans=func(arr, v, n, 0, n - 1);
                if(ans==0)
                    System.out.println(-1);
                else
                    System.out.println(ans);
            }
}
}
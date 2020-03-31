import java.util.Scanner;

public class Main {
    public static boolean func(int p,int q,int m,int n,int k)
    {
        if(p>8 || q>8 || m>8 || n>8 || k<0)
        {
            return false;
        }
        else if(p==m && q==n)
        {
            return true;
        }
        else
        {
            boolean b = func(p + 2, q + 1, m, n, k - 1) || func(p + 2, q - 1, m, n, k - 1) || func(p - 2, q + 1, m, n, k - 1) || func(p - 2, q - 1, m, n, k - 1) || func(p + 1, q + 2, m, n, k - 1) || func(p + 1, q - 2, m, n, k - 1) || func(p - 1, q + 2, m, n, k - 1) || func(p - 1, q - 2, m, n, k - 1);
            return b;
        }
    }
    public static void main(String[] args) {
	// write your code here
        Scanner scan=new Scanner(System.in);
        int m,n,k;
        m=scan.nextInt();
        n=scan.nextInt();
        k=scan.nextInt();
        if(func(1,1,m,n,k))
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
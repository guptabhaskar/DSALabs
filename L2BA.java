import java.util.Scanner;

public class Main {
    public static long func(long n,long l)
    {
        long sum=0;
        if(n!=0)
        {
            long c=n%10;
            n=n/10;
            sum= (long) (Math.pow(c,l)+func(n,l));
        }
        return sum;
    }

    public static void main(String[] args) {
    // write your code here
        Scanner input=new Scanner(System.in);
        int t=input.nextInt();
        for(int i=0;i<t;i++)
        {   String s=input.next();
            long n=Long.parseLong(s);
            if(func(n,s.length())==n)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
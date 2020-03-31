import java.util.Scanner;

public class Main {

    public static int func(int n)
    {
        if(n==1)
            return 1;
        else if(n==2)
            return 2;
        else if(n==3)
            return 4;
        else
            return func(n-1)+func(n-2)+func(n-3);
    }


    public static void main(String[] args) {
	// write your code here
        Scanner input=new Scanner(System.in);
        int t=input.nextInt();
        System.out.println(func(t));


    }
}
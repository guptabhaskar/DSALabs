import java.util.Scanner;

class Stack{
    static final int MAX=1000;
    int top;
    int a[]=new int[1000];
    Stack()
    {
        top=-1;
    }

    boolean push(int x)
    {
        if(top>=(MAX-1))
        {
            System.out.println("Stack Overflow");
            return false;
        }
        else
        {
            a[++top]=x;
            return true;
        }
    }

    int pop() {
        if (top < 0) {
            System.out.println("Stack Underflow");
            return 0;
        } else {
            int x = a[top--];
            return x;
        }
    }

    int peek()
        {
            if(top<0)
            {
                System.out.println("Stack Underflow");
                return 0;
            }
            else
            {
                int x=a[top];
                return x;
            }
        }
    }

public class Main {
    public static void main(String[] args) {
	// write your code here
        Stack s=new Stack();
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        for(int i=0;i<n;i++)
        {
         int a1=input.nextInt();
         if(a1==1)
         {
             int a2=input.nextInt();
             s.push(a2);
         }
         else if(a1==2)
         {
             System.out.println(s.pop());
         }
         else
         {
             System.out.println(s.peek());
         }
        }
    }
}
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.*;

class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
class Stack{
    char[] arr=new char[100001];
    int top=-1;
    void push(char LR)
    {
        arr[++top]=LR;
    }
    void pop(){
        if(top>=0)
            top--;
    }
    char peek()
    {
            return arr[top];
    }
    boolean isEmpty()
    {
        if(top==-1)
            return true;
        return false;
    }
}


public class Main {

    public static void main(String[] args) {
	// write your code here
        FastReader scan=new FastReader();
        String s=scan.next();
        String y="Yes";
        String n="No";
        Stack st=new Stack();
        st.push(s.charAt(0));
        for(int i=1;i<s.length();i++)
        {
            if(!st.isEmpty())
            {
                char s1=st.peek();
                if(s.charAt(i)==s1)
                {
                    st.pop();
                }
                else{
                    st.push(s.charAt(i));
                }
            }
            else {
                st.push(s.charAt(i));
            }
        }
        if(st.isEmpty())
        {
            System.out.println(y);
        }
        else
        {
            System.out.println(n);
        }
    }
}

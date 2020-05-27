package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            tokenizer = new StringTokenizer(
                    reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        Reader.init(System.in);
        int n=Reader.nextInt();
        int q=Reader.nextInt();
        ArrayList<Integer>[] graph=new ArrayList[n];
        int[] parent=new int[n];
        long[] value=new long[n];
        int[] count=new int[n];
        int[] direction=new int[n];
        int[] blocked=new int[n];
        for(int i=0;i<n;i++)
        {
            ArrayList<Integer> temp=new ArrayList<>();
            graph[i]=temp;
            parent[i]=-1;
            count[i]=1;
            value[i] = Reader.nextInt();
        }

        for(int i=0;i<q;i++)
        {
            int type=Reader.nextInt(); //w
            if(type==1)
            {
                int a1=Reader.nextInt();
                int v=Reader.nextInt();
                value[a1-1]=v;
            }
            else if(type==2)
            {
                int a1=Reader.nextInt();
                int a2=Reader.nextInt();
                connect(graph,parent,count,direction,blocked,a1-1,a2-1);
            }
            else
            {
                int a1=Reader.nextInt();
                int a2=Reader.nextInt();
                int vel=Reader.nextInt();
                findvel(parent,direction,blocked,value,a1-1,a2-1,vel);
            }
        }
    }

    private static void findvel(int[] parent, int[] direction, int[] blocked, long[] value, int a1, int a2, long vel)
    {
        int p1=find(parent,a1);
        int p2=find(parent,a2);
        if(p1!=p2 || (blocked[p1]==1))
        {
            System.out.println(0);
        }
        else
        {
            print(direction,value,a1,a2,vel);
        }
    }
    private static void print(int[] direction,long[] value,int a1,int a2,long vel)
    {
        if(direction[a1]!=direction[a2])
            System.out.print("-");
        long num=vel*value[a1];
        long den=value[a2];
        long gcd=GCD(num,den);
        num=num/gcd;
        den=den/gcd;
        System.out.println(num+"/"+den);
    }
    private static void connect(ArrayList<Integer>[] graph, int[] parent, int[] count, int[] direction, int[] blocked, int a1, int a2)
    {
        int p1=find(parent,a1);
        int p2=find(parent,a2);
        if((blocked[p1]==1) || (blocked[p2]==1))
        {
            blocked[p1]=1;
            blocked[p2]=1;
        }
        if(p1==p2)
        {
            if(direction[a1]==direction[a2])
                blocked[p1]=1;
            return;
        }
        if(count[p1]<count[p2])
        {
            count[p2]=count[p2]+count[p1];
            dfs(a1,p2,graph,parent,direction,!(direction[a2]==1));
        }
        else
        {
            count[p1]=count[p1]+count[p2];
            dfs(a2,p1,graph,parent,direction,!(direction[a1]==1));
        }
        graph[a1].add(a2);
        graph[a2].add(a1);
    }

    private static void dfs(int a1, int p1, ArrayList<Integer>[] graph, int[] parent, int[] direction, boolean b)
    {
        if(parent[a1]==p1)
            return;
        parent[a1]=p1;
        if(b)
            direction[a1]=1;
        else
            direction[a1]=0;
        for(int i=0;i<graph[a1].size();i++)
        {
            dfs(graph[a1].get(i),p1,graph,parent,direction,!b);
        }
    }

    private static int find(int[] parent,int a1)
    {
        while(parent[a1]>=0)
            a1=parent[a1];
        return a1;
    }


    private static long GCD(long b1, long b2)
    {
        if(b1==0)
            return b2;
        else
            return GCD(b2%b1,b1);
    }
}

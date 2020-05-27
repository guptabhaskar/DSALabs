package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
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

//    static double nextDouble() throws IOException {
//        return Double.parseDouble( next() );
//    }
}

public class Main {
    static class Node implements Comparable<Node>{
        int[] arr=new int[2];
        public Node(int x,int y)
        {
            arr[0]=x;
            arr[1]=y;
        }
        public int compareTo(Node x)
        {
            return Integer.compare(arr[1],x.arr[1]);
        }
    }

    public static void main(String[] args) throws IOException {
        // write your code here
        Reader.init(System.in);
        int n,m;
        n=Reader.nextInt();
        m=Reader.nextInt();
        ArrayList<Node>[] edges=new ArrayList[n];
        for(int i=0;i<n;i++)
        {
            ArrayList<Node> temp=new ArrayList<>();
            edges[i]=temp;
        }
        for(int i=0;i<m;i++)
        {
            int a,b;
            int c;
            a=Reader.nextInt();
            b=Reader.nextInt();
            c=Reader.nextInt();
            Node a1=new Node(a-1,c);
            Node b1=new Node(b-1,c);
            edges[b-1].add(a1);
            edges[a-1].add(b1);
        }
//        ArrayList<Integer>[] p=new ArrayList[n];
        ArrayList<ArrayList<Integer>> patroltime=new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            ArrayList<Integer> temp=new ArrayList<>();
            patroltime.add(temp);
            int k;
            k=Reader.nextInt();
            for(int j=0;j<k;j++)
            {
                int tim=Reader.nextInt();
                patroltime.get(i).add(tim);
            }
        }
        int[] distance=new int[n];   //results
        int[] visited=new int[n];
        for(int i=0;i<n;i++)
            distance[i]=Integer.MAX_VALUE;
        distance[0]=0;
        PriorityQueue<Node> pq=new PriorityQueue<>();
        pq.add(new Node(0,-1));
        while(pq.size()>0)
        {
            int value=pq.poll().arr[0];
            int d=distance[value];
            int val=Integer.MIN_VALUE;
            int c=0;
            if(visited[value]==1)
                continue;
            visited[value]=1;
            for(int i=0;i<patroltime.get(value).size();i++)
            {
                if(d==patroltime.get(value).get(i) || patroltime.get(value).get(i)==(val+1))
                {
                    c=c+1;
                }
                if(d==patroltime.get(value).get(i))
                {
                    val=d;
                }
                if(patroltime.get(value).get(i)==(val+1))
                {
                    val=val+1;
                }
            }
            if(value!=(n-1))
            {
                distance[value]=distance[value]+c;
            }
            for(int i=0;i<edges[value].size();i++)
            {
                if(distance[edges[value].get(i).arr[0]]<=distance[value]+edges[value].get(i).arr[1])
                    continue;
                else
                {
                    distance[edges[value].get(i).arr[0]]=distance[value]+edges[value].get(i).arr[1];
                    Node temp=new Node(edges[value].get(i).arr[0],distance[edges[value].get(i).arr[0]]);
                    pq.add(temp);
                }
            }
        }
        if(distance[n-1]==Integer.MAX_VALUE) //Can't reach nth universe
            System.out.println(-1);
        else
            System.out.println(distance[n-1]); //Reached nth universe
    }
}
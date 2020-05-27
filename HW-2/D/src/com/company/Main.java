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

    public static void main(String[] args) throws IOException{
	// write your code here
        Reader.init(System.in);
        int n,h,x;
        n=Reader.nextInt();
        int[][] dis=new int[3][n];
        ArrayList[] tree=new ArrayList[n];
        //Initialisation
        for(int i=0;i<n;i++)
        {
            ArrayList<Integer> temp=new ArrayList<>();
            tree[i]=temp;
        }
        h=Reader.nextInt();
        x=Reader.nextInt();
        boolean[] hotspots=new boolean[n];
        for(int i=0;i<h;i++)
        {
            //To mark hotspots
            int tobemarked=Reader.nextInt()-1;
            hotspots[tobemarked]=true;
        }
        for(int i=0;i<n-1;i++)
        {
            //Add Edges
            int c1,c2;
            c1=Reader.nextInt();
            c2=Reader.nextInt();
            AddEdge(tree,c1-1,c2-1);
        }
        DFS(tree,hotspots,dis,0,-1,true);
        DFS(tree,hotspots,dis,0,-1,false);
        int epicenters=0;
        for(int i=0;i<n;i++)
        {
            if(dis[0][i]<=x && dis[2][i]<=x)
                epicenters+=1;
        }
        System.out.println(epicenters);
    }

    private static void DFS(ArrayList<Integer>[] tree, boolean[] hotspots, int[][] dis, int a1, int a2,boolean choice)
    {
        if(hotspots[a1])
        {
            if(choice)      //First DSF
                dis[0][a1]=0;
            else           //Second DSF
                dis[2][a1]=0;
        }
        else
        {
            if(choice)          //First DSF
                dis[0][a1]=Integer.MIN_VALUE;
            else                //Second DSF
                dis[2][a1]=Integer.MIN_VALUE;
        }
        if(choice)              //First DSF
            dis[1][a1]=Integer.MIN_VALUE;
        else                    //Second DSF
        {
            if(a2==-1)
            {
                dis[2][a1]=dis[1][a1];
            }
            else
            {
                dis[2][a1]=dis[2][a2]+1;
                if(dis[0][a1]+1==dis[0][a2])
                {
                    dis[2][a1]=Math.max(dis[1][a2]+1,dis[2][a1]);
                }
                else
                {
                    dis[2][a1]=Math.max(dis[0][a2]+1,dis[2][a1]);
                }
            }
        }
        for(int i=0;i<tree[a1].size();i++)
        {
            int value=tree[a1].get(i);
            if(a2==value)
                continue;
            if(choice)          //First DSF
                DFS(tree,hotspots,dis,value,a1,true);
            else                //Second DSF
                DFS(tree,hotspots,dis,value,a1,false);
            if(choice)          //First DSF
            {
                if (dis[0][a1]<dis[0][value]+1) {
                    dis[1][a1]=dis[0][a1];
                    dis[0][a1]=dis[0][value]+1;
                } else if (dis[1][a1]<dis[0][value]+1) {
                    dis[1][a1]=dis[0][value]+1;
                }
            }
        }
    }

    private static void AddEdge(ArrayList<Integer>[] tree, int c1, int c2) {
        tree[c1].add(c2);
        tree[c2].add(c1);
    }
}

//Test
//5 2 2
////2 5
////1 2
////2 3
////2 4
////4 5
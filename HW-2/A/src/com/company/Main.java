package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.LogManager;

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
        int tno = Reader.nextInt();
        for (int i = 0; i < tno; i++) {
            HashMap<Integer, ArrayList<Integer>> set=new HashMap<>();
            int n = Reader.nextInt();
            int[] sets = new int[n];
            for (int j = 0; j < n; j++)
                sets[j] =-1;
            int l = Reader.nextInt();
            int k = Reader.nextInt();
            for (int j = 0; j < l; j++) {
                int s, t;
                s = Reader.nextInt();
                t = Reader.nextInt();
                makesets(sets, s - 1, t - 1);
            }
//            for(int j=0;j<n;j++)
//            {
//                System.out.println(sets[j]);
//            }
//            System.out.println();
            for(int j=0;j<n;j++)
            {
                root1(sets,j);
            }
//            for(int j=0;j<n;j++)
//            {
//                System.out.println(sets[j]);
//            }
//            System.out.println(sets);
            HashMap<Integer,Integer> roots=new HashMap<>();
            int c=0;
            for(int j=0;j<n;j++)
            {
                if(sets[j]<0) {
                    if (!set.containsKey(j)) {
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(j);
                        set.put(j, temp);
                    } else {
                        ArrayList<Integer> temp = set.get(j);
                        temp.add(j);
                        set.replace(j, temp);
                    }
                    roots.put(j,c);
                    c+=1;
                }
                else{
                    if (!set.containsKey(sets[j])) {
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(j);
                        set.put(sets[j], temp);
                    } else {
                        ArrayList<Integer> temp = set.get(sets[j]);
                        temp.add(j);
                        set.replace(sets[j], temp);
                    }
                }
//                set.put(sets[j],set.get(j).add(j));
            }
            long[][] arr = new long[c][c];
            for(int p=0;p<c;p++)
                for(int q=0;q<c;q++) {
                    if(p!=q)
                        arr[p][q] = Long.MAX_VALUE;
                }
            for (int j = 0; j < k; j++) {
                int u, v;
                long d;
                u = Reader.nextInt();
                v = Reader.nextInt();
                d = Reader.nextInt();
                int a1=root(sets,u-1);
                int a2=root(sets,v-1);
                if(a1!=a2)
                {
                    if(d<arr[roots.get(a1)][roots.get(a2)])
                    {
                        arr[roots.get(a1)][roots.get(a2)]=d;
                        arr[roots.get(a2)][roots.get(a1)]=d;
                    }
                }
//                boolean flag;
//                flag = checksets(sets, u - 1, v - 1);
//                if (!flag) {
//                    arr[u-1][v-1]=d;
//                    arr[v-1][u-1]=d;
//                }
            }
////            for(int j=0;j<n;j++) {
////                root(sets, j);
////                System.out.println(sets[j]);
////            }
//
////            for(int x=0;x<n;x++)
////            {
////                for (int y = 0; y < n; y++)
////                    System.out.println(arr[x][y]);
////                System.out.println();
////            }
//
            boolean[] visited=new boolean[c];
            long[] results=new long[c];
//            long[] finalr=new long[n];
            for(int x=0;x<c;x++) {
                results[x] = Long.MAX_VALUE;
//                finalr[x]=Long.MIN_VALUE;
            }
            results[0]=0;
            dijkstra(arr,results,visited,c);
//            for(int j=0;j<n;j++)
//            {
//                System.out.println(results[j]);
//            }
//            ArrayList<Long> temp1=new ArrayList<>();
//            sameset(results,set,n,temp1,roots);
            long max=Long.MIN_VALUE;
            for(int j=0;j<c;j++)
            {
                if(max<results[j])
                {
                    max=results[j];
                }
            }
            System.out.println(max);
        }
    }

//    private static void sameset(long[] results, HashMap<Integer, ArrayList<Integer>> set, int n, ArrayList<Long> temp1, ArrayList<Integer> setroot) {
//        for(int i=0;i<setroot.size();i++)
//        {
//            Long minimum= Long.MAX_VALUE;
//            for(int j=0;j<set.get(setroot.get(i)).size();j++)
//            {
//                if(results[set.get(setroot.get(i)).get(j)]<minimum)
//                    minimum=results[set.get(setroot.get(i)).get(j)];
//            }
//            temp1.add(minimum);
//        }
//    }

    private static boolean checksets(int[] sets, int x, int y)
    {
        int a1=root(sets,x);
        int a2=root(sets,y);
        if(a1==a2)
            return true;
        return false;
    }

    private static void makesets(int[] sets, int x, int y)
    {
        int a1=root(sets,x);
        int a2=root(sets,y);
        if(sets[a1]<=sets[a2])
        {
            sets[a1]=sets[a1]+sets[a2];
            sets[a2]=a1;
        }
        else
        {
            sets[a2]=sets[a1]+sets[a2];
            sets[a1]=a2;
        }
    }
    private static int root(int[] sets, int r)
    {
        while(sets[r]>=0)
        {
         r=sets[r];
        }
        return r;
    }

    private static void root1(int[] sets, int r)
    {
        int parent=r;
        while(sets[parent]>=0)
        {
            parent=sets[parent];
        }
        if(sets[r]>=0)
            sets[r]=parent;
    }

    //graph of distances
    //results for dijkstra [0,Max,Max.....]
    //set of visited [F,F,F,F..]
    //No of elements
    private static void dijkstra(long[][] graph, long[] results, boolean[] visited, int n)
    {
        for(int i=0;i<n;i++)
        {
            int x=calculator(results,visited,n);
            visited[x]=true;
            for (int j=0;j<n;j++)
            {
                if(!visited[j] && graph[x][j]!=0 && (results[x]+graph[x][j]<results[j]))
                {
                    results[j]=results[x]+graph[x][j];
                }
            }
        }
    }

    private static int calculator(long[] distance, boolean[] visited, int n) {
        long mind=Long.MAX_VALUE;
        int mindv=-1;
        for (int i=0;i<n;i++)
        {
            if(!visited[i] && distance[i]<mind)
            {
                mind=distance[i];
                mindv=i;
            }
        }
        return mindv;
    }
}
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

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

class Node{
    public ArrayList<Integer> connect;
    public ArrayList<Long> dist;
    public int supply;
    public Node()
    {
        supply=-1;
        connect= new ArrayList<>();
        dist= new ArrayList<>();
    }
}


class Tree{
    public  Node[] n;
    int espidx,log,lev[],memo[][];
    public Tree(int v,int e){
        log=(int)Math.ceil(Math.log(v)/Math.log(2));
        memo=new int[v+1][log+1];
        lev=new int[v+1];
        for (int i = 0; i <=v; i++)
            Arrays.fill(memo[i], -1);
        espidx=e;
        n=new Node[v];
        for(int i=0;i<v;i++)
        {
            n[i]=new Node();
        }
    }
    //    public void display()
//    {
//        for (Node node : n) {
//            for (int j = 0; j < node.connect.size(); j++) {
//                System.out.print(node.connect.get(j) + " ");
//            }
//            System.out.println();
//            for (int j = 0; j < node.dist.size(); j++) {
//                System.out.print(node.dist.get(j) + " ");
//            }
//            System.out.println();
//        }
//    }
    public void AddEdge(int u,int v,long w)
    {
        n[u].connect.add(v);
        n[u].dist.add(w);
        n[v].connect.add(u);
        n[v].dist.add(w);
    }
    public void AddSupply(int i)
    {
        n[i].supply=1;
    }
    public void PreProcess()
    {
        dfs(espidx,espidx);
    }
    public void dfs(int u, int p)
    {
        memo[u][0] = p;
        for (int i = 1; i <= log; i++)
            memo[u][i] = memo[memo[u][i - 1]][i - 1];
        for (Integer v : n[u].connect) {
            if (v != p)
            {
                lev[v] = lev[u] + 1;
                dfs(v, u);
            }
        }
    }
    public int lca(int u, int v)
    {
        if (lev[u] < lev[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        for (int i = log; i >= 0; i--) {
            if ((lev[u] - (int)Math.pow(2, i)) >= lev[v])
                u = memo[u][i];
        }
        if (u == v)
            return u;
        for (int i = log; i >= 0; i--) {
            if (memo[u][i] != memo[v][i]) {
                u = memo[u][i];
                v = memo[v][i];
            }
        }
        return memo[u][0];
    }
    public boolean CanEscape(int s, int e, int R)
    {
        if(R==espidx)
            return true;
//        if(e<s)
//        {
//            int temp=s;
//            s=e;
//            e=temp;
//
//        }
        int l=lca(s,e);
        if(l==s)
        {
            s=e;
        }
        if(lca(R,s)==s)
            return false;
        return true;
    }
    public void FindDistance(ArrayList<Long> D,int c1,int c2,int start,int p,long l)
    {
        if(n[start].supply==1)
        {
            D.add(l);
        }
        for(int k=0;k<n[start].connect.size();k++)
        {
            if(n[start].connect.get(k)!=p && checkblocked(c1,c2,n[start].connect.get(k),start))
                FindDistance(D,c1,c2,n[start].connect.get(k),start,l+n[start].dist.get(k));
        }

    }
    public static boolean checkblocked(int c1,int c2,int k,int n)
    {
        if(c1==k && c2==n)
            return false;
        else if(c1==n && c2==k)
            return false;
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        // write your code here
        FastReader scan = new FastReader();
        int n, s, q, e;
        n = scan.nextInt();
        s = scan.nextInt();
        q = scan.nextInt();
        e = scan.nextInt() - 1;
        Tree T = new Tree(n, e);
        int[][] uv = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            int u = scan.nextInt() - 1;
            int v = scan.nextInt() - 1;
            int w = scan.nextInt();
            uv[i][0] = u;
            uv[i][1] = v;
            T.AddEdge(u, v, w);
        }
//        T.display();
        for (int i = 0; i < s; i++) {
            T.AddSupply(scan.nextInt() - 1);
        }
        T.PreProcess();
        for (int i = 0; i < q; i++) {
            int I = scan.nextInt()-1;
            int R = scan.nextInt()-1;
//            System.out.println(T.lca(I,R));
//            System.out.println(T.lca(I,R));
            if (T.CanEscape(uv[I][0], uv[I][1], R))
                System.out.println("escaped");
            else {
                ArrayList<Long> D=new ArrayList<>();
                if(s==n)
                    System.out.println("0");
                else
                { long l=0;
                    T.FindDistance(D,uv[I][0],uv[I][1],R,-1,l);
                    if(D.size()==0)
                        System.out.println("oo");
                    else {
                        long min=Long.MAX_VALUE;
                        for (Long aLong : D) {
                            if (aLong < min) {
                                min = aLong;
                            }
                        }
                        System.out.println(min);
                    }
                }
            }

        }
    }
}

package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void direct(Scanner scan,int cachel,int bsize,int k,int t)
    {
        int x=0;
        int offset;
        while(Math.pow(2,x)<bsize)
        {
            x+=1;
        }
        offset=x;
        ArrayList<ArrayList<String>> cache= new ArrayList<>();
        for(int i=0;i<cachel;i++)
        {
            ArrayList<String> a1=new ArrayList<>();
            cache.add(a1);
        }
        for(int i=0;i<t;i++)
        {
            System.out.println("Enter \"R\" if you want to Read and \"W\" if you want to Write: ");
            String OP;
            OP=scan.next();
            String add;
            System.out.println("Enter address to read or write: ");
            add=scan.next();
            int bno,nset,setno,y,temp;
            String tag;
            boolean flag;
            if(OP.equals("R"))
            {
                bno = Integer.parseInt(add, 2) / bsize;
                nset = cachel / k;
                setno = bno % nset;
                y = 0;
                for (int j = 0; j < nset; j++)
                {
                    if (Math.pow(2, y) < nset)
                        y += 1;
                    else
                        break;
                }
                tag = add.substring(0, add.length() - y - offset);
                temp = setno;
                flag = true;
                for (int j = temp; j < temp + k; j++)
                {
//                    System.out.println(j);
                    if (cache.get(j).size() == 0)
                    {
                        cache.get(j).add(tag);
                        cache.get(j).add("Null");
                        System.out.println("Cache Miss: B"+bno+" is placed in Line No. " + j);
                        flag = false;
                        break;
                    }
                    else if (cache.get(j).get(0).equals(tag))
                    {
                        flag = false;
                        System.out.println("Cache Hit: " + cache.get(j).get(1));
                        break;
                    }
                }
                if (flag)
                {
                    for (int j = 0; j < cache.size(); j++)
                    {
                        if (j == temp + k - 1)
                        {
                            cache.get(j).set(0, tag);
                            cache.get(j).set(1, "Null");
                        }
                        else if (j >= temp && j < temp + k)
                        {
                            cache.get(j).set(0, cache.get(j + 1).get(0));
                            cache.get(j).set(1, cache.get(j + 1).get(1));
                        }
                    }
                    System.out.println("Cache Miss: Acc to FIFO B"+bno+" is placed.");
                }
            }
            else if (OP.equals("W"))
            {
                String data;
                System.out.println("Enter data to write: ");
                data = scan.next();
                bno = Integer.parseInt(add, 2) / bsize;
                nset = cachel / k;
                setno = bno % nset;
                y = 0;
                for (int j = 0; j < nset; j++)
                {
                    if (Math.pow(2, y) < nset)
                        y += 1;
                    else
                        break;
                }
                tag = add.substring(0, add.length() - y - offset);
                temp = setno;
                flag = true;
                for (int j = temp; j < temp + k; j++)
                {
                    if (cache.get(j).size() == 0)
                    {
                        cache.get(j).add(tag);
                        cache.get(j).add(data);
                        flag = false;
                        break;
                    }
                    else if (cache.get(j).get(0).equals(tag))
                    {
                        flag = false;
                        ArrayList<String> temp1 = new ArrayList<>();
                        temp1.add(cache.get(j).get(0));
                        temp1.add(data);
                        cache.set(j, temp1);
                        break;
                    }
                }
                if (flag)
                {
                    for (int j = 0; j < cache.size(); j++)
                    {
                        if (j==temp+k-1)
                        {
                            cache.get(j).set(0,tag);
                            cache.get(j).set(1,data);
                        }
                        else if(j>=temp && j<temp+k)
                        {
                            cache.get(j).set(0, cache.get(j + 1).get(0));
                            cache.get(j).set(1, cache.get(j + 1).get(1));
                        }
                    }
                }
            }
            System.out.println(cache);
        }
    }


    public static void fully(Scanner scan,int cachel,int bsize,int k,int t)
    {
        int x=0;
        int offset;
        while(Math.pow(2,x)<bsize)
        {
            x+=1;
        }
        offset=x;
        ArrayList<ArrayList<String>> cache= new ArrayList<>();
        for(int i=0;i<cachel;i++)
        {
            ArrayList<String> a1=new ArrayList<>();
            cache.add(a1);
        }
        for(int i=0;i<t;i++)
        {
            System.out.println("Enter \"R\" if you want to Read and \"W\" if you want to Write: ");
            String OP;
            OP=scan.next();
            String add;
            System.out.println("Enter address to read or write: ");
            add=scan.next();
            int bno,nset,setno,y,temp;
            String tag;
            boolean flag;
            if(OP.equals("R"))
            {
                bno = Integer.parseInt(add, 2) / bsize;
                nset = cachel / k;
                setno = bno % nset;
                y = 0;
                for (int j = 0; j < nset; j++)
                {
                    if (Math.pow(2, y) < nset)
                        y += 1;
                    else
                        break;
                }
                tag = add.substring(0, add.length() - y - offset);
                temp = nset * setno;
                flag = true;
                for (int j = temp; j < temp + k; j++)
                {
//                    System.out.println(j);
                    if (cache.get(j).size() == 0)
                    {
                        cache.get(j).add(tag);
                        cache.get(j).add("Null");
                        System.out.println("Cache Miss: B" + bno + " is placed in Line No. " + j);
                        flag = false;
                        break;
                    }
                    else if (cache.get(j).get(0).equals(tag))
                    {
                        flag = false;
                        System.out.println("Cache Hit: " + cache.get(j).get(1));
                        break;
                    }
                }
                if (flag)
                {
                    for (int j = 0; j < cache.size(); j++)
                    {
                        if (j == temp + k - 1)
                        {
                            cache.get(j).set(0, tag);
                            cache.get(j).set(1, "Null");
                        }
                        else if (j >= temp && j < temp + k)
                        {
                            cache.get(j).set(0, cache.get(j + 1).get(0));
                            cache.get(j).set(1, cache.get(j + 1).get(1));
                        }
                    }
                    System.out.println("Cache Miss: Acc to FIFO B"+bno+" is placed.");
                }
            }
            else if (OP.equals("W"))
            {
                String data;
                System.out.println("Enter data to write: ");
                data = scan.next();
                bno = Integer.parseInt(add, 2) / bsize;
                nset = cachel / k;
                setno = bno % nset;
                y = 0;
                for (int j = 0; j < nset; j++)
                {
                    if (Math.pow(2, y) < nset)
                        y += 1;
                    else
                        break;
                }
                tag = add.substring(0, add.length() - y - offset);
                temp = nset * setno;
                flag = true;
                for (int j = temp; j < temp + k; j++)
                {
                    if (cache.get(j).size() == 0)
                    {
                        cache.get(j).add(tag);
                        cache.get(j).add(data);
                        flag = false;
                        break;
                    }
                    else if (cache.get(j).get(0).equals(tag))
                    {
                        flag = false;
                        ArrayList<String> temp1 = new ArrayList<>();
                        temp1.add(cache.get(j).get(0));
                        temp1.add(data);
                        cache.set(j, temp1);
                        break;
                    }
                }
                if (flag)
                {
                    for (int j = 0; j < cache.size(); j++)
                    {
                        if (j==temp+k-1)
                        {
                            cache.get(j).set(0,tag);
                            cache.get(j).set(1,data);
                        }
                        else if(j>=temp && j<temp+k)
                        {
                            cache.get(j).set(0, cache.get(j + 1).get(0));
                            cache.get(j).set(1, cache.get(j + 1).get(1));
                        }
                    }
                }
            }
            System.out.println(cache);
        }
    }

    public static void nway(Scanner scan,int cachel,int bsize,int k,int t)
    {
        int x=0;
        int offset;
        while(Math.pow(2,x)<bsize)
        {
            x+=1;
        }
        offset=x;
        ArrayList<ArrayList<String>> cache= new ArrayList<>();
        for(int i=0;i<cachel;i++)
        {
            ArrayList<String> a1=new ArrayList<>();
            cache.add(a1);
        }
        for(int i=0;i<t;i++)
        {
            System.out.println("Enter \"R\" if you want to Read and \"W\" if you want to Write: ");
            String OP;
            OP=scan.next();
            String add;
            System.out.println("Enter address to read or write: ");
            add=scan.next();
            int bno,nset,setno,y,temp;
            String tag;
            boolean flag;
            if(OP.equals("R"))
            {
                bno = Integer.parseInt(add, 2) / bsize;
                nset = cachel / k;
                setno = bno % nset;
                y = 0;
                for (int j = 0; j < nset; j++)
                {
                    if (Math.pow(2, y) < nset)
                        y += 1;
                    else
                        break;
                }
                tag = add.substring(0, add.length()-y-offset);
                temp = nset * setno;
                flag = true;
                for (int j = temp; j < temp + k; j++)
                {
//                    System.out.println(j);
                    if (cache.get(j).size() == 0)
                    {
                        cache.get(j).add(tag);
                        cache.get(j).add("Null");
                        System.out.println("Cache Miss: B" + bno + " is placed in Line No. " + j);
                        flag = false;
                        break;
                    }
                    else if (cache.get(j).get(0).equals(tag))
                    {
                        flag = false;
                        System.out.println("Cache Hit: " + cache.get(j).get(1));
                        break;
                    }
                }
                if (flag)
                {
                    for (int j = 0; j < cache.size(); j++)
                    {
                        if (j==temp+k-1)
                        {
                            cache.get(j).set(0, tag);
                            cache.get(j).set(1, "Null");
                        }
                        else if (j>=temp && j<temp+k)
                        {
                            cache.get(j).set(0, cache.get(j + 1).get(0));
                            cache.get(j).set(1, cache.get(j + 1).get(1));
                        }
                    }
                    System.out.println("Cache Miss: Acc to FIFO B" + bno + " is placed.");
                }
            }
            else if (OP.equals("W"))
            {
                String data;
                System.out.println("Enter data to write: ");
                data = scan.next();
                bno = Integer.parseInt(add, 2) / bsize;
                nset = cachel / k;
                setno = bno % nset;
                y = 0;
                for (int j=0;j<nset;j++)
                {
                    if (Math.pow(2, y)<nset)
                        y += 1;
                    else
                        break;
                }
                tag = add.substring(0,add.length()-y-offset);
                temp = nset * setno;
                flag = true;
                for (int j=temp;j<temp+k;j++)
                {
                    if (cache.get(j).size() == 0)
                    {
                        cache.get(j).add(tag);
                        cache.get(j).add(data);
                        flag = false;
                        break;
                    }
                    else if (cache.get(j).get(0).equals(tag))
                    {
                        flag = false;
                        ArrayList<String> temp1 = new ArrayList<>();
                        temp1.add(cache.get(j).get(0));
                        temp1.add(data);
                        cache.set(j, temp1);
                        break;
                    }
                }
                if (flag)
                {
                    for (int j=0;j<cache.size();j++)
                    {
                        if (j==temp+k-1)
                        {
                            cache.get(j).set(0,tag);
                            cache.get(j).set(1,data);
                        }
                        else if(j>=temp && j<temp+k)
                        {
                            cache.get(j).set(0, cache.get(j + 1).get(0));
                            cache.get(j).set(1, cache.get(j + 1).get(1));
                        }
                    }
                }
            }
            System.out.println(cache);
        }
    }

    public static void main(String[] args) {
        // write your code here
        Scanner scan=new Scanner(System.in);
        System.out.println("\"1\" for Direct Mapping");
        System.out.println("\"2\" for Fully Associative Mapping");
        System.out.println("\"3\" for n-way Set Associative Mapping");
//        System.out.println("\"4\" for Level-2 Cache");
        int mode;
        mode=scan.nextInt();
        int cacheline,bsize;
//        int caches;
//        System.out.println("Enter Cache Size: ");
//        caches=scan.nextInt();
        System.out.println("Enter Cache Line: ");
        cacheline=scan.nextInt();
        System.out.println("Enter Block Size: ");
        bsize=scan.nextInt();
        if(mode==1)
        {
            int op;
            System.out.println("Number of operations: ");
            op=scan.nextInt();
            direct(scan,
                    cacheline,
                    bsize,
                    1,
                    op);
        }
        else if(mode==2)
        {
            int op;
            System.out.println("Number of operations: ");
            op=scan.nextInt();
            fully(scan,
                    cacheline,
                    bsize,
                    cacheline,
                    op);
        }
        else if(mode==3)
        {
            int n,op;
            System.out.println("Enter value of \"n\": ");
            n=scan.nextInt();
            System.out.println("Number of operations: ");
            op=scan.nextInt();
            nway(scan,
                    cacheline,
                    bsize,
                    n,
                    op);
        }
        else
        {
            System.out.println("Wrong Press");
        }
//        else
//        {
//            level2();
//        }
    }
}

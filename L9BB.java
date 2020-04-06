package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
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

class Huffman
{
    public HuffmanNode[] mH=new HuffmanNode[26];
    public int currentSize;
    HuffmanNode root;
    public Huffman(){
        currentSize =0;
        root=null;
    }
    public void createHeap(int [] arrA){
        for(int i=0;i<26;i++){
            HuffmanNode h=new HuffmanNode((char)i,arrA[i]);
            insert(h);
        }
    }
    //abcdefghiijklmnopqrstuvwxyzwewewewewtototop
//            00110
//            110011
//            111000
//            111011
//            100
//            111001
//            00010
//            110000
//            0010
//            111101
//            01101
//            111110
//            111111
//            00011
//            1101
//            0000
//            110001
//            01111
//            111100
//            010
//            01100
//            01110
//            101
//            111010
//            110010
//            00111
    public void insert(HuffmanNode x) {
        mH[currentSize] = x;
        currentSize++;
        bubbleUp(currentSize-1);
    }

    public void bubbleUp(int pos) {
        int parentIdx = (pos-1)/2;
        while (pos > 0 && mH[parentIdx].freq > mH[pos].freq) {
            swap(pos,parentIdx);
            pos = parentIdx;
            parentIdx = (pos-1)/2;
        }
    }

    public HuffmanNode extractMin() {
        Huffman.HuffmanNode min = mH[0];
        mH[0] = mH[currentSize-1];
        mH[currentSize-1]=null;
        currentSize--;
        sinkDown(0);
        return min;
    }

    public void sinkDown(int k) {
        int smallest = k;
        int leftChildIdx = 2 * k+1;
        int rightChildIdx = 2 * k+2;
        if (leftChildIdx < heapSize() && mH[smallest].freq>mH[leftChildIdx].freq) {
            smallest = leftChildIdx;
        }
        if (rightChildIdx < heapSize() && mH[smallest].freq>mH[rightChildIdx].freq) {
            smallest = rightChildIdx;
        }
        if (smallest != k) {
            swap(k, smallest);
            sinkDown(smallest);
        }
    }

    public void swap(int a, int b) {
        Huffman.HuffmanNode temp = mH[a];
        mH[a] = mH[b];
        mH[b] = temp;
    }
    public int heapSize(){
        return currentSize;
    }
    public static class HuffmanNode{
        char data;
        int freq;
        HuffmanNode left;
        HuffmanNode right;
        public HuffmanNode(char d,int f)
        {
            data=d;
            left=right=null;
            freq=f;
        }
        public HuffmanNode(HuffmanNode l, HuffmanNode r)
        {
            data='!';
            left=l;
            right=r;
            freq=l.freq+r.freq;
        }
    }
    public void createtree(int[] arr)
    {
        HuffmanNode a,b;
        createHeap(arr);
        while(currentSize!=1)
        {
            a=extractMin();
            b=extractMin();
            HuffmanNode h1 = new HuffmanNode(a, b);
            insert(h1);
        }
        root=extractMin();
    }
    public static void createoutput(HuffmanNode root,String[] output,String s)
    {
        if(root==null)
        {
            return;
        }
        else if(Objects.equals(root.data,'!'))
        {
            createoutput(root.left,output,s+"0");
            createoutput(root.right,output,s+"1");
        }
        else
        {
            output[root.data]=s;
        }
    }
}


public class Main {
    public static void main(String[] args) {
        // write your code here
        FastReader fast=new FastReader();
        String s=fast.next();
        Huffman h=new Huffman();
        int[] arr=new int[26];
        // int[] order=new int[26];
        // int j=0;
        for(int i=0;i<s.length();i++)
        {
            int index=(int)s.charAt(i)-97;
            // if(arr[index]==0) {
            //     order[j] = index;
            //     j += 1;
            // }
            arr[index]+=1;
        }
        h.createtree(arr);
        String[] output=new String[26];
        Huffman.createoutput(h.root, output, "");
        for(int i=0;i<26;i++)
        {
            System.out.println(output[i]);
        }
    }
}
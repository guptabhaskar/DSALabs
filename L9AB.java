package com.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class lab9b{
    static class minHeap {								//class min heap is borrowed from google classroom DSA LAB 8 solution
        public int [] mH;
        public int currentSize;
        public minHeap(){
            mH = new int [1000001];
            currentSize =0;
        }

        public void printHeap(){
            for(int i=1;i<mH.length;i++){
                System.out.print(" " + mH[i]);
            }
            System.out.println("");
        }
        public void insert(int x) {
            currentSize++;
            int idx = currentSize;
            mH[idx] = x;
            bubbleUp(idx);
        }

        public void bubbleUp(int pos) {
            int parentIdx = pos/2;
            int currentIdx = pos;
            while (currentIdx > 0 && mH[parentIdx] > mH[currentIdx]) {

                swap(currentIdx,parentIdx);
                currentIdx = parentIdx;
                parentIdx = parentIdx/2;
            }
        }

        public int deleteMin() {
            int min = mH[1];
            mH[1] = mH[currentSize];
            mH[currentSize] = 0;
            sinkDown(1);
            currentSize--;
            return min;
        }

        public void sinkDown(int k) {
            int smallest = k;
            int leftChildIdx = 2 * k;
            int rightChildIdx = 2 * k+1;
            if (leftChildIdx < heapSize() && mH[smallest] > mH[leftChildIdx]) {
                smallest = leftChildIdx;
            }
            if (rightChildIdx < heapSize() && mH[smallest] > mH[rightChildIdx]) {
                smallest = rightChildIdx;
            }
            if (smallest != k) {

                swap(k, smallest);
                sinkDown(smallest);
            }
        }

        public void swap(int a, int b) {
            int temp = mH[a];
            mH[a] = mH[b];
            mH[b] = temp;
        }
        public boolean isEmpty() {
            return currentSize == 0;
        }

        public int heapSize(){
            return currentSize;
        }
    }
    static class Reader {
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
            return Double.parseDouble(next());
        }
    }
    public static void main(String[] args)throws IOException{
        Reader.init(System.in);
        int n=Reader.nextInt();
        double[] A=new double[n];
        double[] Xor=new double[n];
        for(int i=0;i<n;i++) {
            A[i]=Reader.nextDouble();
        }
        lab9b.minHeap he=new lab9b.minHeap();
        for(int i=0;i<n;i++) {
            he.insert((int)A[i]);
            if(i>=2) {
                int m1 = he.deleteMin();
                int m2 = he.deleteMin();
                int m3 = he.deleteMin();

                Xor[i] = m1 ^ m2 ^ m3;

                he.insert(m1);
                he.insert(m2);
                he.insert(m3);
            }
        }

        int q=Reader.nextInt();
        for(int i=0;i<q;i++){
            int k=Reader.nextInt();
            if(k<3)
                System.out.println(-1);
            else
            System.out.println((int)Xor[k-1]);
        }
    }
}
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

class Heap {
    public int capacity;
    public double [] s;
    public int size;
    public Heap(){
        this.capacity=100005;
        s = new double[100005];
        size=0;
    }

    public void insert(double s1) {
        size++;
        int i = size;
        s[i] = s1;
        bubbleUp(i);
    }

    public void bubbleUp(int pos) {
        int parentIdx = pos/2;
        int currentIdx = pos;
        while (parentIdx > 0 && s[parentIdx] < s[currentIdx]) {
            swap(currentIdx,parentIdx);
            currentIdx = parentIdx;
            parentIdx = parentIdx/2;
        }
    }

    public double extractMax() {
        double min = s[1];
        s[1] = s[size];
        s[size] = 0;
        sinkDown(1);
        size--;
        return min;
    }

    public void sinkDown(int k) {
        int smallest = k;
        int leftChildIdx = 2 * k;
        int rightChildIdx = 2 * k+1;
        if (leftChildIdx < size && s[smallest] < s[leftChildIdx]) {
            smallest = leftChildIdx;
        }
        if (rightChildIdx < size && s[smallest]< s[rightChildIdx]) {
            smallest = rightChildIdx;
        }
        if (smallest != k) {

            swap(k, smallest);
            sinkDown(smallest);
        }
    }
    public void swap(int a, int b) {
        double temp = s[a];
        s[a] = s[b];
        s[b] = temp;
    }
    public double peek()
    {
        return s[1];
    }
}

public class Main {
    public static void mergesort(double[] s, double[] d, double[] ratio, int l, int r)
    {
        if(l<r)
        {
            int mid=(l+r)/2;
            mergesort(s,d,ratio,l,mid);
            mergesort(s,d,ratio,mid+1,r);
            merge(s,d,ratio,l,mid,r);
        }
    }
    public static void merge(double[] s, double[] d, double[] ratio, int l, int mid, int r)
    {
        int n1=mid-l+1;
        int n2=r-mid;
        double L[]=new double[n1];
        double R[]=new double[n2];
        double L1[][]=new double[n1][2];
        double R1[][]=new double[n1][2];
        for(int i=0;i<n1;i++) {
            L[i]=ratio[l+i];
            L1[i][0]=s[l+i];
            L1[i][1]=d[l+i];
        }
        for(int i=0;i<n2;i++)
        {
            R[i]=ratio[mid+1+i];
            R1[i][0]=s[mid+1+i];
            R1[i][1]=d[mid+1+i];
        }
        int i=0,j=0;
        int k=l;
        while(i<n1 && j<n2)
        {
            if(L[i]<=R[j])
            {
                ratio[k]=L[i];
                s[k]=L1[i][0];
                d[k]=L1[i][1];
                i++;
            }
            else
            {
                ratio[k]=R[j];
                s[k]=R1[j][0];
                d[k]=R1[j][1];
                j++;
            }
            k++;
        }
        while(i<n1)
        {
            ratio[k]=L[i];
            s[k]=L1[i][0];
            d[k]=L1[i][1];
            i++;
            k++;
        }
        while(j<n2)
        {
            ratio[k]=R[j];
            s[k]=R1[j][0];
            d[k]=R1[j][1];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        // write your code here
        FastReader scan=new FastReader();
        int n,k;
        n=scan.nextInt();
        k=scan.nextInt();
        double[] s=new double[n];
        double[] d=new double[n];
        double[] ratio=new double[n];
        for(int i=0;i<n;i++)
            s[i]=scan.nextDouble();
        for(int i=0;i<n;i++)
        {
            d[i]=scan.nextInt();
            ratio[i]= d[i]/s[i];
        }
        mergesort(s,d,ratio,0,n-1);
        Heap H=new Heap();
        int s1=0;
        for(int i=0;i<k-1;i++)
        {
            H.insert(s[i]);
            s1+=s[i];
        }
//        //Arrays after sorting
//        for(int i=0;i<n;i++)
//            System.out.print(s[i]+" ");
//        System.out.println();
//        for(int i=0;i<n;i++)
//            System.out.print(d[i]+" ");
//        System.out.println();
//        for(int i=0;i<n;i++)
//            System.out.print(ratio[i]+" ");
//        System.out.println();
        double min=ratio[k-1]*(s1+s[k-1]);
        if(H.peek()>s[k-1]) {
            s1-=H.peek();
            H.extractMax();
            s1+=s[k-1];
            H.insert(s[k-1]);
        }
        for(int i=k;i<n;i++)
        {
            double temp=0;
//            s1-=H.peek();
//            s1+=s[i];
            temp+=(d[i]/s[i])*(s1+s[i]);
//            sum+=r*s1;
////            System.out.println(sum);
////            for(int j=0;j<k-1;j++)
////            {
////                System.out.println(temp[j][0]+" "+temp[j][1]);
////            }
//            s1-=s[i];
//            s1+=H.peek();
            if(H.peek()>s[i]) {
                s1-=H.peek();
                H.extractMax();
                s1+=s[i];
                H.insert(s[i]);
            }
            if(temp<min)
            {
                min=temp;
            }
        }
        System.out.println((int)Math.ceil(min));
    }
}
//Google Classroom Lab Solutions were used to learn and implement MaxHeap
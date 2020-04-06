import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

class MaxHeap{
    int[][] mH=new int[1000002][2];
    int size = 0;
    public void display()
    {
        for(int i=0;i<size;i++)
        {
            System.out.print(mH[i][0]+" ");
        }
        System.out.println();
        for(int i=0;i<size;i++)
        {
            System.out.print(mH[i][1]+" ");
        }
        System.out.println();
    }
    int parentIndex(int x) {
        if (x == 0) return -1;
        else return (x - 1) / 2;
    }
    int left(int x) {
        return 2 * x + 1;
    }

    int right(int x) {
        return 2 * x + 2;
    }

    void swapindexes(int i, int j) {
        int[] temp = mH[i];
        mH[i] = mH[j];
        mH[j] = temp;
    }

    int[] ExtractMax() {
        if (size <= 0)
            return null;
        if (size == 1) {
            size--;
            return mH[0];
        }
        int[] temp = mH[0];
        swapindexes(0, size - 1);
        size--;
        int x = 0;
        while (true) {
            int l = left(x);
            int r = right(x);
            int largest = x;
            boolean b=false;
            if (l > size && r > size)
                break;
            if (l < size && ceil(mH[l][0]) > ceil(mH[x][0])) {
                    largest = l;
                    b=true;
                }
            if (r < size && ceil(mH[r][0]) > ceil(mH[x][0])){
                    largest=r;
                    b=true;
            }
            if(!b)
            {
                boolean c=false;
                if(l<size)
                {
                    if(ceil(mH[l][0])==ceil(mH[x][0]) && mH[l][1]<mH[x][1]) {
                        largest = l;
                        c = true;
                    }
                }
                if(r<size)
                {
                    if(ceil(mH[r][0])==ceil(mH[r][0]) && mH[r][1]<mH[x][1] && !c)
                        largest=r;
                    else if(ceil(mH[r][0])==ceil(mH[x][0]) && mH[r][1]<mH[x][1] && c && mH[r][1]<mH[largest][1])
                        largest=r;
                }
            }
            if (largest != x) {
                swapindexes(x, largest);
            }
            if (largest == x)
                break;
            x = largest;

        }
        return temp;
    }

    void Insert(int x,int y) {
        mH[size][0] = x;
        mH[size][1]=y;
        int i = size;
        size++;
        while (i > 0 && ((ceil(mH[parentIndex(i)][0])<ceil(mH[i][0])) || (ceil(mH[parentIndex(i)][0])==ceil(mH[i][0]) && mH[parentIndex(i)][1]>mH[i][1]))) {
//            System.out.println("DONE");
            if(ceil(mH[parentIndex(i)][0])<ceil(mH[i][0])) {
                swapindexes(parentIndex(i), i);
                i = parentIndex(i);
            }
            else if(ceil(mH[parentIndex(i)][0])==ceil(mH[i][0]) && mH[parentIndex(i)][1]>mH[i][1])
            {
                swapindexes(parentIndex(i), i);
                i = parentIndex(i);
            }
        }

    }
    public static int ceil(int w)
    {
        return (int)Math.ceil((double)w/(double)2);
    }
}

public class Main {
    public static int ceil(int w)
    {
        return (int)Math.ceil((double)w/(double)2);
    }

    public static void main(String[] args) {
        // write your code here
        FastReader fast=new FastReader();
        int t=fast.nextInt();
        for(int i=0;i<t;i++)
        {
            int n,k;
            n=fast.nextInt();
            k=fast.nextInt();
            int[] work=new int[n];
            int[] bore=new int[n];
            long sumofwork=0;
            for(int j=0;j<n;j++)
            {
                work[j]=fast.nextInt();
                sumofwork+=work[j];
            }
            for(int j=0;j<n;j++)
            {
                bore[j]=fast.nextInt();
            }
            MaxHeap m=new MaxHeap();
            for(int j=0;j<n;j++) {
                m.Insert(work[j], bore[j]);
            }
            long W=0;
            long B=0;
//            m.display();
            for(int j=0;j<k;j++)
            {
                int w2;
                if(m.size==0)
                    break;
                int[] e=m.ExtractMax();
//                m.display();
                w2=ceil(e[0]);
                W+=w2;
                B+=w2*e[1];
                if(e[0]-w2>0)
                    m.Insert(e[0]-w2,e[1]);
//                m.display();
            }
            System.out.print(sumofwork-W);
            System.out.print(" ");
            System.out.print(B);
            System.out.println();
        }
    }
}
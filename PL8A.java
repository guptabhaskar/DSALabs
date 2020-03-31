import java.util.Scanner;
class Heap{
    int[] arr;
    int size;
    public Heap(int maxsize)
    {
        size=1;
        arr=new int[maxsize+1];
        arr[0]=1;
    }
    public void maximum()
    {
        System.out.println(arr[0]);
    }
    public void delete()
    {
        arr[0]=arr[size-1];
        size-=1;
        heapify(0);
    }
    public void heapify(int x)
    {
        int largest=x;
        int l=2*x+1;
        int r=2*x+2;
        if(l<size && arr[l]>arr[largest])
            largest=l;
        if(r<size && arr[r]>arr[largest])
            largest=r;
        if(largest!=x)
        {
            int swap=arr[x];
            arr[x]=arr[largest];
            arr[largest]=swap;
            heapify(largest);
        }
    }
    public void insert(int x)
    {
        arr[size]=x;
        size++;
        heapify2(size-1);
    }
    public void print()
    {
        for(int i=0;i<size;i++) {
            System.out.print(arr[i]);
            System.out.print(" ");
        }
        System.out.println();
    }
    public void heapify2(int i)
    {
        int parent = (i - 1) / 2;
            if (arr[i] > arr[parent]) {
                int swap=arr[i];
                arr[i]=arr[parent];
                arr[parent]=swap;
                heapify2(parent);
            }
    }
}

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner scan=new Scanner(System.in);
        int q=scan.nextInt();
        int a;
        int b;
        Heap h=new Heap(q+1);
        for(int i=0;i<q;i++)
        {
            a=scan.nextInt();
            if(a==1)
            {
              b=scan.nextInt();
              h.insert(b);
//              h.print();
            }
            else if(a==2) {
                h.maximum();
//                h.print();
            } else
            {
                h.delete();
//                h.print();
            }
        }
    }
}
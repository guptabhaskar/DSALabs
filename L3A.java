import java.util.Scanner;

public class Main {


    public static double logg(double m) {
        return(m*Math.log10(m));
    }

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int t=input.nextInt();
        for(int k=0;k<t;k++) {

            double z=input.nextDouble();
            double time=input.nextDouble();

            double d=time/z;

            double s=1;
            double m=d;
            double e=2*m;

            while(s<=e) {
                double w=logg(m);
                if(w==d) {
                    break;
                }
                else if(w>d) {
                    e=m-1;
                    m=(s+e)/2;
                }
                else if(w<d) {
                    s=m+1;
                    m=(s+e)/2;

                }
            }
            int c=(int)m-1;
            double[] B= {c-1,c,c+1,c+2};
            double u=0;
            for (int q=0;q<4;q++) {
                if(logg(B[q])<=d) {
                    u=Math.max(u,B[q]);
                }
            }
            System.out.println((int)u);


        }
    }
}
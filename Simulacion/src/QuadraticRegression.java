import java.util.ArrayList;

public class QuadraticRegression{
    public static void main(String[] args){

        //Establecer el dataset
        DataSet.LeerVarsity();
        ArrayList<DataSet> datos = DataSet.Datos;

        double sumx=0, sumy=0, sumx2=0, sumx3=0, sumx4=0, sumxy=0, sumx2y=0;
        double n= datos.size();

        for (int i=0; i< datos.size(); i++){
            double x = datos.get(i).factor1;
            double y = datos.get(i).yield;
            sumx += x;
            sumy += y;
            sumx2 += (x * x);
            sumx3 += (x * x * x);
            sumx4 += (x * x * x * x);
            sumxy += (x * y);
            sumx2y += ((x * x) * y);

        }

        double a = (n * sumx2y - sumx2 * sumy) / (n * sumx4 - sumx2 * sumx2);
        double b = (n * sumxy - sumx * sumy) / (n * sumx2 - sumx * sumx);
        double c = (sumy -a *sumx2 -b *sumx)/n;

        System.out.println("Ecuacion de la recta: "+ a +"x2 "+b+ "x "+c);
        simulacion(a, b, c);
    }

    public static void simulacion(double a, double b, double c){
        double ejemplo1=-5, ejemplo2=-4,ejemplo3=4, ejemplo4=5, ejemplo5=6;
        double ysimulada1= a*ejemplo1*ejemplo1 + b * ejemplo1 + c;
        double ysimulada2= a*ejemplo2*ejemplo2 + b * ejemplo2 + c;
        double ysimulada3= a*ejemplo3*ejemplo3 + b * ejemplo3 + c;
        double ysimulada4= a*ejemplo4*ejemplo4 + b * ejemplo4 + c;
        double ysimulada5= a*ejemplo5*ejemplo5 + b * ejemplo5 + c;
        System.out.println("Simulacion ");
        System.out.println("X="+ejemplo1+" = "+ysimulada1);
        System.out.println("X="+ejemplo2+" = "+ysimulada2);
        System.out.println("X="+ejemplo3+" = "+ysimulada3);
        System.out.println("X="+ejemplo4+" = "+ysimulada4);
        System.out.println("X="+ejemplo5+" = "+ysimulada5);
    }
}

import java.util.ArrayList;

public class SimpleLinearRegression{

    public static void main(String[] args){
        double sumx=0, sumy=0, sumxy=0, sumxx=0;
        double promx=0, promy=0;
        double m=0, b=0;

        //Establecer el dataset
        DataSet.LeerStartups50();
        ArrayList<DataSet> datos = DataSet.Datos;

        //Calculo de sumatorias
        for (int i=0; i< datos.size(); i++){
            sumx+=datos.get(i).factor2;
            sumy+=datos.get(i).yield;
            sumxx+=(datos.get(i).factor2 * datos.get(i).factor2);
            sumxy+=(datos.get(i).yield * datos.get(i).factor2);
        }

        //Calculo de promedios
        promx = sumx/datos.size();
        promy = sumy/datos.size();


        //Calculo pendiente
        m= (datos.size()*sumxy - sumx *sumy) / (datos.size() * sumxx - sumx*sumx);

        //Calculo interseccion
        b= promy - (m * promx);

        System.out.println("La ecuacion de la recta es: Y="+m+"X "+b);
        System.out.println("Ejemplos a simular");
        simulacionDataset1(b, m);
    }

    public static void simulacionDataset1(double b, double m){
        double ejemplo1=118671.85, ejemplo2=145530.06, ejemplo3=151377.59, ejemplo4=110594.11, ejemplo5=144135.98;
        System.out.println("X="+ejemplo1+" Y="+ ((m*ejemplo1)+b));
        System.out.println("X="+ejemplo2+" Y="+ ((m*ejemplo2)+b));
        System.out.println("X="+ejemplo3+" Y="+ ((m*ejemplo3)+b));
        System.out.println("X="+ejemplo4+" Y="+ ((m*ejemplo4)+b));
        System.out.println("X="+ejemplo5+" Y="+ ((m*ejemplo5)+b));
    }

    public static void simulacionDataset2(double b, double m){
        double ejemplo1=35, ejemplo2=45.92, ejemplo3=28.17, ejemplo4=52.43, ejemplo5=62.05;
        System.out.println("X="+ejemplo1+" Y="+ ((m*ejemplo1)+b));
        System.out.println("X="+ejemplo2+" Y="+ ((m*ejemplo2)+b));
        System.out.println("X="+ejemplo3+" Y="+ ((m*ejemplo3)+b));
        System.out.println("X="+ejemplo4+" Y="+ ((m*ejemplo4)+b));
        System.out.println("X="+ejemplo5+" Y="+ ((m*ejemplo5)+b));
    }
}

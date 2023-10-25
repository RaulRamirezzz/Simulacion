import java.util.ArrayList;
import java.lang.Math;

public class PolynomialRegression {
    public static void main(String[] args){
        DataSet.Polynomial();
        ArrayList<DataSet> datos = DataSet.Datos;

        int grado=3;
        int col=grado+1;
        int n= datos.size();
        double matrizSumatorias[][]=new double[col][col+1];
        double matrizGauss[][]=new double[col][col+1];
        double[] soluciones = new double[col];

        matrizSumatorias =  guardarMatriz(datos, grado);
        matrizGauss = gaussJordan(matrizSumatorias, grado);
        soluciones = separarResultados(matrizGauss, grado);

        for (int i=0; i<col; i++){
            System.out.println("B"+i+": "+soluciones[i]);
        }
        System.out.println("Simulaciones");
        simulacion(soluciones, grado);
    }

    public static double[][] guardarMatriz(ArrayList<DataSet>datos, int grado){
        int col=grado+1;
        int n= datos.size();
        double matrizSumatorias[][]=new double[col][col+1];
        double matrizSumX[]=new double[2* col];
        double matrizSumY[]=new double[col];

        for (int i=0; i< datos.size(); i++){
            double x = datos.get(i).factor1;
            double y = datos.get(i).yield;
            //Sumatorias de x
            for (int j=0; j<2*col; j++){
                matrizSumX[j] += Math.pow(x, j);
            }
            //Sumatorias x*y
            for (int k=0; k<col; k++){
                matrizSumY[k] += Math.pow(x, k) * y;
            }
        }

        for (int i=0; i<col; i++){
            for (int j=0; j<col; j++){
                matrizSumatorias[i][j] = matrizSumX[i + j];
            }
            matrizSumatorias[i][col] = matrizSumY[i];
        }

        return matrizSumatorias;
    }

    public static double[][] gaussJordan(double[][]matrizSumatorias, int pol){
        int col=pol+1;
        for (int i = 0; i < col; i++) {
            // Hacer que el elemento en la diagonal sea igual a 1
            double pivot = matrizSumatorias[i][i];
            for (int j = i; j < col + 1; j++) {
                matrizSumatorias[i][j] /= pivot;
            }

            // Eliminar otros elementos en la columna
            for (int k = 0; k < col; k++) {
                if (k != i) {
                    double factor = matrizSumatorias[k][i];
                    for (int j = i; j < col + 1; j++) {
                        matrizSumatorias[k][j] -= factor * matrizSumatorias[i][j];
                    }
                }
            }
        }
        return matrizSumatorias;
    }

    public static double[] separarResultados(double[][]gauss, int pol){
        int col=pol+1;
        double[] soluciones = new double[col];
        for (int i = 0; i < col; i++) {
            soluciones[i] = gauss[i][col];
        }
        return soluciones;
    }

    public static void simulacion (double[]soluciones, int pol){
        double[]ejemplos={45, 78, 86, 102, 53};
        double resultado=0;
        for (int i=0; i<ejemplos.length; i++){
            for (int j=0; j<pol; j++){
                resultado += soluciones[j] * Math.pow(ejemplos[i], j);
            }
            System.out.println("X"+ejemplos[i]+": "+ resultado);
            resultado=0;
        }
    }
}

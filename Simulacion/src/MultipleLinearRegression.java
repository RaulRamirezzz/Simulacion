import java.util.ArrayList;

public class MultipleLinearRegression{



    public static void main(String[] args){

        DataSet.LeerStartups50();
        ArrayList<DataSet> datos = DataSet.Datos;

        int numeroColumnasDataSet=4;
        int numeroFilasDataSet=datos.size();

        double[][] matriz =new double[numeroFilasDataSet][numeroColumnasDataSet];
        double[][] matrizTras = new double[numeroColumnasDataSet][numeroFilasDataSet];
        double[][] matrizResMul = new double[numeroColumnasDataSet][numeroColumnasDataSet];
        double[][] matrizInversa = new double[numeroColumnasDataSet][numeroColumnasDataSet];
        double[] matrizResMulXY = new double[numeroColumnasDataSet];
        double[] matrizB = new double[numeroColumnasDataSet];


        //Guardar arraylist dentro de una matriz
        matriz = CrearMatriz(datos, numeroColumnasDataSet);
        //Transponer la matriz
        matrizTras = TransponerMatriz(matriz, numeroColumnasDataSet);
        //Multiplicar la matriz transpuesta por la matriz
        matrizResMul = MultiplicarTransXporX(matriz, matrizTras, numeroColumnasDataSet);
        //Gauss
        matrizInversa = GaussJordan(matrizResMul, numeroColumnasDataSet);
        //Calcular el producto de la matriz transpuesta de X por Y
        matrizResMulXY = MultiplicarTransXporY(datos, matrizTras, numeroColumnasDataSet);
        //Calcular las B
        matrizB = CalcularCoeficientes(matrizInversa, matrizResMulXY, numeroColumnasDataSet);
        //Simulacion
        Simulacion1(matrizB);

    }

    public static double[][]CrearMatriz(ArrayList<DataSet> datos, int numeroColumnasDataSet){
        int numeroFilasDataSet= datos.size();
        double[][] matriz =new double[numeroFilasDataSet][numeroColumnasDataSet];
        for (int i=0; i<numeroFilasDataSet; i++){
            matriz[i][0]=1;
            matriz[i][1]=datos.get(i).factor1;
            matriz[i][2]=datos.get(i).factor2;
            //Comentar en caso de usar otro dataset
            matriz[i][3]=datos.get(i).factor3;
        }
        return matriz;
    }

    public static double [][] TransponerMatriz(double[][]matriz, int numeroColumnasDataSet){
        //Matriz traspuesta
        int numeroFilasDataSet=matriz.length;
        double[][] matrizTras = new double[numeroColumnasDataSet][numeroFilasDataSet];

        for (int i=0; i<numeroFilasDataSet; i++){
            for (int j=0; j<numeroColumnasDataSet; j++){
                matrizTras[j][i] = matriz[i][j];
            }
        }
        return matrizTras;
    }

    public static double[] MultiplicarTransXporY(ArrayList<DataSet> datos, double[][]matrizTras, int numeroColumnasDataSet){
        int numeroFilasDataSet=datos.size();
        double[] matrizResMulXY = new double[numeroColumnasDataSet];
        for (int i=0; i<numeroColumnasDataSet; i++){
            for (int j=0; j<numeroFilasDataSet; j++){
                matrizResMulXY[i] +=matrizTras[i][j] * datos.get(j).yield;
            }
        }
        return matrizResMulXY;
    }

    public static double[][] MultiplicarTransXporX(double[][]matriz, double[][]matrizTras, int numeroColumnasDataSet){
        int numeroFilasDataSet=matriz.length;
        double[][] matrizResMul = new double[numeroColumnasDataSet][numeroColumnasDataSet];


        for (int i=0; i<numeroColumnasDataSet; i++){
            for (int j=0; j<numeroColumnasDataSet; j++){
                for (int k=0; k<numeroFilasDataSet; k++){
                    matrizResMul[i][j]+=matrizTras[i][k]*matriz[k][j];
                }
            }
        }
        return matrizResMul;
    }

    public static double[][]GaussJordan(double[][]matrizResMul, int numeroColumnasDataSet){
        double[][] matrizIdentidad = new double[numeroColumnasDataSet][numeroColumnasDataSet];
        double[][] matrizAumentada = new double[numeroColumnasDataSet][2 * numeroColumnasDataSet];
        double[][] matrizInversa = new double[numeroColumnasDataSet][numeroColumnasDataSet];

        //MatrizIdentidad
        for (int i = 0; i < numeroColumnasDataSet; i++) {
            matrizIdentidad[i][i] = 1.0;
        }

        // Concatenar la matriz identidad a la derecha de la matriz original

        for (int i = 0; i < numeroColumnasDataSet; i++) {
            for (int j = 0; j < numeroColumnasDataSet; j++) {
                matrizAumentada[i][j] = matrizResMul[i][j];
                matrizAumentada[i][j + numeroColumnasDataSet] = matrizIdentidad[i][j];
            }
        }

        // Aplicar el método de Gauss-Jordan para llevar la matriz aumentada a su forma REFF
        for (int i = 0; i < numeroColumnasDataSet; i++) {
            // Dividir la fila i por el pivote
            double pivote = matrizAumentada[i][i];
            for (int j = 0; j < 2 * numeroColumnasDataSet; j++) {
                matrizAumentada[i][j] /= pivote;
            }

            // Hacer ceros por encima y por debajo del pivote
            for (int k = 0; k < numeroColumnasDataSet; k++) {
                if (k != i) {
                    double factor = matrizAumentada[k][i];
                    for (int j = 0; j < 2 * numeroColumnasDataSet; j++) {
                        matrizAumentada[k][j] -= factor * matrizAumentada[i][j];
                    }
                }
            }
        }

        // Separar la parte derecha (inversa) de la matriz aumentada

        for (int i = 0; i < numeroColumnasDataSet; i++) {
            for (int j = 0; j < numeroColumnasDataSet; j++) {
                matrizInversa[i][j] = matrizAumentada[i][j + numeroColumnasDataSet];
            }
        }
        return matrizInversa;
    }

    public static double[]CalcularCoeficientes(double[][]matrizInversa, double[]matrizResMulXY, int numeroColumnasDataSet){
        double[] matrizB = new double[numeroColumnasDataSet];
        for (int i=0; i<numeroColumnasDataSet; i++){
            for (int j=0; j<numeroColumnasDataSet; j++){
                matrizB[i] += matrizInversa[i][j]*matrizResMulXY[j];
            }
        }

        for (int i=0; i<numeroColumnasDataSet; i++){
            System.out.println("B"+i+"= "+matrizB[i]);
        }
        return matrizB;
    }

    public static void Simulacion1(double[]matrizB){
        double [][] matriz50 ={
                {1, 73994.56, 122782.75, 303319.26},
                {1, 67532.53, 105751.03, 304768.73},
                {1, 77044.01, 99281.34, 140574.81},
                {1, 64664.71, 139553.16, 137962.62},
                {1, 75328.87, 144135.98, 134050.07}
        };

        System.out.println("Simulacion");
        for (int i=0; i<5; i++){
            double simulacion = matrizB[0] + matrizB[1] * matriz50[i][1]+ matrizB[2] * matriz50[i][2]+ matrizB[3] * matriz50[i][3];
            System.out.println(simulacion);
        }
    }

    public static void Simulacion2(double[]matrizB){
        double [][] matrizChemical = {
                {1, 47.5, 30.3},
                {1, 47.9, 30.5},
                {1, 50.2, 30.7},
                {1, 52.8, 30.8},
                {1, 53.2, 30.9}
        };

        for (int i=0; i<5; i++){
            double simulacion = matrizB[0] + matrizB[1] * matrizChemical[i][1]+ matrizB[2] * matrizChemical[i][2];
            System.out.println(simulacion);
        }
    }

}

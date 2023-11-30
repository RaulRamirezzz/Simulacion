import java.math.BigDecimal;
import java.util.ArrayList;

public class PolynomialRegression {
    public static void main(String[] args) {
        DataSet.Polynomial();
        ArrayList<DataSet> datos = DataSet.Datos;

        int grado = 2;
        int col = grado + 1;

        BigDecimal[][] matrizSumatorias = Matematicas.guardarMatriz(datos, grado);
        BigDecimal[][] matrizGauss = Algebra.gaussJordan(matrizSumatorias, grado);
        BigDecimal[] soluciones = Algebra.separarResultados(matrizGauss, grado);

        BigDecimal error = Matematicas.calcularError(datos, soluciones, grado);
        System.out.println("Error cuadrático medio: " + error);

        for (int i = 0; i < col; i++) {
            System.out.println("B" + i + ": " + soluciones[i]);
        }

        System.out.println("Simulaciones");
        Matematicas.simulacion(soluciones, grado);
    }
}
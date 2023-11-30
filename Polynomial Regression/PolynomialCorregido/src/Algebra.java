import java.math.BigDecimal;

public class Algebra {
    public static BigDecimal[][] gaussJordan(BigDecimal[][] matrizSumatorias, int pol) {
        int col = pol + 1;
        for (int i = 0; i < col; i++) {
            // Hacer que el elemento en la diagonal sea igual a 1
            BigDecimal pivot = matrizSumatorias[i][i];
            for (int j = i; j < col + 1; j++) {
                matrizSumatorias[i][j] = matrizSumatorias[i][j].divide(pivot, BigDecimal.ROUND_HALF_UP);
            }

            // Eliminar otros elementos en la columna
            for (int k = 0; k < col; k++) {
                if (k != i) {
                    BigDecimal factor = matrizSumatorias[k][i];
                    for (int j = i; j < col + 1; j++) {
                        matrizSumatorias[k][j] = matrizSumatorias[k][j].subtract(factor.multiply(matrizSumatorias[i][j]));
                    }
                }
            }
        }
        return matrizSumatorias;
    }

    public static BigDecimal[] separarResultados(BigDecimal[][] gauss, int pol) {
        int col = pol + 1;
        BigDecimal[] soluciones = new BigDecimal[col];
        for (int i = 0; i < col; i++) {
            soluciones[i] = gauss[i][col];
        }
        return soluciones;
    }
}
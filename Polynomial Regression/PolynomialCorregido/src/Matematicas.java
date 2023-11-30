import java.math.BigDecimal;
import java.util.ArrayList;

public class Matematicas {
    public static BigDecimal[][] guardarMatriz(ArrayList<DataSet> datos, int grado) {
        int col = grado + 1;
        int n = datos.size();
        BigDecimal[][] matrizSumatorias = new BigDecimal[col][col + 1];
        BigDecimal[] matrizSumX = new BigDecimal[2 * col];
        BigDecimal[] matrizSumY = new BigDecimal[col];

        for (int i = 0; i < datos.size(); i++) {
            BigDecimal x = BigDecimal.valueOf(datos.get(i).factor1);
            BigDecimal y = BigDecimal.valueOf(datos.get(i).yield);

            // Sumatorias de x
            for (int j = 0; j < 2 * col; j++) {
                if (matrizSumX[j] == null) {
                    matrizSumX[j] = BigDecimal.ZERO;
                }
                matrizSumX[j] = matrizSumX[j].add(x.pow(j));
            }

            // Sumatorias x*y
            for (int k = 0; k < col; k++) {
                if (matrizSumY[k] == null) {
                    matrizSumY[k] = BigDecimal.ZERO;
                }
                matrizSumY[k] = matrizSumY[k].add(x.pow(k).multiply(y));
            }
        }

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < col; j++) {
                matrizSumatorias[i][j] = matrizSumX[i + j];
            }
            matrizSumatorias[i][col] = matrizSumY[i];
        }

        return matrizSumatorias;
    }

    public static void simulacion(BigDecimal[] soluciones, int pol) {
        BigDecimal[] ejemplos = {BigDecimal.valueOf(45), BigDecimal.valueOf(78), BigDecimal.valueOf(86),
                BigDecimal.valueOf(102), BigDecimal.valueOf(53)};
        BigDecimal resultado = BigDecimal.ZERO;

        if (pol == 1) {
            for (int i = 0; i < ejemplos.length; i++) {
                resultado = soluciones[0].add(ejemplos[i].multiply(soluciones[1]));
                System.out.println("X" + ejemplos[i] + ": " + resultado);
                resultado = BigDecimal.ZERO;
            }
        } else {
            for (int i = 0; i < ejemplos.length; i++) {
                for (int j = 0; j < pol; j++) {
                    resultado = resultado.add(soluciones[j].multiply(ejemplos[i].pow(j)));
                }
                System.out.println("X" + ejemplos[i] + ": " + resultado);
                resultado = BigDecimal.ZERO;
            }
        }
    }

    public static BigDecimal calcularError(ArrayList<DataSet> datos, BigDecimal[] coeficientes, int grado) {
        int m = datos.size();
        BigDecimal errorCuadraticoMedio = BigDecimal.ZERO;

        for (DataSet dato : datos) {
            BigDecimal x = BigDecimal.valueOf(dato.factor1);
            BigDecimal y = BigDecimal.valueOf(dato.yield);

            // Calcular el valor predicho por el polinomio
            BigDecimal valorPredicho = BigDecimal.ZERO;
            for (int j = 0; j <= grado; j++) {
                valorPredicho = valorPredicho.add(coeficientes[j].multiply(x.pow(j)));
            }

            // Calcular el cuadrado de la diferencia y sumarlo al error cuadrático medio
            errorCuadraticoMedio = errorCuadraticoMedio.add(valorPredicho.subtract(y).pow(2));
        }

        // Calcular el ECM promediando las diferencias cuadráticas
        errorCuadraticoMedio = errorCuadraticoMedio.divide(BigDecimal.valueOf(m), BigDecimal.ROUND_HALF_UP);

        return errorCuadraticoMedio;
    }
}

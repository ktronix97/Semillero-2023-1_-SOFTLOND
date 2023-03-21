import java.util.Arrays;

/***
 * Clase que realiza diferentes funciones con varios cálculos númericos. 
 * @author dalia
 * @version 1.0
 *
 */

public class CálculosNúmericos {
	
	/***
	 * Calcular la raíz cuadrada
	 * @param numero
	 * @return el resultado de la raiz cuadrada de el número dado.
	 * @throws ArithmeticException
	 */
	public static double calcularRaizCuadrada(double numero) throws ArithmeticException {
	    if(numero < 0) {
	        throw new ArithmeticException("No se puede calcular la raíz cuadrada de un número negativo");
	    }
	    return Math.sqrt(numero);
	}
	
	/***
	 * Calcula la pendiente de una recta.
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return 
	 */
	
	public static double calcularPendiente(double x1, double y1, double x2, double y2) {
	    if(x1 == x2) {
	        throw new ArithmeticException("La pendiente no está definida para una recta vertical");
	    }
	    return (y2 - y1) / (x2 - x1);
	}

	/***
	 * Calcula el punto medio de una recta.
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return un arreglo con los puntos (x,y).
	 */

	public static double[] calcularPuntoMedio(double x1, double y1, double x2, double y2) {
	    double puntoMedioX = (x1 + x2) / 2;
	    double puntoMedioY = (y1 + y2) / 2;
	    double[] puntoMedio = {puntoMedioX, puntoMedioY};
	    return puntoMedio;
	}
	
    /***
     * Calcula las raíces de una ecuación cuadrática.
     * @param a
     * @param b
     * @param c
     * @return si tiene raíces retorna un arreglo con todas las raíces de la ecuación sino retorna una excepcion.
     */
	
	public static double[] calcularRaices(double a, double b, double c) {
	    double discriminante = b * b - 4 * a * c;
	    if(discriminante < 0) {
	        throw new ArithmeticException("La ecuación cuadrática no tiene raíces reales");
	    }
	    double raizDiscriminante = Math.sqrt(discriminante);
	    double x1 = (-b + raizDiscriminante) / (2 * a);
	    double x2 = (-b - raizDiscriminante) / (2 * a);
	    double[] raices = {x1, x2};
	    return raices;
	}
	//
	/***
	 * Convertir un número en base 10 a un número en base b.
	 * @param numero 
	 * @param base no debe ser cero.
	 * @return el equivalente del número en la base dada.
	 */
	public static String convertirBase(int numero, int base) {
	    if(numero < 0) {
	        throw new ArithmeticException("El número debe ser positivo");
	    }
	    if(base==0) {
	        throw new ArithmeticException("La base no debe ser cero");
	    }
	    
	    StringBuilder resultado = new StringBuilder();
	    while(numero > 0) {
	        int residuo = numero % base;
	        resultado.insert(0, residuo);
	        numero = numero / base;
	    }
	    return resultado.toString();
	}
	
	/***
	 * Ejecuta todos los métodos de la clase con valores de prueba.
	 * @param args
	 */

	public static void main(String[] args) {
		double [] puntoMedio = calcularPuntoMedio(5, 3, 2, 8);
    	try {
    		System.out.print(calcularRaizCuadrada(200));
    		System.out.print("\n");
    		System.out.print(calcularPendiente(10, 50, 34, 20));
    		System.out.print("\n");
    		System.out.print(Arrays.toString(puntoMedio));
    		System.out.print("\n");
    		System.out.print(convertirBase(10, 2));
    		System.out.print("\n");
    		System.out.print(calcularRaices(5, 10, 20));
    	
    		}
    		catch(Exception e) {
	    		System.out.print(e);
    		}
	}

}

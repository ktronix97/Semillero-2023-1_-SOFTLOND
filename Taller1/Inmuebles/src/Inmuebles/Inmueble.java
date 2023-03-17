package Inmuebles;

/**
* Esta clase denominada Inmueble modela un inmueble que posee
* como atributos un identificador, un área, una dirección y un precio
* de venta. Es la clase raíz de una jerarquía de herencia.
* @author dalia
* @version 1.0
*/

public class Inmueble {

	protected int identificadorInmobiliario;
	protected int area; 
	protected String direccion; 
	protected double precioVenta;
	
	
	Inmueble(int identificadorInmobiliario, int area, String direccion) {
		this.identificadorInmobiliario = identificadorInmobiliario;
		this.area = area;
		this.direccion = direccion;
		}
		/**
		* Método que a partir del valor del área de un inmueble, calcula su
		* precio de venta
		* @param valorArea el valor unitario por área de un determinado
		* inmueble
		* @return precio de venta del inmueble
		*/
		double calcularPrecioVenta(double valorArea) {
		precioVenta = area * valorArea;
		return precioVenta;
		}
		/**
		* Método que muestra en pantalla los datos de un inmueble
		*/
		void imprimir() {
		System.out.println("Identificador inmobiliario = " +
		identificadorInmobiliario);
		System.out.println("Area = " + area);
		System.out.println("Dirección = " + direccion);
		System.out.println("Precio de venta = $" + precioVenta);
		}
	}


import org.junit.Test;

import compra.Compra;
import producto.Producto;
import usuarios.persona.Cliente;

public class tesdecompra {
	
	@Test
	public void testCompra() {
		Cliente cliente1 = new Cliente("jorge","perez",333);
		Compra compra1= new Compra(1,0);
		Producto agua = new Producto(1,33.f,"agua",15,15.f);
		Producto coca = new Producto(2,50.f,"coca",25,25.f);
		compra1.comprar(agua);
		compra1.comprar(coca);
		cliente1.agregarCompras(compra1);		
		System.out.println("puntos "+compra1.calcularCantidadDePuntos());
		System.out.println("total: " +compra1.calcularTotalApagar());
	}
	
	
}

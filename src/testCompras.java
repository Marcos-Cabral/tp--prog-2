import org.junit.Test;

import compra.Compra;
import producto.Producto;
import sistema.Sistema;
import usuarios.persona.*;

public class testCompras {
	@Test
	public void testearCompra() {
		Sistema sistema = Sistema.getInstancia();
		Cliente c1 = new Cliente("c","c","c","c",1000.f);
		Producto p1 = new Producto(1,30.f,"p1",10,30.f);
		Producto p2 = new Producto(2,30.f,"p1",10,30.f);
		Compra compra = new Compra(0.f,c1,p1);
		
		Compra x=sistema.comprar("c", 1, "c", 1);
		
	}
}

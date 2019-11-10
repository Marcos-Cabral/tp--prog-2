import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import compra.Compra;
import local.Local;
import producto.Producto;
import usuarios.persona.*;


public class testeito {
	@Test
	public void testearQueBuscaComprasDeUnUsuario() {
		Cliente cliente1 = new Cliente("c1", "c1", "c1","c1", 33.f);
		Compra compra1 = new Compra(1,0,cliente1);
		Compra compra2= new Compra(2,0,cliente1);
		Producto p1 = new Producto(1,1.f,"p1",10,10.f);
		Producto p2 = new Producto(2,1.f,"p1",10,10.f);
		compra1.agregarCompra(p1);
		compra1.agregarCompra(p2);
		compra2.agregarCompra(p1);
		Encargado encargado1= new Encargado("c1", "c1", "c1","c1");
		Local local1 =new Local("local1",encargado1);
		local1.agregarCompra(compra1);
		local1.agregarCompra(compra2);
		ArrayList<Cliente> c = local1.buscarComprasPersona(cliente1);
		Assert.assertTrue(c.contains(cliente1));
	} 
}




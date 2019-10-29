import org.junit.Test;

import compra.Compra;
import producto.Producto;
import sistema.Sistema;
import usuarios.persona.Cliente;
import usuarios.persona.Usuario;

public class testsistema {
	@Test
	public void testSistema() { 
		Sistema sist1= new Sistema("ALMACEN");
		Cliente cliente1 = new Cliente("marcos","cabral",1);
		Usuario user= new Usuario("marcos","cabral",300.f,cliente1);
		Compra compra1 = new Compra(1,0);
		Producto agua = new Producto(1, 100.f, "agua", 10, 10.f);
		Producto coca = new Producto(2, 60.f, "coca", 10, 10.f);
		
		compra1.comprar(agua);
		compra1.comprar(coca);
		
		cliente1.agregarCompras(compra1);
		
		sist1.cargarUsuario(user);
		sist1.pagar(user, compra1);
	}
	
	
}

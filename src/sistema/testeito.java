package sistema;
import Excepciones.NoExisteExcepcion;
import Usuarios.Cliente;
import Usuarios.Encargado;
import Usuarios.Usuario;
import productos.Producto;

import org.junit.Assert;
import org.junit.Test;

public class testeito {
	
	@Test(expected = NoExisteExcepcion.class)
	public void testeDeIniciarSesion() throws NoExisteExcepcion{
		Sistema perfumeria = Sistema.getInstancia();
		Assert.assertFalse(perfumeria.IngresarAlSistema("x","d"));
	}
	@Test
	public void testParaRegistrarse(){
		Sistema perfumeria = Sistema.getInstancia();
		Usuario cliente= new Cliente("a","a","a","a",3);
		Producto p = new Producto(1,10,"1",10,20);		
		Assert.assertTrue(perfumeria.registrarse(cliente));
	}
	@Test
	public void testQuePruebaSiHayUnUsuarioLogeado() {
		Sistema perfumeria = Sistema.getInstancia();
		Usuario cliente= new Cliente("a","a","a","a",3);
		Producto p = new Producto(1,10,"1",10,20);	
		Assert.assertFalse(perfumeria.cargarProducto(p));
	}
}









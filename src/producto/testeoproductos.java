package producto;

import org.junit.Assert;
import org.junit.Test;



public class testeoproductos {
	
	@Test
	public void testQuetesteaQueSeAgregeCategoriaAProducto() {
		Categoria refrescos = new Categoria("refrescos");
		Producto agua = new Producto(1,33.f,"agua",30,15.f);
		Producto coca = new Producto(2,50.f,"coca",50,25.f);
		Assert.assertTrue(refrescos.aniadirleCategoriaAUnProducto(agua));
		Assert.assertTrue(refrescos.aniadirleCategoriaAUnProducto(coca));			
	}
	@Test
	public void testQuetesteaQueMuestraProductosPorCategoria() {
		Categoria refrescos = new Categoria("refrescos");
		Producto agua = new Producto(1,33.f,"agua",30,15.f);
		Producto coca = new Producto(2,50.f,"coca",50,25.f);
		Assert.assertTrue(refrescos.aniadirleCategoriaAUnProducto(agua));
		Assert.assertTrue(refrescos.aniadirleCategoriaAUnProducto(coca));			
		refrescos.mostrarProductosPorCategoria();	
	}	
	@Test
	public void testQueQuitaCategoriaAunProducto() {
		Categoria refrescos = new Categoria("refrescos");
		Producto agua = new Producto(1,33.f,"agua",30,15.f);
		Assert.assertTrue(refrescos.aniadirleCategoriaAUnProducto(agua));
		Assert.assertTrue(refrescos.quitarleCategoriaAUnProducto(agua));
		refrescos.mostrarProductosPorCategoria();
	}
}

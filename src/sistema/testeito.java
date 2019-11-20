package sistema;

import Excepciones.NoExisteExcepcion;
import Usuarios.Cliente;
import Usuarios.Encargado;
import Usuarios.Usuario;
import compra.Compra;
import local.Local;
import productos.Producto;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

public class testeito {   

	@Test
	public void comprarSaldo() {
		Sistema perfumeria = Sistema.getInstancia();
		Cliente cliente =new Cliente("rr", "rr", "rr", "rr", 30);
		perfumeria.registrarse(cliente);
		perfumeria.salirDelSistema();
		try {
			perfumeria.IngresarAlSistema("rr", "rr");
			perfumeria.mostrarProductos();
			Compra c1=perfumeria.compra(cliente,"ezeiza",1);			
			System.out.println(c1.calcularTotalApagar());
			System.out.println("1 es "+c1.getNumeroOrden());
			Assert.assertTrue(perfumeria.pagar(cliente,c1, 1));			
		} catch (NoExisteExcepcion e) {
			e.printStackTrace();
		}
	}
	@Test
	public void comparConpuntosError() {
		Sistema perfumeria = Sistema.getInstancia();
		Cliente cliente =new Cliente("w", "w", "w", "w", 30);
		perfumeria.salirDelSistema();
		perfumeria.registrarse(cliente);
		try {
			perfumeria.IngresarAlSistema("w", "w");
			perfumeria.mostrarProductos();
			Compra c1=perfumeria.compra(cliente,"ezeiza",1);			
			System.out.println(c1.calcularTotalApagarPuntos());
			System.out.println("2 es "+c1.getNumeroOrden());
			Assert.assertFalse(perfumeria.pagar(cliente,c1, 2));			
		} catch (NoExisteExcepcion e) {
			e.printStackTrace();
		}
	}
	@Test
	public void comparConPuntosBien() {
		Sistema perfumeria = Sistema.getInstancia();
		Cliente cliente =new Cliente("o", "o", "o", "o", 30);
		perfumeria.salirDelSistema();
		perfumeria.registrarse(cliente);
		try {
			perfumeria.IngresarAlSistema("o", "o");
			perfumeria.mostrarProductos();
			Compra c1=perfumeria.compra(cliente,"ezeiza",1);
			Compra c2=perfumeria.compra(cliente,"ezeiza",1);
			
			System.out.println(perfumeria.pagar(cliente,c1, 1));
			System.out.println("3 es "+c1.getNumeroOrden());
			System.out.println("3 es "+c2.getNumeroOrden());
			System.out.println(perfumeria.pagar(cliente,c2, 2));
		} catch (NoExisteExcepcion e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = NoExisteExcepcion.class)
	public void testeDeIniciarSesion() throws NoExisteExcepcion {
		Sistema perfumeria = Sistema.getInstancia();
		Assert.assertFalse(perfumeria.IngresarAlSistema("x", "d"));
	}

	@Test
	public void testParaRegistrarse() {
		Sistema perfumeria = Sistema.getInstancia();
		Usuario cliente = new Cliente("a", "a", "a", "a", 3);
		Assert.assertTrue(perfumeria.registrarse(cliente));
	}

	@Test
	public void testQuePruebaSiHayUnUsuarioLogeado() {
		Sistema perfumeria = Sistema.getInstancia();
		Producto p = new Producto(1, 10, "1", 10, 20);
		Assert.assertFalse(perfumeria.cargarProducto(p));
	}

	@Test
	public void testQuePruebaDetectarUnEncargado() {
		Sistema perfumeria = Sistema.getInstancia();
		Usuario encargado= new Encargado("a","a");
		perfumeria.registrarAdmin(encargado);
		try {
			perfumeria.IngresarAlSistema("admin", "admin");
			Assert.assertTrue(perfumeria.detectarEncargado(encargado));
			perfumeria.salirDelSistema();
		} catch (NoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("53");
		}
	}
	@Test
	public void testQuePruebaDetectarUnEncargadoQueEsUnCliente() {
		Sistema perfumeria = Sistema.getInstancia();
		Usuario cliente= new Cliente("r","r","r","r",3);
		Assert.assertFalse(perfumeria.detectarEncargado(cliente));
	}
	@Test
	public void testQuePruebaSiSalgoDelSistema() throws NoExisteExcepcion {
		Sistema perfumeria = Sistema.getInstancia();
		Usuario cliente= new Cliente("f","f","f","f",3);
		perfumeria.registrarse(cliente);
		perfumeria.IngresarAlSistema("f", "f");
		Boolean a = perfumeria.salirDelSistema();
		Assert.assertTrue(a);
	}
		
	@Test
	public void testQuePruebaSiEncuentraElProducto() throws NoExisteExcepcion{
		Sistema perfumeria = Sistema.getInstancia();
		Producto p1 = new Producto(1, 10, "p1", 10, 10);
		perfumeria.cargarProducto(p1);
		Assert.assertEquals(p1 ,perfumeria.buscarProducto(1));
	}
	@Test(expected = NoExisteExcepcion.class)
	public void testQuePruebaSiNoEncuentraElProducto() throws NoExisteExcepcion{
		Sistema perfumeria = Sistema.getInstancia();
		Producto p1 = new Producto(1, 10, "p1", 10, 10);
		perfumeria.cargarProducto(p1);
		perfumeria.buscarProducto(8);
	}
	@Test
	public void testQuePruebaSiEncuentraElLocal() throws NoExisteExcepcion{
		Sistema perfumeria = Sistema.getInstancia();
		Encargado jefe = new Encargado("maxi", "reyes");
		Local l1 = new Local("ezeiza", jefe);
		perfumeria.cargarLocal(l1);
		Assert.assertEquals(l1 ,perfumeria.buscarLocal("ezeiza"));
	}
	
	@Test(expected = NoExisteExcepcion.class)
	public void testQuePruebaSiNoEncuentraElLocal() throws NoExisteExcepcion{
		Sistema perfumeria = Sistema.getInstancia();
		Encargado jefe = new Encargado("maxi", "reyes");
		Local l1 = new Local("ezeiza", jefe);
		perfumeria.cargarLocal(l1);
		perfumeria.buscarLocal("tablada");
	}

	
	@Test
	public void testCancelarCompra() {
		Sistema perfumeria = Sistema.getInstancia();
		Cliente cliente10 = new Cliente("c", "c", "c", "c", 20);
		cliente10.setPuntos(30);
		Encargado jefe20 = new Encargado("carlos", "perez");
		Local l20 = new Local("lala", jefe20);
		Producto p400 = new Producto(2, 10, "p2", 10, 10);

		perfumeria.registrarAdmin(jefe20);
		perfumeria.registrarse(cliente10);
		//perfumeria.IngresarAlSistema("c", "c");
		perfumeria.salirDelSistema();
		try {
			perfumeria.IngresarAlSistema("admin", "admin");
			perfumeria.cargarLocal(l20);
			perfumeria.cargarProducto(p400);
			perfumeria.salirDelSistema();
			
			perfumeria.IngresarAlSistema("c", "c");
			Compra compra = perfumeria.compra(cliente10, "lala", 2);
			Compra esperada = new Compra(10, cliente10, p400);
			perfumeria.salirDelSistema();
			
			perfumeria.IngresarAlSistema("admin", "admin");
			perfumeria.cancelarCompra(l20, 1);
			perfumeria.salirDelSistema();
			
			esperada.setNumeroOrden(compra.getNumeroOrden());
			Assert.assertTrue(perfumeria.getComprasCanceladas().contains(compra));;
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
	

}

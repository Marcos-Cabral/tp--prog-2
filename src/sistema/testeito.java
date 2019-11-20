package sistema;

import Excepciones.NoExisteExcepcion;
import Usuarios.Cliente;
import Usuarios.Encargado;
import Usuarios.Usuario;
import compra.Compra;
import local.Local;
import productos.Producto;

import org.junit.Assert;
import org.junit.Test;

public class testeito { 

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
	public void testQuePruebaSiNoHayUsuarioLogeado(){
		Sistema perfumeria = Sistema.getInstancia();
		Usuario cliente= new Cliente("f","f","f","f",3);
		perfumeria.registrarse(cliente);
		Assert.assertFalse(perfumeria.usuarioLogeado());
	}
	@Test
	public void testQuePruebaDetectarUnEncargado() {
		Sistema perfumeria = Sistema.getInstancia();
		Usuario encargado= new Encargado("a","a");
		perfumeria.registrarAdmin(encargado);
		try {
			perfumeria.IngresarAlSistema("admin", "admin");
			Assert.assertTrue(perfumeria.detectarEncargado(encargado));
		} catch (NoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void testQuePruebaSiNoDelSistema(){
		Sistema perfumeria = Sistema.getInstancia();
		Usuario cliente= new Cliente("t","t","t","t",3);
		try {
			perfumeria.IngresarAlSistema("t", "t");
			Boolean a = perfumeria.salirDelSistema();
			Assert.assertTrue(a);
		} catch (NoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	public void testDeComprarConPlata() {
		Sistema perfumeria = Sistema.getInstancia();
		Cliente cliente = new Cliente("mkz", "mkz", "mkz", "mkz", 10);
		Encargado jefe = new Encargado("pedro", "perez");
		Local l1 = new Local("localsito1", jefe);
		Producto p1 = new Producto(77, 10, "p1", 10, 10);

		try {
			perfumeria.registrarAdmin(jefe);
			perfumeria.registrarse(cliente);
			perfumeria.IngresarAlSistema("admin", "admin");
			Assert.assertTrue(perfumeria.cargarLocal(l1));
			Assert.assertTrue(perfumeria.cargarProducto(p1));
			perfumeria.salirDelSistema();
			perfumeria.IngresarAlSistema("mkz", "mkz");
			Compra c1 = perfumeria.comprar("localsito1", 1, 1, 1);
			Compra esperada = new Compra(10, cliente, p1);
			esperada.setNumeroOrden(2);
			perfumeria.salirDelSistema();
			Assert.assertEquals(esperada, c1);

		} catch (NoExisteExcepcion e) {

			System.out.println(e.getMessage());
		}

	}

	@Test
	public void testQueNoPuedaComprarConPlata() {
		Sistema perfumeria = Sistema.getInstancia();
		Cliente cliente = new Cliente("b", "b", "b", "b", 20);
		cliente.setSaldo(0);
		Encargado jefe = new Encargado("pedro", "perez");
		Local l1 = new Local("ezeiza", jefe);
		Producto p1 = new Producto(1, 10, "p1", 10, 10);
		perfumeria.registrarAdmin(jefe);
		perfumeria.registrarse(cliente);

		try {
			perfumeria.IngresarAlSistema("admin", "admin");
			perfumeria.cargarProducto(p1);
			perfumeria.cargarLocal(l1);
			perfumeria.salirDelSistema();
			perfumeria.IngresarAlSistema("b", "b");
			Compra c1 = perfumeria.comprar("ezeiza", 1, 1, 1);
			Compra esperada = new Compra(10, cliente, p1);
			esperada.setNumeroOrden(1);
			perfumeria.salirDelSistema();
			Assert.assertEquals(null, c1);
		} catch (NoExisteExcepcion e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testDeNoComprarConPuntos() {
		Sistema perfumeria = Sistema.getInstancia();
		Cliente cliente = new Cliente("z", "z", "z", "z", 20);
		cliente.setPuntos(0);
		Encargado jefe = new Encargado("carlos", "perez");
		Local l1 = new Local("miramar", jefe);
		Producto p4 = new Producto(2, 10, "p2", 10, 10);

		perfumeria.registrarAdmin(jefe);
		perfumeria.registrarse(cliente);

		try {
			perfumeria.IngresarAlSistema("admin", "admin");
			perfumeria.cargarProducto(p4);
			perfumeria.cargarLocal(l1);
			perfumeria.salirDelSistema();
			perfumeria.IngresarAlSistema("z", "z");
			Compra compra = perfumeria.comprar("miramar", 2, 1, 2);		
			Assert.assertEquals(null, compra);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
	@Test
	public void testDeComprarConPuntos() {
		Sistema perfumeria = Sistema.getInstancia();
		Cliente cliente = new Cliente("c", "c", "c", "c", 20);
		cliente.setPuntos(30);
		Encargado jefe = new Encargado("carlos", "perez");
		Local l1 = new Local("lala", jefe);
		Producto p4 = new Producto(2, 10, "p2", 10, 10);

		perfumeria.registrarAdmin(jefe);
		perfumeria.registrarse(cliente);
		perfumeria.salirDelSistema();
		try {
			perfumeria.IngresarAlSistema("admin", "admin");
			perfumeria.cargarLocal(l1);
			perfumeria.cargarProducto(p4);
			perfumeria.salirDelSistema();
			perfumeria.IngresarAlSistema("c", "c");
			Compra compra = perfumeria.comprar("lala", 2, 1, 2);
			Compra esperada = new Compra(10, cliente, p4);
			esperada.setNumeroOrden(4);
			Assert.assertEquals(esperada, compra);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}

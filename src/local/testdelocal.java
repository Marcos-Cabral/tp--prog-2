package local;

import org.junit.Assert;
import org.junit.Test;

import producto.Producto;
import usuarios.persona.Encargado;

public class testdelocal {
	@Test
	public void testQuePruebaSiHayEncargado() {
		Encargado encargado1= new Encargado("marcos","cabral",111);
		Local local1 = new Local("local1",encargado1);		
		Assert.assertTrue(local1.getEncargado().equals(encargado1));
	}
	@Test
	public void testQueCargaProductos() {
		Encargado encargado1= new Encargado("marcos","cabral",111);
		Local local1 = new Local("local1",encargado1);
		Producto agua = new Producto(1,33.f,"agua",30,15.f);
		Assert.assertTrue(local1.cargarProductos(agua));		
	}
	@Test
	public void testQueBuscaStock() {
		Encargado encargado1= new Encargado("marcos","cabral",111);
		Local local1 = new Local("local1",encargado1);
		Producto agua1 = new Producto(1,33.f,"agua",30,15.f);
		Producto agua2 = new Producto(1,33.f,"agua",30,15.f);
		Producto agua3 = new Producto(1,33.f,"agua",30,15.f);
		local1.cargarProductos(agua1);
		local1.cargarProductos(agua2);
		local1.cargarProductos(agua3);
		Integer resultado=local1.buscarStock(agua1);
		Integer esperado=3;
		Assert.assertEquals(esperado,resultado);
	}
	
	
	
}

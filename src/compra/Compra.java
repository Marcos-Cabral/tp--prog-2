package compra;

import java.util.ArrayList;

import producto.Producto;
import usuarios.persona.Cliente;

public class Compra {
	private Integer numeroOrden;
	private Float cantidadPuntos;
	private ArrayList <Producto> productos= new ArrayList<Producto>();
	private Cliente cliente;
	
	public Compra(Float puntos,Cliente cliente, Producto producto) {
		cantidadPuntos=puntos;
		this.cliente=cliente;
		this.productos.add(producto);
	}
	
	
	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public void agregarComprar(Producto producto) {		
		productos.add(producto);		
	}
	
	
	public Float calcularCantidadDePuntos() {
		for(Producto aux : productos) {
			this.cantidadPuntos+=aux.getValorPuntos();
		}
		return this.cantidadPuntos;
	}
	
	
	
	public Float calcularTotalApagar() {
		Float total=0.f;
		for(Producto aux : productos) {
			total+=aux.getPrecio();
		}
		return total;
	}
	
	public Float calcularTotalApagarPuntos() {
		Float total=0.f;
		for(Producto aux : productos) {
			total+=aux.getPrecioPuntos();
		}
		return total;
	}
	
	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	public Integer getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(Integer numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public Float getCantidadPuntos() {
		return cantidadPuntos;
	}

	public void setCantidadPuntos(Float cantidadPuntos) {
		this.cantidadPuntos = cantidadPuntos;
	}
	
}

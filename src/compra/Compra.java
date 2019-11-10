package compra;

import java.util.ArrayList;

import producto.Producto;
import usuarios.persona.Cliente;

public class Compra {
	private Integer numeroOrden;
	private Integer cantidadPuntos;
	private ArrayList <Producto> productos= new ArrayList<Producto>();
	private Cliente cliente;
	
	public Compra(Integer numeroOrden, Integer cantidadPuntos,Cliente cliente) {
		this.numeroOrden = numeroOrden;
		this.cantidadPuntos = cantidadPuntos;
		this.cliente=cliente;
	}	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public void agregarCompra(Producto producto) {		
		productos.add(producto);		
	}	
	public Integer calcularCantidadDePuntos() {
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

	public Integer getCantidadPuntos() {
		return cantidadPuntos;
	}

	public void setCantidadPuntos(Integer cantidadPuntos) {
		this.cantidadPuntos = cantidadPuntos;
	}
	
}

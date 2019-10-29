package compra;

import java.util.ArrayList;

import producto.Producto;

public class Compra {
	private Integer numeroOrden;
	private Integer cantidadPuntos;
	private ArrayList <Producto> productos= new ArrayList<Producto>();
	
	public Compra(Integer numeroOrden, Integer cantidadPuntos) {
		this.numeroOrden = numeroOrden;
		this.cantidadPuntos = cantidadPuntos;
	}
	
	public Boolean comprar(Producto producto) {		
		productos.add(producto);		
		return true;
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

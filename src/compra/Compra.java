package compra;
import java.util.ArrayList;
import Usuarios.Cliente;
import Usuarios.Usuario;
import productos.Producto;



public class Compra {
	public  Integer numeroOrden;
	private Integer cantidadPuntos;
	private ArrayList <Producto> productos= new ArrayList<Producto>();
	private Usuario cliente;
	
	public Integer getNumeroOrden() {
		return numeroOrden;
	}

	public  void setNumeroOrden(Integer orden) {
		this.numeroOrden = orden;
	}

	public Compra(Integer puntos,Usuario cliente, Producto producto) {
		cantidadPuntos=puntos;
		this.cliente=(Cliente)cliente;
		this.productos.add(producto);
	}	

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void agregarComprar(Producto producto) {		
		productos.add(producto);		
	}
	
	public Integer calcularCantidadDePuntos() {
		for(Producto aux : productos) {
			this.cantidadPuntos+=aux.getValorPuntos();
		}
		return this.cantidadPuntos;
	}		
	
	public Integer calcularTotalApagar() {
		Integer total=0;
		for(Producto aux : productos) {
			total+=aux.getPrecio();
		}
		return total;
	}
	
	public Integer calcularTotalApagarPuntos() {
		Integer total=0;
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

	public Integer getCantidadPuntos() {
		return cantidadPuntos;
	}

	public void setCantidadPuntos(Integer cantidadPuntos) {
		this.cantidadPuntos = cantidadPuntos;
	}
	
}









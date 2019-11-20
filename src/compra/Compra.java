package compra;
import java.util.ArrayList;
import Usuarios.Cliente;
import Usuarios.Usuario;
import productos.Producto;



public class Compra implements Comparable<Compra> {
	public  Integer numeroOrden;
	private Integer cantidadPuntos; 
	private ArrayList <Producto> productos= new ArrayList<Producto>();
	private Usuario cliente; 
	private Boolean pagado;
	
	
	  
	public Boolean getPagado() {
		return pagado;
	}

	public void setPagado(Boolean pagado) {
		this.pagado = pagado;
	}

	public Integer puntosAFavorDeUsuario() {
		Integer puntos=0;
		for(Producto aux : productos) {
			puntos+=aux.getPrecioPuntos();
		}
		return puntos;
	}
	
	public Integer getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(Integer numeroOrden) {
		this.numeroOrden = numeroOrden;
	}
  
	public Compra(Integer puntos,Usuario cliente, Producto producto) {
		cantidadPuntos=puntos;   
		this.cliente=(Cliente)cliente;
		this.productos.add(producto);
		this.pagado=false;
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

	
	

	@Override
	public String toString() {
		return "Compra [numeroOrden=" + numeroOrden + ", cantidadPuntos=" + cantidadPuntos + ", productos=" + productos
				+ ", cliente=" + cliente + "]";
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroOrden == null) ? 0 : numeroOrden.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compra other = (Compra) obj;
		if (numeroOrden == null) {
			if (other.numeroOrden != null)
				return false;
		} else if (!numeroOrden.equals(other.numeroOrden))
			return false;
		return true;
	}

	//@Override
	public int compareTo(Compra o) {
		return this.numeroOrden.compareTo(o.getNumeroOrden());
	}
	
	

	

	
}









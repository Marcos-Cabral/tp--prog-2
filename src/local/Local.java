package local;

import java.util.ArrayList;

import compra.Compra;
import producto.Producto;
import usuarios.persona.Encargado;

public class Local {
	
	private String nombre;
	private ArrayList <Compra> compras = new ArrayList<Compra>();
	private ArrayList <Producto> productos= new ArrayList<Producto>();
	private Encargado encargado;
	
	public Local(String nombreLocal, Encargado encargado) {
		this.nombre=nombreLocal;
		this.encargado=encargado;
	}	
	public Boolean cargarProductos(Producto producto) {		
		if(producto!=null) {
			productos.add(producto);
			return true;
		}
		return false;
	}	
	public Integer buscarStock(Producto producto) {		
		Integer stock=0;
		for(Producto aux: productos) {
			if(aux.getId().equals(producto.getId())) {
				stock++;
			}
		}		
		return stock;
	}
	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}
	public Encargado getEncargado() {
		return this.encargado;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Compra> getCompras() {
		return compras;
	}

	public void setCompras(ArrayList<Compra> compras) {
		this.compras = compras;
	}	
	
}

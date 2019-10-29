package producto;
import java.util.ArrayList;
import java.util.Iterator;

public class Categoria {
	private ArrayList<Producto> productos = new ArrayList<>();
	private String descripcionCategoria;
	
	public Categoria(String descripcionCategoria) {
		this.descripcionCategoria=descripcionCategoria;
	}	
	
	public Boolean aniadirleCategoriaAUnProducto(Producto producto) {
		
		for(Producto aux : productos) {
			if(aux.equals(producto)) { //si el producto ya esta agregado
				return false;	//no lo vuelve a agregar
			}
		}
		this.productos.add(producto);		// si no esta lo agrega
		return true;
	}	
	public Boolean quitarleCategoriaAUnProducto(Producto producto) {
		
		//Iterator <Producto> it = productos.iterator() ;
		if(productos.contains(producto)) {
			productos.remove(producto);
			return true;
		}
		/*while(it.hasNext()) {
			Producto aux = it.next();
			if(producto.equals(aux)) {
				it.remove();
				return true;
			}
		}	*/	
		return false;
	}
	public void mostrarProductosPorCategoria() {
		System.out.println(this.descripcionCategoria+": ");
		for(Producto aux : productos) {
			System.out.println(aux.getDescripcion());
		}
	}
	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	public String getDescripcionCategoria() {
		return descripcionCategoria;
	}

	public void setDescripcionCategoria(String descripcionCategoria) {
		this.descripcionCategoria = descripcionCategoria;
	}
	
}

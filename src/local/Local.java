
package local;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import compra.Compra;
import producto.Producto;
import usuarios.persona.Cliente;
import usuarios.persona.Encargado;

public class Local {
	private String nombre;
	private Set<Compra> compras = new TreeSet<Compra>();
	private Encargado encargado;

	
	public Local(String nombreLocal, Encargado encargado) {
		this.nombre=nombreLocal;
		this.encargado=encargado;
	}	
	
	public Set<Compra> buscarComprasPersona(Cliente c1){
		Set<Compra> todasLasCompras = new TreeSet<Compra>();
		for(Compra aux : compras) {
			if(aux.getCliente().equals(c1)) {
				todasLasCompras.add(aux);
			}
		}
		return todasLasCompras;
	}
	
	public boolean eliminarCompra(Integer nro) {
		Iterator<Compra> it = this.compras.iterator();
		while(it.hasNext()) {
			Compra aux = it.next();
			if(aux.getNumeroOrden().equals(nro)) {
				it.remove();
				return true;
			}
		}
		return false;
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

	public void añadirCompra(Compra compra) {
		compras.add(compra);
	}

	public Set<Compra> getCompras() {
		return compras;
	}


	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

	
	
}

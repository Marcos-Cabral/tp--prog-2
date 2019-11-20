package local;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import Usuarios.Cliente;
import Usuarios.Encargado;
import Usuarios.Usuario;
import compra.Compra;

public class Local implements Comparable<Local>{
	private String nombre;
	private Set<Compra> compras = new TreeSet<Compra>();
	private Encargado encargado;

	public Local(String nombreLocal, Encargado encargado) {
		this.nombre = nombreLocal;
		this.encargado = encargado;
	}

	// buscar compras por persona
	public Set<Compra> buscarComprasPersona(Cliente c1) {
		Set<Compra> todasLasCompras = new TreeSet<Compra>();
		for (Compra aux : compras) {
			if (aux.getCliente().equals(c1)) {
				todasLasCompras.add(aux);
			}
		}
		return todasLasCompras;
	}

	// cancelar compra
	public boolean eliminarCompra(Integer nro) {
		Iterator<Compra> it = this.compras.iterator();
		while (it.hasNext()) {
			Compra aux = it.next();
			if (aux.getNumeroOrden().equals(nro)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	public void añadirCompra(Compra compra) {
		compras.add(compra);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Compra> getCompras() {
		return compras;
	}

	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

	public Encargado getEncargado() {
		return encargado;
	}

	public void setEncargado(Encargado encargado) {
		this.encargado = encargado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Local other = (Local) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Local [nombre=" + nombre + "]";
	}

	public int compareTo(Local o) {
		return this.nombre.compareTo(o.getNombre());
	}
}

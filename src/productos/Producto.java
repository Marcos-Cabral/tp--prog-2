package productos;

import Usuarios.Usuario;

public class Producto implements Comparable<Producto>{
	private Integer id;
	private Integer precio;
	private String descripcion;
	private Integer valorPuntos;
	private Integer precioPuntos;
	
	public Producto(Integer id, Integer precio, String descripcion, Integer valorPuntos, Integer precioPuntos) {
		this.id = id;
		this.precio = precio; 
		this.descripcion = descripcion;
		this.valorPuntos = valorPuntos;
		this.precioPuntos = precioPuntos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getValorPuntos() {
		return valorPuntos;
	}

	public void setValorPuntos(Integer valorPuntos) {
		this.valorPuntos = valorPuntos;
	}

	public Integer getPrecioPuntos() {
		return precioPuntos;
	}

	public void setPrecioPuntos(Integer precioPuntos) {
		this.precioPuntos = precioPuntos;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", precio=" + precio + ", descripcion=" + descripcion + ", valorPuntos="
				+ valorPuntos + ", precioPuntos=" + precioPuntos + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Producto other = (Producto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	//@Override
	public int compareTo(Producto o) {
		return this.id.compareTo(o.getId());
	}
	
}

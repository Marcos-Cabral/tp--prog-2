package producto;

public class Producto {
	private Integer id;
	private Float precio;
	private String descripcion;
	private Integer valorPuntos;
	private Float precioPuntos;
	
	public Producto(Integer id, Float precio, String descripcion, Integer valorPuntos, Float precioPuntos) {
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
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
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
	public Float getPrecioPuntos() {
		return precioPuntos;
	}
	public void setPrecioPuntos(Float precioPuntos) {
		this.precioPuntos = precioPuntos;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
		result = prime * result + ((precioPuntos == null) ? 0 : precioPuntos.hashCode());
		result = prime * result + ((valorPuntos == null) ? 0 : valorPuntos.hashCode());
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
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		if (precioPuntos == null) {
			if (other.precioPuntos != null)
				return false;
		} else if (!precioPuntos.equals(other.precioPuntos))
			return false;
		if (valorPuntos == null) {
			if (other.valorPuntos != null)
				return false;
		} else if (!valorPuntos.equals(other.valorPuntos))
			return false;
		return true;
	}
	
}

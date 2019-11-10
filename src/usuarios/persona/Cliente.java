package usuarios.persona;

import java.util.ArrayList;

import compra.Compra;

public class Cliente extends Usuario{
	private Float saldo;
	public Cliente(String nombre, String apellido, String email, String password,Float saldo) {
		super(nombre, apellido, email, password);
		this.saldo=saldo;
	} 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (saldo == null) {
			if (other.saldo != null)
				return false;
		} else if (!saldo.equals(other.saldo))
			return false;
		return true;
	}	
}

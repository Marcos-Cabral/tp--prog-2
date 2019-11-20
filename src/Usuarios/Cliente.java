package Usuarios;

public class Cliente extends Usuario{
	private Integer puntos;
	private Integer saldo;
	
	public Cliente(String nombre, String apellido, String email, String password,Integer saldo) {
		super(nombre, apellido, email, password);
		this.saldo=saldo;
		this.puntos=0;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}
	
}

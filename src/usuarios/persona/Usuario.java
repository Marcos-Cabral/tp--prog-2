package usuarios.persona;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Usuario {
	private String nombre;
	private String apellido;	
	private String email;
	private String password;
	private Float saldo;
	private Float puntos;
	
	public Usuario(String nombre, String apellido, String email, String password, Float saldo) {
		this.puntos=0.f;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.saldo = saldo;
	}
	
	public Float getPuntos() {
		return puntos;
	}

	public void setPuntos(Float puntos) {
		this.puntos = puntos;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Float getSaldo() {
		return saldo;
	}
	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}
	

	
	
}

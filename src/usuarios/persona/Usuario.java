package usuarios.persona;

import java.util.ArrayList;
import java.util.Iterator;

public class Usuario {
	
	private String email;
	private String password;
	private Float saldo;
	protected Cliente cliente;
	
	public Usuario(String email,String password, Float saldo, Cliente cliente) {
		this.email=email;
		this.password=password;
		this.saldo=saldo;
		this.cliente=cliente;
	}	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

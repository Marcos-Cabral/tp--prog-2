package sistema;

import java.util.ArrayList;
import java.util.Iterator;

import compra.Compra;
import producto.Producto;
import usuarios.persona.Usuario;

public class Sistema {
	private String nombre;
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private ArrayList <Compra> compras= new ArrayList<Compra>();
	
	public Sistema (String nombre) {
		this.nombre=nombre;
	}	
	
	public boolean pagar(Usuario user, Compra compra) {
		for(Usuario aux : usuarios) {
			if(aux.getPassword().equals(user.getPassword())) {
				compras.addAll(aux.getCliente().getCompras());
				for(Compra auxcompra:compras) {
					if(auxcompra.getNumeroOrden().equals(compra.getNumeroOrden())) {
						System.out.println("Total a pagar: "+compra.calcularTotalApagar());
						Float saldo = user.getSaldo();
						saldo= saldo-compra.calcularTotalApagar();
						user.setSaldo(saldo);
						System.out.println("Saldo nuevo: "+saldo);
					}
				}
			}
		}
		
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	/*a*b*m*/
	public Boolean cargarUsuario(Usuario user) {		
		for(Usuario aux : this.usuarios) {
			if(aux.getEmail().equals(user.getEmail())) {
				return false;
			}
		}
		this.usuarios.add(user);
		return true;
	}
	public Boolean borrarUsuario(Usuario user) {
		Iterator<Usuario> it = this.usuarios.iterator();		
		while(it.hasNext()) {
			Usuario aux = it.next();
			if(aux.getEmail().equals(user.getEmail()) && aux.getPassword().equals(user.getPassword())) {
				it.remove();
				return true;
			}
		}		
		return false;
	}
	public Boolean modificarUsuario(Usuario user, String contrasenia) {		
		for(Usuario aux : this.usuarios) {
			if(aux.getEmail().equals(user.getEmail())) {
				user.setPassword(contrasenia);
				return true;
			}
		}
		return false;
	}
}

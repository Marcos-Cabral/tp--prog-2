package local;

import java.util.ArrayList;

import compra.Compra;
import producto.Producto;
import usuarios.persona.Cliente;
import usuarios.persona.Encargado;
import usuarios.persona.Usuario;

public class Local {
	
	private String nombre;
	private ArrayList <Compra> compras = new ArrayList<Compra>();
	private Encargado encargado;
	
	public Local(String nombreLocal, Encargado encargado) {
		this.nombre=nombreLocal;
		this.encargado=encargado;
	}	
	public void agregarCompra(Compra compra) {
		compras.add(compra);
	}
	
	public  ArrayList<Cliente> buscarComprasPersona(Cliente cliente) {
	 ArrayList<Cliente> clientes= new ArrayList<Cliente>();
		for(Compra aux : compras) {			
			if(aux.getCliente().equals(cliente)) { 
				clientes.add(cliente);
			}
		}
			
		return clientes;
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

	public ArrayList<Compra> getCompras() {
		return compras;
	}

	public void setCompras(ArrayList<Compra> compras) {
		this.compras = compras;
	}
	
}

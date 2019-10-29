package usuarios.persona;

import java.util.ArrayList;

import compra.Compra;

public class Cliente extends Persona{
	protected ArrayList <Compra> compras = new ArrayList<Compra>();
	
	public Cliente(String nombre, String apellido, Integer dni) {
		super(nombre,apellido,dni);
	}
	
	public void agregarCompras(Compra compra) {		
		compras.add(compra);
	}
	
	public ArrayList<Compra> getCompras() {
		return compras;
	}

	public void setCompras(ArrayList<Compra> compras) {
		this.compras = compras;
	}
	
	
	
}

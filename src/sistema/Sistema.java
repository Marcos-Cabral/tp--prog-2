package sistema;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import compra.Compra;
import local.Local;
import producto.Producto;
import usuarios.persona.Cliente;
import usuarios.persona.Usuario;

public class Sistema {
	private String nombre;
	private Set<Usuario> usuarios = new TreeSet<Usuario>();
	private Set<Compra> compras= new TreeSet<Compra>();
	private Set<Local> local= new HashSet<Local>();
	private Set<Producto> productos = new TreeSet<Producto>();
	private Usuario usuarioLogeado;
	private Integer NroOrden=0;
	private static Sistema instancia;
	
	private Sistema (String nombre) {
		this.nombre=nombre;
	}	
	
	public static Sistema getInstancia(String nombre) {
		if(instancia==null) {
			instancia= new Sistema(nombre);
		}
		return instancia;
	}
	
	
	public void eliminarCompraSistema(Integer nro) {
		Iterator<Compra> it = this.compras.iterator();
		while(it.hasNext()) {
			Compra aux = it.next();
			if(aux.getNumeroOrden().equals(nro)) {
				it.remove();
			}
		}
	}
	
	//lo tiene que hacer un admin
	public void cancelarCompra(Local l1,Integer nro) {
		eliminarCompraSistema(nro);
		if(l1.eliminarCompra(nro)) {
			System.out.println("La compra ha sido cancelada");
		}
	}

	public boolean pagar(Compra compra, Integer pago) {
			if(pago==1) {
				Float total = compra.getCliente().getSaldo(); //total de saldo
				if(total>=compra.calcularTotalApagar()) { //si te alcanza para pagar
					Float saldoFinal = total-compra.calcularTotalApagar(); //al total que tengo le resto lo que hay que pagar
					compra.getCliente().setSaldo(saldoFinal);
					return true;
				}else {
					//excepcion
					return false;
				}
			}else {
				Float puntos=compra.getCliente().getPuntos();//asigno los puntos que tengo
				if(puntos>=compra.calcularTotalApagarPuntos()) {//veo si los puntos que tengo son mayor a lo que me sale pagar en puntos
					Float puntosFinales = puntos-compra.calcularTotalApagarPuntos();//saco los puntos de la compra a los puntos que ya tengo
					Float puntosGanados =compra.calcularCantidadDePuntos()+puntosFinales;//a esos puntos le agrego lo ganado por la compra
					compra.getCliente().setSaldo(puntosGanados);//se lo asigno a el saldo
				}else {
					return false;//excp
				}
			}
			return false;
		}
	
	//buscar todas las compras que hizo una persona en todos los locales.
	public Set<Compra> buscarComprasPersonaTodosLocales(Cliente c1){
		Set<Compra> todasLasCompras = new TreeSet<Compra>();
		for(Compra aux : compras) {
			if(aux.getCliente().equals(c1)) {
				todasLasCompras.add(aux);
			}
		}
		return todasLasCompras;
	}
	
	
	public Set<Compra> comprasRealizadas(Cliente c1, String local){
		if(local!="") {
			Local l1=buscarLocal(local);
			return l1.buscarComprasPersona(c1);
		}else {
			return buscarComprasPersonaTodosLocales(c1);
		}
	}
	
	//busca un local especifico
	public Local buscarLocal(String nombre) {
		for(Local aux : local) {
			if(aux.getNombre().equals(nombre)) {
				return aux;
			}
		}
		return null;
	}
	
	//busca un producto especifico
	public Producto buscarProducto(Integer id) {
		for(Producto aux : productos) {
			if(aux.getId().equals(id)) {
				return aux;
			}
		}
		return null;
	}
	
	//busca un usuario especifico
	public Usuario buscarUsuario(String mail) {
		for(Usuario aux : usuarios) {
			if(aux.getEmail().equals(mail)) {
				return aux;
			}
		}
		return null;
	}
	
	public boolean IngresarAlSistema(String email,String password) {
		Usuario ahora = buscarUsuario(email);
		
	}
	
	public Compra comprar(String nombre, Integer id, String mail, Integer comprar) {
		Local l1 = buscarLocal(nombre);
		if(l1!=null) {
			Producto p1 = buscarProducto(id);
			if(p1!=null) {
				Cliente u1 =(Cliente)buscarUsuario(mail);
				if(u1!=null) { //si existe el local,usuario, y producto, y confirma la compra :
					if(comprar==1) {
						NroOrden++;
						Compra c1 = new Compra(p1.getPrecioPuntos(),u1,p1);
						c1.setNumeroOrden(NroOrden);
						compras.add(c1);
						l1.añadirCompra(c1);
						if(pagar(c1,1)) {
							//se paga
						}
				}else {
					cancelarCompra(l1,NroOrden);
				}
			}
		}
		
	}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	/*a*b*m*/
	//que lo haga un admin todo
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

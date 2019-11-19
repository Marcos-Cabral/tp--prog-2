package sistema;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import Excepciones.NoExisteExcepcion;
import Usuarios.Cliente;
import Usuarios.Encargado;
import Usuarios.Usuario;
import compra.Compra;
import local.Local;
import productos.Producto;

public class Sistema {
	private String nombre;
	private Set<Usuario> usuarios;
	private Set<Compra> compras = new TreeSet<Compra>();
	private Set<Local> local;
	private Set<Producto> productos;
	private Usuario usuarioLogeado;
	private Integer NroOrden = 0;
	private static Sistema instancia;

	private Sistema() {
		this.nombre = "sistema";
		Encargado jefe = new Encargado("maxi", "reyes");
		usuarios = new TreeSet<Usuario>();
		productos = new TreeSet<Producto>();
		compras = new TreeSet<Compra>();
		Producto p1 = new Producto(1, 10, "p1", 10, 10);
		Producto p2 = new Producto(2, 15, "p2", 15, 20);
		Producto p3 = new Producto(3, 20, "p3", 20, 30);
		productos.add(p1);
		productos.add(p2);
		productos.add(p3);
		Local l1 = new Local("ezeiza", jefe);
		Local l2 = new Local("lomas", jefe);
		Local l3 = new Local("turdera", jefe);
		local = new HashSet<Local>();
		local.add(l1);
		local.add(l2);
		local.add(l3);
	}

	public static Sistema getInstancia() {
		if (instancia == null) {
			instancia = new Sistema();
		}
		return instancia;
	}

	// METODOS DE UN ADMIN
	public void eliminarCompraSistema(Integer nro) {

		Iterator<Compra> it = this.compras.iterator();
		while (it.hasNext()) {
			Compra aux = it.next();
			if (aux.getNumeroOrden().equals(nro)) {
				it.remove();
			}
		}

	}

	public void cancelarCompra(Local l1, Integer nro) {
		if (detectarEncargado(usuarioLogeado)) {
			eliminarCompraSistema(nro);
			if (l1.eliminarCompra(nro)) {
				System.out.println("La compra ha sido cancelada");
			}
		} else {
			System.out.println("No eres un admin");
		}
	}

	public Usuario buscarUsuario(String email) throws NoExisteExcepcion {

		for (Usuario aux : usuarios) {
			if (aux.getEmail().equals(email)) {
				return aux;
			}
		}

		throw new NoExisteExcepcion("No existe el usuario");
	}

	// VER SI ESTA LOGEADO
	public Boolean cargarProducto(Producto producto) {
		if (usuarioLogeado()) {
			if (detectarEncargado(usuarioLogeado)) {
				for (Producto aux : this.productos) {
					if (aux.getId().equals(producto.getId())) {
						return false;
					}
				}
				this.productos.add(producto);
				return true;
			}
		}
		
		return false;
	}

	public Boolean cargarLocal(Local local) {
		if (usuarioLogeado()) { 
			if (detectarEncargado(usuarioLogeado)) {
				for (Local aux : this.local) {
					if (aux.getNombre().equals(local.getNombre())) {
						return false;
					}
				}
				this.local.add(local);
				return true;
			}
		} else {
			System.out.println("Logeese");
		}
		return false;
	}

	// YA ESTA CREO
	public Boolean borrarUsuario(String email) throws NoExisteExcepcion {

		Usuario user = buscarUsuario(email);
		if (usuarioLogeado()) {
			if (detectarEncargado(usuarioLogeado)) {
				Iterator<Usuario> it = this.usuarios.iterator();
				while (it.hasNext()) {
					Usuario aux = it.next();
					if (aux.getEmail().equals(user.getEmail()) && aux.getPassword().equals(user.getPassword())) {
						it.remove();
						return true;
					}
				}
			} else {
				System.out.println("No es admin");
				return false;
			}
		} else {
			System.out.println("Logeese");
			return false;
		}

		throw new NoExisteExcepcion("No existe el usuario");
	}

	// CLIENTE
	public boolean pagar(Compra compra, Integer pago) {
		Cliente cliente = (Cliente) compra.getCliente();
		if (pago == 1) { // 1 para pagar con saldo
			Integer total = cliente.getSaldo();

			if (total >= compra.calcularTotalApagar()) { // si te alcanza para pagar
				Integer saldoFinal = total - compra.calcularTotalApagar(); // al total que tengo le resto lo que hay que
																			// pagar
				cliente.setSaldo(saldoFinal);
				System.out.println("Pago sin problemas");
				return true; // se pago bien con saldo
			} else {
				System.out.println("No posee saldo suficiente");
				return false; // no hay plata
			}
		} else {
			Integer puntos = cliente.getPuntos();// asigno los puntos que tengo

			if (puntos >= compra.calcularTotalApagarPuntos()) {// veo si los puntos que tengo son mayor a lo que me sale
																// pagar en puntos
				Integer puntosFinales = puntos - compra.calcularTotalApagarPuntos();// saco los puntos de la compra a
																					// los
																					// puntos que ya tengo
				Integer puntosGanados = compra.calcularCantidadDePuntos() + puntosFinales;// a esos puntos le agrego lo
																							// ganado por la compra
				cliente.setSaldo(puntosGanados);// se lo asigno a el saldo

				System.out.println("pago sin problemas");
				return true;
			}
			System.out.println("puntos insuficientes");
			return false;
		}

	}

	public void mostrarUsuarios() {
		if (usuarioLogeado()) {
			for (Usuario aux : usuarios) {
				System.out.println(aux.toString());
			}
		}
	}

	public void mostrarLocales() {
		if (usuarioLogeado()) {
			for (Local aux : local) {
				System.out.println(aux.toString());
			}
		}
	}

	public void mostrarCompras() {
		for (Compra aux : compras) {
			System.out.println(aux.toString());
		}
	}

	public void mostrarProductos() {
		if (usuarioLogeado()) {
			for (Producto aux : productos) {
				System.out.println(aux.toString());
			}
		} else {
			System.out.println("Logeese");
		}
	}

	public void agregarCompras(Compra c1) {
		compras.add(c1);
	}

	public Compra comprar(String nombreLocal, Integer id, Integer opcion, Integer opcionPago) throws NoExisteExcepcion {
		Local local = buscarLocal(nombreLocal);
		if (local != null) {
			Producto producto = buscarProducto(id);
			if (producto != null) {
				if (opcion == 1) {
					NroOrden++;
					Compra compra = new Compra(producto.getPrecioPuntos(), this.usuarioLogeado, producto);
					compra.setNumeroOrden(NroOrden);
					agregarCompras(compra);
					local.añadirCompra(compra);
					if (pagar(compra, opcionPago)) {
						return compra;
					}
				} else {
					cancelarCompra(local, NroOrden);
				}
			}
		}

		return null;
	}

	public Set<Compra> buscarMisComprasEnTodosLosLocales() {
		Set<Compra> todasLasCompras = new TreeSet<Compra>();
		Cliente c1 = (Cliente) this.usuarioLogeado;
		for (Compra aux : compras) {
			if (aux.getCliente().equals(c1)) {
				todasLasCompras.add(aux);
			}
		}
		return todasLasCompras;
	}

	public Set<Compra> comprasRealizadas(String local) throws NoExisteExcepcion {
		Cliente c1 = (Cliente) usuarioLogeado;
		if (local != "") {
			Local l1 = buscarLocal(local);
			return l1.buscarComprasPersona(c1);
		} else {
			return buscarMisComprasEnTodosLosLocales();
		}
	}

	// USUARIO
	public Local buscarLocal(String nombre) throws NoExisteExcepcion {
		for (Local aux : local) {
			if (aux.getNombre().equals(nombre)) {
				return aux;
			}
		}
		throw new NoExisteExcepcion("No existe el local");
	}

	public Producto buscarProducto(Integer id) throws NoExisteExcepcion {
		for (Producto aux : productos) {
			if (aux.getId().equals(id)) {
				return aux;
			}
		}
		throw new NoExisteExcepcion("No existe el producto");
	}

	// Sistema
	public boolean usuarioLogeado() {
		if (usuarioLogeado == null) {
			return false; // devuelve falso si no hay nadie
		}
		return true; // true si hay un usuario logeado
	}

	public boolean IngresarAlSistema(String email, String password) throws NoExisteExcepcion {
		if (!usuarioLogeado()) {
			Usuario online = buscarUsuario(email);
			if (online.getPassword().equals(password)) {
				this.usuarioLogeado = online;
				System.out.println("Logeado");
				return true;
			} else {
				System.out.println("Password incorrecta");
			}
		} else {
			System.out.println("Ya se encuentra en el sistema");
			return false;
		}
		return false;
	}

	public boolean salirDelSistema() {
		if (usuarioLogeado()) {// si la persona esta en el sistema
			usuarioLogeado = null;// lo saco
			System.out.println("Salio");
			return true;
		}
		System.out.println("Su cuenta ya se encontraba fuera del sistema");
		return false;
	}

	public boolean detectarEncargado(Usuario user) {
		if (user instanceof Encargado) {
			return true;
		}
		return false;
	}

	public void registrarAdmin(Usuario user) {
		usuarios.add(user);
	}

	public Boolean registrarse(Usuario user) {
		for (Usuario aux : this.usuarios) {
			if (aux.getEmail().equals(user.getEmail())) {
				return false;
			}
		}
		this.usuarios.add(user);
		return true;
	}

	public static void main(String[] args) {
		Sistema perfumeria = getInstancia();
		// elegir admin o cliente
		// sos cliente:
		// Usuario cliente= new Cliente("a","a","a","a",3);
		// sos admin:
		Usuario admin1 = new Encargado("a", "a");
		Producto p = new Producto(1, 10, "1", 10, 20);

		perfumeria.registrarse(admin1);
		try {
			perfumeria.IngresarAlSistema("admin", "admin");
		} catch (NoExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		perfumeria.cargarProducto(p);
		perfumeria.mostrarProductos();

	}

}

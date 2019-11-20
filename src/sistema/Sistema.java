package sistema;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import Excepciones.NoExisteExcepcion;
import Usuarios.Cliente;
import Usuarios.Cript;
import Usuarios.Encargado;
import Usuarios.Usuario;
import compra.Compra;
import local.Local;
import productos.Producto;

public class Sistema {
	private String nombre;
	private Set<Usuario> usuarios;
	private Set<Compra> compras = new TreeSet<Compra>();
	private Set<Compra> comprasCanceladas = new TreeSet<Compra>();
	private Set<Local> local;
	private Set<Producto> productos;
	private Usuario usuarioLogeado;
	private Integer NroOrden = 0;
	private static Sistema instancia;
	private String encriptPass;

	private Sistema() {
		this.nombre = "sistema";
		Encargado jefe = new Encargado("maxi", "reyes");
		usuarios = new TreeSet<Usuario>();
		productos = new TreeSet<Producto>();
		compras = new TreeSet<Compra>();
		comprasCanceladas = new TreeSet<Compra>();
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
	public boolean eliminarCompraSistema(Integer nro) {

		Iterator<Compra> it = this.compras.iterator();
		while (it.hasNext()) {
			Compra aux = it.next();
			if (aux.getNumeroOrden().equals(nro)) {
				if (!aux.getPagado()) {
					comprasCanceladas.add(aux);
					it.remove();
					return true;
				} else {
					System.out.println("La compra no se puede cancelar porque ya se pago");
					return false;
				}
			}
		}
		return false;
	}

	public void cancelarCompra(Local l1, Integer nro) {
		if (detectarEncargado(usuarioLogeado)) {
			if (eliminarCompraSistema(nro)) {
				if (l1.eliminarCompra(nro)) {
					System.out.println("La compra ha sido cancelada");
				}
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

	public boolean pagarSaldo(Cliente c1, Compra compra) {
		if (usuarioLogeado instanceof Cliente) {
			System.out.println("saldo:" + c1.getSaldo());
			Integer total = c1.getSaldo();
			if (total >= compra.calcularTotalApagar()) {
				Integer puntosDeEstaCompra = compra.puntosAFavorDeUsuario();
				c1.setPuntos(puntosDeEstaCompra);
				Integer saldoFinal = total - compra.calcularTotalApagar();
				c1.setSaldo(saldoFinal);
				compra.setPagado(true);
				System.out.println("Pago con efectivo sin problemas");
				System.out.println("Su saldo actual es de " + c1.getSaldo());
				return true;
			} else {
				compra.setPagado(false);
				System.out.println("No posee saldo suficiente");
				return false;
			}
		}
		System.out.println("no es cliente");
		return false;
	}

	public boolean pagarPuntos(Cliente c1, Compra compra) {
		if (usuarioLogeado instanceof Cliente) {
			System.out.println("Puntos:" + c1.getPuntos());
			Integer total = c1.getPuntos();
			if (total >= compra.calcularTotalApagarPuntos()) {
				Integer saldoFinal = total - compra.calcularTotalApagar();
				c1.setPuntos(saldoFinal);
				compra.setPagado(true);
				System.out.println("Pago con puntos sin problemas");
				System.out.println("sus puntos actuales son: " + c1.getPuntos());
				return true;
			} else {
				compra.setPagado(false);
				System.out.println("No posee puntos suficiente");
				System.out.println("Los puntos se ganan comprando con dinero");
				return false;
			}
		}
		System.out.println("no es cliente");
		return false;
	}

	// CLIENTE
	public boolean pagar(Cliente c1, Compra compra, Integer pago) {
		if (!compra.getPagado()) {

			if (pago == 1) {
				if (!pagarSaldo(c1, compra)) {
					return false;
				}
			} else if (pago == 2) {
				if (!pagarPuntos(c1, compra)) {
					return false;
				}
			}
			return true;
		}
		System.out.println("Ya habia pagado esa compra");
		return false;
	}

	public Compra compra(Cliente c1, String nombreLocal, Integer id) {
		Compra compra;
		try {
			Local local = buscarLocal(nombreLocal);
			Producto producto = buscarProducto(id);
			NroOrden++;
			compra = new Compra(producto.getPrecioPuntos(), c1, producto);
			compra.setNumeroOrden(NroOrden);
			agregarCompras(compra);
			local.añadirCompra(compra);
			compra.setPagado(false);
			return compra;
		} catch (NoExisteExcepcion e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Compra buscarCompra(Integer nro) throws NoExisteExcepcion {
		Iterator<Compra> it = this.compras.iterator();
		while (it.hasNext()) {
			Compra aux = it.next();
			if (aux.getNumeroOrden().equals(nro)) {
				return aux;
			}
		}
		throw new NoExisteExcepcion("No existe el producto");
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

	public void mostrarComprasUsuario(Set<Compra> comprasUsuario) {
		for (Compra aux : comprasUsuario) {
			System.out.println(aux.toString() + "\n");
		}
	}

	public void mostrarCompras() {
		for (Compra aux : compras) {
			System.out.println(aux.toString() + "\n");
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
			String descrip = Cript.deCript(this.encriptPass, password);
			if (online.getPassword().equals(password) && password.equals(descrip)) {

				this.usuarioLogeado = online;
				System.out.println("Logeado");
				return true;
			} else {
				System.out.println("Password incorrecta");
				return false;
			}
		}
		System.out.println("Ya se encuentra en el sistema");
		return false;

	}

	public boolean IngresarAlSistemaAdmin(String email, String password) throws NoExisteExcepcion {
		if (!usuarioLogeado()) {
			Usuario online = buscarUsuario(email);			
			if (online.getPassword().equals(password)) {

				this.usuarioLogeado = online;
				System.out.println("Logeado");
				return true;
			} else {
				System.out.println("Password incorrecta");
				return false;
			}
		}
		System.out.println("Ya se encuentra en el sistema");
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
		this.encriptPass = Cript.Cript(user.getPassword());
		this.usuarios.add(user);
		return true;
	}

	public Set<Compra> getComprasCanceladas() {
		return comprasCanceladas;
	}

	public Set<Compra> getCompras() {
		return compras;
	}

	public void setComprasCanceladas(Set<Compra> comprasCanceladas) {
		this.comprasCanceladas = comprasCanceladas;
	}
}

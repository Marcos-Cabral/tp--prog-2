package main;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import Excepciones.NoExisteExcepcion;
import Usuarios.Cliente;
import Usuarios.Encargado;
import Usuarios.Usuario;
import compra.Compra;
import local.Local;
import productos.Producto;
import sistema.Sistema;

public class ClasePrincipal {


	public static void main(String[] args) {
		Sistema sistema = Sistema.getInstancia();
		Usuario user = new Cliente("", "", "", "", 0);
		Usuario admin = new Encargado("", "");
		Scanner teclado = new Scanner(System.in);
		Integer opcion;
		do {
			System.out.println("Bienvenido, antes de entrar al sistema, diganos es 1)Cliente.2)Admin.0)salir");
			opcion = teclado.nextInt();
			if (opcion == 1) {
				System.out.println("Usted ingreso al sistema de clientes");
				do {
					System.out.println("1.Registrarse");
					System.out.println("2.Logearse");
					System.out.println("3.Cerrar sistema");
					opcion = teclado.nextInt();
					switch (opcion) {
					case 1:
						System.out.println("Ingrese nombre");
						String name = teclado.next();
						System.out.println("Ingrese apellido");
						String subname = teclado.next();
						System.out.println("Ingrese email");
						String email = teclado.next();
						System.out.println("Ingrese contrasenia");
						String password = teclado.next();
						System.out.println("Ingrese saldo");
						Integer saldo = teclado.nextInt();
						user = new Cliente(name, subname, email, password, saldo);
						while (!sistema.registrarse(user)) {
							System.out.println("Ingrese otro email");
							email = teclado.next();
							user = new Cliente(name, subname, email, password, saldo);
						}

						System.out.println("Cliente registrado");
						break;
					case 2:
						System.out.println("Ingrese email");
						email = teclado.next();
						System.out.println("Ingrese contrasenia");
						password = teclado.next();

						try {
							sistema.IngresarAlSistema(email, password);
							if (!sistemaCliente(user, sistema)) {
								opcion = 1;
							}
						} catch (NoExisteExcepcion e) {
							opcion = 1;
							System.out.println(e.getMessage());
						}
						break;
					case 3:
						opcion = 2;
						break;
					default:
						System.out.println("Ingrese opcion valida");
						break;

					}
				} while (opcion != 2);

			} else if (opcion == 2) {
				System.out.println("Usted ingreso al sistema de admins");
				do {
					System.out.println("1.Registrarse");
					System.out.println("2.Logearse");
					System.out.println("4.Salir");
					opcion = teclado.nextInt();
					switch (opcion) {
					case 1:
						System.out.println("Ingrese nombre");
						String name = teclado.next();
						System.out.println("Ingrese apellido");
						String subname = teclado.next();
						admin = new Encargado(name, subname);

						sistema.registrarAdmin(admin);
						System.out.println(
								"Admin registrado, recuerde ingresar con la contraseña y email dado por el trabajo");
						break;

					case 2:
						System.out.println("Ingrese email");
						String email = teclado.next();
						System.out.println("Ingrese contrasenia");
						String password = teclado.next();

						if (email.equals("admin") && password.equals("admin")) {
							try {
								sistema.IngresarAlSistema(email, password);
								if (!sistemaAdmin(admin, sistema)) {
									opcion = 1;
								}
							} catch (NoExisteExcepcion e) {
								opcion = 1;
								System.out.println(e.getMessage());
							}
						} else {
							System.out.println("no es un admin");
						}
						break;

					case 3:
						break;
					case 4:
						System.out.println("Adios...");
						break;
					default:
						System.out.println("Ingrese opcion valida");
						break;
					}
				} while (opcion != 2 && opcion != 4);
			} else {
				break;
			}
		} while (opcion != 3);
	}

	public static boolean sistemaAdmin(Usuario admin, Sistema sistema) {
		Scanner teclado = new Scanner(System.in);
		Integer opcion;
		do {
			System.out.println("1.Mostrar productos");
			System.out.println("2.Mostrar locales");
			System.out.println("3.Mostrar usuarios");
			System.out.println("4.Eliminar usuarios");
			System.out.println("5.Registrar usuarios");
			System.out.println("6.Cargar productos");
			System.out.println("7.Cargar locales");
			System.out.println("8.Cancelar compra");
			System.out.println("9.Salir de la sesion");
			opcion = teclado.nextInt();

			switch (opcion) {
			case 1:
				sistema.mostrarProductos();
				break;
			case 2:
				sistema.mostrarLocales();
				break;
			case 3:
				sistema.mostrarUsuarios();
				break;
			case 4:
				sistema.mostrarUsuarios();
				System.out.println("Ingrese el email a eliminar");
				String email = teclado.next();
				try {
					sistema.borrarUsuario(email);
					System.out.println("Usuario eliminado");
				} catch (NoExisteExcepcion e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				System.out.println("Ingrese nombre");
				String name = teclado.next();
				System.out.println("Ingrese apellido");
				String subname = teclado.next();
				System.out.println("Ingrese email");
				email = teclado.next();
				System.out.println("Ingrese contrasenia");
				String password = teclado.next();
				System.out.println("Ingrese saldo");
				Integer saldo = teclado.nextInt();

				Usuario user = new Cliente(name, subname, email, password, saldo);

				while (!sistema.registrarse(user)) {
					System.out.println("Ingrese otro email");
					email = teclado.next();
					user = new Cliente(name, subname, email, password, saldo);
				}

				System.out.println("Cliente registrado");
				break;

			case 6:
				System.out.println("Ingrese id");
				Integer id = teclado.nextInt();
				System.out.println("Ingrese precio");
				Integer precio = teclado.nextInt();
				System.out.println("Ingrese descripion");
				String descripcion = teclado.next();
				System.out.println("Ingrese valor puntos");
				Integer valor = teclado.nextInt();
				System.out.println("Ingrese precio puntos");
				Integer preciop = teclado.nextInt();
				Producto producto = new Producto(id, precio, descripcion, valor, precio);

				while (!sistema.cargarProducto(producto)) {
					System.out.println("Ingrese un id distinto");
					id = teclado.nextInt();
				}

				break;
			case 7:
				System.out.println("Ingrese nombre de local");
				String nombre = teclado.next();
				Local local = new Local(nombre, (Encargado) admin);
				while (!sistema.cargarLocal(local)) {
					System.out.println("Ingrese otro nombre de local");
					nombre = teclado.next();
				}
				break;
			case 8:
				break;
			case 9:
				sistema.salirDelSistema();
				opcion = 9;
				break;
			default:
				System.out.println("Ingrese opcion valida");
				break;
			}

		} while (opcion != 9);
		return false;
	}

	public static boolean sistemaCliente(Usuario cliente, Sistema sistema) {
		Scanner teclado = new Scanner(System.in);
		Integer opcion;
		do {
			System.out.println("1.Mostrar Productos");
			System.out.println("2.Mostrar Locales");
			System.out.println("3.Comprar");
			System.out.println("4.Pagar");
			System.out.println("5.Ver mis compras");
			System.out.println("6.Salir de la sesion");
			opcion = teclado.nextInt();

			switch (opcion) {
			case 1:
				sistema.mostrarProductos();
				break;
			case 2:
				sistema.mostrarLocales();
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				sistema.salirDelSistema();
				opcion = 6;
				break;
			default:
				System.out.println("Ingrese opcion valida");
				break;
			}

		} while (opcion != 6);
		return false;
	}
}

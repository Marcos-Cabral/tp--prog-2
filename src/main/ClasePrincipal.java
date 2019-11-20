package main;

import java.util.HashSet;
import java.util.InputMismatchException;
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


	private static Scanner teclado;

	public static void main(String[] args) {
		Sistema sistema = Sistema.getInstancia();
		Usuario user = new Cliente("", "", "", "", 0);
		Usuario admin = new Encargado("", "");
		teclado = new Scanner(System.in);
		Integer opcion;
		do {
			System.out.println("Bienvenido, antes de entrar al sistema, diganos es 1)Cliente.2)Admin.0)salir");
			
			boolean flag = true;
			opcion = 9;
			do {
				try
				{
					opcion = teclado.nextInt();
					flag = false;
				}
				catch(InputMismatchException e) 
				{
					System.out.println("Ingrese un numero");
					teclado.nextLine();continue; 
				}
			}while(flag == true);

				
			if(opcion == 1) {
				System.out.println("Usted ingreso al sistema de clientes");
				do {
					System.out.println("1.Registrarse");
					System.out.println("2.Logearse");
					System.out.println("3.Cerrar sistema");
					
					
					flag = true;
					opcion = 9;
					do {
						try
						{
							opcion = teclado.nextInt();
							flag = false;
						}
						catch(InputMismatchException e) 
						{
							System.out.println("Ingrese un numero");
							teclado.nextLine();continue; 
						}
					}while(flag == true);
					
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
						
						flag = true;
						Integer saldo = 0;
						do {
							try
							{	
								saldo = teclado.nextInt();
								flag = false;
							}
							catch(InputMismatchException e) 
							{
								System.out.println("Ingrese un numero");
								teclado.nextLine();continue; 
							}
						}while(flag == true);
						
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
							if(sistema.IngresarAlSistema(email, password)) {
								if (!sistemaCliente(user, sistema)) {
									opcion = 1;
								}
							}else {
								break;
							}
							
						} catch (NoExisteExcepcion e) {							
							System.out.println(e.getMessage());
							opcion = 1;
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
					
					flag = true;
					opcion = 9;
					do {
						try
						{
							opcion = teclado.nextInt();
							flag = false;
						}
						catch(InputMismatchException e) 
						{
							System.out.println("Ingrese un numero");
							teclado.nextLine();continue; 
						}
					}while(flag == true);

					
					
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
								sistema.IngresarAlSistemaAdmin(email, password);
								if (!sistemaAdmin(admin, sistema)) {
									opcion = 1;
								}
							} catch (NoExisteExcepcion e) {								
								System.out.println(e.getMessage());
								opcion = 1;
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
			System.out.println("4.Mostrar compras");
			System.out.println("5.Eliminar usuarios");
			System.out.println("6.Registrar usuarios");
			System.out.println("7.Cargar productos");
			System.out.println("8.Cargar locales");
			System.out.println("9.Cancelar compra");
			System.out.println("10.Salir de la sesion");
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
				Set<Compra>comprasHechas = sistema.getCompras();
				if(comprasHechas.size()>0) {
					sistema.mostrarCompras();
				} else {
					System.out.println("No hay compras en el sistema");
				}
				
				break;
			case 5:
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
			case 6:
				System.out.println("Ingrese nombre");
				String name = teclado.next();
				System.out.println("Ingrese apellido");
				String subname = teclado.next();
				System.out.println("Ingrese email");
				email = teclado.next();
				System.out.println("Ingrese contrasenia");
				String password = teclado.next();
				
				System.out.println("Ingrese saldo");
				Boolean flag = true;
				Integer saldo = 0;
				do {
					try
					{	
						saldo = teclado.nextInt();
						flag = false;
					}
					catch(InputMismatchException e) 
					{
						System.out.println("Ingrese un numero");
						teclado.nextLine();continue; 
					}
				}while(flag == true);

				Usuario user = new Cliente(name, subname, email, password, saldo);

				while (!sistema.registrarse(user)) {
					System.out.println("Ingrese otro email");
					email = teclado.next();
					user = new Cliente(name, subname, email, password, saldo);
				}

				System.out.println("Cliente registrado");
				break;

			case 7:
				sistema.mostrarProductos();
				System.out.println("Ingrese id");
				flag = true;
				Integer id = 0;
				do {
					try
					{	
						id = teclado.nextInt();
						flag = false;
					}
					catch(InputMismatchException e) 
					{
						System.out.println("Ingrese un numero");
						teclado.nextLine();continue; 
					}
				}while(flag == true);
				
				System.out.println("Ingrese precio");
				
				flag = true;
				Integer precio = 0;
				do {
					try
					{	
						precio = teclado.nextInt();
						flag = false;
					}
					catch(InputMismatchException e) 
					{
						System.out.println("Ingrese un numero");
						teclado.nextLine();continue; 
					}
				}while(flag == true);
				System.out.println("Ingrese descripion");
				String descripcion = teclado.next();
				System.out.println("Ingrese valor puntos");
				flag = true;
				Integer valor = 0;
				do {
					try
					{	
						valor = teclado.nextInt();
						flag = false;
					}
					catch(InputMismatchException e) 
					{
						System.out.println("Ingrese un numero");
						teclado.nextLine();continue; 
					}
				}while(flag == true);
				
				System.out.println("Ingrese precio puntos");
				flag = true;
				Integer preciop = 0;
				do {
					try
					{	
						preciop = teclado.nextInt();
						flag = false;
					}
					catch(InputMismatchException e) 
					{
						System.out.println("Ingrese un numero");
						teclado.nextLine();continue; 
					}
				}while(flag == true);
				
				Producto producto = new Producto(id, precio, descripcion, valor, preciop);

				while (!sistema.cargarProducto(producto)) {
					System.out.println("Ingrese un id distinto");
					id = teclado.nextInt();
					 producto.setId(id);

				}

				break;
			case 8:
				sistema.mostrarLocales();
				System.out.println("Ingrese nombre de local");
				String nombre = teclado.next();
				Local local = new Local(nombre, (Encargado) admin);
				while (!sistema.cargarLocal(local)) {
					System.out.println("Ingrese otro nombre de local");
					nombre = teclado.next();
				}
				break;
			case 9:
				sistema.mostrarCompras();
				System.out.println("Ingrese nombre de local");
				nombre = teclado.next();
				
				try {
					Local localsito = sistema.buscarLocal(nombre);
					System.out.println("Ingrese el id de la compra");
					Integer idCompra = teclado.nextInt();
					sistema.cancelarCompra(localsito, idCompra);
				} catch (NoExisteExcepcion e){
					System.out.println(e.getMessage());
				}catch(InputMismatchException e) 
				{
					System.out.println("Ingrese un numero");
					teclado.nextLine();continue; 
				}
				break;
			case 10:
				sistema.salirDelSistema();
				opcion = 10;
				break;
			default:
				System.out.println("Ingrese opcion valida");
				break;
			}
		} while (opcion != 10);
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
				sistema.mostrarLocales();
				System.out.println("Ingrese nombre del local");
				String nombre = teclado.next();
				
				Boolean flag = true;
				Integer idProd = 0;
				do {
					try
					{	
						sistema.mostrarProductos();
						System.out.println("Ingrese id del producto");
						idProd = teclado.nextInt();
						flag = false;
					}
					catch(InputMismatchException e) 
					{
						System.out.println("Ingrese un numero");
						teclado.nextLine();continue; 
					}
				}while(flag == true);
					Compra compraHecha = sistema.compra((Cliente)cliente, nombre, idProd);
					if(compraHecha!=null) {
						System.out.println(compraHecha);
					}
				break;
			case 4:
				System.out.println("Ingrese numero de orden");
				
				flag = true;
				Integer nroOrden = 0;
				Integer formaPago = 0;
				do {
					try
					{	
						nroOrden = teclado.nextInt();
						compraHecha = sistema.buscarCompra(nroOrden);
						System.out.println("Ingrese forma de pago: 1.Efectivo, 2.Puntos");
						formaPago = teclado.nextInt();
						sistema.pagar((Cliente)cliente, compraHecha, formaPago);
						flag = false;
					}
					catch(InputMismatchException e) 
					{
						System.out.println("Ingrese un numero");
						teclado.nextLine();continue; 
					} catch (NoExisteExcepcion e) {
						System.out.println("No existe el numero de orden");
						flag=false;
					}
				}while(flag == true);
	
				break;
			case 5:
				Set<Compra> comprasUsuario = sistema.buscarMisComprasEnTodosLosLocales();
				if(comprasUsuario.size()>0) {
					sistema.mostrarComprasUsuario(comprasUsuario);
				} else {
					System.out.println("No posee compras");
				}
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

package Usuarios;

import java.util.Comparator;

public class OrdenPorEmail implements Comparator<Usuario> {

	//@Override
	public int compare(Usuario arg0, Usuario arg1) {
		if (arg0.getNombre().compareTo(arg1.getNombre()) == 0) {
			return arg0.getApellido().compareTo(arg1.getApellido());
		}
		return arg0.getNombre().compareTo(arg1.getNombre());
	}
}

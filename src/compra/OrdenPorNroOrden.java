package compra;

import java.util.Comparator;

public class OrdenPorNroOrden implements Comparator<Compra> {

	//@Override
	public int compare(Compra arg0, Compra arg1) {
		if(arg0.getNumeroOrden().compareTo(arg1.getNumeroOrden())==0) {
			
		}
		return arg0.getNumeroOrden().compareTo(arg1.getNumeroOrden());
	}

	
}

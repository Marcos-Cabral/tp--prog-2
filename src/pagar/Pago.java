package pagar;

public class Pago {
	public enum TipoPago {
		efectivo,
		puntos
	}
	private TipoPago tipo;
	public Pago(Integer opcion) {
		
		if(opcion==1) {
			 this.tipo=tipo.efectivo;
		}
		else if(opcion==2) {
			this.tipo=tipo.puntos;
		}else {
			//Tiene que ir excepcion
		}
		
	}
}

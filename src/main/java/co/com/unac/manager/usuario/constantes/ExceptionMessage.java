package co.com.unac.manager.usuario.constantes;

public class ExceptionMessage {
	
	private ExceptionMessage() {
		throw new IllegalStateException("Exception class");
	}

	public static final String VALOR_RECARGA_INVALIDO = "EL VALOR DEBE SER MAYOR O IGUAL 1000";
	public static final String SALDO_INSUFICIENTE = "NO TIENE SUFICIENTE SALDO EN LA CIVICA PARA VIAJAR";
}

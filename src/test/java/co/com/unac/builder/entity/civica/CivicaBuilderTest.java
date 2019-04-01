package co.com.unac.builder.entity.civica;

import co.com.unac.builder.entity.usuario.UsuarioBuilderTest;
import co.com.unac.model.Civica;
import co.com.unac.model.Usuario;

public class CivicaBuilderTest {

	private static final long ID_CIVICA = 1;
	private static final double SALDO=1000;
	private static final Usuario USUARIO= new UsuarioBuilderTest().buildUsuario();
	
	public Civica buildCivica() {
		Civica civica = new Civica();
		civica.setId(ID_CIVICA);
		civica.setSaldo(SALDO);
		civica.setUsuario(USUARIO);
		return civica;
	}
}

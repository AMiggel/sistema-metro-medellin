package co.com.unac.builder.entity.civica;

import co.com.unac.builder.entity.usuario.UsuarioBuilderTest;
import co.com.unac.model.Civica;
import co.com.unac.model.Usuario;

public class CivicaBuilderTest {

	private static final long ID_CIVICA = 1;
	private static final double SALDO=1000;
	private static final Usuario USUARIO= new UsuarioBuilderTest().buildUsuario();
	
	private double saldo;
	private long id;
	private Usuario usuario;
	
	public CivicaBuilderTest() {
		this.saldo=SALDO;
		this.id= ID_CIVICA;
		this.usuario= USUARIO;
	}
	
	public Civica buildCivica() {
	Civica civica = new Civica();
	civica.setId(this.id);
	civica.setSaldo(this.saldo);
	civica.setUsuario(this.usuario);
	
	return civica;
	}
	
	public CivicaBuilderTest setSaldo (double saldo) {
		 this.saldo= saldo;
		 return this;
	}
}

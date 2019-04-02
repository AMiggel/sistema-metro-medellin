package co.com.unac.builder.entity.usuario;

import co.com.unac.builder.entity.civica.CivicaBuilderTest;
import co.com.unac.model.Civica;
import co.com.unac.model.Usuario;

public class UsuarioBuilderTest {
	
	private static final long ID_USUARIO = 1;
	private static final String NOMBRE="ANTONIO";
	private static final String APELLIDO= "MARIN";
	private static final String IDENTIFICACION="1042353312";
	private static final String TIPO_IDENTIFICACION= "CEDULA";
	private static final String EDAD="22";
	private static final String TIPO_POBLACION= "ESTUDIANTE";
	private static final Civica CIVICA= new CivicaBuilderTest().buildCivica();
	
	
	public Usuario buildUsuario(){
		Usuario usuario = new Usuario();
		usuario.setId(ID_USUARIO);
		usuario.setNombre(NOMBRE);
		usuario.setApellido(APELLIDO);
		usuario.setIdentificacion(IDENTIFICACION);
		usuario.setTipoIdentificacion(TIPO_IDENTIFICACION);
		usuario.setEdad(EDAD);
		usuario.setTipoPoblacion(TIPO_POBLACION);
		usuario.setCivica(CIVICA);
		return usuario;
		
	}


	
}
